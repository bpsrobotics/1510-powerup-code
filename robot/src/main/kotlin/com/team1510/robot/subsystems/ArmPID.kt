package com.team1510.robot.subsystems


import com.ctre.phoenix.motorcontrol.ControlMode
import com.team2898.engine.logic.*
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.team1510.robot.config.*
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.kinematics.Rotation2d
import com.team2898.engine.motion.DriveSignal
import com.team2898.engine.motion.TalonWrapper
import com.team2898.engine.motion.VelocityRamp
import sun.security.pkcs11.wrapper.Constants
import javax.naming.ldap.Control

object ArmPID : Subsystem(50.0, "ArmPID") {


    val masterArm = TalonWrapper(RIGHT_ARM_MOTOR_CANID)
    val slaveArm = TalonWrapper(LEFT_ARM_MOTOR_CANID)

    val speedRamp = VelocityRamp(accelRate = 2.0, deaccelRate = 2.0)

    var targetPos = 0.0
        set(value) {
            masterArm.setPositionControl(
                    value
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

        masterArm.setPID(0.5, 0.00, 0.0, 0.0)

        //masterArm.setBrakeMode()

        masterArm.sensorCollection.setPulseWidthPosition(0,10)

        val absolutePosition: Int = masterArm.sensorCollection.pulseWidthPosition

        /* set the quadrature (relative) sensor to match absolute */
        masterArm.sensorCollection.setQuadraturePosition(0, 10)

        //masterArm.setSelectedSensorPosition(absolutePosition, 2, 10);

    }

    fun setCurrentPosition() {
        var targetPositionRotations = ArmPID.masterArm.sensorCollection.quadraturePosition.toDouble() //OI.manipRightY * 4096 * 2//4096//10.0 * 4096;
        masterArm.set(ControlMode.Position, targetPositionRotations)
    }

    fun setCenter() {
        masterArm.set(ControlMode.Position, 0.0)
    }

    fun setFront()  {
        masterArm.set(ControlMode.Position, 1000.0)
    }

    fun setBack()   {
        masterArm.set(ControlMode.Position, -950.0)
    }

    fun setFrontSwitch()   {
        masterArm.set(ControlMode.Position, 500.0)
        println("Setting front")
    }

    fun setBackSwitch() {
        masterArm.set(ControlMode.Position, -500.0)
    }

    /*Enter a degree so the arm can turn to
    fun moveToPos(angle:Double) {
        targetPos = Rotation2d.createFromDegrees(angle)
    }*/

    fun updatePower(input: Double) {
        masterArm.set(input)
    }

    fun resetEncoders(){
        masterArm.sensorCollection.setQuadraturePosition(0, 0)
    }
    fun poseToEncoderUnits(pose: Rotation2d): Double {

        return pose.radians / (2 * Math.PI)  * 4096 //Convert to native encoder units
    }

    fun moveTo(input:Double)
    {
        Arm.masterArm.set(ControlMode.Position, input * 4096 / 2)
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