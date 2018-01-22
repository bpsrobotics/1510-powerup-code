package main.kotlin.com.team1510.robot

import com.ctre.phoenix.drive.IDrivetrain
import com.team1510.robot.commands.Autonomous
import edu.wpi.first.wpilibj.networktables.NetworkTable as nt
import edu.wpi.first.wpilibj.command.Scheduler
import com.team1510.robot.subsystems.*
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.logic.LoopManager
import edu.wpi.first.networktables.NetworkTableInstance
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.DriverStation
import edu.wpi.first.wpilibj.IterativeRobot
import edu.wpi.first.wpilibj.hal.FRCNetComm
import edu.wpi.first.wpilibj.hal.HAL
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard
import main.kotlin.com.team1510.robot.commands.Teleop

class Robot : IterativeRobot() {

    val autoCommand = Autonomous()

    val teleopCommand = Teleop()
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