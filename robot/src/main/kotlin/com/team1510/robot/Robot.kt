package com.team1510.robot

import com.team1510.robot.commands.Autonomous
import com.team1510.robot.commands.PIDTeleop
import com.team2898.engine.logic.LoopManager
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
//import com.team1510.robot.commands.Teleop
import com.team1510.robot.subsystems.DrivePIDTest
import edu.wpi.first.wpilibj.networktables.NetworkTable as nt

class Robot : IterativeRobot() {

    val autoCommand = Autonomous()

    val teleopCommand = PIDTeleop()
    override fun robotInit() {

    }

    override fun autonomousInit() {

        if (teleopCommand.isRunning) teleopCommand.cancel()
        autoCommand.start()

    }

    override fun autonomousPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun teleopInit() {
        println("teleop started")
        if (autoCommand.isRunning) autoCommand.cancel()
        teleopCommand.start()
    }

    override fun teleopPeriodic() {
        Scheduler.getInstance().run()
    }

    override fun disabledInit() {
        LoopManager.onDisable()
    }
}