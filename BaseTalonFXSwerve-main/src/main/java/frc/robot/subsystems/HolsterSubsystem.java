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
    private DigitalInput initialHolsterSensor;
    private DigitalInput finalHolsterSensor;
    private DigitalInput launcherHolsterSensor;   
   
    // Method to run Holster for Intake
    public HolsterSubsystem(int ID) 
    {
        holsterSRX = new TalonSRX(ID);
        initialHolsterSensor = new DigitalInput(0);
        finalHolsterSensor = new DigitalInput(1);
        launcherHolsterSensor = new DigitalInput(2);

    }

    // Method to reverse Holster
    public void Reverse()
    {
        holsterSRX.set(TalonSRXControlMode.Current, MechanismConstants.Holster_Backwards_Speed);
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
    public boolean getInitialConveyorSensor() {
        return !initialHolsterSensor.get();
      }
    
      public boolean getFinalConveyorSensor() {
        return !finalHolsterSensor.get();
      }
    
      public boolean getLauncherConveyorSensor() {
        return !launcherHolsterSensor.get();
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
    SmartDashboard.putBoolean("Intake Sensor", !initialHolsterSensor.get());
    SmartDashboard.putBoolean("Holster Sensor", !finalHolsterSensor.get());
    SmartDashboard.putBoolean("Shooter Sensor", !launcherHolsterSensor.get());
    // This method will be called once per scheduler run
    }   
}