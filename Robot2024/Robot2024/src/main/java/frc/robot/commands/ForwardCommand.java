// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ForwardCommand extends Command {
  private final ForwardSubsystem m_subsystem;
  private final SwerveDriveSubsystem swerveDriveSubsystem;
  /**
   * Creates a new ForwardCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ForwardCommand(SwerveDriveSubsystem swerveDriveSubsystem) {
    this.swerveDriveSubsystem = swerveDriveSubsystem;
    addRequirements(swerveDriveSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    swerveDriveSubsystem.drive(Constants.DEFAULT_DRIVE_SPEED, 0, 0); // Drive forward at default speed
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void end(boolean interrupted) {
    swerveDriveSubsystem.stop(); // Stop the drive at the end of the command
}
  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
