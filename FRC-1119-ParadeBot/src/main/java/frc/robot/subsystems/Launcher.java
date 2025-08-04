package frc.robot.subsystems;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase {
    private SparkMax leftLauncherMotor; 
    private SparkMax rightLauncherMotor;

    public void launcher() {
        SparkMax leftLauncherMotor = new SparkMax(31, MotorType.kBrushless);
        SparkMax rightLauncherMotor = new SparkMax(32, MotorType.kBrushless);
    }

    public void launch() {
        leftLauncherMotor.setVoltage(3);
        rightLauncherMotor.setVoltage(3);

    }

    public void stop() {
        leftLauncherMotor.setVoltage(0);
        rightLauncherMotor.setVoltage(0);
    }

}
