package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Rotation2d;
import frc.robot.Constants;
import frc.robot.subsystems.NavXSubsystem;
import com.kauailabs.navx.frc.AHRS;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.util.Units;

public class SwerveModule {
    private final TalonFX driveMotor;
    private final TalonFX steerMotor;
    private final CANCoder absoluteEncoder;

    // Encoder constants
    private static final double ENCODER_RESOLUTION = 2048; // Encoder counts per revolution

    // Swerve module configuration
    private static final double MAX_MODULE_SPEED = 5.0; // Maximum module speed in meters per second
    private static final double MAX_MODULE_ROTATION_SPEED = 3 * Math.PI; // Maximum module rotation speed in radians per second

    // Constructor
    public SwerveModule(int driveMotorID, int steerMotorID, int absoluteEncoderID) {
        driveMotor = new TalonFX(driveMotorID);
        steerMotor = new TalonFX(steerMotorID);
        absoluteEncoder = new CANCoder(absoluteEncoderID);

        // Configure drive motor
        driveMotor.configFactoryDefault();
        driveMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

        // Configure steer motor
        steerMotor.configFactoryDefault();
        steerMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);

        // Configure absolute encoder
        absoluteEncoder.configFactoryDefault();
    }

    // Method to set the desired module state
    public void setModuleState(SwerveModuleState desiredState) {
        double driveOutput = desiredState.getSpeedMetersPerSecond() / MAX_MODULE_SPEED;
        double angle = desiredState.getAngle().getRadians();

        driveMotor.set(ControlMode.PercentOutput, driveOutput);
        steerMotor.set(ControlMode.Position, angle);
    }

    // Method to get current module state
    public SwerveModuleState getModuleState() {
        double driveSpeed = driveMotor.getSelectedSensorVelocity() * Units.inchesToMeters(ENCODER_RESOLUTION) / 10.0;
        double angle = absoluteEncoder.getPosition();
        
        return new SwerveModuleState(driveSpeed, new Rotation2d(angle));
    }
}
