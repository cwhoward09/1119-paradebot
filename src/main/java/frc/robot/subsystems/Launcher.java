package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class Launcher extends SubsystemBase {
    private SparkMax leftLauncherMotor;
    private SparkMax rightLauncherMotor;

    public Launcher() {
        leftLauncherMotor = new SparkMax(31, MotorType.kBrushless);
        rightLauncherMotor = new SparkMax(32, MotorType.kBrushless);

        leftLauncherMotor.configure(
            Configs.Launcher.launcherConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );

        rightLauncherMotor.configure(
            Configs.Launcher.launcherConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );
    }

    public void launch() {
        leftLauncherMotor.setVoltage(-12);
        rightLauncherMotor.setVoltage(-12);
    }

    public void launcherStop() {
        leftLauncherMotor.setVoltage(0);
        rightLauncherMotor.setVoltage(0);
    }

}
