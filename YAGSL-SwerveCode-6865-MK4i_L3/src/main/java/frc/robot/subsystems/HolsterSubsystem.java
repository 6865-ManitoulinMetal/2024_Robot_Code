package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.Command;
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
    public void Intake() 
    {
        holsterSrx.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Intake_Speed);
    }
   
    // Method to reverse Holster
    public void Reverse()
    {
        holsterSrx.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Backwards_Speed);
    }

    public void Shoot()
    {
        holsterSrx.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Forwards_Speed);
    }

    public void Stop()
    {
        holsterSrx.set(TalonSRXControlMode.Current, 0);
    }
   
    public Command holsterIntake() 
    {
        return runOnce(
            () -> 
            {
                Intake();
            }
            );
    }

    public Command holsterStop() 
    {
        return runOnce(
            () -> 
            {
                Stop();
            }
            );
    }
    
    @Override
    public void periodic()
    { 
        
    }   
}