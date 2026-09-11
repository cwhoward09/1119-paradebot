package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
// import frc.robot.commands.CollectNote;
// import frc.robot.commands.Rumble;
import frc.robot.commands.TeleopDrive;
import frc.robot.lib.util.AxisButton;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Pivot.pivotSetpoints;

public class RobotContainer {
  // Controllers
  private final Joystick driveController = new Joystick(0);
  private final Joystick operatorController = new Joystick(1);

  // Axes
  private final int translationAxis = XboxController.Axis.kLeftY.value;
  private final int rotationAxis = XboxController.Axis.kRightX.value;

  // Driver Buttons

  private final JoystickButton driveRightStick = new JoystickButton(driveController, XboxController.Button.kRightStick.value);


  // Operator Buttons
  private final JoystickButton leftBumper = new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
  private final JoystickButton rightBumper = new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

  private final AxisButton leftTrigger = new AxisButton(operatorController, XboxController.Axis.kLeftTrigger.value, 0.5);
  private final AxisButton rightTrigger = new AxisButton(operatorController, XboxController.Axis.kRightTrigger.value, 0.5);

  private final JoystickButton opA = new JoystickButton(operatorController, XboxController.Button.kA.value);
  private final JoystickButton opY = new JoystickButton(operatorController, XboxController.Button.kY.value);
  private final JoystickButton opX = new JoystickButton(operatorController, XboxController.Button.kX.value);
  private final JoystickButton opB = new JoystickButton(operatorController, XboxController.Button.kB.value);

  private final JoystickButton opRightStick = new JoystickButton(operatorController, XboxController.Button.kRightStick.value);
  private final JoystickButton opLeftStick = new JoystickButton(operatorController, XboxController.Button.kLeftStick.value);



  // Subsystems
  private final Drive drive = new Drive();
  private final Intake intake = new Intake();
  private final Launcher launcher = new Launcher();
  private final Pivot pivot = new Pivot();

  public RobotContainer() {
    // Default Commands
    drive.setDefaultCommand(
      new TeleopDrive(
        () -> -driveController.getRawAxis(translationAxis),
        () -> -driveController.getRawAxis(rotationAxis),
        () -> driveRightStick.getAsBoolean(),
        drive
      ));

  // Named Commands

  configureButtonBindings();

  }

  public void configureButtonBindings() {
    // Driver Button Binds


   
    leftBumper.onTrue(intake.collectNote(driveController));
    leftBumper.onTrue(pivot.setPivotPosition(pivotSetpoints.intakePosition));
    leftBumper.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));
    leftBumper.onFalse(pivot.setPivotPosition(pivotSetpoints.idlePosition));

    leftTrigger.onTrue(new InstantCommand(() -> intake.outtake(), intake));
    leftTrigger.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));

    rightTrigger.onTrue(new InstantCommand(() -> launcher.launch(), launcher));
    rightTrigger.onFalse(new InstantCommand(() -> launcher.launcherStop(), launcher));

    rightBumper.onTrue(new InstantCommand(() -> intake.intake(), intake));
    rightBumper.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));

    opA.onTrue(pivot.setPivotPosition(pivotSetpoints.launchPositionLow));
    opX.onTrue(pivot.setPivotPosition(pivotSetpoints.idlePosition));
    opY.onTrue(pivot.setPivotPosition(pivotSetpoints.launchPositionHigh));
    opB.onTrue(pivot.setPivotPosition(pivotSetpoints.launchPositionMid));

    opLeftStick.onTrue(new InstantCommand(() -> launcher.launchSlow(), launcher));
    opLeftStick.onFalse(new InstantCommand(() -> launcher.launcherStop(), launcher));
    opRightStick.onTrue(pivot.setPivotPosition(pivotSetpoints.L2));

  }

  // Misc
  public Joystick getDriveController() {
    return driveController;
  }

  public Joystick getOperatorController() {
    return operatorController;
  }
}