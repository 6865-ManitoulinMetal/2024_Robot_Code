package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class IntakeSubsystem extends SubsystemBase 
{
    public TalonSRX intakeSrx1;
    public TalonSRX intakeSrx2;

    public IntakeSubsystem(int ID1, int ID2) 
    {
        // Creates intake motor
        intakeSrx1 = new TalonSRX(ID1);
        intakeSrx2 = new TalonSRX(ID2);
    }
   
   
    // Method to run intake inwards
    public void intakeIn() 
    {
        intakeSrx1.set(TalonSRXControlMode.Current, MechanismConstants.Intake_Speed);
        intakeSrx2.set(TalonSRXControlMode.Current, MechanismConstants.Intake_Speed);
    }
   
   
   
   
    // Method to reverse intake
    public void intakeOut()
    {
        intakeSrx1.set(TalonSRXControlMode.Current, MechanismConstants.Intake_Reverse_Speed);
        intakeSrx2.set(TalonSRXControlMode.Current, MechanismConstants.Intake_Reverse_Speed);
    }
   

    @Override
    public void periodic()
    { 
        
    }    
}