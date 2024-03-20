package frc.robot.autos;

import java.util.List;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelDeadlineGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Constants;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.ShootCommand;
import frc.robot.subsystems.HolsterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import frc.robot.subsystems.Swerve;

public class LeftTwoRingAuto extends SequentialCommandGroup {
    
    public LeftTwoRingAuto(Swerve s_Swerve, HolsterSubsystem holster, IntakeSubsystem intake, ShooterSubsystem shooter){
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
        
        Trajectory toRingTrajectory1 =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(0.5, 0)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(1, 0, new Rotation2d(0)),
                configS);
        
        SwerveControllerCommand swerveControllerApproachRing1 =
            new SwerveControllerCommand(
                toRingTrajectory1,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        
        Trajectory toRingTrajectory2 =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(-0.3, -0.5)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(-0.6, -1, new Rotation2d(60)),
                configR);
            
        SwerveControllerCommand swerveControllerApproachRing2 =
            new SwerveControllerCommand(
                toRingTrajectory2,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);


        
        Trajectory toRingTrajectory3 =
            TrajectoryGenerator.generateTrajectory(
                // Start at the origin facing the +X direction
                new Pose2d(0, 0, new Rotation2d(0)),
                // Pass through these two interior waypoints, making an 's' curve path
                List.of(new Translation2d(0.1, 0.4)),
                // End 3 meters straight ahead of where we started, facing forward
                new Pose2d(1, 0.5, new Rotation2d(0)),
                configS);
        
        SwerveControllerCommand swerveControllerApproachRing3 =
            new SwerveControllerCommand(
                toRingTrajectory3,
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
                List.of(new Translation2d(0, -1.5)),
                new Pose2d(-1.3, -1.5, new Rotation2d(0)),
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
                        new Translation2d(5, 1)),
                new Pose2d(6, 1, new Rotation2d(0)),
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
            new InstantCommand(() -> s_Swerve.setPose(toRingTrajectory1.getInitialPose())),
            new ShootCommand(holster, shooter, 45).withTimeout(2),
            new ParallelDeadlineGroup(
                new SequentialCommandGroup(
                    swerveControllerApproachRing1,
                    s_Swerve.stopSwerveCommand(),
                    new WaitCommand(0.5),
                    new InstantCommand(() -> s_Swerve.setPose(toRingTrajectory2.getInitialPose())),
                    swerveControllerApproachRing2,
                    s_Swerve.stopSwerveCommand(),
                    new InstantCommand(() -> s_Swerve.setPose(toRingTrajectory3.getInitialPose())),
                    swerveControllerApproachRing3,
                    s_Swerve.stopSwerveCommand(),
                    new WaitCommand(1),
                    new InstantCommand(() -> s_Swerve.setPose(toSpeakerTrajectory.getInitialPose())),
                    swerveControllerApproachSpeaker,
                    s_Swerve.stopSwerveCommand()
                    ),
                new IntakeCommand(intake, holster)
                ),
            new WaitCommand(1),
            new ShootCommand(holster, shooter, 45).withTimeout(2),
            new InstantCommand(() -> s_Swerve.setPose(toParkTrajectory.getInitialPose())),
            swerveControllerPark,
            s_Swerve.stopSwerveCommand()
        );
    }
}