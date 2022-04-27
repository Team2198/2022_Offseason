package frc.robot.commands;
import frc.robot.commands.PID_test;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class Position_Scheduler extends SequentialCommandGroup{
    PID_test pid;
    public Position_Scheduler(){
        this.pid = new PID_test();
        addCommands(this.pid);
    }

}