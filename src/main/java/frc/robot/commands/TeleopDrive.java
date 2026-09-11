package frc.robot.commands;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

public class TeleopDrive extends Command {
    private DoubleSupplier translationSupplier;
    private DoubleSupplier rotationSupplier;
    private BooleanSupplier overrideSupplier;

    private Drive drive;

    public TeleopDrive(DoubleSupplier translationSupplier, DoubleSupplier rotationSupplier, BooleanSupplier overrideSupplier, Drive drive) {
        this.translationSupplier = translationSupplier;
        this.rotationSupplier = rotationSupplier;
        this.overrideSupplier = overrideSupplier;
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void execute() {
        double translationVal = MathUtil.applyDeadband(translationSupplier.getAsDouble(), 0.2);
        double rotationVal = MathUtil.applyDeadband(rotationSupplier.getAsDouble(), 0.2);
        boolean override = overrideSupplier.getAsBoolean();

        if (override == false) {
            drive.drive(translationVal * 1, rotationVal);
        } else {
            drive.drive(0, 0);
        }
        
    }
}