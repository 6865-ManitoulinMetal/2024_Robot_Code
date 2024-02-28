package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;
//import frc.robot.Constants;

public class DriveForwardCommand extends Command {
    private final SwerveDriveSubsystem swerveSubsystem;
    private final double speed;

    public DriveForwardCommand(SwerveDriveSubsystem swerveSubsystem, double speed) {
        this.swerveSubsystem = swerveSubsystem;
        this.speed = speed*frc.robot.Constants.MAX_WHEEL_SPEED;
        addRequirements(swerveSubsystem);
    }

    @Override
    public void initialize() {
        // No initialization needed
    }

    @Override
    public void execute() {
        swerveSubsystem.drive(speed, 0, 0); // Drive forward at given speed
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