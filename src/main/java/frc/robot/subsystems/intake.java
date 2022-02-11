// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
/** Add your docs here. */
public class Intake extends SubsystemBase{
    Compressor compressor;
    public Intake(){
      this.compressor = new Compressor(PneumaticsModuleType.CTREPCM);


    }
    public void setComp(){
         boolean compStatus = compressor.enabled();
         if (compStatus == true){
         compressor.disable();
         }
        else{ 
         compressor.enableDigital();
        }

      }
    } 
