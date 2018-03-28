package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team1510.robot.subsystems.DrivePIDTest
import com.team2898.engine.async.AsyncLooper
import com.ctre.phoenix.motorcontrol.ControlMode



class PIDTeleop : Command() {

    override fun initialize() {

    }

    override fun execute() {
        DrivePIDTest.teleopPeriodic()
    }
    override fun isFinished(): Boolean {
        return false
    }

}