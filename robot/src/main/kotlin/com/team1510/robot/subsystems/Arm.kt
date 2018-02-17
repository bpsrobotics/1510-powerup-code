package com.team1510.robot.subsystems

import com.team2898.engine.logic.*
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.team1510.robot.config.*
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.kinematics.Rotation2d
import com.team2898.engine.motion.DriveSignal
import com.team2898.engine.motion.TalonWrapper
import com.team2898.engine.motion.VelocityRamp

object Arm : Subsystem(50.0, "Arm") {


    val masterArm = TalonWrapper(LEFT_ARM_MOTOR_CANID)
    val slaveArm = TalonWrapper(RIGHT_ARM_MOTOR_CANID)

    val speedRamp = VelocityRamp(accelRate = 2.0, deaccelRate = 2.0)

    var targetPos = Rotation2d(1.0, 0.0)
        set(value) {
            masterArm.setPositionControl(
                    poseToEncoderUnits(
                            targetPos
                    )
            )
        }

    init {

        slaveArm slaveTo masterArm

       // masterArm.setPositionControl()

        masterArm.setMagEncoder()

        masterArm.configContinuousCurrentLimit(CONT_MAX_AMPS, 0)
        masterArm.configPeakCurrentLimit(PEAK_MAX_AMPS, 0)
        masterArm.configPeakCurrentDuration(PEAK_MAX_AMPS_DUR_MS, 0)
        masterArm.enableCurrentLimit(true)

        masterArm.setPID(0.0, 0.0, 0.0, 0.0)
        masterArm.sensorCollection.setQuadraturePosition(0, 0)
    }

    /*Enter a degree so the arm can turn to
    fun moveToPos(angle:Double) {
        targetPos = Rotation2d.createFromDegrees(angle)
    }*/

    fun updatePower(input: Double) {
        masterArm.set(input)
    }

    fun poseToEncoderUnits(pose: Rotation2d): Double {

        return pose.radians / (2 * Math.PI)  * 4096 //Convert to native encoder units
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