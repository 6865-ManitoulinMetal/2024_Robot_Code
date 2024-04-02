package frc.robot.subsystems;


import frc.robot.Constants.MechanismConstants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class ClimberSubsystem extends SubsystemBase 
{
    public TalonFX climberFX;

    public ClimberSubsystem(int ID) 
    {
        // Creates climber
        climberFX = new TalonFX(ID);
        climberFX.getConfigurator().apply(Robot.ctreConfigs.climberFXConfig);
        climberFX.setNeutralMode(NeutralModeValue.Brake);
    }
   
   
    // Method to run climber up
    public void up() 
    {
        climberFX.set(1);
    }
   
   
   
   
    // Method to reverse climber
    public void down()
    {
        climberFX.set(-1);
    }

    // Method to stop intake
    public void stop()
    {
        climberFX.set(0);
    }

    public Command raiseClimber() 
    {
        return runOnce(
            () -> 
            {
                up();
            }
            );
    }

    public Command stopClimber() 
    {
        return runOnce(
            () -> 
            {
                stop();
            }
            );
    }

     public Command lowerClimber() 
    {
        return runOnce(
            () -> 
            {
                down();
            }
            );
    }
   

    @Override
    public void periodic()
    { 
        
    }
   
}