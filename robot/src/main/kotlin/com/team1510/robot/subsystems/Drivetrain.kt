package com.team1510.robot.subsystems

import com.team2898.engine.logic.*
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.team1510.robot.config.*
import com.team2898.engine.motion.DriveSignal
import com.team2898.engine.motion.TalonWrapper

object Drivetrain : Subsystem(50.0, "Drivetrain") {

    val leftMaster = TalonWrapper(LEFT_MASTER_CANID)
    val rightMaster = TalonWrapper(RIGHT_MASTER_CANID)
    val leftSlave = TalonWrapper(LEFT_SLAVE_CANID)
    val rightSlave = TalonWrapper(RIGHT_SLAVE_CANID)
    val masterList = listOf(leftMaster, rightMaster)

    init {
        leftSlave slaveTo leftMaster
        rightSlave slaveTo rightMaster
        masters {
            enableVoltageCompensation(true)
            configVoltageCompSaturation(12.0, 0)

            setMagEncoder()

            configContinuousCurrentLimit(CONT_MAX_AMPS, 0)
            configPeakCurrentLimit(PEAK_MAX_AMPS, 0)
            configPeakCurrentDuration(PEAK_MAX_AMPS_DUR_MS, 0)
            enableCurrentLimit(true)

            configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0)
            configVelocityMeasurementWindow(32, 0)

            setPID(Kp, Ki, Kd, Kf)
        }
    }

    fun setVelocityControl()
    {
        masters {setVelocityControl()}
    }

    fun updateDrive(input: DriveSignal)
    {
        leftMaster.set(input.left)
        rightMaster.set(-input.right)

       // println("$input")
    }

    val leftEncPostion
        get() = leftMaster.sensorCollection.quadraturePosition.toDouble() * ENC_TO_IN

    val rightEncPosition
        get() = rightMaster.sensorCollection.quadraturePosition.toDouble() * ENC_TO_IN

    val leftEncVelocity
        get() = leftMaster.sensorCollection.quadratureVelocity.toDouble() * ENC_TO_IN

    val rightEncVelocity
        get() = rightMaster.sensorCollection.quadratureVelocity.toDouble() * ENC_TO_IN

    fun resetEncoders()
    {
        leftMaster.sensorCollection.setQuadraturePosition(0, 10)
        rightMaster.sensorCollection.setQuadraturePosition(0, 10)
    }


    fun masters(block: TalonWrapper.() -> Unit) {
        masterList.forEach { srx ->
            srx.block()
        }
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
