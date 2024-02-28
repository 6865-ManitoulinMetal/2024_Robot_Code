package frc.robot.subsystems;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.SwerveModule;
import frc.robot.subsystems.NavXSubsystem;

public class SwerveDriveSubsystem extends SubsystemBase {

    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule rearLeftModule;
    private final SwerveModule rearRightModule;
    private final NavXSubsystem navXSubsystem;

    private final SwerveDriveKinematics kinematics;
    private final SwerveDriveOdometry odometry;

    private final SwerveModulePosition[] initialModuleStates; // Declare as class member

    public SwerveDriveSubsystem(NavXSubsystem navXSubsystem) {
        this.navXSubsystem = navXSubsystem;

        // Initialize Swerve Modules
        frontLeftModule = new SwerveModule(Constants.DRIVE_MOTOR_FRONT_LEFT_ID, Constants.ROTATION_MOTOR_FRONT_LEFT_ID, navXSubsystem, Constants.ENCODER_FRONT_LEFT_ID,0);
        frontRightModule = new SwerveModule(Constants.DRIVE_MOTOR_FRONT_RIGHT_ID, Constants.ROTATION_MOTOR_FRONT_RIGHT_ID, navXSubsystem, Constants.ENCODER_FRONT_RIGHT_ID,0);
        rearLeftModule = new SwerveModule(Constants.DRIVE_MOTOR_REAR_LEFT_ID, Constants.ROTATION_MOTOR_REAR_LEFT_ID, navXSubsystem, Constants.ENCODER_REAR_LEFT_ID,0);
        rearRightModule = new SwerveModule(Constants.DRIVE_MOTOR_REAR_RIGHT_ID, Constants.ROTATION_MOTOR_REAR_RIGHT_ID, navXSubsystem, Constants.ENCODER_REAR_RIGHT_ID, 0);

        // Define the wheel positions relative to the center of the robot
        Translation2d[] wheelPositions = {
            new Translation2d(Constants.WHEELBASE_WIDTH / 2.0, Constants.WHEELBASE_LENGTH / 2.0),    // Front Left
            new Translation2d(Constants.WHEELBASE_WIDTH / 2.0, -Constants.WHEELBASE_LENGTH / 2.0),   // Front Right
            new Translation2d(-Constants.WHEELBASE_WIDTH / 2.0, Constants.WHEELBASE_LENGTH / 2.0),   // Rear Left
            new Translation2d(-Constants.WHEELBASE_WIDTH / 2.0, -Constants.WHEELBASE_LENGTH / 2.0)  // Rear Right
        };

        // Create a swerve drive kinematics object
        kinematics = new SwerveDriveKinematics(wheelPositions);

        // Initialize initialModuleStates array
        initialModuleStates = new SwerveModulePosition[]{
            frontLeftModule.getState(),
            frontRightModule.getState(),
            rearLeftModule.getState(),
            rearRightModule.getState()
        };

        // Create a swerve drive odometry object with the initial module states
        odometry = new SwerveDriveOdometry(kinematics, Rotation2d.fromDegrees(navXSubsystem.getAngle()), initialModuleStates);

        // Set default command for the subsystem
        setDefaultCommand(new DriveCommand(this));
    }

    // Method to get the pose of a specific swerve module
    public Pose2d getModulePose(SwerveModule module) {
        // Call the method in SwerveModule to get the pose
        return module.getPose();
    }

    // Method to drive the robot using x, y, and rotation speed
    public void drive(double xSpeed, double ySpeed, double rotSpeed) {
        // Update the pose
        odometry.update(Rotation2d.fromDegrees(navXSubsystem.getAngle()), initialModuleStates);

        // Utility class for normalizing wheel speeds
        SwerveDriveKinematicsUtil.normalizeWheelSpeeds(kinematics.toSwerveModuleStates(new ChassisSpeeds(xSpeed, ySpeed, rotSpeed)), Constants.MAX_WHEEL_SPEED);

        // Calculate and apply the desired module speeds
        SwerveModuleState[] swerveModuleStates = kinematics.toSwerveModuleStates(new ChassisSpeeds(xSpeed, ySpeed, rotSpeed));
        frontLeftModule.setDesiredState(swerveModuleStates[0]);
        frontRightModule.setDesiredState(swerveModuleStates[1]);
        rearLeftModule.setDesiredState(swerveModuleStates[2]);
        rearRightModule.setDesiredState(swerveModuleStates[3]);
    }
}
