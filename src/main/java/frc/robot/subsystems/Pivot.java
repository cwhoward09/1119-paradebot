package frc.robot.subsystems;

import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class Pivot extends SubsystemBase {
    private SparkFlex leftPivotMotor;
    private SparkFlex rightPivotMotor;

    private DigitalInput magnetSwitch;

    private SparkClosedLoopController pivotController;
    private RelativeEncoder pivotEncoder;

    private double pivotTargetPosition;

    public enum pivotSetpoints {
        intakePosition,
        idlePosition,
        launchPosition;
    }

    public Pivot() {
        leftPivotMotor = new SparkFlex(12, MotorType.kBrushless);
        rightPivotMotor = new SparkFlex(11, MotorType.kBrushless);

        magnetSwitch = new DigitalInput(0);

        pivotController = leftPivotMotor.getClosedLoopController();
        pivotEncoder = leftPivotMotor.getEncoder();

        pivotTargetPosition = 7;

        leftPivotMotor.configure(
            Configs.Pivot.leftPivotConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );

        rightPivotMotor.configure(
            Configs.Pivot.rightPivotConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        );
    }

    public void pivotToSetpoint() {
        pivotController.setReference(
            pivotTargetPosition,
            ControlType.kMAXMotionPositionControl
        );
    }

    // public boolean getMagnetSwitch() {
    //     return magnetSwitch.get();
    // }

    public Command setPivotPosition(pivotSetpoints setpoint) {
        return this.runOnce(
            () -> {
                switch(setpoint) {
                    case intakePosition:
                        pivotTargetPosition = 2;
                        break;

                    case idlePosition:
                        pivotTargetPosition = 7;
                        break;

                    case launchPosition:
                        pivotTargetPosition = 25;
                        break;

                    default:
                        pivotTargetPosition = 1;
                        break;
                }
            }
        );
    }

    @Override
    public void periodic() {
        pivotToSetpoint();

        SmartDashboard.putNumber("Pivot Position", pivotEncoder.getPosition());
        SmartDashboard.putNumber("Pivot Target", pivotTargetPosition);

        // SmartDashboard.putBoolean("Magnet High Signal", getMagnetSwitch());
    }
}
