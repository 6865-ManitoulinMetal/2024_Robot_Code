// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final CommandXboxController m_driverController =
      new CommandXboxController(OperatorConstants.kDriverControllerPort);
  private final SwerveDriveSubsystem swerveDriveSubsystem;
  private final NavXSubsystem navXSubsystem;
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  
  public RobotContainer() {
    {
      private final XboxController controller = new XboxController(Constants.OperatorConstants.kDriverControllerPort);
      private final SwerveDriveSubsystem swerveDriveSubsystem;
      private final NavXSubsystem navXSubsystem;
  
      public RobotContainer() {
          // Sample wheel positions (relative to robot center)
          Translation2d[] wheelPositions = {
              new Translation2d(14.75, 14.75),
              new Translation2d(14.75, -14.75),
              new Translation2d(-14.75, 14.75),
              new Translation2d(-14.75, -14.75)
          };
  
          SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
              wheelPositions[0], wheelPositions[1], wheelPositions[2], wheelPositions[3]
          );
  
          // Sample values for measurements
          double wheelbaseWidth = 0.5; // Sample wheelbase width (0.5 meters)
          double wheelbaseLength = 0.5; // Sample wheelbase length (0.5 meters)
          double rotationOffset = 0.1; // Sample rotation offset (0.1 meters)
          int encoderCPR = 1024; // Sample encoder CPR
          double encoderDistancePerPulse = 0.01; // Sample encoder distance per pulse (0.01 meters per pulse)
          double gyroSensitivity = 0.1; // Sample gyro sensitivity (0.1 degrees per second per volt)
          double maxWheelSpeed = 2.0; // Sample maximum wheel speed (2.0 meters per second)
  
          // Sample gear ratios for the MKvi L3 swerve module
          Map<SwerveModule, Double> gearRatios = new HashMap<>();
          double gearRatio = Constants.GEAR_RATIO_MKVI_L3; // Get the gear ratio for MKvi L3 swerve module
          gearRatios.put(module1, gearRatio);
          gearRatios.put(module2, gearRatio);
          gearRatios.put(module3, gearRatio);
          gearRatios.put(module4, gearRatio);
  
          // Initialize subsystems
          swerveDriveSubsystem = new SwerveDriveSubsystem(
              kinematics, modules,
              wheelbaseWidth, wheelbaseLength,
              rotationOffset, encoderCPR, encoderDistancePerPulse,
              gyroSensitivity, maxWheelSpeed,
              gearRatios
          );
          navXSubsystem = new NavXSubsystem();
  
          // Set default command for swerve drive subsystem
          swerveDriveSubsystem.setDefaultCommand(new DriveCommand(swerveDriveSubsystem, controller));
      }
    configureBindings();
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
