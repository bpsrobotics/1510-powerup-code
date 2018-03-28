package com.team1510.robot


import com.team1510.robot.commands.*
import com.team1510.robot.config.*
import com.team1510.robot.subsystems.Arm
import com.team2898.engine.logic.LoopManager
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
import com.team1510.robot.subsystems.DrivePIDTest
import com.team1510.robot.subsystems.Intake
//import com.team1510.robot.subsystems.Arm
import edu.wpi.first.wpilibj.CameraServer
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.command.Command
import edu.wpi.first.wpilibj.smartdashboard.*
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser
import edu.wpi.first.wpilibj.networktables.NetworkTable as nt

class Robot : IterativeRobot() {

    var autoCommand: Command = CrossLine()

    val teleopCommand = Teleop()

    val autoChooser: SendableChooser<AutoMode> = SendableChooser()

    override fun robotInit() {
        
        Intake.intakeRetract()
        //Arm.updatePower(0.0)
        CameraServer.getInstance().startAutomaticCapture(1)
        CameraServer.getInstance().startAutomaticCapture(0)

        autoChooser.addDefault("CrossLine", AutoMode.CROSS_LINE)
        autoChooser.addObject("Center Switch", AutoMode.CENTER_SWITCH)
        autoChooser.addObject("Right Switch", AutoMode.RIGHT_SWITCH)
        autoChooser.addObject("Left Switch", AutoMode.LEFT_SWITCH)

        SmartDashboard.putData("Auto Chooser", autoChooser)
    }

    override fun autonomousInit() {

        if (teleopCommand.isRunning) teleopCommand.cancel()
        autoCommand = AutoManager(autoChooser.selected)
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


