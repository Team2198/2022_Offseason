// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    // Controller
    public final static int Xbox_Controller= 0;
    public final static int JoystickY_Port = 1;

    // Drive Train
        // Right
    public final static int Left_One = 2;
    public final static int Left_Two = 3;
    public final static int Left_Three = 4;
        
        // Left
    public final static int Right_One = 5;
    public final static int Right_Two = 6;
    public final static int Right_Three = 7;

    // Elevator
    public final static int E_One = 8;
    

    // Climber
    public final static int Climb_Motor = 10;

    // Intake
    public final static int Intake = 9;    

    // Shooter
    public final static int  S_One = 12;
    public final static int S_Two = 11;
    
        // Pneumatics
    public final static int solenoid_chF = 0;
    public final static int solenoid_chR = 1;
}