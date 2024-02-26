package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.SwerveDriveSubsystem;
import frc.robot.subsystems.NavXSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class RobotContainer {
    private static final int DRIVER_CONTROLLER_PORT = 0;
    private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
    private final SwerveDriveSubsystem m_swerveDriveSubsystem;
    private final NavXSubsystem m_navXSubsystem = new NavXSubsystem();

    private final XboxController m_driverController = new XboxController(DRIVER_CONTROLLER_PORT);
    private final JoystickButton m_buttonA = new JoystickButton(m_driverController, XboxController.Button.kA.value);

    public RobotContainer() {
        m_swerveDriveSubsystem = new SwerveDriveSubsystem(m_navXSubsystem);
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        //m_buttonA.whenActive(new ExampleCommand(m_exampleSubsystem));
        // Add more button bindings for controlling the SwerveDriveSubsystem
        // For example:
        // new JoystickButton(m_driverController, XboxController.Button.kBumperLeft.value)
        //     .whenActive(new RotateLeftCommand(m_swerveDriveSubsystem));
        // new JoystickButton(m_driverController, XboxController.Button.kBumperRight.value)
        //     .whenActive(new RotateRightCommand(m_swerveDriveSubsystem));
        // new JoystickButton(m_driverController, XboxController.Button.kX.value)
        //     .whileActiveContinuous(new MoveForwardCommand(m_swerveDriveSubsystem));
        // new JoystickButton(m_driverController, XboxController.Button.kY.value)
        //     .whileActiveContinuous(new MoveBackwardCommand(m_swerveDriveSubsystem));
    }

    public Command getAutonomousCommand() {
        return Autos.exampleAuto(m_exampleSubsystem);
    }
}
