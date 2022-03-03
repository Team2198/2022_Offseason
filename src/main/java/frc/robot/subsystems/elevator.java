package frc.robot.subsystems;
import edu.wpi.first.wpilibj.XboxController;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class Elevator extends SubsystemBase {
   WPI_VictorSPX EOne;
   WPI_VictorSPX SRight;
   WPI_VictorSPX SLeft;   
   GenericHID genericHID;
   RobotContainer robotContainer;
   
   public  Elevator(){
      this.EOne = new WPI_VictorSPX(Constants.E_One);
      this.SRight = new WPI_VictorSPX(2);
      this.SLeft = new WPI_VictorSPX(3);
   
      //dkjfklfjdkl
   }
   public  void setEMotor(boolean b){
      if (b == true){
         EOne.set(1);
         SRight.set(1);
         SLeft.set(1);
      }
     if (b == false){
         EOne.set(1);
         SRight.set(0);
         SLeft.set(0);
     }
   
   }
}