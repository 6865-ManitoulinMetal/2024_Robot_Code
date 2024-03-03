package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class ShooterSubsystem extends SubsystemBase 
{
    public TalonFX shooter;
    public ShooterSubsystem(int ID) 
    {
        // Creates intake motor
        shooter = new TalonFX(ID);
    }
   
       // Method to run shooter
    public void runShooter(double speed) 
    {
        shooter.set(speed);
    }
   
   

    @Override
    public void periodic()
    { 
        
    }    
}