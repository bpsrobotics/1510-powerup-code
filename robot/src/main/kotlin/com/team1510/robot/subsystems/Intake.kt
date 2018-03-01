package com.team1510.robot.subsystems


import com.team2898.engine.logic.*
import com.team1510.robot.config.*
import edu.wpi.first.wpilibj.DoubleSolenoid
import edu.wpi.first.wpilibj.PWMTalonSRX
import edu.wpi.first.wpilibj.Talon


object Intake: Subsystem(50.0, "Intake"){

    // Two Solenoids to extend
    //Two Talon SRs to rotate motors
    //Both sides controlled independently and together

    val intakeSolenoid = DoubleSolenoid(INTAKE_SOL_FWD_ID, INTAKE_SOL_REV_ID)

    val rightIntakeTalon = Talon(RIGHT_INTAKE_PWM_ID)
    val leftIntakeTalon = Talon(LEFT_INTAKE_PWM_ID)


    fun updateRightIntake(intake: Boolean = false, outtake: Boolean = false){
        if((!intake && ! outtake) || (intake && outtake)) {
            rightIntakeTalon.set(0.0)
        }
        if(intake) {
            rightIntakeTalon.set(1.0)
        }
        if(outtake){
            rightIntakeTalon.set(-1.0)
        }
        //println("Right ${rightIntakeTalon.get()}, Left ${leftIntakeTalon.get()}")
    }

    fun updateLeftIntake(intake: Boolean = false, outtake: Boolean = false){
        if((!intake && ! outtake) || (intake && outtake)) {
            leftIntakeTalon.set(0.0)
        }
        if(intake) {
            leftIntakeTalon.set(0.75)
        }
        if(outtake){
            leftIntakeTalon.set(-0.75)
        }
        //println("Right ${rightIntakeTalon.get()}, Left ${leftIntakeTalon.get()}")
    }

    /*fun intake(){
        rightIntakeTalon.set(.5)
        leftIntakeTalon.set(.5)
    }

    fun outtake(){
        rightIntakeTalon.set(-.5)
        leftIntakeTalon.set(-.5)
    }*/

    fun stop(){
        rightIntakeTalon.set(0.0)
        leftIntakeTalon.set(0.0)
    }

    /*fun intakePosition(right: Boolean , left: Boolean)
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

    }*/


    fun intakeExtend() {
        intakeSolenoid.set(DoubleSolenoid.Value.kForward)
    }

    fun intakeRetract() {
        intakeSolenoid.set(DoubleSolenoid.Value.kReverse)
    }

    /*
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