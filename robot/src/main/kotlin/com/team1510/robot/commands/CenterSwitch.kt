package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.ArmPID
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import edu.wpi.first.wpilibj.DriverStation



class CenterSwitch : CommandGroup() {

    init {
        Drivetrain.resetEncoders()
        ArmPID.setCenter()
        val gameData: String = DriverStation.getInstance().gameSpecificMessage
        if (gameData.isNotEmpty()) {
            if (gameData[0] == 'R') {
                addSequential(AutoDrive(.3, .7, 15.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3,0.0,  5.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, -.6, 10.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, 0.0,10.0))
                addSequential(EjectSwitch())

                //Move arm and dump

            } else {
                addSequential(AutoDrive(.3, -.5, 15.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3,0.0,  5.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, .7, 11.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, 0.0,10.0))
                addSequential(EjectSwitch())

                //Move arm and dump

            }
        }
    }
}