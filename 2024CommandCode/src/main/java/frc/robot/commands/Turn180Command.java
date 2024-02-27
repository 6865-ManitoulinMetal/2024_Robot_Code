package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class Turn180Command extends Command {
    private final SwerveDriveSubsystem swerveSubsystem;

    public Turn180Command(SwerveDriveSubsystem swerveSubsystem) {
        this.swerveSubsystem = swerveSubsystem;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        // No initialization needed
    }

    @Override
    public void execute() {
        // Adjust the rotation speed as needed to perform a 180-degree turn
        swerveSubsystem.drive(0, 0, 0.5); // Example speed, adjust as necessary
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.drive(0, 0, 0); // Stop turning
    }

    @Override
    public boolean isFinished() {
        // You may want to add logic here to determine when to finish the command
        return false; // This command never finishes on its own
    }
}