// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.SwerveDriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TurnRight90Command extends Command {
    private final SwerveDriveSubsystem swerveDriveSubsystem;
  /**
   * Creates a new TurnRight90Command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnRight90Command(SwerveDriveSubsystem swerveDriveSubsystem) {
    this.swerveDriveSubsystem = swerveDriveSubsystem;
    addRequirements(swerveDriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // Use kinematics to calculate wheel speeds for turning right
    SwerveDriveKinematics.WheelSpeeds wheelSpeeds = swerveDriveSubsystem.getKinematics().toWheelSpeeds(
    new ChassisSpeeds(0, 0, Math.toRadians(90)) // Turn right by 90 degrees
    );

    // Set wheel speeds
    swerveDriveSubsystem.setWheelSpeeds(wheelSpeeds);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    swerveDriveSubsystem.stop(); // Stop the drive at the end of the command
}
  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
        // Check if the desired angle is reached
        // Assuming a fixed duration for turning right
        return true; // Adjust as needed based on your requirements
    }
}
