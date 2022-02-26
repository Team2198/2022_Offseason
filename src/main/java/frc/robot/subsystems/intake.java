// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.Constants;



/** Add your docs here. */
public class Intake {
  Compressor compressor;    
  DoubleSolenoid solenoid;  
  public Intake(){
        this.compressor = new Compressor(PneumaticsModuleType.CTREPCM);
        this.solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, Constants.solenoid_chF, Constants.solenoid_chR);
    }

    public void toggleComp(){
        if (compressor.enabled() == false){
            compressor.enableDigital();
        }
        else{
            if (compressor.enabled() == true){
                compressor.disable();
            }
        }
    }

    public void reset(){
        solenoid.set(DoubleSolenoid.Value.kReverse);
        toggleComp();
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
