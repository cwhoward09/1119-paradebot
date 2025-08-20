package frc.robot;

import com.revrobotics.spark.SparkFlex;
import com.revrobotics.spark.config.SparkFlexConfig;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.subsystems.Pivot.pivotSetpoints;

public final class Configs {
    public final class Intake {
        public static final SparkMaxConfig intakeConfig = new SparkMaxConfig();

        static {
            intakeConfig
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(20);
        }
    }

    public final class Launcher {
        public static final SparkMaxConfig launcherConfig = new SparkMaxConfig();
        
        static {
            launcherConfig
                .idleMode(IdleMode.kBrake)
                .smartCurrentLimit(20);
        }
    }

    public final class Pivot {
        public static final SparkFlexConfig leftPivotConfig = new SparkFlexConfig();
        public static final SparkFlexConfig rightPivotConfig = new SparkFlexConfig();

        static {
            leftPivotConfig
                .idleMode(IdleMode.kBrake)
                .inverted(false)
                .smartCurrentLimit(40)
                .voltageCompensation(12);

            leftPivotConfig.closedLoop
                .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                .p(0.1)
                .outputRange(-1, 1);

            leftPivotConfig.closedLoop.maxMotion
                .maxVelocity(1000)
                .maxAcceleration(1500)
                .allowedClosedLoopError(0.1);

            rightPivotConfig
                .idleMode(IdleMode.kBrake)
                .inverted(false)
                .follow(12, true)
                .smartCurrentLimit(40)
                .voltageCompensation(12);
        }
    }
}