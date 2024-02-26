package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveDriveKinematicsUtil {

    public static void normalizeWheelSpeeds(SwerveModuleState[] swerveModuleStates, double maxWheelSpeed) {
        // Find the maximum speed among all wheel speeds
        double maxSpeed = 0.0;
        for (SwerveModuleState moduleState : swerveModuleStates) {
            double speed = moduleState.speedMetersPerSecond;
            if (speed > maxSpeed) {
                maxSpeed = speed;
            }
        }

        // If the maximum speed exceeds the maximum allowed speed, scale all speeds down
        if (maxSpeed > maxWheelSpeed) {
            double scaleFactor = maxWheelSpeed / maxSpeed;
            for (SwerveModuleState moduleState : swerveModuleStates) {
                moduleState.speedMetersPerSecond *= scaleFactor;
            }
        }
    }
}
