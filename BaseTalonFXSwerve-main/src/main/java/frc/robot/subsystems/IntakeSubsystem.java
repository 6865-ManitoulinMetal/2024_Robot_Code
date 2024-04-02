package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase 
{
    public CANSparkMax intakeSM1;
    public CANSparkMax intakeSM2;

    public IntakeSubsystem(int ID1, int ID2) 
    {
        // Creates intake motor
        intakeSM1 = new CANSparkMax(ID1, MotorType.kBrushless);
        intakeSM2 = new CANSparkMax(ID2, MotorType.kBrushless);
    }
   
   
    // Method to run intake inwards
    public void intakeIn() 
    {
        intakeSM1.set(-MechanismConstants.Intake_Speed_1);
        intakeSM2.set(-MechanismConstants.Intake_Speed_2);
    }
   
   
   
   
    // Method to reverse intake
    public void intakeOut()
    {
        intakeSM1.set(-MechanismConstants.Intake_Reverse_Speed);
        intakeSM2.set(-MechanismConstants.Intake_Reverse_Speed);
    }

    // Method to stop intake
    public void Stop()
    {
        intakeSM1.set(0);
        intakeSM2.set(0);
    }

    public Command noteIntake() 
    {
        return runOnce(
            () -> 
            {
                intakeIn();
            }
            );
    }

    public Command stopIntake() 
    {
        return runOnce(
            () -> 
            {
                Stop();
            }
            );
    }

     public Command reverseIntake() 
    {
        return runOnce(
            () -> 
            {
                intakeOut();
            }
            );
    }
   

    @Override
    public void periodic()
    { 
        
    }
   
}