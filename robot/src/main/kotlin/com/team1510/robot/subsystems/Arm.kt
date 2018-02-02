package com.team1510.robot.subsystems

import com.team2898.engine.logic.*
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.team1510.robot.config.*
import com.team2898.engine.motion.DriveSignal
import com.team2898.engine.motion.TalonWrapper

object Arm : Subsystem(50.0, "Arm") {


    val masterArm = TalonWrapper(LEFT_ARM_MOTOR_ID)
    val slaveArm = TalonWrapper(RIGHT_ARM_MOTOR_ID)

    init {

        slaveArm slaveTo masterArm

        masterArm.enableVoltageCompensation(true)
        masterArm.configVoltageCompSaturation(12.0, 0)

        masterArm.setMagEncoder()

        masterArm.configContinuousCurrentLimit(CONT_MAX_AMPS, 0)
        masterArm.configPeakCurrentLimit(PEAK_MAX_AMPS, 0)
        masterArm.configPeakCurrentDuration(PEAK_MAX_AMPS_DUR_MS, 0)
        masterArm.enableCurrentLimit(true)

        masterArm.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0)
        masterArm.configVelocityMeasurementWindow(32, 0)

        masterArm.setPID(Kp, Ki, Kd, Kf)
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