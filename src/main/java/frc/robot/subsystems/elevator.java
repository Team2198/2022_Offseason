package frc.robot.subsystems;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

import com.revrobotics.RelativeEncoder;
//import com.revrobotics.SparkMaxAlternateEncoder;
import com.revrobotics.SparkMaxRelativeEncoder;


public class Elevator extends SubsystemBase {
   WPI_VictorSPX EOne;
   CANSparkMax SRight;
   CANSparkMax SLeft;
   WPI_VictorSPX intake;
   GenericHID genericHID;
   RobotContainer robotContainer;
   RelativeEncoder leftEncoder;
   
   public  Elevator(){
      
      this.EOne = new WPI_VictorSPX(Constants.E_One);
      this.SRight = new CANSparkMax(Constants.S_One, MotorType.kBrushless);
      this.SLeft = new CANSparkMax(Constants.S_Two, MotorType.kBrushed);
      this.intake = new WPI_VictorSPX(Constants.Intake);
      this.intake.setInverted(true);
      this.SLeft.setInverted(true);
      this.leftEncoder = SLeft.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 4096);
      
      //dkjfklfjdkl
   }
   public void setEMotor(boolean b){
      if (b){
         intake.set(1);
        
      }
      else{
         intake.set(0);
      }
   
   }

   public void setSh(boolean b){
      if (b){
        
        SRight.set(0.6);
        SLeft.set(0.6);
        
      }
      else{
         SLeft.set(0);
         SRight.set(0);
      }
 }  

   public void setEle(double speed){
      leftEncoder.setVelocityConversionFactor(6*Math.PI);
      double velocity = leftEncoder.getVelocityConversionFactor();
      if (velocity > 0.5){
         EOne.set(speed);   
      } 
   }

}