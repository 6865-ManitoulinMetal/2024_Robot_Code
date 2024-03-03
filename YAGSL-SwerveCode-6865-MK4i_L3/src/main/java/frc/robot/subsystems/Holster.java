import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class HolsterSubsystem extends SubsystemBase {
    private final TalonFX holsterMotor;

    public HolsterSubsystem(int motorCANID) {
        holsterMotor = new TalonFX(motorCANID);
    }

    public void turnOn() {
        holsterMotor.set(ControlMode.PercentOutput, 1.0);
    }

    public void turnOff() {
        holsterMotor.set(ControlMode.PercentOutput, 0.0);
    }
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;

public class ToggleHolsterCommand extends CommandBase {
    private final HolsterSubsystem holster;
    private final XboxController controller;

    public ToggleHolsterCommand(HolsterSubsystem holster, XboxController controller) {
        this.holster = holster;
        this.controller = controller;
        addRequirements(holster);
    }

    @Override
    public void execute() {
        if (controller.getAButtonPressed()) {
            if (holster.isHolsterOn()) {
                holster.turnOff();
            } else {
                holster.turnOn();
            }
        }
    }

    @Override
    public boolean isFinished() {
        return false; // This command runs continuously until interrupted
    }
}
