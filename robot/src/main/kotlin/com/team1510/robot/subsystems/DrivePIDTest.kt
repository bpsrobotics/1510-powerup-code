package com.team1510.robot.subsystems

import com.ctre.phoenix.motorcontrol.ControlMode
import com.team2898.engine.logic.*
import com.ctre.phoenix.motorcontrol.VelocityMeasPeriod
import com.team1510.robot.config.*
import com.team2898.engine.async.AsyncLooper
import com.team2898.engine.motion.DriveSignal
import com.team2898.engine.motion.TalonWrapper
import edu.wpi.first.wpilibj.Joystick

object DrivePIDTest : Subsystem(50.0, "DrivePIDTest") {

    val _talon = TalonWrapper(RIGHT_MASTER_CANID)
    val _talonSlave = TalonWrapper(RIGHT_SLAVE_CANID)
    val _talon2 = TalonWrapper(LEFT_MASTER_CANID)
    val _talon2Slave = TalonWrapper(LEFT_SLAVE_CANID)

    val masterList = listOf(_talon, _talon2)

    fun masters(block: TalonWrapper.() -> Unit) {
        masterList.forEach { srx ->
            srx.block()
        }
    }

    val driverController: Joystick = Joystick(0)
    var _loops = 0;
    var _sb = StringBuilder()

    init {
        //_talon.inverted = false;
        //_talonSlave.inverted = false;
        /* first choose the sensor */
        _talonSlave slaveTo _talon
        _talon2Slave slaveTo _talon2
        masters {
            setMagEncoder()
            setSensorPhase(false);
            /* set the peak, nominal outputs, and deadband */
            configContinuousCurrentLimit(CONT_MAX_AMPS, 0)
            configPeakCurrentLimit(PEAK_MAX_AMPS, 0)
            configPeakCurrentDuration(PEAK_MAX_AMPS_DUR_MS, 0)
            enableCurrentLimit(true)

            configVelocityMeasurementPeriod(VelocityMeasPeriod.Period_10Ms, 0)
            configVelocityMeasurementWindow(32, 0)

            configForwardSoftLimitEnable(false,0)
            configReverseSoftLimitEnable(false, 0)

        }

        _talon.setPID(0.0, Ki, Kd, .282)
        _talon2.setPID(0.0, Ki, Kd, .28)
        //P Left test bot: .1705 F left test bot: .28
    }
    /** This function is called periodically during operator control */
    fun teleopPeriodic()
    {
        /* get gamepad axis */
        val leftYstick = driverController.getY();
        val motorOutput = _talon.getMotorOutputPercent();
        /* prepare line to print */
        _sb.append("\tout:");
        _sb.append(motorOutput);
        _sb.append("\tspd:");
        _sb.append(_talon.getSelectedSensorVelocity(0));

        if (driverController.getRawButton(1)) {
            /* Speed mode */
            /*
            * 4096 Units/Rev * 560 RPM / 600 100ms/min in either direction:
            * velocity setpoint is in units/100ms
            */
            val targetVelocity_talon_UnitsPer100ms = -leftYstick * 4096 * 560.0 / 600;
            val targetVelocity_talon2_UnitsPer100ms = leftYstick * 4096 * 560.0 / 600;

            /* 1500 RPM in either direction */
            _talon.set(ControlMode.Velocity, targetVelocity_talon_UnitsPer100ms);
            _talon2.set(ControlMode.Velocity, targetVelocity_talon2_UnitsPer100ms);

            /* append more signals to print when in speed mode. */
            _sb.append("\terr:");
            _sb.append(_talon.getClosedLoopError(0));
            _sb.append("\ttrg:");
            _sb.append(targetVelocity_talon_UnitsPer100ms);

        } else {
            /* Percent output mode */
            _talon.set(ControlMode.PercentOutput, -leftYstick);
            _talon2.set(ControlMode.PercentOutput, leftYstick);

        }
        if (++_loops >= 10) {
            _loops = 0;
            System.out.println(_sb.toString());
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
