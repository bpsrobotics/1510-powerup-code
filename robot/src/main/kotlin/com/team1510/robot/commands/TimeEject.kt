package com.team1510.robot.commands


import com.team1510.robot.subsystems.ArmPID
import com.team1510.robot.OI
import com.team1510.robot.config.*
import com.team1510.robot.subsystems.Drivetrain
import com.team1510.robot.subsystems.Intake
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.motion.CheesyDrive
import com.team2898.engine.motion.DriveSignal
import edu.wpi.first.wpilibj.command.Command

open class TimeEject(val timeout: Double) : Command(timeout) {


    override fun initialize() {
    //reqDistance = distance * IN_TO_ENC
    }

    override fun execute() {
        Intake.leftIntakeTalon.set(.50)
        Intake.rightIntakeTalon.set(.50)
    }


    override fun end() {
        Intake.stop()
    }

    override fun isFinished(): Boolean {
        return isTimedOut()
    }
}

