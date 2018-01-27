package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper

class Teleop : Command() {

    override fun initialize() {

    }

    override fun execute() {
        CheesyDrive.updateQuickTurn(OI.quickTurn)
        Drivetrain.updateDrive(
                CheesyDrive.updateCheesy(
                        (if (!OI.quickTurn) OI.turn else -OI.leftTrigger + OI.rightTrigger),
                        -OI.throttle,
                        OI.quickTurn,
                        true
                )
        )
        AsyncLooper(1.0) {
            println("throttle ${OI.throttle}")
        }.start()
    }
    override fun isFinished(): Boolean {
        return false
    }

}