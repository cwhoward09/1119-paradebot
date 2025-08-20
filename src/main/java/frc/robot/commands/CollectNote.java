// package frc.robot.commands;

// import edu.wpi.first.wpilibj2.command.Command;
// import frc.robot.subsystems.Intake;

// public class CollectNote extends Command {
//     private Intake intake;

//     public CollectNote (Intake intake) {
//         addRequirements(intake);

//         this.intake = intake;
//     }

//     @Override
//     public void execute() {
//         intake.intake();
//     }

//     @Override
//     public void end(boolean interrupted) {
//         intake.intakeStop();
//     }

//     @Override
//     public boolean isFinished() {
//         return intake.hasNote();
//     }
// }
