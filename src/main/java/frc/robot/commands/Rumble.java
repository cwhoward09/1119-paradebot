// package frc.robot.commands;

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.GenericHID.RumbleType;
// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.Intake;

// public class Rumble extends Command {
    
//     private Intake intake;
//     private boolean limit;
//     private Joystick driveController;
//     private Joystick operatorController;

//     public Rumble (Intake intake, Joystick driver, Joystick operator){

//         this.intake = intake;
//         this.driveController = driveController;
//         this.operatorController = operatorController;
//     }

//     @Override
//     public void execute() {
//         if (intake.hasNote()) {
//             driveController.setRumble(RumbleType.kBothRumble, 1);
//             operatorController.setRumble(RumbleType.kBothRumble, 1);
//         } else {
//             driveController.setRumble(RumbleType.kBothRumble, 0);
//             operatorController.setRumble(RumbleType.kBothRumble, 0);
//         }
//     }

//     @Override
//     public void end(boolean interrupted) {
//         driveController.setRumble(RumbleType.kBothRumble, 0);
//         operatorController.setRumble(RumbleType.kBothRumble, 0);
//     }
// }