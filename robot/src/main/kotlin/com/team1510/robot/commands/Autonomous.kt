package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import edu.wpi.first.wpilibj.DriverStation



class Autonomous : CommandGroup() {

    init {
        Drivetrain.resetEncoders()
        val gameData: String = DriverStation.getInstance().gameSpecificMessage
        if (gameData.isNotEmpty()) {
            if (gameData[0] == 'R') {
                Drivetrain.resetEncoders()
                addSequential(AutoDrive(.3,0.0, 40.0))
                addSequential(EjectSwitch())

                //Move arm and dump
            }
            else end()
        }
    }
}