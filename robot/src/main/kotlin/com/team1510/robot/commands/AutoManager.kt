package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.config.AutoMode
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.CommandGroup
import edu.wpi.first.wpilibj.command.WaitCommand
import edu.wpi.first.wpilibj.DriverStation



class AutoManager(val auto: AutoMode) : CommandGroup() {

    init {
        val currentGameData: String = DriverStation.getInstance().gameSpecificMessage
        when(auto)
        {
            AutoMode.CROSS_LINE -> addSequential(CrossLine())
            AutoMode.CENTER_SWITCH -> addSequential(CenterSwitch(currentGameData))
            AutoMode.RIGHT_SWITCH -> addSequential(RightSwitch(currentGameData))
            AutoMode.LEFT_SWITCH -> addSequential(LeftSwitch(currentGameData))

        }
    }
}