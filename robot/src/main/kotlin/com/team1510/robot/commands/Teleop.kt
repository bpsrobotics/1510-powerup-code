package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper
import com.ctre.phoenix.motorcontrol.ControlMode
import com.team1510.robot.subsystems.Intake


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

        if(OI.intake) Intake.intake()

        if(OI.outtake) Intake.outtake()

        if(OI.intakeExtend) Intake.intakePosition(true, true)

        if(OI.intakeRetract) Intake.intakePosition(true, false)
        
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