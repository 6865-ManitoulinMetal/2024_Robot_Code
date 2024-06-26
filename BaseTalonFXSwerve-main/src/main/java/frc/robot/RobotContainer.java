package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Command.InterruptionBehavior;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.Constants.MechanismConstants;
import frc.robot.autos.*;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */

public class RobotContainer
{

    /* Controllers */
    private final Joystick driver = new Joystick(0);
    private final CommandXboxController driverXbox = new CommandXboxController(0);
    private final CommandXboxController mechanismsXbox = new CommandXboxController(1);

    /* Drive Controls */
    private final int translationAxis = XboxController.Axis.kLeftY.value;
    private final int strafeAxis = XboxController.Axis.kLeftX.value;
    private final int rotationAxis = XboxController.Axis.kRightX.value;
    
    // The robot's subsystems and commands are defined here...
    private final IntakeSubsystem intake = new IntakeSubsystem(MechanismConstants.Intake_ID_1, MechanismConstants.Intake_ID_2);
    private final HolsterSubsystem holster = new HolsterSubsystem(10);
    private final ShooterSubsystem shooter = new ShooterSubsystem(11);
    private final ClimberSubsystem climber = new ClimberSubsystem(21);    
    private final PnuematicsSubsystem pnuematics = new PnuematicsSubsystem(1,2,3);   

    /* Driver Buttons */
    private final JoystickButton zeroGyro = new JoystickButton(driver, XboxController.Button.kY.value);
    private final JoystickButton robotCentric = new JoystickButton(driver, XboxController.Button.kLeftBumper.value);
   
    /* Subsystems */
    public static final Swerve s_Swerve = new Swerve();
    private final LEDSubsystem led = new LEDSubsystem(holster);

   private final SendableChooser<Command> autoChooser;

    /** The container for the robot. Contains subsystems, OI devices, and commands. */
    public RobotContainer() 
    {

        s_Swerve.setDefaultCommand(
            new TeleopSwerve(
                s_Swerve, 
                () -> -Math.pow(driver.getRawAxis(translationAxis), 3), 
                () -> -Math.pow(driver.getRawAxis(strafeAxis), 3), 
                () -> -Math.pow(driver.getRawAxis(rotationAxis), 3), 
                () -> robotCentric.getAsBoolean()
            )
        );

         // Put Some buttons on the SmartDashboard

        SmartDashboard.putData("Green LED", new RunCommand(() -> led.green(),led));
        SmartDashboard.putData("Red LED", new RunCommand(() -> led.red(),led));
        SmartDashboard.putData("Blue LED", new RunCommand(() -> led.blue(),led));
        SmartDashboard.putData("Pink LED", new RunCommand(() -> led.pink(),led));
        SmartDashboard.putData("Turquoise LED", new RunCommand(() -> led.turquoise(),led));
    
        // set up Auto Chooser
        autoChooser = new SendableChooser<>();
        autoChooser.setDefaultOption("Center 2 Ring Auto", new CenterTwoRingAuto(s_Swerve, holster, intake, shooter));
        autoChooser.addOption("Left 2 Ring Auto", new LeftTwoRingAuto(s_Swerve, holster, intake, shooter));
        autoChooser.addOption("Right 2 Ring Auto", new RightTwoRingAuto(s_Swerve, holster, intake, shooter));
        autoChooser.addOption("Leave", new Leave(s_Swerve, shooter, holster));
        SmartDashboard.putData(autoChooser);
        led.gold(); // Turns on Gold LED's even when disabled ---- There may be a better place 
    //for this I think this is a periodic call and this would be best to be a one time call but it seems to work
    


        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be created by
     * instantiating a {@link GenericHID} or one of its subclasses ({@link
     * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
     * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */
    private void configureButtonBindings() 
    {
        /* Driver Buttons */
        zeroGyro.onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));
        
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
 // Bind  button to run the command when pressed

    driverXbox.b().onTrue(new InstantCommand(() -> s_Swerve.zeroHeading()));

    // On hold a, intake note into holster - stops when breaks beam
    mechanismsXbox.leftTrigger().onTrue(
            new IntakeCommand(intake, holster));

    // Cancel intake command
    mechanismsXbox.leftTrigger().onFalse(new ParallelCommandGroup(holster.stopHolster(), intake.stopIntake()));

    // Shoot command trigger
    mechanismsXbox.rightTrigger().whileTrue(
        new SequentialCommandGroup(
            new IntakeSensoredReversal(holster, pnuematics),
            new ShootCommand(holster, shooter, 55))
            );
    
    // Reverse holster/intake trigger
    mechanismsXbox.a().whileTrue(holster.reverseHolster());
    mechanismsXbox.b().whileTrue(intake.reverseIntake());    
    mechanismsXbox.a().onFalse(holster.stopHolster());
    mechanismsXbox.b().onFalse(intake.stopIntake());    


    // Toggling holster
    mechanismsXbox.x().and(mechanismsXbox.leftTrigger().negate()).onTrue(pnuematics.flipHolster());

    driverXbox.rightTrigger().onTrue(climber.raiseClimber());
    driverXbox.rightTrigger().onFalse(climber.stopClimber());
    driverXbox.rightBumper().onTrue(climber.lowerClimber());
    driverXbox.rightBumper().onFalse(climber.stopClimber());

    driverXbox.leftTrigger().onTrue(new InstantCommand( () -> 
        {
            Constants.Swerve.maxSpeed = 10;
        }))
        .onFalse(new InstantCommand( () -> 
        {
            Constants.Swerve.maxSpeed = 3;
        }));

        driverXbox.a().onTrue(
        new InstantCommand( () -> 
        {
            s_Swerve.resetModulesToAbsolute();
        }
        )
        );


    }


    
    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() 
    {
       /* An ExampleCommand will run in autonomous */
        return autoChooser.getSelected();
        /* 
        PathPlannerPath path = PathPlannerPath.fromPathFile("Example Path");

        return AutoBuilder.followPath(path);*/
    }
}