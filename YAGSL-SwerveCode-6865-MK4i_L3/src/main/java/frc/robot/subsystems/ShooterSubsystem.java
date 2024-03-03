package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class ShooterSubsystem extends SubsystemBase 
{
    public TalonSRX intakeSrx;

    public ShooterSubsystem(int ID) 
    {
        // Creates intake motor
        intakeSrx = new TalonSRX(ID);
    }
   
   
    // Method to run intake inwards
    public void intakeIn() 
    {
        intakeSrx.set(TalonSRXControlMode.Current, MechanismConstants.Intake_Speed);
    }
   
   
   
   
    // Method to reverse intake
    public void intakeOut()
    {
        intakeSrx.set(TalonSRXControlMode.Current, MechanismConstants.Intake_Reverse_Speed);
    }
   

    @Override
    public void periodic()
    { 
        
    }    
}