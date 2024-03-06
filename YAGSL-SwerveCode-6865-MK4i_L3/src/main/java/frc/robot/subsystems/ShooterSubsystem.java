package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.TalonFX;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MechanismConstants;
import com.ctre.phoenix6.BaseStatusSignal;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.controls.VelocityVoltage;

public class ShooterSubsystem extends SubsystemBase 
{
    public static VelocityVoltage velocityRequest;
    public static TalonFX shooter;

    public ShooterSubsystem(int ID)
    {
        // Creates intake motor
        shooter = new TalonFX(ID);
        shooter.setInverted(false);

        // set slot 0 gains
        var slot0Configs = new Slot0Configs();
        slot0Configs.kS = 0.05; // Add 0.05 V output to overcome static friction
        slot0Configs.kV = 0.3; // A velocity target of 1 rps results in 0.3 V output
        slot0Configs.kP = 0.11; // An error of 1 rps results in 0.11 V output
        slot0Configs.kI = 0; // no output for integrated error
        slot0Configs.kD = 0; // no output for error derivative

    
        shooter.getConfigurator().apply(slot0Configs);
        velocityRequest = new VelocityVoltage(0).withSlot(0);
    }
   
    // Method to run shooter
    public void runShooter(double speed) 
    {        
        // create a velocity closed-loop request, voltage output, slot 0 configs
        final VelocityVoltage velocityRequest = new VelocityVoltage(0).withSlot(0);

        // set velocity to 45 rps
        shooter.setControl(velocityRequest.withVelocity(speed));
    }

    public double getSpeed()
    {
        return shooter.getVelocity().getValueAsDouble();
    }

    public void Stop()
    {
        shooter.setControl(velocityRequest.withVelocity(0));
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

    public Command shooterStop() 
    {
        return runOnce(
            () -> 
            {
                runShooter(0);
            }
            );
    }
   

    @Override
    public void periodic()
    { 
        
    }
}