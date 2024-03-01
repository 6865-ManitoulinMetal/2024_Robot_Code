package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class IntakeSubsystem extends SubsystemBase 
{
    public TalonSRX intakeSrx;

    public IntakeSubsystem(int IntakeID) 
    {
        // Creates intake motor
        intakeSrx = new TalonSRX(IntakeID);
    }
   
   
    // Method to run intake inwards
    public void intakeIn() 
    {
        intakeSrx.set(TalonSRXControlMode.Current, MechanismConstants.intakeSpeed);
    }
   
   
   
   
    // Method to reverse intake
    public void intakeOut()
    {
        intakeSrx.set(TalonSRXControlMode.Current, MechanismConstants.intakeReverseSpeed);
    }
   

    @Override
    public void periodic()
    { 
        
    }    
}