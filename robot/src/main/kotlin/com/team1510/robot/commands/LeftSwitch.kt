package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand

class LeftSwitch : CommandGroup() {

    init {
        Drivetrain.resetEncoders()
        addSequential(AutoDrive(.3, -.5, 15.0))
        addSequential(object: WaitCommand(.25){})
        addSequential(AutoDrive(.3,0.0,  5.0))
        addSequential(object: WaitCommand(.25){})
        addSequential(AutoDrive(.3, .7, 10.0))
        addSequential(object: WaitCommand(.25){})
        addSequential(AutoDrive(.3, 0.0,10.0))
    }
}