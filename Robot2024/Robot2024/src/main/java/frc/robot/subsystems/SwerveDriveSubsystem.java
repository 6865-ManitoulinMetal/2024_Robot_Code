package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics.SwerveDriveWheelStates;
import edu.wpi.first.math.kinematics.SwerveDriveOdometry;
import edu.wpi.first.math.kinematics.SwerveModulePosition;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.subsystems.NavXSubsystem;

public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveModule frontLeftModule;
    private final SwerveModule frontRightModule;
    private final SwerveModule rearLeftModule;
    private final SwerveModule rearRightModule;
    private NavXSubsystem navX = new NavXSubsystem();

    private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
            new Translation2d(Constants.TRACK_WIDTH / 2, Constants.WHEELBASE / 2),
            new Translation2d(Constants.TRACK_WIDTH / 2, -Constants.WHEELBASE / 2),
            new Translation2d(-Constants.TRACK_WIDTH / 2, Constants.WHEELBASE / 2),
            new Translation2d(-Constants.TRACK_WIDTH / 2, -Constants.WHEELBASE / 2)
    );

    private final SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, navX.getHeading(), null);

    public SwerveDriveSubsystem(SwerveModule frontLeftModule, SwerveModule frontRightModule,
                                SwerveModule rearLeftModule, SwerveModule rearRightModule, NavXSubsystem navX) {
        this.frontLeftModule = frontLeftModule;
        this.frontRightModule = frontRightModule;
        this.rearLeftModule = rearLeftModule;
        this.rearRightModule = rearRightModule;
        this.navX = navX;
            
            // Initialize swerve kinematics and odometry
            // Replace the placeholders with your actual swerve module positions
            Translation2d[] modulePositions = { /* Populate with your SwerveModulePositions */ };
            kinematics = new SwerveDriveKinematics(modulePositions);
            odometry = new SwerveDriveOdometry(kinematics, new Rotation2d(), null, new Pose2d());
    
            // Reset the odometry with initial pose and gyro angle
            Rotation2d gyroAngle = navX.getHeading(); // Assuming you have a method to get the current gyro angle
            Pose2d initialPose = new Pose2d(); // Assuming you have an initial pose
            odometry.resetPosition(gyroAngle, modulePositions, initialPose);
        }

    // Method to drive the robot using field-centric control
    public void drive(double xSpeed, double ySpeed, double rotSpeed) {
        ChassisSpeeds speeds = new ChassisSpeeds(xSpeed, ySpeed, rotSpeed);
        SwerveModuleState[] moduleStates = kinematics.toSwerveModuleStates(speeds);

        frontLeftModule.setModuleState(moduleStates[0]);
        frontRightModule.setModuleState(moduleStates[1]);
        rearLeftModule.setModuleState(moduleStates[2]);
        rearRightModule.setModuleState(moduleStates[3]);
    }

    @Override
    public void periodic() {

        
        odometry.update(navX.getHeading(), frontLeftModule.getModuleState(), frontRightModule.getModuleState(),
                rearLeftModule.getModuleState(), rearRightModule.getModuleState());
    }

    // Method to get robot pose
    public Pose2d getPose() {
        return odometry.getPoseMeters();
    }

    // Method to reset robot pose
    public void resetPose(Pose2d pose) {
        odometry.resetPosition(pose, navX.getHeading());
    }

	public Object getOdometry() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getOdometry'");
	}

    public SwerveDriveKinematics getKinematics() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getKinematics'");
    }

    public void setWheelSpeeds(SwerveDriveWheelStates wheelSpeeds) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setWheelSpeeds'");
    }

    public void stop() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stop'");
    }
}
