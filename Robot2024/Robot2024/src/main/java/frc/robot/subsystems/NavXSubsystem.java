package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.geometry.Rotation2d;

// NavXSubsystem.java
// Represents the NavX Subsystem for gyro sensor control.
public class NavXSubsystem extends SubsystemBase {
    private final AHRS navX;

    public NavXSubsystem() {
        // Initialize NavX2MXP with SerialPort.Port.kMXP
        navX = new AHRS(SerialPort.Port.kMXP);
    }

    // Get the current heading angle from NavX sensor
    public Rotation2d getHeading() {
        return Rotation2d.fromDegrees(navX.getYaw());
    }
    public double getAngle() {
        // Return current angle from NavX
        return navX.getAngle();
    }
    // Reset the NavX sensor to zero heading
    public void reset() {
        navX.reset();
    }
}