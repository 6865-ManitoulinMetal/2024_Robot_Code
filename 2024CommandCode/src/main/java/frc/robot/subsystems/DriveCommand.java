package frc.robot.subsystems;


import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.SwerveDriveSubsystem;

public class DriveCommand extends Command {
    private final SwerveDriveSubsystem driveSubsystem;

    public DriveCommand(SwerveDriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
        addRequirements(driveSubsystem);
    }

    @Override
    public void execute() {
        // Put your driving logic here
        // For example:
        // driveSubsystem.drive(xSpeed, ySpeed, rotSpeed);
    }
}