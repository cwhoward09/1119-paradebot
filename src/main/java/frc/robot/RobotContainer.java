package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
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

  // Operator Buttons
  private final JoystickButton leftBumper = new JoystickButton(operatorController, XboxController.Button.kLeftBumper.value);
  private final JoystickButton rightBumper = new JoystickButton(operatorController, XboxController.Button.kRightBumper.value);

  private final AxisButton leftTrigger = new AxisButton(operatorController, XboxController.Axis.kLeftTrigger.value, 0.5);
  private final AxisButton rightTrigger = new AxisButton(operatorController, XboxController.Axis.kRightTrigger.value, 0.5);

  private final JoystickButton opA = new JoystickButton(operatorController, XboxController.Button.kA.value);
  private final JoystickButton opY = new JoystickButton(operatorController, XboxController.Button.kY.value);

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
        drive
      ));

  // Named Commands

  configureButtonBindings();

  }

  public void configureButtonBindings() {
    // Driver Button Binds


    // Operator Button Binds
    // leftBumper.whileTrue(new CollectNote(intake)
    //         .andThen(new InstantCommand(
    //             () -> intake.outtake(), intake).withTimeout(.09)
    //             .andThen(new InstantCommand( () -> intake.intakeStop(), intake))));

    // leftBumper.onTrue(new InstantCommand(() -> pivot.setPivotPosition(pivotSetpoints.intakePosition), pivot));
    // leftBumper.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));
    // leftBumper.onFalse(new InstantCommand(() -> pivot.setPivotPosition(pivotSetpoints.idlePosition)));
    // leftBumper.whileTrue(new Rumble(intake, driveController, operatorController));

    leftBumper.onTrue(new InstantCommand(() -> intake.intake(), intake));
    leftBumper.onTrue(pivot.setPivotPosition(pivotSetpoints.intakePosition));
    leftBumper.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));
    leftBumper.onFalse(pivot.setPivotPosition(pivotSetpoints.idlePosition));

    rightBumper.onTrue(new InstantCommand(() -> intake.outtake(), intake));
    rightBumper.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));

    leftTrigger.onTrue(new InstantCommand(() -> launcher.launch(), launcher));
    leftTrigger.onFalse(new InstantCommand(() -> launcher.launcherStop(), launcher));

    rightTrigger.onTrue(new InstantCommand(() -> intake.intake(), intake));
    rightTrigger.onFalse(new InstantCommand(() -> intake.intakeStop(), intake));

    opA.onTrue(pivot.setPivotPosition(pivotSetpoints.intakePosition));
    opY.onTrue(pivot.setPivotPosition(pivotSetpoints.launchPosition));

  }

  // Misc
  public Joystick getDriveController() {
    return driveController;
  }

  public Joystick getOperatorController() {
    return operatorController;
  }
}