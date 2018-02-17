package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team1510.robot.subsystems.Drivetrain
import com.team2898.engine.async.AsyncLooper
import com.ctre.phoenix.motorcontrol.ControlMode
import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Intake
import com.team1510.robot.subsystems.Ramp
import com.team2898.engine.kinematics.Rotation2d
import edu.wpi.first.wpilibj.DriverStation

class Teleop : Command() {

    override fun initialize() {
        Drivetrain.resetEncoders()
        //Drivetrain.setVelocityControl()
        AsyncLooper(1.0) {
            //println("Position ${Drivetrain.leftEncPostion} , ${Drivetrain.rightEncPosition}")
            //println("Velocity ${Drivetrain.leftEncVelocity} , ${Drivetrain.rightEncVelocity}")
            //println("Error ${Drivetrain.rightMaster.getClosedLoopError(0)}")
            println("X:  ${OI.manipX} Y: ${OI.manipY}")
            //if(OI.intake) println("Intaking")
            //if(OI.outtake) println("Releasing")
        }.start()
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


        Intake.updateRightIntake(OI.manipRightTrigger, OI.manipRightBumper)
        Intake.updateLeftIntake(OI.manipLeftTrigger, OI.manipLeftBumper)

        //println("${OI.intake}, ${OI.outtake}")

        if(OI.manipA) Intake.intakeExtend()

        if(OI.manipB) Intake.intakeRetract()

        if(OI.manipX) Ramp.releaseLeftLock()

        if(OI.manipY) Ramp.extendLeft()

        //Arm.updatePower(OI.manipRightY)

        Arm.targetPos = Rotation2d( OI.manipRightY , Math.sqrt(1 - (Math.pow(OI.manipRightY, 2.0))))

        /*if(OI.manipDeployRamp) {
            Ramp.releaseLock()
            if(OI.manipDeployRamp) {
                Ramp.extendBoth()
            }
        }*/


        //val targetVelocity = OI.throttle * 4096 * 500.0 / 600
        /* 1500 RPM in either direction */
        //Drivetrain.rightMaster.set(ControlMode.Velocity, targetVelocity)

    }


    override fun isFinished(): Boolean {
        return false
    }

}