package com.team1510.robot

import com.team1510.robot.commands.*
import com.team2898.engine.logic.LoopManager
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import com.team1510.robot.subsystems.DrivePIDTest
//import com.team1510.robot.subsystems.Arm
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.smartdashboard.*
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.networktables.NetworkTable as nt

class Robot : IterativeRobot() {

    val autoCommand = Autonomous()

    val teleopCommand = Teleop()

    val autoChooser = SendableChooser()

    override fun robotInit() {
        //Arm.updatePower(0.0)
        CameraServer.getInstance().startAutomaticCapture(0)
        CameraServer.getInstance().startAutomaticCapture(1)

        autoChooser().addDefault("CrossLine", CrossLine())
        autoChooser().addObject("Center Switch", CenterSwitch())
        autoChooser().addObject("Right Switch", RightSwitch())
        autoChooser().addObject("Left Switch", LeftSwitch())

        SmartDashboard.putData("Auto Chooser", autoChooser())
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


