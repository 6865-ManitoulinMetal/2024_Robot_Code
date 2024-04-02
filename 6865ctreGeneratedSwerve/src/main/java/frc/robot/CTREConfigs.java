package frc.robot;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

public final class CTREConfigs {
    public TalonFXConfiguration climberFXConfig = new TalonFXConfiguration();
        /* Climber Current Limits */
    public CTREConfigs()
        {
        climberFXConfig.CurrentLimits.StatorCurrentLimitEnable = false;
        climberFXConfig.CurrentLimits.StatorCurrentLimit = 20;
        climberFXConfig.CurrentLimits.SupplyCurrentLimitEnable = false;
        climberFXConfig.CurrentLimits.SupplyCurrentLimit = 30;
    }
}