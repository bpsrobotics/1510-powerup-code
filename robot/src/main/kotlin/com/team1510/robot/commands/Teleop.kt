package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper
import com.ctre.phoenix.motorcontrol.ControlMode
//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Intake


class Teleop : Command() {

    override fun initialize() {
        Drivetrain.resetEncoders()
        //Drivetrain.setVelocityControl()
        AsyncLooper(1.0) {
            //println("Position ${Drivetrain.leftEncPostion} , ${Drivetrain.rightEncPosition}")
            //println("Velocity ${Drivetrain.leftEncVelocity} , ${Drivetrain.rightEncVelocity}")
            //println("Error ${Drivetrain.rightMaster.getClosedLoopError(0)}")
            println("Arm input ${OI.manipRightY}")
            //if(OI.intake) println("Intaking")
            //if(OI.outtake) println("Releasing")

        }.start()
    }

    override fun execute() {
        CheesyDrive.updateQuickTurn(OI.quickTurn)
        Drivetrain.updatePIDDrive(
                CheesyDrive.updateCheesy(
                        (if (!OI.quickTurn) OI.turn else -OI.leftTrigger + OI.rightTrigger),
                        -OI.throttle,
                        OI.quickTurn,
                        true
                )
        )


        Intake.updateIntake(OI.intake, OI.outtake)
        //println("${OI.intake}, ${OI.outtake}")

        if(OI.manipA) Intake.intakeExtend()

        if(OI.manipB) Intake.intakeRetract()

        //if(OI.manipX) Arm.moveToPos(180.0)

        //if(OI.manipY) Arm.moveToPos(0.0)

        //Arm.updatePower(OI.manipRightY)

        
        //val targetVelocity = OI.throttle * 4096 * 500.0 / 600
        /* 1500 RPM in either direction */
        //Drivetrain.rightMaster.set(ControlMode.Velocity, targetVelocity)

    }


    override fun isFinished(): Boolean {
        return false
    }

}