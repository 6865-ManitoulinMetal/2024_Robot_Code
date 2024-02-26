// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.SwerveDriveSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics.SwerveDriveWheelStates;
import edu.wpi.first.wpilibj2.command.Command;

public class TurnLeft90Command extends Command {
    private final SwerveDriveSubsystem swerveDriveSubsystem;
  /**
   * Creates a new TurnLeft90Command.
   *
   * @param subsystem The subsystem used by this command.
   */
  public TurnLeft90Command(SwerveDriveSubsystem swerveDriveSubsystem) {
    this.swerveDriveSubsystem = swerveDriveSubsystem;
    addRequirements(swerveDriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SwerveDriveWheelStates wheelSpeeds = swerveDriveSubsystem.getKinematics().toWheelSpeeds(
      new ChassisSpeeds(0, 0, Math.toRadians(-90)) // Turn left by 90 degrees
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
        // Assuming a fixed duration for turning left
        return true; // Adjust as needed based on your requirements
    }
}
