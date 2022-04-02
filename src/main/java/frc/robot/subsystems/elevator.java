package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.XboxController;

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
   
   public Elevator(){
      
      this.EOne = new WPI_VictorSPX(Constants.E_One);

      this.SRight = new CANSparkMax(Constants.S_One, MotorType.kBrushless);
      this.SLeft = new CANSparkMax(Constants.S_Two, MotorType.kBrushless);
      this.intake = new WPI_VictorSPX(Constants.Intake);
      this.intake.setInverted(true);
      this.SLeft.setInverted(true);
      this.leftEncoder = SLeft.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
      
      //dkjfklfjdkl
   }
  
  
   public void setIntake(boolean b){
      if (b == true){
         intake.set(0.7);
      }
     if (!b){
         intake.set(0);
     }
   }

   public void setSh(boolean b){
      if (b){
        SRight.set(0.3);
        SLeft.set(0.3);
        
      }
      else{
         SLeft.set(0);
         SRight.set(0);
      }



   }  

   public void setUpper(){
      SRight.set(0.75);
      SLeft.set(0.75);
   }

   public void setTarmac(){
      SRight.set(0.65);
      SLeft.set(0.65);
   }


      


   public void trig_Sh(double speed){
        SRight.set(speed);
        SLeft.set(speed);
   }
   public void setEle(double speed){
      leftEncoder.setVelocityConversionFactor(6*Math.PI);
      double velocity = leftEncoder.getVelocityConversionFactor();
      if (velocity > 0.5){
         EOne.set(speed);   
      } 
   }

   public void revert_setSh(boolean b){
      if (b){
        SRight.set(-0.2);
        SLeft.set(-0.2);
        
      }
      else{
         SLeft.set(0);
         SRight.set(0);
      }
   }  

   public void auto_shoot(){
       SRight.set(0.3);
       SLeft.set(0.3);
   }

   public void auto_shoot_two(){
      SRight.set(0.75);
      SRight.set(0.75);
   }
     
   
   

   public void setZero(boolean b){
      if (b == true){
        intake.set(0);
        EOne.set(0);
      }
      if (b == false){
        SRight.set(0);
        SLeft.set(0);
      }
   }

}