// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;



/** Add your docs here. */
public class Intake {
  Compressor compressor;    
  DoubleSolenoid solenoid;  
  public Intake(){
        this.compressor = new Compressor(PneumaticsModuleType.CTREPCM);
        this.solenoid = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);
    }

    public void setComp(boolean piston){
        if (compressor.enabled()){
            compressor.disable();
        }
        else{
            compressor.enableDigital();
            if (piston == true){
                solenoid.toggle();
            }
            if (piston == false){
                solenoid.set(DoubleSolenoid.Value.kOff);

            }

            

        }
    }
}
