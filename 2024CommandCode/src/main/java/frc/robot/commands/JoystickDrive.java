package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;
import java.util.function.Supplier;

public class JoystickDrive extends Command {
    private final SwerveDriveSubsystem swerveSubsystem;
    private final Supplier<Double> xSpeed, ySpeed;

    public JoystickDrive(SwerveDriveSubsystem swerveSubsystem, Supplier<Double> xSpeed, Supplier<Double> ySpeed) {
        this.swerveSubsystem = swerveSubsystem;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        // No initialization needed
    }

    @Override
    public void execute() {
        swerveSubsystem.drive(
                                xSpeed.get()*frc.robot.Constants.DEFAULT_DRIVE_SPEED, //x speed
                                ySpeed.get()*frc.robot.Constants.DEFAULT_DRIVE_SPEED, //y speed
                                0 //with no rotation
                                ); // Drive with given x and y speed
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.drive(0, 0, 0); // Stop driving
    }

    @Override
    public boolean isFinished() {
        // You may want to add logic here to determine when to finish the command
        return false; // This command never finishes on its own
    }
}