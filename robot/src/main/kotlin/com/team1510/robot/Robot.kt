package com.team1510.robot

import com.team1510.robot.commands.Autonomous
import com.team1510.robot.commands.PIDTeleop
import com.team2898.engine.logic.LoopManager
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.command.Scheduler
<<<<<<< HEAD
//import com.team1510.robot.commands.Teleop
import com.team1510.robot.subsystems.DrivePIDTest
=======
import com.team1510.robot.commands.Teleop
import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Intake
import edu.wpi.first.wpilibj.CameraServer
>>>>>>> c7e50976d5009c2efebd6111573f2c642b38891c
import edu.wpi.first.wpilibj.networktables.NetworkTable as nt

class Robot : IterativeRobot() {

    val autoCommand = Autonomous()

    val teleopCommand = PIDTeleop()
    override fun robotInit() {
        Intake.intakeRetract()
        Arm.updatePower(0.0)
        CameraServer.getInstance().startAutomaticCapture();
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