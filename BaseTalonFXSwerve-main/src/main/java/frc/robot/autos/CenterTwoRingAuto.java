package frc.robot.autos;

import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.HolsterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Swerve;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.MaxVelocityConstraint;
import edu.wpi.first.math.trajectory.constraint.TrajectoryConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class CenterTwoRingAuto extends SequentialCommandGroup {
    
    public CenterTwoRingAuto(Swerve s_Swerve, HolsterSubsystem holster, IntakeSubsystem intake, ShooterSubsystem shooter){
        TrajectoryConfig config =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics);

        TrajectoryConfig configS =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond/2,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared/2)
                .setKinematics(Constants.Swerve.swerveKinematics);

        TrajectoryConfig configR =
            new TrajectoryConfig(
                    Constants.AutoConstants.kMaxSpeedMetersPerSecond,
                    Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
                .setKinematics(Constants.Swerve.swerveKinematics).setReversed(true);
        // An example trajectory to follow.  All units in meters.
        

        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);
        
        Trajectory toRingTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(0.5, 0)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(1.40, 0, new Rotation2d(0)),
                configS);
        
            SwerveControllerCommand swerveControllerApproachRing =
            new SwerveControllerCommand(
                toRingTrajectory,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        Trajectory toSpeakerTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                List.of(new Translation2d(-0.5, 0)),
                new Pose2d(-1.3, 0, new Rotation2d(0)),
                configR);

        SwerveControllerCommand swerveControllerApproachSpeaker =
            new SwerveControllerCommand(
                toSpeakerTrajectory,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);
        

        Trajectory toParkTrajectory =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                List.of(new Translation2d(3, 1),
                        new Translation2d(5, 1.5)),
                new Pose2d(6, 2, new Rotation2d(0)),
                config);
    
            SwerveControllerCommand swerveControllerPark =
                new SwerveControllerCommand(
                    toParkTrajectory,
                    s_Swerve::getPose,
                    Constants.Swerve.swerveKinematics,
                    new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                    new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                    thetaController,
                    s_Swerve::setModuleStates,
                    s_Swerve);        
                

        addCommands( 
            new InstantCommand(() -> s_Swerve.setPose(toRingTrajectory.getInitialPose())),
            new ShootCommand(holster, shooter, 45).withTimeout(2),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    swerveControllerApproachRing,
                    s_Swerve.stopSwerveCommand(),
                    new WaitCommand(1),
                    new InstantCommand(() -> s_Swerve.setPose(toSpeakerTrajectory.getInitialPose())),
                    swerveControllerApproachSpeaker,
                    s_Swerve.stopSwerveCommand()), 
                new IntakeCommand(intake, holster)),
            new WaitCommand(1),
            new ShootCommand(holster, shooter, 45).withTimeout(2),
            swerveControllerPark,
            s_Swerve.stopSwerveCommand()
        );
    }
}