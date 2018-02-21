package com.team1510.robot.commands

import com.team1510.robot.subsystems.Arm
import edu.wpi.first.wpilibj.command.Command

class Autonomous : Command() {

    override fun initialize() {
        Arm.resetEncoders()
    }

    override fun execute() {
        Arm.moveTo(.5)
    }
    override fun isFinished(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}