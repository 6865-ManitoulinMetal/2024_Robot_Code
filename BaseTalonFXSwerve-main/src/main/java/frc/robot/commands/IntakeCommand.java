package frc.robot.commands;

import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.HolsterSubsystem;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;


public class IntakeCommand extends Command {    
    
    private IntakeSubsystem intakeSubsystem;
    private HolsterSubsystem holsterSubsystem;
    public IntakeCommand(IntakeSubsystem intakeSubsystem, HolsterSubsystem holsterSubsystem) {
        this.holsterSubsystem = holsterSubsystem;
        this.intakeSubsystem = intakeSubsystem;
        addRequirements(holsterSubsystem,intakeSubsystem);
    }

    @Override
    public void initialize() {
    intakeSubsystem.noteIntake();
    holsterSubsystem.holsterIntake();
    

    }

public boolean isFinished(){
new WaitCommand(2.0);
return true;
}


    public void end(){

holsterSubsystem.holsterStop();
intakeSubsystem.stopIntake();
    }
}