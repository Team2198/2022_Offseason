package frc.robot.subsystems;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gyro_Programming extends SubsystemBase{

    private ADXRS450_Gyro gyro;
    public Gyro_Programming(){
        this.gyro = new ADXRS450_Gyro();
    }


    public double gyro_angle(){
        return gyro.getAngle();
    }

    public void calibrate_gyro(){
        gyro.calibrate();
    }

}
