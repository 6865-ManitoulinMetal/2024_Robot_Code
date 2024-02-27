package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class StrafeCommand extends Command {
    private final SwerveDriveSubsystem swerveSubsystem;
    private final double speed;

    public StrafeCommand(SwerveDriveSubsystem swerveSubsystem, double speed) {
        this.swerveSubsystem = swerveSubsystem;
        this.speed = speed;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        // No initialization needed
    }

    @Override
    public void execute() {
        swerveSubsystem.drive(0, speed, 0); // Strafe at given speed
    }

    @Override
    public void end(boolean interrupted) {
        swerveSubsystem.drive(0, 0, 0); // Stop strafing
    }

    @Override
    public boolean isFinished() {
        // You may want to add logic here to determine when to finish the command
        return false; // This command never finishes on its own
    }
}