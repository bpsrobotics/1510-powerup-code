package main.kotlin.com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import com.team2898.engine.motion.DriveSignal
import edu.wpi.first.wpilibj.command.Command
import main.kotlin.com.team1510.robot.OI
import main.kotlin.com.team1510.robot.subsystems.Drivetrain

class Teleop : Command() {


    override fun initialize() {

    }

    override fun execute() {

        Drivetrain.updateDrive(
                CheesyDrive.updateCheesy(
                        (if (!OI.quickTurn) OI.turn else -OI.leftTrigger + OI.rightTrigger),
                        -OI.throttle,
                        OI.quickTurn,
                        true
                ).times(4096)
        )
    }
    override fun isFinished(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}