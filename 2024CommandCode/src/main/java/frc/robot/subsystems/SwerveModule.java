package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;

import frc.robot.Constants;
import frc.robot.subsystems.NavXSubsystem;

public class SwerveModule {
    private final TalonFX driveMotor;
    private final TalonFX rotationMotor;
    private final CANcoder canCoder;
    private final NavXSubsystem navXSubsystem;
    private final int encoderId;
    private SwerveModuleState currentState; // Current state of the module
    private double currentPositionX = 0.0;
    private double currentPositionY = 0.0;
    private double initialPosition;
    private double offset;

    private final SimpleMotorFeedforward driveFeedForward = new SimpleMotorFeedforward(Constants.Swerve.driveKS, Constants.Swerve.driveKV, Constants.Swerve.driveKA);

    /* drive motor control requests */
    private final DutyCycleOut driveDutyCycle = new DutyCycleOut(0);
    private final VelocityVoltage driveVelocity = new VelocityVoltage(0);

    /* angle motor control requests */
    private final PositionVoltage anglePosition = new PositionVoltage(0);
    
    public SwerveModule(int driveMotorId, int rotationMotorId, NavXSubsystem navXSubsystem, int encoderId, double encoderOffset) {
        this.driveMotor = new TalonFX(driveMotorId);
        this.rotationMotor = new TalonFX(rotationMotorId);
        this.canCoder = new CANcoder(encoderId);
        this.navXSubsystem = navXSubsystem;
        this.encoderId = encoderId; // Placeholder variable to store encoder ID based on motor ID
        this.offset = encoderOffset;
        this.initialPosition = this.canCoder.getAbsolutePosition().getValue() * 1024.0 / 360.0 * 6.12 - this.offset;
    }

    public SwerveModulePosition getState() {
        // Create a SwerveModulePosition object based on the current state
        return new SwerveModulePosition(
        );
    }

    public void setDesiredState(SwerveModuleState desiredState, boolean isOpenLoop) {
        // Ensure the desired state is optimized
        desiredState = SwerveModuleState.optimize(desiredState, getState().angle); 
    
        // Calculate the desired position for the rotation motor
        double desiredAngleDegrees = desiredState.angle.getDegrees();
        double desiredPosition = (desiredAngleDegrees + initialPosition) * Constants.GEAR_RATIO_MKVI_L3; // Account for initial position and gear ratio
    
        // Set the position of the rotation motor
        rotationMotor.set(anglePosition.getPosition(desiredPosition));
    
        // Set the speed of the drive motor
        setSpeed(desiredState, isOpenLoop);
    }
    
    private void setSpeed(SwerveModuleState desiredState, boolean isOpenLoop) {
        if (isOpenLoop) {
            // Open-loop control: set velocity directly
            double velocity = desiredState.speedMetersPerSecond / Constants.MAX_WHEEL_SPEED;
            driveMotor.set(driveDutyCycle, velocity);
        } else {
            // Closed-loop control: use feedforward and velocity control
            double feedforwardVoltage = driveFeedForward.calculate(desiredState.speedMetersPerSecond);
            driveMotor.set(driveVelocity, desiredState.speedMetersPerSecond, feedforwardVoltage);
        }
    }

    public void updateState(SwerveModuleState newState) {
        currentState = newState;
    }

    public Pose2d getPose() {
        double angleDegrees = currentState.angle.getDegrees();
        double speedMetersPerSecond = currentState.speedMetersPerSecond;
        double deltaX = speedMetersPerSecond * Math.cos(Math.toRadians(angleDegrees));
        double deltaY = speedMetersPerSecond * Math.sin(Math.toRadians(angleDegrees));
        return new Pose2d(currentPositionX, currentPositionY, Rotation2d.fromDegrees(angleDegrees));
    }
}
