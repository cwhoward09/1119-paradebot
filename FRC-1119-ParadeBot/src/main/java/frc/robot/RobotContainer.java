package frc.robot;

import com.ctre.phoenix6.mechanisms.swerve.LegacySwerveModuleConstantsFactory;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.TeleopDrive;
import frc.robot.lib.util.AxisButton;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;

public class RobotContainer {
  // Controllers
  private final Joystick driveController = new Joystick(0);
  private final Joystick operatorController = new Joystick(1);

  // Axes
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  // Driver Buttons

  // Operator Buttons
  private final JoystickButton leftBumper = new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
  private final JoystickButton rightBumper = new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

  private final AxisButton leftTrigger = new AxisButton(operatorController, XboxController.Axis.kLeftTrigger.value, 0.5);
  private final AxisButton rightTrigger = new AxisButton(operatorController, XboxController.Axis.kRightTrigger.value, 0.5);

  // Subsystems
  private final Drive drive = new Drive();
  private final Intake intake = new Intake();
  private final Launcher launcher = new Launcher();

  public RobotContainer() {
    // Default Commands
    drive.setDefaultCommand(
      new TeleopDrive(
        () -> driveController.getRawAxis(translationAxis),
        () -> driveController.getRawAxis(rotationAxis),
        drive
      ));

  // Named Commands

  }

  public void configureButtonBindings() {
    // Driver Button Binds


    // Operator Button Binds
    leftBumper.onTrue(new InstantCommand(() -> intake.intake(), intake));
    leftBumper.onFalse(new InstantCommand(() -> intake.stop(), intake));

    rightBumper.onTrue(new InstantCommand(() -> intake.outtake(), intake));
    rightBumper.onFalse(new InstantCommand(() -> intake.stop(), intake));

    leftTrigger.onTrue(new InstantCommand(() -> launcher.launch(), launcher));
    leftTrigger.onFalse(new InstantCommand(() -> launcher.stop(), launcher));

    rightTrigger.onTrue(new InstantCommand(() -> intake.intake(), intake));
    rightTrigger.onFalse(new InstantCommand(() -> intake.stop(), intake));

  }

  // Misc
  public Joystick getDriveController() {
    return driveController;
  }

  public Joystick getOperatorController() {
    return operatorController;
  }
}