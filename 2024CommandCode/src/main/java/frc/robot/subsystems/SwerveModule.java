package frc.robot.subsystems;

import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
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

    
    public SwerveModule(int driveMotorId, int rotationMotorId, NavXSubsystem navXSubsystem, int encoderId, double encoderOffset) {
        this.driveMotor = new TalonFX(driveMotorId);
        this.rotationMotor = new TalonFX(rotationMotorId);
        this.canCoder = new CANcoder(encoderId);
        this.navXSubsystem = navXSubsystem;
        this.encoderId = encoderId; // Placeholder variable to store encoder ID based on motor ID
        this.offset = encoderOffset;
        this.initialPosition = this.canCoder.getAbsolutePosition().getValue()*1024/360*6.12-this.offset;
    }

    public SwerveModulePosition getState() {
        // Create a SwerveModulePosition object based on the current state
        return new SwerveModulePosition(
        );
    }

    public void setDesiredState(SwerveModuleState state) {
        // TODO: Implement setting desired state, add initial position to encoder value in degrees and account for the gear ratio 1024*6.12/360, including updating position
        throw new UnsupportedOperationException("Unimplemented method 'setDesiredState'");
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
