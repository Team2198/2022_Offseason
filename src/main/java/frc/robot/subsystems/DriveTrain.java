package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
  import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import frc.robot.Constants;


public class DriveTrain extends SubsystemBase {    
    WPI_TalonSRX leftOne;  
    WPI_TalonSRX leftTwo;
    MotorControllerGroup left;
    WPI_TalonSRX rightOne;
    WPI_TalonSRX rightTwo;    
    MotorControllerGroup right;
    DifferentialDrive differentialDrive;

    public DriveTrain(){
        this.leftOne= new WPI_TalonSRX(Constants.Left_One);
        this.leftTwo= new WPI_TalonSRX(Constants.Left_Two);  
        this.left = new MotorControllerGroup(this.leftOne, this.leftTwo);
        this.left.setInverted(true);
        this.rightOne = new WPI_TalonSRX(Constants.Right_One);
        this.rightTwo = new WPI_TalonSRX(Constants.Right_Two);
        this.right = new MotorControllerGroup(this.rightOne, this.rightTwo);
        this.differentialDrive = new DifferentialDrive(this.left, this.right);  
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
