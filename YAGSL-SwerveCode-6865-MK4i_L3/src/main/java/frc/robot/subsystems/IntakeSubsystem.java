package frc.robot.subsystems;


import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Talon;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class IntakeSubsystem extends SubsystemBase 
{
    public TalonSRX intakeSrx;

    

    public IntakeSubsystem(int IntakeID) 
    {
        // Create double solenoid
        intakeSrx = new TalonSRX(IntakeID);
    }
   
   
    // Method to run intake inwards
    public void intakeIn() 
    {
        intakeSrx.set(TalonSRXControlMode.Current, 0.5);
    }
   
   
   
   
    // Method to reverse intake
    public void intakeOut()
    {
        intakeSrx.set(TalonSRXControlMode.Current, -0.5);
    }
   

    @Override
    public void periodic()
    { 
        
    }    
}