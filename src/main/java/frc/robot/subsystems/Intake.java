package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;

public class Intake extends SubsystemBase {
  private SparkMax intakeMotor;
  private DigitalInput limit;

  public Intake() {
    intakeMotor = new SparkMax(21, MotorType.kBrushless);
    limit = new DigitalInput(1);

    intakeMotor.configure(
      Configs.Intake.intakeConfig,
      ResetMode.kResetSafeParameters,
      PersistMode.kPersistParameters
    );
  }

  public void intake() {
    intakeMotor.setVoltage(-5);
  }

  public void outtake() {
    intakeMotor.setVoltage(5);
  }

  public void intakeStop() {
    intakeMotor.setVoltage(0);
  }

  public boolean hasNote() {
    return limit.get();
  }

  @Override
  public void periodic() {
    SmartDashboard.putBoolean("Has Note", hasNote());
  }
}