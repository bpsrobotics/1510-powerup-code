package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup

class RightSwitch : CommandGroup() {

    init {

        addSequential(AutoTurn(.3, .7, 10.0))
        addSequential(AutoDrive(.3,  8.0))
        addSequential(AutoTurn(.4, -.5, 15.0))
        addSequential(AutoDrive(.4,  8.0))

    }
}