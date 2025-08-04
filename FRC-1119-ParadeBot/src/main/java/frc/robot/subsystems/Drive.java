package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

    private WPI_TalonSRX leftLeader;
    private WPI_VictorSPX leftFollower;

    private WPI_TalonSRX rightLeader;
    private WPI_VictorSPX rightFollower;

    private final DifferentialDrive robotDrive;


    public Drive() {
        this.leftLeader = new WPI_TalonSRX(3);
        this.leftFollower = new WPI_VictorSPX(2);

        this.rightLeader = new WPI_TalonSRX(1);
        this.rightFollower = new WPI_VictorSPX(0);

        this.leftFollower.follow(this.leftLeader);
        this.rightFollower.follow(this.rightLeader);

        this.rightLeader.setInverted(true);

        this.robotDrive = new DifferentialDrive(this.leftLeader, this.rightLeader);
    }

    public void drive(double speed, double rotation) {
        this.robotDrive.arcadeDrive(speed, rotation);
    }

}