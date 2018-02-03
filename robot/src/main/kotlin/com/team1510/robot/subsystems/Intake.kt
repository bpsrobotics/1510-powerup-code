package com.team1510.robot.subsystems


import com.team2898.engine.logic.*
import com.team1510.robot.config.*
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.Talon

object Intake: Subsystem(50.0, "Intake"){

    // Two Solenoids to extend
    //Two Talon SRs to rotate motors
    //Both sides controlled independently and together

    val rightIntakeSolenoid = DoubleSolenoid(RIGHT_INTAKE_SOL_FWD_ID, RIGHT_INTAKE_SOL_REV_ID)
    val leftIntakeSolenoid = DoubleSolenoid(LEFT_INTAKE_SOL_FWD_ID, LEFT_INTAKE_SOL_FWD_ID)

    val rightIntakeTalon = Talon(RIGHT_INTAKE_PWM_ID)
    val leftIntakeTalon = Talon(LEFT_INTAKE_PWM_ID)

    fun intake(){
        rightIntakeTalon.set(intakeSpeed)
        leftIntakeTalon.set(intakeSpeed)
    }

    fun outtake(){
        rightIntakeTalon.set(outtakeSpeed)
        leftIntakeTalon.set(outtakeSpeed)
    }

    fun intakePosition(right: Boolean , left: Boolean)
    {
        if(rightIntakeSolenoid.get() != booleanToSolValue(right)) {
            rightIntakeSolenoid.set(booleanToSolValue(right))
        }
        if(leftIntakeSolenoid.get() != booleanToSolValue(left)) {
            leftIntakeSolenoid.set(booleanToSolValue(left))
        }
    }

    fun booleanToSolValue(input: Boolean) : DoubleSolenoid.Value
    {
        if(input) return DoubleSolenoid.Value.kForward
        else return DoubleSolenoid.Value.kReverse

    }

    /*Backup code:

    fun rightIntakeExtend() {
        rightIntakeSolenoid.set(DoubleSolenoid.Value.kForward)
    }

    fun leftIntakeExtend() {
        leftIntakeSolenoid.set(DoubleSolenoid.Value.kForward)
    }

    fun bothIntakeExtend() {
        rightIntakeSolenoid.set(DoubleSolenoid.Value.kForward)
        leftIntakeSolenoid.set(DoubleSolenoid.Value.kForward)
    }

    fun bothIntakeRetract(){
        rightIntakeSolenoid.set(DoubleSolenoid.Value.kReverse)
        leftIntakeSolenoid.set(DoubleSolenoid.Value.kReverse)
    }
    */


    override fun onStart() {}

    override fun onLoop() {}

    override fun onStop() {}

    override fun selfCheckup(): Boolean {
        return false
    }

    override fun selfTest(): Boolean {
        return false
    }

    override val enableTimes = listOf(GamePeriods.TELEOP, GamePeriods.AUTO)

}