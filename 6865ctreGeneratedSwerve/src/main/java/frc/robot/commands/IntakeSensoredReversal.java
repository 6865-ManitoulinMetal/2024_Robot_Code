package frc.robot.commands;


import frc.robot.subsystems.HolsterSubsystem;
import frc.robot.subsystems.PnuematicsSubsystem;
import edu.wpi.first.wpilibj2.command.Command;


public class IntakeSensoredReversal extends Command {    
    
    private HolsterSubsystem holsterSubsystem;
    private PnuematicsSubsystem pnuematicsSubsystem;

    public IntakeSensoredReversal(HolsterSubsystem holsterSubsystem, PnuematicsSubsystem pnuematicsSubsystem) 
    {
        this.holsterSubsystem = holsterSubsystem;
        this.pnuematicsSubsystem = pnuematicsSubsystem;
        addRequirements(holsterSubsystem);
    }

    @Override
    public void initialize() {
        this.holsterSubsystem.Reverse();
    }

    public boolean isFinished()
    {
        return this.holsterSubsystem.getHolsterSensor() || this.pnuematicsSubsystem.areRaised();
        //return holsterSubsystem.getHolsterSensor();
    }


    public void end(boolean interrupted)
    {
        this.holsterSubsystem.Stop();
    }
}