package frc.robot.subsystems;


import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;


public class PnuematicsSubsystem extends SubsystemBase 
{
    public DoubleSolenoid doubleSolenoid1;

    

    public PnuematicsSubsystem(int pcmId, int solenoidChannel0, int solenoidChannel1) 
    {
        // Create double solenoid
        this.doubleSolenoid1 = new DoubleSolenoid(PneumaticsModuleType.REVPH, 0, 1);
    }
   
   
    // Method to raise solenoid
    public void raise() 
    {
        doubleSolenoid1.set(DoubleSolenoid.Value.kForward);
    }
   
   
   
   
    // Method to lower solenoid
    public void lower()
    {
        doubleSolenoid1.set(DoubleSolenoid.Value.kReverse);
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
        
    }    
}