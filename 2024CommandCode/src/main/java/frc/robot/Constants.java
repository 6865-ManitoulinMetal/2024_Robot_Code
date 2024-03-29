// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
// Constants.java
// This file contains constants used throughout the project.

/**
 * Constants class containing configuration constants for swerve modules and robot parameters.
 */
public class Constants {
    // Front Left Swerve Module Constants
    public static final int DRIVE_MOTOR_FRONT_LEFT_ID = 0;
    public static final int ROTATION_MOTOR_FRONT_LEFT_ID = 1;
    public static final int ENCODER_FRONT_LEFT_ID = 1;
    public static final double ENCODER_FRONT_LEFT_OFFSET = 0.0;
    public static final boolean ENCODER_FRONT_LEFT_INVERT = false;

    // Front Right Swerve Module Constants
    public static final int DRIVE_MOTOR_FRONT_RIGHT_ID = 2;
    public static final int ROTATION_MOTOR_FRONT_RIGHT_ID = 3;
    public static final int ENCODER_FRONT_RIGHT_ID = 2;
    public static final double ENCODER_FRONT_RIGHT_OFFSET = 0.0;
    public static final boolean ENCODER_FRONT_RIGHT_INVERT = false;

    // Rear Left Swerve Module Constants
    public static final int DRIVE_MOTOR_REAR_LEFT_ID = 4;
    public static final int ROTATION_MOTOR_REAR_LEFT_ID = 5; 
    public static final int ENCODER_REAR_LEFT_ID = 4;
    public static final double ENCODER_REAR_LEFT_OFFSET = 0.0;
    public static final boolean ENCODER_REAR_LEFT_INVERT = false;

    // Rear Right Swerve Module Constants
    public static final int DRIVE_MOTOR_REAR_RIGHT_ID = 6;
    public static final int ROTATION_MOTOR_REAR_RIGHT_ID = 7;
    public static final int ENCODER_REAR_RIGHT_ID = 3;
    public static final double ENCODER_REAR_RIGHT_OFFSET = 0.0;
    public static final boolean ENCODER_REAR_RIGHT_INVERT = false;

    // Front motor flipped
    public static final boolean FRONT_MOTOR_FLIPPED = true; // Change this according to your configuration

    // Gear ratios for swerve modules
    // Sample gear ratio for MKvi L3 swerve module
    public static final double GEAR_RATIO_MKVI_L3 = 6.12; // overall gear ratio for MKvi L3 swerve module

    public static final double DEFAULT_DRIVE_SPEED = 0.2; // Default drive speed 0.2 unless overwritten in commands
    public static final int TRACK_WIDTH = 0;
    public static final int WHEELBASE = 0;
    
    // Additional constants for robot motion control
    public static final double WHEEL_POSITION = 12.25;  // Wheel position relative to robot center
    public static final double WHEELBASE_WIDTH = 24.5;  // Width of the wheelbase
    public static final double WHEELBASE_LENGTH = 24.5;  // Length of the wheelbase
    public static final double ROTATION_OFFSET = 0.1;  // Rotation offset
    public static final int ENCODER_CPR = 1024;  // Encoder Counts Per Revolution
    public static final double ENCODER_DISTANCE_PER_PULSE = 0.01;  // Distance traveled per encoder pulse
    public static final double GYRO_SENSITIVITY = 0.1;  // Gyroscope sensitivity
    public static final double MAX_WHEEL_SPEED = 0.2;  // Maximum wheel speed
    public static final String Swerve = null;

    // Operator Constants
    public static class OperatorConstants {
        public static final int kDriverControllerPort = 0;
    }
    // Swerve constants
    public static class Swerve {
        public static final double driveKS = 0.0; // Placeholder value, replace with your actual value
        public static final double driveKV = 0.0; // Placeholder value, replace with your actual value
        public static final double driveKA = 0.0; // Placeholder value, replace with your actual value
    // Define other swerve constants here
}
}
