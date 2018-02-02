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

        val talonWrapper = slaveArm slaveTo masterArm

        masterArm.setPositionControl()

        masterArm.setMagEncoder()

        masterArm.configContinuousCurrentLimit(CONT_MAX_AMPS, 0)
        masterArm.configPeakCurrentLimit(PEAK_MAX_AMPS, 0)
        masterArm.configPeakCurrentDuration(PEAK_MAX_AMPS_DUR_MS, 0)
        masterArm.enableCurrentLimit(true)

        masterArm.configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0)
        masterArm.configVelocityMeasurementWindow(32, 0)

        masterArm.setPID(0, 0, 0, 0)
    }

    fun moveToPos(degrees:Int): Int {
        var a = (masterArm.sensorCollection.quadraturePosition * 360 ) / 4096
        if (a < degrees) {
            //degree decrease to a
        } else {
            //degree increase to a
        }
        return degrees
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