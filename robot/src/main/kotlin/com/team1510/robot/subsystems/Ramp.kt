package com.team1510.robot.subsystems

import com.team2898.engine.logic.*
import com.team1510.robot.config.*
import edu.wpi.first.wpilibj.DoubleSolenoid

object Ramp : Subsystem(50.0, "Ramp"){

    val rampLocks = DoubleSolenoid(RAMP_LOCK_SOL_FWD_ID, RAMP_LOCK_SOL_REV_ID)
    val rightRamp = DoubleSolenoid(RIGHT_RAMP_SOL_FWD_ID, RIGHT_RAMP_SOL_REV_ID)
    val leftRamp = DoubleSolenoid(LEFT_RAMP_SOL_FWD_ID, LEFT_RAMP_SOL_REV_ID)

    val isLockReleased
        get() = rampLocks.get() == DoubleSolenoid.Value.kReverse

    val isRightRampExtended
        get() = rightRamp.get() == DoubleSolenoid.Value.kForward

    val isLeftRampExtended
        get() = leftRamp.get() == DoubleSolenoid.Value.kForward

    fun releaseLock() {
        rampLocks.set(DoubleSolenoid.Value.kReverse)
    }

    fun lockRamps() {
        rampLocks.set(DoubleSolenoid.Value.kForward)
    }

    fun deployRightRamp() {
        rightRamp.set(DoubleSolenoid.Value.kForward)
    }

    fun retractRightRamp() {
        rightRamp.set(DoubleSolenoid.Value.kReverse)
    }

    fun deployLeftRamp() {
        leftRamp.set(DoubleSolenoid.Value.kForward)
    }

    fun retractLeftRamp() {
        leftRamp.set(DoubleSolenoid.Value.kReverse)
    }


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

