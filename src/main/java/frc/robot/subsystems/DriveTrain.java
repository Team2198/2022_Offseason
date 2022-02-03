package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;




public class DriveTrain extends SubsystemBase {
    
    
    WPI_TalonSRX left;  
    WPI_TalonSRX right;
    DifferentialDrive differentialDrive;

    public DriveTrain(){
        this.left = new WPI_TalonSRX(0);  
        this.left.setInverted(true);
        this.right = new WPI_TalonSRX(1);
        this.differentialDrive = new DifferentialDrive(left, right);  
    }
    
    
    public void setMotor(double y, double x){
        differentialDrive.arcadeDrive(-y, x, true);
    }
    
    
    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }
  
    @Override
    public void simulationPeriodic() {
      // This method will be called once per scheduler run during simulation
    }
  
}
