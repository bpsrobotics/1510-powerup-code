package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup

class Autonomous : CommandGroup() {

    init {

        addSequential(AutoTurn(.3, .5, 20.0))
        addSequential(AutoDrive(.5,  15.0))
        addSequential(AutoTurn(.3, -.6, 15.0))
        addSequential(AutoDrive(.5, 10.0))

    }
}