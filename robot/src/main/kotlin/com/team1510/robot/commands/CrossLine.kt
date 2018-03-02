package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.ArmPID
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup

class CrossLine : CommandGroup() {

    init{
        ArmPID.setCurrentPosition()
        addSequential(AutoDrive(.3,0.0, 40.0))

    }
}