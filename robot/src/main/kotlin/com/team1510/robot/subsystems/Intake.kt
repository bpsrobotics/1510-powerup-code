package main.kotlin.com.team1510.robot.subsystems

import com.team1510.robot.config.intakeSpeed
import com.team1510.robot.config.outtakeSpeed
import com.team2898.engine.logic.*
import main.kotlin.com.team1510.robot.config.*
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


    override val enableTimes: List<GamePeriods>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

    override fun onStart() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onLoop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStop() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selfCheckup(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun selfTest(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}