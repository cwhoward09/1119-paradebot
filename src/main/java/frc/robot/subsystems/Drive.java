package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class Drive extends SubsystemBase {

    private SparkMax leftLeader;
    private SparkMax leftFollower;

    private SparkMax rightLeader;
    private SparkMax rightFollower;

    private final DifferentialDrive robotDrive;

    public Drive() {
        this.leftLeader = new SparkMax(0, null);
        this.leftFollower = new SparkMax(0, null);

        this.rightLeader = new SparkMax(0, null);
        this.rightFollower = new SparkMax(0, null);

        this.leftFollower.follow(this.leftLeader);
        this.rightFollower.follow(this.rightLeader);

        this.rightLeader.setInverted(true);
        this.rightFollower.setInverted(true);

        this.robotDrive = new DifferentialDrive(this.leftLeader, this.rightLeader);
    }

    public void drive(double speed, double rotation) {
        this.robotDrive.arcadeDrive(speed, rotation);
    }

}