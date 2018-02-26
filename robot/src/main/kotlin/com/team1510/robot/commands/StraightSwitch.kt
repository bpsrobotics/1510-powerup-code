package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup

class StraightSwitch : CommandGroup() {

    init{
        addSequential(AutoDrive(.3,0.0, 40.0))

    }
}