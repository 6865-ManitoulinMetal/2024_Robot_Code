package frc.robot.commands;


import frc.robot.subsystems.HolsterSubsystem;
import frc.robot.subsystems.PnuematicsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class IntakeSensoredReversal extends Command {    
    
    private HolsterSubsystem holsterSubsystem;

    public IntakeSensoredReversal(HolsterSubsystem holsterSubsystem) 
    {
        this.holsterSubsystem = holsterSubsystem;
        addRequirements(holsterSubsystem);
    }

    @Override
    public void initialize() {
        this.holsterSubsystem.Reverse();
    }

    public boolean isFinished()
    {
        return holsterSubsystem.getHolsterSensor();
        //return holsterSubsystem.getHolsterSensor();
    }


    public void end(boolean interrupted)
    {
        this.holsterSubsystem.Stop();
    }
}