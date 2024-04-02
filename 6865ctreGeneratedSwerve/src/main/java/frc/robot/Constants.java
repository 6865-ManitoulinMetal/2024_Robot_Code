package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

public final class Constants {
    public static final double stickDeadband = 0.1;

    public static final class MechanismConstants
  { 
    // Intake ID and speeds
    public static final int Intake_ID_1 = 25;
    public static final int Intake_ID_2 = 26;
    public static final double Intake_Speed_1 = 1;
    public static final double Intake_Speed_2 = 1;
    public static final double Intake_Reverse_Speed = -1;

    // Holster IDs and speeds
    public static final int Holster_Motor_ID = 10;
    public static final double Holster_Intake_Speed = -0.6;
    public static final double Holster_Forwards_Speed = -1;
    public static final double Holster_Backwards_Speed = 1;
    public static final int Holster_Sensor_ID = 0;

 
    // Shooter IDs and speeds
    public static final int Shooter_Motor_ID = 11;
    //public static final double Shooter_Speed = 1;
    //public static final double Shooter_Forwards_Speed = -0.9;
    ///public static final double Shooter_Backwards_Speed = 0.4;

     // Climber IDs and speeds
     public static final int Climber_Motor_ID = 21;

    /* Climber Current Limits */
    public TalonFXConfiguration climberFXConfig = new TalonFXConfiguration();

    
  }
    //PWM PORTS
    public static final int LED_PORT = 9;
  
}

