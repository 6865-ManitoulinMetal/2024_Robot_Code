// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.SwerveDriveSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ForwardCommand extends Command {
  private final SwerveDriveSubsystem swerveDriveSubsystem;
  private final double targetDistanceMeters;
  private double initialPosition;
  /**
   * Creates a new ForwardCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ForwardCommand(SwerveDriveSubsystem swerveDriveSubsystem) {
    this.swerveDriveSubsystem = swerveDriveSubsystem;
    this.targetDistanceMeters = targetDistanceMeters;
    addRequirements(swerveDriveSubsystem);
  }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
    initialPosition = swerveDriveSubsystem.getOdometry().getPoseMeters().getX(); // Get initial position from odometry
    }

    // Called when the command is initially scheduled.
    @Override
    public void execute() {
        // Calculate distance driven
        double distanceDriven = Math.abs(swerveDriveSubsystem.getOdometry().getPoseMeters().getX() - initialPosition);

        // Calculate remaining distance to target
        double remainingDistance = targetDistanceMeters - distanceDriven;

        // Use kinematics to calculate wheel speeds for driving forward
        SwerveDriveKinematics.WheelSpeeds wheelSpeeds = swerveDriveSubsystem.getKinematics().toWheelSpeeds(
            new ChassisSpeeds(remainingDistance, 0, 0)
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

    // Check if the target distance is reached
    return Math.abs(swerveDriveSubsystem.getOdometry().getPoseMeters().getX() - initialPosition) >= targetDistanceMeters;
}
}
