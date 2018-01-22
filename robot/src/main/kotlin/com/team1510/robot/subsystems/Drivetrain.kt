package main.kotlin.com.team1510.robot.subsystems

import com.team2898.engine.logic.*
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.team1510.robot.config.*
import com.team2898.engine.motion.DriveSignal
import com.team2898.engine.motion.TalonWrapper
import main.kotlin.com.team1510.robot.config.*

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

    fun updateDrive(input: DriveSignal)
    {
        leftMaster.set(input.left)
        rightMaster.set(input.right)
    }

    fun masters(block: TalonWrapper.() -> Unit) {
        masterList.forEach { srx ->
            srx.block()
        }
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





}
