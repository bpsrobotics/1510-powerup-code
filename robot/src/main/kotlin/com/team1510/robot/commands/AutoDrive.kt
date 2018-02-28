package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.OI
import com.team1510.robot.config.*
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.motion.CheesyDrive
import com.team2898.engine.motion.DriveSignal
import edu.wpi.first.wpilibj.command.Command

open class AutoDrive(val throttle: Double = 0.0, val turn : Double = 0.0, val distance: Double = 0.0) : Command() {

    var reqDistance = distance * IN_TO_ENC
    override fun initialize() {

        //reqDistance = distance * IN_TO_ENC
        Drivetrain.resetEncoders()
    }

    override fun execute() {
        println("Driving ${((Math.abs(Drivetrain.leftEncPostion) + Math.abs(Drivetrain.rightEncPosition)) / 2.0 > reqDistance)}")

        Drivetrain.updatePIDDrive(
                    CheesyDrive.updateCheesy(
                            turn,
                            throttle,
                            false,
                            true
                    )
        )
    }

    override fun isFinished(): Boolean {
        return ((Math.abs(Drivetrain.leftEncPostion) + Math.abs(Drivetrain.rightEncPosition)) / 2.0 > reqDistance)
    }

    override fun end() {
        Drivetrain.updatePIDDrive(DriveSignal(0.0, 0.0))
        Drivetrain.resetEncoders()
    }


}