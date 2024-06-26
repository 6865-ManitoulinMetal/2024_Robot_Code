// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix6.Utils;
import com.ctre.phoenix6.mechanisms.swerve.SwerveRequest;
import com.ctre.phoenix6.mechanisms.swerve.SwerveModule.DriveRequestType;
import com.pathplanner.lib.auto.AutoBuilder;
import com.pathplanner.lib.auto.NamedCommands;
import com.pathplanner.lib.commands.PathPlannerAuto;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants.MechanismConstants;
import frc.robot.commands.IntakeCommand;
import frc.robot.commands.IntakeSensoredReversal;
import frc.robot.commands.ShootCommand;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.ClimberSubsystem;
import frc.robot.subsystems.HolsterSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.PnuematicsSubsystem;
import frc.robot.subsystems.ShooterSubsystem;

public class RobotContainer {
  private double MaxSpeed = TunerConstants.kSpeedAt12VoltsMps; // kSpeedAt12VoltsMps desired top speed
  private double MaxAngularRate = 1.5 * Math.PI; // 3/4 of a rotation per second max angular velocity

  /* Setting up bindings for necessary control of the swerve drive platform */
  private final CommandXboxController driverXbox = new CommandXboxController(0);
  private final CommandXboxController mechanismsXbox = new CommandXboxController(1); // My joystick
  private final CommandSwerveDrivetrain drivetrain = TunerConstants.DriveTrain; // My drivetrain

    private final IntakeSubsystem intake = new IntakeSubsystem(MechanismConstants.Intake_ID_1, MechanismConstants.Intake_ID_2);
    private final HolsterSubsystem holster = new HolsterSubsystem(10);
    private final ShooterSubsystem shooter = new ShooterSubsystem(11);
    private final ClimberSubsystem climber = new ClimberSubsystem(21);    
    private final PnuematicsSubsystem pnuematics = new PnuematicsSubsystem(1,2,3);   

  private final SwerveRequest.FieldCentric drive = new SwerveRequest.FieldCentric()
      .withDeadband(MaxSpeed * 0.1).withRotationalDeadband(MaxAngularRate * 0.1) // Add a 10% deadband
      .withDriveRequestType(DriveRequestType.OpenLoopVoltage); // I want field-centric
                                                               // driving in open loop
  private final SwerveRequest.SwerveDriveBrake brake = new SwerveRequest.SwerveDriveBrake();
  private final SwerveRequest.PointWheelsAt point = new SwerveRequest.PointWheelsAt();
  private final Telemetry logger = new Telemetry(MaxSpeed);

  private final SendableChooser<Command> autoChooser;

  private void configureBindings() {
    drivetrain.setDefaultCommand( // Drivetrain will execute this command periodically
        drivetrain.applyRequest(() -> drive.withVelocityX(driverXbox.getLeftY() * MaxSpeed) // Drive forward with
                                                                                           // negative Y (forward)
            .withVelocityY(driverXbox.getLeftX() * MaxSpeed) // Drive left with negative X (left)
            .withRotationalRate(driverXbox.getRightX() * MaxAngularRate) // Drive counterclockwise with negative X (left)
        ));

    driverXbox.a().whileTrue(drivetrain.applyRequest(() -> brake));
    driverXbox.b().whileTrue(drivetrain
        .applyRequest(() -> point.withModuleDirection(new Rotation2d(driverXbox.getLeftY(), driverXbox.getLeftX()))));

    // reset the field-centric heading on left bumper press
    driverXbox.leftBumper().onTrue(drivetrain.runOnce(() -> drivetrain.seedFieldRelative()));

    if (Utils.isSimulation()) {
      drivetrain.seedFieldRelative(new Pose2d(new Translation2d(), Rotation2d.fromDegrees(90)));
    }
    drivetrain.registerTelemetry(logger::telemeterize);

    // Mechanism Control

     // On hold a, intake note into holster - stops when breaks beam
    mechanismsXbox.leftTrigger().onTrue(
            new IntakeCommand(intake, holster));

    // Cancel intake command
    mechanismsXbox.leftTrigger().onFalse(new ParallelCommandGroup(holster.stopHolster(), intake.stopIntake()));

    // Shoot command trigger
    mechanismsXbox.rightTrigger().whileTrue(
        new SequentialCommandGroup(
            new IntakeSensoredReversal(holster),
            new ShootCommand(holster, shooter, 55))
            );
    
    // Reverse holster/intake trigger
    mechanismsXbox.a().whileTrue(holster.reverseHolster());
    mechanismsXbox.b().whileTrue(intake.reverseIntake());    
    mechanismsXbox.a().onFalse(holster.stopHolster());
    mechanismsXbox.b().onFalse(intake.stopIntake());    


    // Toggling holster
    mechanismsXbox.x().and(mechanismsXbox.leftTrigger().negate()).onTrue(pnuematics.flipHolster());

    // Climber triggers
    driverXbox.rightTrigger().onTrue(climber.raiseClimber());
    driverXbox.rightTrigger().onFalse(climber.stopClimber());
    driverXbox.rightBumper().onTrue(climber.lowerClimber());
    driverXbox.rightBumper().onFalse(climber.stopClimber());

  }

  public RobotContainer() {
    configureBindings();

    NamedCommands.registerCommand("SensoredIntake", new IntakeCommand(intake, holster));
    NamedCommands.registerCommand("SensoredReversal", new IntakeSensoredReversal(holster));
    NamedCommands.registerCommand("Shoot", new SequentialCommandGroup(
      new IntakeSensoredReversal(holster),
      new ShootCommand(holster, shooter, 55)
      ).withTimeout(1.5));

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);
  }

  public Command getAutonomousCommand() {
    return new PathPlannerAuto("C-Inside");
  }
}
