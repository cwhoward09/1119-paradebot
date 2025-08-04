package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private SparkMax intakeMotor;
  private DigitalInput limit;

  public Intake() {
    SparkMax intakeMotor = new SparkMax(21, MotorType.kBrushless);
    DigitalInput limit = new DigitalInput(0); //TODO: Set ID and Channel
  }

  public void intake() {
    intakeMotor.setVoltage(2);
  }
  public void outtake() {
   intakeMotor.setVoltage(-2);
  }

  public void stop() {
    intakeMotor.setVoltage(0);
  }

  public boolean hasNote() {
    return limit.get();
  }
}