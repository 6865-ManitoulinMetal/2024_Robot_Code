package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;


public class PnuematicsSubsystem extends SubsystemBase 
{
    public DoubleSolenoid doubleSolenoid1;
    private Compressor compressor;


    

    public PnuematicsSubsystem(int pcmId, int solenoidChannel0, int solenoidChannel1) 
    {
        this.doubleSolenoid1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
        this.compressor = new Compressor(PneumaticsModuleType.REVPH);
        this.compressor.enableAnalog(80, 115);


    }
   
   
    // Method to raise both pneumatic systems
    public void raise() 
    {
        doubleSolenoid1.set(DoubleSolenoid.Value.kForward);
    }
   
     public Command raiseHolster() 
    {
        return runOnce(
            () -> 
            {
                raise();
            }
            );
    }
   
    // Method to lower both pneumatic systems
    public void lower()
    {
        doubleSolenoid1.set(DoubleSolenoid.Value.kReverse);
    }
   
    public Command lowerHolster() 
    {
        return runOnce(
            () -> 
            {
                lower();
            }
            );
    }

    public Command flipHolster() 
    {
        return runOnce(
            () -> 
            {
                toggle();
            }
            );
    }
   
    // Method to check if both pneumatic systems are raised
    public boolean areRaised() 
    {
        return doubleSolenoid1.get() == DoubleSolenoid.Value.kForward;
    }
   
   
    // Method to check if both pneumatic systems are lowered
    public boolean areLowered() 
    {
        return doubleSolenoid1.get() == DoubleSolenoid.Value.kReverse; 
    }
   
   
    // Example of how to toggle the state
    public void toggle() 
    {
        if (areRaised()) 
        {
            lower();
        } 
        else 
        {
            raise();
        }
    }
   

    @Override
    public void periodic()
    { 
        SmartDashboard.putNumber("PSI: ", this.compressor.getPressure());
    }    
}