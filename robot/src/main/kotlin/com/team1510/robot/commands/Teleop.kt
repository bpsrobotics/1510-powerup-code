package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper
import com.ctre.phoenix.motorcontrol.ControlMode



class Teleop : Command() {

    override fun initialize() {
        Drivetrain.resetEncoders()
        //Drivetrain.setVelocityControl()
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
//Arm's reaction according to button pressing
    if (OI.bButton) {
        Arm.moveToPos(/*full forward angle*/)
    }
    if (OI.xButton) {
        Arm.moveToPos(/*full backward angle*/)
    }
    if (OI.yButton) {
        Arm.moveToPos(/*mid position angle*/)
    }
        //val targetVelocity = OI.throttle * 4096 * 500.0 / 600
        /* 1500 RPM in either direction */
        //Drivetrain.rightMaster.set(ControlMode.Velocity, targetVelocity)
        AsyncLooper(1.0) {
            //println("Position ${Drivetrain.leftEncPostion} , ${Drivetrain.rightEncPosition}")
            println("Velocity ${Drivetrain.leftEncVelocity} , ${Drivetrain.rightEncVelocity}")
            //println("Error ${Drivetrain.rightMaster.getClosedLoopError(0)}")

        }.start()
    }
    override fun isFinished(): Boolean {
        return false
    }

}