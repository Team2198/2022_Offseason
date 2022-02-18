package frc.robot.subsystems;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Elevator extends SubsystemBase {
   
   WPI_TalonSRX EOne;
   XboxController xboxController;   
   GenericHID genericHID;
   RobotContainer robotContainer;
   
   public  Elevator(){
      this.EOne = new WPI_TalonSRX(Constants.E_One);
      
   }
   public  void setEMotor(){
       EOne.set(1);
   }
   
}