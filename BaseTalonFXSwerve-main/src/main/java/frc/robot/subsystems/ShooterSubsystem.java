package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;

public class ShooterSubsystem extends SubsystemBase 
{
    public static TalonFX shooter;
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
   
    public Command shoot() 
    {
        return runOnce(
            () -> 
            {
                runShooter(MechanismConstants.Shooter_Speed);
            }
            );
    }
   

    @Override
    public void periodic()
    { 
        
    }
}