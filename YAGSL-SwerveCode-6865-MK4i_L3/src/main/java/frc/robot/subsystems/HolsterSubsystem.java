package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class HolsterSubsystem extends SubsystemBase 
{
    public TalonSRX holsterSrx;

    public HolsterSubsystem(int ID) 
    {
        // Creates holster motor
        holsterSrx = new TalonSRX(ID);
    }
   
   
    // Method to run Holster for Intake
    public void holsterIntake() 
    {
        holsterSrx.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Intake_Speed);
    }
   
    // Method to reverse Holster
    public void holsterReverse()
    {
        holsterSrx.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Backwards_Speed);
    }

    public void holsterShoot()
    {
        holsterSrx.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Forwards_Speed);
    }
   

    @Override
    public void periodic()
    { 
        
    }    
}