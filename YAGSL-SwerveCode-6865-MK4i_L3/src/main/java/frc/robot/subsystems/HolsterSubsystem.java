package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.MotorTypeValue;

public class HolsterSubsystem extends SubsystemBase
{
    private static TalonFX holsterMotor; {
         
        {holsterMotor = new TalonFX(16);


        new int isMotorOn = 0;


        if(holster trigger && isMotorOn = 0)
            {
                holsterMotor.set(0.2);
                isMotorOn = 1;
            }


        if(holsterTrigger && isMotorOn = 1)
            {
                holsterMotor.set(0);
                isMotorOn = 0;
            }


        if(holsterReverseTrigger)
            {
                holsterMotor.set(-0.2);
                isMotorOn = 1;
            }


    }


}

}