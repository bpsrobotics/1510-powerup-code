package com.team1510.robot.commands

import com.team2898.engine.motion.CheesyDrive
import edu.wpi.first.wpilibj.command.Command
import com.team1510.robot.OI
import com.team2898.engine.async.AsyncLooper
import com.ctre.phoenix.motorcontrol.ControlMode
import com.team1510.robot.subsystems.*
import com.team2898.engine.kinematics.Rotation2d
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.DriverStation

class Teleop : Command() {

    var positionControlSet = false
    /** save the target position to servo to  */
    var targetPositionRotations: Double = 0.toDouble()

    override fun initialize() {
        Drivetrain.resetEncoders()
        Intake.intakeRetract()
        Ramp.lockRamps()
        Ramp.retractRamps()
        //Arm.masterArm.setPositionControl()
        //Drivetrain.setVelocityControl()
//        AsyncLooper(1.0) {
//            //println("Position ${Drivetrain.leftEncPostion} , ${Drivetrain.rightEncPosition}")
//            //println("Velocity ${Drivetrain.leftEncVelocity} , ${Drivetrain.rightEncVelocity}")
//            //println("Error ${Drivetrain.rightMaster.getClosedLoopError(0)}")
//            //println("X:  ${OI.manipX} Y: ${OI.manipY}")
//            println("Arm Position: ${Arm.masterArm.sensorCollection.quadraturePosition.toDouble()}")
//            //if(OI.intake) println("Intaking")
//            //if(OI.outtake) println("Releasing")
//        }.start()
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


        Intake.updateRightIntake(OI.manipRightTrigger, OI.manipRightBumper)
        Intake.updateLeftIntake(OI.manipLeftTrigger, OI.manipLeftBumper)

        //println("${OI.intake}, ${OI.outtake}")

        updatePositionControlSettings()

        if(OI.manipA) Intake.intakeExtend()

        if(OI.manipB) Intake.intakeRetract()

        ArmPID.masterArm.set(ControlMode.PercentOutput, -OI.manipLeftY)

//            println("velocity")

//        if (positionControlSet) {
//            /* Position mode - button just pressed */
//
//            /* 10 Rotations * 4096 u/rev in either direction */
//            ArmPID.setCurrentPosition()
//            println("position")
//            // targetPositionRotations = ArmPID.masterArm.sensorCollection.pulseWidthPosition.toDouble() //OI.manipRightY * 4096 * 2//4096//10.0 * 4096;
//               // ArmPID.masterArm.set(ControlMode.Position, targetPositionRotations)
//
//        }
//        /* on button2 just straight drive */
//        if (!positionControlSet) {
//            /* Percent voltage mode */
//            ArmPID.masterArm.set(ControlMode.PercentOutput, -OI.manipLeftY)
//            println("velocity")
//        }

        /* save button state for on press detect */
        //lastButtonX = OI.manipX

        if(OI.manipBack && OI.manipStart) Ramp.releaseLock()

        if(Ramp.isLockReleased && OI.doubleJoystickClick) Ramp.deployRamps()

    }

    fun updatePositionControlSettings(){

        if(OI.manipX) positionControlSet = true
        if(OI.manipY) positionControlSet = false

    }
    override fun isFinished(): Boolean {
        return false
    }

}