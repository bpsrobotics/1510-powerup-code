package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.OI
import com.team1510.robot.config.*
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.motion.CheesyDrive
import com.team2898.engine.motion.DriveSignal
import edu.wpi.first.wpilibj.command.Command

class AutoDrive(val throttle: Double = 0.0, val distance: Double = 0.0) : Command() {

    var reqDistance = distance * IN_TO_ENC
    var isComplete = false

    override fun initialize() {

        //reqDistance = distance * IN_TO_ENC
        Drivetrain.resetEncoders()
        println("Required distance: ${reqDistance}")
        AsyncLooper(10.0) {
            println((Math.abs(Drivetrain.leftEncPostion) + Math.abs(Drivetrain.rightEncPosition)) / 2.0)

        }.start()
    }

    override fun execute() {

        if((Math.abs(Drivetrain.leftEncPostion) + Math.abs(Drivetrain.rightEncPosition)) / 2.0 > reqDistance)
        {
            isComplete = true
        }
        else {
            Drivetrain.updatePIDDrive(
                    CheesyDrive.updateCheesy(
                            0.0,
                            throttle,
                            false,
                            true
                    )
            )
        }
    }

    override fun isFinished(): Boolean {
        return isComplete
    }

    override fun end() {
        Drivetrain.updatePIDDrive(DriveSignal(0.0, 0.0))
        //Drivetrain.resetEncoders()
    }


}