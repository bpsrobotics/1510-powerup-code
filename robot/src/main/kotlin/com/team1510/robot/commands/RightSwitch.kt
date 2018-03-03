package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.ArmPID
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand

class RightSwitch : CommandGroup() {

    init {
        Drivetrain.resetEncoders()
        ArmPID.setCenter()
        val gameData: String = DriverStation.getInstance().gameSpecificMessage
        if (gameData.isNotEmpty()) {
            if (gameData[0] == 'R') {
                addSequential(object: WaitCommand(.5){})
                addSequential(UpPosition())
                addSequential(AutoDrive(.3,0.0, 105.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, -.9, 30.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(EjectSwitch())
                addSequential(object: WaitCommand(.5){})
                addSequential(TimeEject(2.0))
                //Move arm and dump
            }
            else addSequential(CrossLine())
        }
    }
}