// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.HolsterSubsystem;
import frc.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj2.command.Command;

/** An example command that uses an example subsystem. */
public class ShootCommand extends Command {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final HolsterSubsystem holster;
  private final ShooterSubsystem shooter;
  private double targetSpeedRPS;


  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public ShootCommand(HolsterSubsystem holster, ShooterSubsystem shooter, double targetSpeedRPS) 
  {
    this.targetSpeedRPS = targetSpeedRPS;
    this.shooter = shooter;
    this.holster = holster;
  

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(shooter, holster);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() 
  {
    this.shooter.runShooter(targetSpeedRPS);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() 
  {
    // shoot if at 97%+ of target RPS
    if (this.shooter.getSpeed() >= 0.97*targetSpeedRPS)
    {
      this.holster.Shoot();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) 
  {
    this.holster.Stop();
    this.shooter.Stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}