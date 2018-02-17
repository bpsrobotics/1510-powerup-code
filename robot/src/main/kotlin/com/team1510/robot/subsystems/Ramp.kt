package com.team1510.robot.subsystems
/*
import com.team2898.engine.logic.*
import main.kotlin.com.team1510.robot.config.*
import edu.wpi.first.wpilibj.DoubleSolenoid
import main.kotlin.com.team1510.robot.subsystems.Ramp.leftRamp
import main.kotlin.com.team1510.robot.subsystems.Ramp.rightRamp

object Ramp : Subsystem(50.0, "Ramp"){

    val rightRamp = DoubleSolenoid(RIGHT_RAMP_SOL_FWD_ID, RIGHT_RAMP_SOL_REV_ID)
    val leftRamp = DoubleSolenoid(LEFT_RAMP_SOL_FWD_ID, LEFT_RAMP_SOL_REV_ID)

    val rightRampLock = DoubleSolenoid(RIGHT_LOCK_SOL_FWD_ID, RIGHT_LOCK_SOL_REV_ID)
    val leftRampLock = DoubleSolenoid(LEFT_LOCK_SOL_FWD_ID, LEFT_LOCK_SOL_REV_ID)



    fun extendRight() {
      rightRamp.set(DoubleSolenoid.Value.kForward)
    }

    fun extendLeft() {
        leftRamp.set(DoubleSolenoid.Value.kForward)
    }
    fun extendBoth(){
        rightRamp.set(DoubleSolenoid.Value.kForward)
        leftRamp.set(DoubleSolenoid.Value.kForward)
    }
    fun retract(){
        rightRamp.set(DoubleSolenoid.Value.kReverse)
        leftRamp.set(DoubleSolenoid.Value.kReverse)
    }

    fun lockRight() {
        rightRampLock.set(DoubleSolenoid.Value.kForward)
    }
    fun lockLeft(){
        leftRampLock.set(DoubleSolenoid.Value.kForward)
    }

    fun lockBoth(){
        rightRampLock.set(DoubleSolenoid.Value.kForward)
        leftRampLock.set(DoubleSolenoid.Value.kForward)
    }

    fun releaseLock(){
        rightRampLock.set(DoubleSolenoid.Value.kReverse)
        leftRampLock.set(DoubleSolenoid.Value.kReverse)
    }
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

    override val enableTimes: List<GamePeriods>
        get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.

}*/