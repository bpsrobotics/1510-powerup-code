package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.ArmPID
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import edu.wpi.first.wpilibj.DriverStation



class CenterSwitch(val gameData: String) : CommandGroup() {

    init {
        Drivetrain.resetEncoders()
        addSequential(object: WaitCommand(1.0){})
        ArmPID.setCenter()
        if (gameData.isNotEmpty()) {
            if (gameData[0] == 'R') {
                Drivetrain.resetEncoders()
                addSequential(AutoDrive(.3, .7, 30.0))
                addSequential(object: WaitCommand(.25){})
                //addSequential(AutoDrive(.3,0.0,  3.0))
                //addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, -.6, 40.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, 0.0,25.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(EjectSwitch())
                addSequential(object: WaitCommand(.5){})
                addSequential(TimeEject(2.0))
                //Move arm and dump

            }

            if (gameData[0] == 'L'){

                addSequential(AutoDrive(.3, -.5, 30.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3,0.0,  27.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, .7, 15.0))
                addSequential(object: WaitCommand(.25){})
                addSequential(AutoDrive(.3, 0.0,10.0))
                addSequential(object: WaitCommand(.5){})
                addSequential(EjectSwitch())
                addSequential(object: WaitCommand(.5){})
                addSequential(TimeEject(2.0))
                //Move arm and dump

            }
        }
        else addSequential(CrossLine())
    }
}