// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;



/** Add your docs here. */
public class Intake {
  

  DoubleSolenoid solenoid;  


  public Intake(){
        
        
       this.solenoid = new DoubleSolenoid(1, PneumaticsModuleType.CTREPCM, 0, 1);
    }



    public void reset(){

        solenoid.set(DoubleSolenoid.Value.kReverse);
        

        SmartDashboard.setDefaultBoolean("hello", true);
        //solenoid.set(DoubleSolenoid.Value.kReverse);

    }

    public void toggleIntake(){
        DoubleSolenoid.Value open = solenoid.get();
        if(open == DoubleSolenoid.Value.kForward || open == DoubleSolenoid.Value.kOff){
            solenoid.set(DoubleSolenoid.Value.kReverse);
        }
        else if(open == DoubleSolenoid.Value.kReverse){
            solenoid.set(DoubleSolenoid.Value.kForward);
        }
    }
}
