package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.filter.SlewRateLimiter;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.interfaces.Gyro;


public class DriveTrain extends SubsystemBase {    
    WPI_TalonSRX leftOne;  
    WPI_TalonSRX leftTwo;
    WPI_TalonSRX leftThree;
    MotorControllerGroup left;
    WPI_TalonSRX rightOne;
    WPI_TalonSRX rightTwo;
    WPI_TalonSRX rightThree;      
    MotorControllerGroup right;
    DifferentialDrive differentialDrive;
    //SlewRateLimiter filter;



    public DriveTrain(){
        this.leftOne= new WPI_TalonSRX(Constants.Left_One);
        this.leftTwo= new WPI_TalonSRX(Constants.Left_Two);  
        this.leftThree= new WPI_TalonSRX(Constants.Left_Three);  
        this.rightOne = new WPI_TalonSRX(Constants.Right_One);
        this.rightTwo = new WPI_TalonSRX(Constants.Right_Two);
        this.rightThree = new WPI_TalonSRX(Constants.Right_Three);
        //this.filter = new SlewRateLimiter(0.5);
        this.leftOne.setNeutralMode(NeutralMode.Coast);
        this.leftTwo.setNeutralMode(NeutralMode.Coast);
        this.leftThree.setNeutralMode(NeutralMode.Coast);
        this.left = new MotorControllerGroup(this.leftOne, this.leftTwo, this.leftThree);
        this.rightOne.setNeutralMode(NeutralMode.Coast);
        this.rightTwo.setNeutralMode(NeutralMode.Coast);
        this.rightThree.setNeutralMode(NeutralMode.Coast);
        this.left.setInverted(true);
        this.right = new MotorControllerGroup(this.rightOne, this.rightTwo, this.rightThree);
        this.differentialDrive = new DifferentialDrive(this.left, this.right);  
      }
    
    
    public void setMotor(double y, double x){
      //differentialDrive.arcadeDrive(y, -x, true);
      /*if(y < 0){
        differentialDrive.curvatureDrive(y, -x, false);  
      }
      else{
        double xRef = -x;
        if(x > .85){
          x = .85;
        }
        differentialDrive.curvatureDrive(y, xRef+0.15, false);
      }*/
      differentialDrive.arcadeDrive(y, -x, false);
      SmartDashboard.putNumber("Y val", y);
      SmartDashboard.putNumber("X val", x);
      //differentialDrive.tankDrive(y, x);
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