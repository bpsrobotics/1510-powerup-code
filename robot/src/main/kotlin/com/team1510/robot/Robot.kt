package com.team1510.robot

import com.team1510.robot.commands.Autonomous
import com.team1510.robot.commands.PIDTeleop
import com.team2898.engine.logic.LoopManager
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import com.team1510.robot.subsystems.DrivePIDTest
import com.team1510.robot.commands.Teleop
//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Intake
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.networktables.NetworkTable as nt

class Robot : IterativeRobot() {

    val autoCommand = Autonomous()

    val teleopCommand = Teleop()
    override fun robotInit() {
        Intake.intakeRetract()
        //Arm.updatePower(0.0)
        CameraServer.getInstance().startAutomaticCapture(0)
        CameraServer.getInstance().startAutomaticCapture(1)
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