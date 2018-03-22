package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.ArmPID
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand

class CrossLine : CommandGroup() {

    init{

        addSequential(object: WaitCommand(.5){})
        addSequential(UpPosition())
        addSequential(AutoDrive(.3,0.0, 80.0))

    }
}