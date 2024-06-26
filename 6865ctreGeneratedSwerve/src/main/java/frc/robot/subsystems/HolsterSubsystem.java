package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class HolsterSubsystem extends SubsystemBase 

{
    public TalonSRX holsterSRX;
    private DigitalInput holsterSensor;   
   
    // Method to run Holster for Intake
    public HolsterSubsystem(int ID) 
    {
        holsterSRX = new TalonSRX(ID);
        holsterSensor = new DigitalInput(0);
    }

    // Method to reverse Holster
    public void Reverse()
    {
        holsterSRX.set(TalonSRXControlMode.PercentOutput, MechanismConstants.Holster_Backwards_Speed);
    }

    public void slowReverse()
    {
        holsterSRX.set(TalonSRXControlMode.PercentOutput, 0.5);
    }

    public void Shoot()
    {
        holsterSRX.set(TalonSRXControlMode.PercentOutput, MechanismConstants.Holster_Forwards_Speed);
    }

    public void Intake(){
        holsterSRX.set(TalonSRXControlMode.PercentOutput, MechanismConstants.Holster_Intake_Speed);
    }

    public void Stop()
    {
        holsterSRX.set(TalonSRXControlMode.PercentOutput, 0);
    }
    public boolean getHolsterSensor() {
        return holsterSensor.get();
      }
    

    public Command holsterIntake() 
    {
        return runOnce(
            () -> 
            {
                Intake();;
            }
            );
    }
     public Command holsterShoot() 
    {
        return runOnce(
            () -> 
            {
                Shoot();;
            }
            );
    }

    public Command reverseHolster()
    {
        return runOnce
        (
            () -> 
            {
                Reverse();
            }
        );
    }

    public Command stopHolster() 
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
        SmartDashboard.putBoolean("Holster Sensor", !holsterSensor.get());
    }   
}