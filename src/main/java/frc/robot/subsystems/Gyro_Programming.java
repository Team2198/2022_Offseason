package frc.robot.subsystems;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;


public class Gyro_Programming extends SubsystemBase{

    AHRS gyro;
    public Gyro_Programming(){
        this.gyro = new AHRS(SPI.Port.kMXP);
    }


    public double yaw_angle(){
        return gyro.getAngle();
    }

    public double pitch_angle(){
        return gyro.getPitch(); 
    }

    public void calibrate_gyro(){
        gyro.calibrate();
    }

}
