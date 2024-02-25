
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SwerveDriveSubsystem extends SubsystemBase {
  public SwerveDriveSubsystem() {
    import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import java.util.Map;

public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule rearLeftModule;
    private final SwerveModule rearRightModule;

    private final SwerveDriveOdometry odometry;

    public SwerveDriveSubsystem(
        SwerveDriveKinematics kinematics,
        Map<SwerveModule, Double> gearRatios
    ) {
        // Initialize swerve modules with motors, encoders, and gear ratios
        frontLeftModule = new SwerveModule(
            Constants.DRIVE_MOTOR_FRONT_LEFT_ID,
            Constants.ROTATION_MOTOR_FRONT_LEFT_ID,
            Constants.ENCODER_FRONT_LEFT_ID,
            Constants.ENCODER_FRONT_LEFT_OFFSET,
            Constants.ENCODER_FRONT_LEFT_INVERT,
            gearRatios.get(SwerveModule.FRONT_LEFT)
        );
        frontRightModule = new SwerveModule(
            Constants.DRIVE_MOTOR_FRONT_RIGHT_ID,
            Constants.ROTATION_MOTOR_FRONT_RIGHT_ID,
            Constants.ENCODER_FRONT_RIGHT_ID,
            Constants.ENCODER_FRONT_RIGHT_OFFSET,
            Constants.ENCODER_FRONT_RIGHT_INVERT,
            gearRatios.get(SwerveModule.FRONT_RIGHT)
        );
        rearLeftModule = new SwerveModule(
            Constants.DRIVE_MOTOR_REAR_LEFT_ID,
            Constants.ROTATION_MOTOR_REAR_LEFT_ID,
            Constants.ENCODER_REAR_LEFT_ID,
            Constants.ENCODER_REAR_LEFT_OFFSET,
            Constants.ENCODER_REAR_LEFT_INVERT,
            gearRatios.get(SwerveModule.REAR_LEFT)
        );
        rearRightModule = new SwerveModule(
            Constants.DRIVE_MOTOR_REAR_RIGHT_ID,
            Constants.ROTATION_MOTOR_REAR_RIGHT_ID,
            Constants.ENCODER_REAR_RIGHT_ID,
            Constants.ENCODER_REAR_RIGHT_OFFSET,
            Constants.ENCODER_REAR_RIGHT_INVERT,
            gearRatios.get(SwerveModule.REAR_RIGHT)
        );

        // Set up kinematics and odometry
        kinematics = new SwerveDriveKinematics(
          wheelPositions[0], wheelPositions[1], wheelPositions[2], wheelPositions[3]
      );
        odometry = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(0.0));
    }

    public void drive(double forward, double strafe, double rotation) {
        // Implement swerve drive logic based on input values
    }

    public SwerveDriveOdometry getOdometry() {
        return odometry;
    }
}
public void updateOdometry(double currentAngle) {
  // Update odometry with current sensor readings
  odometry.update(
      new Rotation2d(Math.toRadians(currentAngle)),
      List.of(
          swerveModules[0].getAngle(),
          swerveModules[1].getAngle(),
          swerveModules[2].getAngle(),
          swerveModules[3].getAngle()
      )
  );

  // Update SmartDashboard with odometry data
  SmartDashboard.putNumber("X", odometry.getPoseMeters().getX());
  SmartDashboard.putNumber("Y", odometry.getPoseMeters().getY());
  SmartDashboard.putNumber("Heading", odometry.getPoseMeters().getRotation().getDegrees());
}
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}




