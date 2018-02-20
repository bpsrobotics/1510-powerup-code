package com.team1510.robot.subsystems

import com.team2898.engine.logic.*
import com.team1510.robot.config.*
import edu.wpi.first.wpilibj.DoubleSolenoid

object Ramp : Subsystem(50.0, "Ramp"){

    val rampLocks = DoubleSolenoid(RAMP_LOCK_SOL_FWD_ID, RAMP_LOCK_SOL_REV_ID)
    val ramps = DoubleSolenoid(RAMP_DEPLOY_SOL_FWD_ID, RAMP_DEPLOY_SOL_REV_ID)

    //val rightRampLock = DoubleSolenoid(RIGHT_LOCK_SOL_FWD_ID, RIGHT_LOCK_SOL_REV_ID)
    //val leftRampLock = DoubleSolenoid(LEFT_LOCK_SOL_FWD_ID, LEFT_LOCK_SOL_REV_ID)

    fun releaseLock() {
        rampLocks.set(DoubleSolenoid.Value.kReverse)
    }

    fun lockRamps() {
        rampLocks.set(DoubleSolenoid.Value.kForward)
    }

    fun deployRamps() {
        ramps.set(DoubleSolenoid.Value.kForward)
    }

    fun retractRamps() {
        ramps.set(DoubleSolenoid.Value.kReverse)
    }
    /*fun releaseRightLock() {
        rightRamp.set(DoubleSolenoid.Value.kReverse)
    }

    fun releaseLeftLock() {
        leftRamp.set(DoubleSolenoid.Value.kReverse)
    }

    fun extendRight() {
        rightRamp.set(DoubleSolenoid.Value.kForward)
    }

    fun extendLeft() {
        leftRamp.set(DoubleSolenoid.Value.kForward)
    }
    */

    /*

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

    }*/

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

