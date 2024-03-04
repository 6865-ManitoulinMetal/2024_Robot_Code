package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class HolsterSubsystem extends SubsystemBase 

{
    public TalonSRX holsterSrx3;
    public void holsterSrx3 (int ID3) 
   
    {
        // Creates holster motor
        holsterSrx3 = new TalonSRX(ID3);
    }
   
   
    // Method to run Holster for Intake
    public void Holster() 
    {
        HolsterSubsystem.set(ControlMode.PercentOutput, MechanismConstants.Holster_Intake_Speed);
    }
   
    private static void set(ControlMode percentoutput, double holsterIntakeSpeed) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }


    // Method to reverse Holster
    public void Reverse()
    {
        holsterSrx3.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Backwards_Speed);
    }

    public void Shoot()
    {
        holsterSrx3.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Forwards_Speed);
    }

    public void Stop()
    {
        holsterSrx3.set(TalonSRXControlMode.Current, 0);
    }
   public Command holsterIntake() 
    {
        return runOnce(
            () -> 
            {
                Holster();
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