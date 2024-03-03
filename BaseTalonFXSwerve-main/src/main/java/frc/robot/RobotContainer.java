package frc.robot;

import java.io.File;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.MechanismConstants;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;


/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final CommandXboxController driverXbox = new CommandXboxController(0);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    

    
  // The robot's subsystems and commands are defined here...
  private final IntakeSubsystem intakeSrx1 = new IntakeSubsystem(MechanismConstants.Intake_ID_1, MechanismConstants.Intake_ID_2);
  private final IntakeSubsystem intakeSrx2 = new IntakeSubsystem(MechanismConstants.Intake_ID_1, MechanismConstants.Intake_ID_2);
  private final HolsterSubsystem holster = new HolsterSubsystem();
  private final ShooterSubsystem shooter = new ShooterSubsystem(12);     
 

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);

    /* Subsystems */
    private final Swerve s_Swerve = new Swerve();


    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() {
        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -driver.getRawAxis(translationAxis), 
                () -> -driver.getRawAxis(strafeAxis), 
                () -> -driver.getRawAxis(rotationAxis), 
                () -> robotCentric.getAsBoolean()
            )
        );

        // Configure the button bindings
        configureButtonBindings();
        
        
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
 // Bind  button to run the command when pressed

 
    driverXbox.a().whileTrue (ParallelCommandGroup (
                        intakeSrx1.intakeSubsystem, MechanismConstants.Intake_Speed),
                        intakeSrx2.intakeSubsystem, MechanismConstants.Intake_Speed);
    
    driverXbox.b().whileTrue(new SequentialCommandGroup(holster.holsterIntake(),shooter.shooterSubsystem()));
        

    JoystickButton raiseButton = new JoystickButton(driver, XboxController.Button.kX.value);
    raiseButton.whenPressed (raise().PneumaticsSubsystem);

    JoystickButton lowerButton = new JoystickButton(driver, XboxController.Button.kY.value);
    lowerButton.whenPressed(lower().PneumaticsSubsystem);
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return new exampleAuto(s_Swerve);
    }
}
