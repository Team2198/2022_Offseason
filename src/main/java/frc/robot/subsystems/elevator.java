// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.XboxController;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

//
public class elevator extends SubsystemBase {
    WPI_VictorSPX EOne;
    WPI_VictorSPX SRight;
    WPI_VictorSPX SLeft;

    XboxController xboxController;   
    GenericHID genericHID;
    RobotContainer robotContainer;
    
    public  elevator(){
       this.EOne = new WPI_VictorSPX(Constants.E_One);
       this.SRight = new WPI_VictorSPX(Constants.S_Right);
       this.SLeft = new WPI_VictorSPX(Constants.S_Left);
    }
    public  void setEMotar(boolean b){
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
    else {
    EOne.set(0);
    SRight.set(0);
    SLeft.set(0);
}
    }
   
}
    










