package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HolsterSubsystem;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class IntakeCommand extends Command {    
    
    private IntakeSubsystem intakeSubsystem;
    private HolsterSubsystem holsterSubsystem;
    public IntakeCommand(IntakeSubsystem intakeSubsystem, HolsterSubsystem holsterSubsystem) 
    {
        this.holsterSubsystem = holsterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(holsterSubsystem,intakeSubsystem);
    }

    @Override
    public void initialize() {
        this.intakeSubsystem.intakeIn();
        this.holsterSubsystem.Intake();
    }

    public boolean isFinished()
    {
        return false;
    }


    public void end(boolean interrupted)
    {
        this.holsterSubsystem.Stop();
        this.intakeSubsystem.Stop();
    }
}