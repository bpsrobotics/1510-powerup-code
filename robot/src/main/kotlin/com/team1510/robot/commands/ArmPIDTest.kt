package com.team1510.robot.commands

/**
 * Example demonstrating the Position closed-loop servo.
 * Tested with Logitech F350 USB Gamepad inserted into Driver Station]
 *
 * Be sure to select the correct feedback sensor using configSelectedFeedbackSensor() below.
 *
 * After deploying/debugging this to your RIO, first use the left Y-stick
 * to throttle the Talon manually.  This will confirm your hardware setup.
 * Be sure to confirm that when the Talon is driving forward (green) the
 * position sensor is moving in a positive direction.  If this is not the cause
 * flip the boolean input to the setSensorPhase() call below.
 *
 * Once you've ensured your feedback device is in-phase with the motor,
 * use the button shortcuts to servo to target position.
 *
 * Tweak the PID gains accordingly.
 */

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import com.team1510.robot.subsystems.ArmPID
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command
import com.ctre.phoenix.motorcontrol.can.TalonSRX
import com.team1510.robot.OI
import edu.wpi.first.networktables.NetworkTable
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard


class ArmPIDTest : Command() {

    var _loops = 0
    var _lastButton1 = false
    /** save the target position to servo to  */
    var targetPositionRotations: Double = 0.toDouble()
    val _sb = StringBuilder()

    override fun initialize() {
        //ArmPID.setCenter()
    }

    override fun execute() {

            /* get gamepad axis */
            var motorOutput = ArmPID.masterArm.motorOutputPercent
            /* deadband gamepad */

            /* prepare line to print */
            _sb.append("\tout:")
            /* cast to int to remove decimal places */
            _sb.append((motorOutput * 100).toInt())
            _sb.append("%") /* perc */

            _sb.append("\tpos:")
            _sb.append(ArmPID.masterArm.getSelectedSensorPosition(0))
            _sb.append("u") /* units */

            _sb.append("\tabs:")
            _sb.append(ArmPID.masterArm.sensorCollection.pulseWidthPosition)
            _sb.append("u")


            SmartDashboard.putNumber("Encoder Val", ArmPID.masterArm.getSelectedSensorPosition(0).toDouble())
            /* on button1 press enter closed-loop mode on target position */
            /*
            ArmPID.masterArm.set(ControlMode.PercentOutput), OI.m
             */
            if (!_lastButton1 && OI.manipA) {
                /* Position mode - button just pressed */

                /* 10 Rotations * 4096 u/rev in either direction */
                targetPositionRotations = ArmPID.masterArm.sensorCollection.pulseWidthPosition.toDouble() //OI.manipRightY * 4096 * 2//4096//10.0 * 4096;
                ArmPID.masterArm.set(ControlMode.Position, targetPositionRotations)

            }
            /* on button2 just straight drive */
            if (OI.manipB) {
                /* Percent voltage mode */
                 ArmPID.masterArm.set(ControlMode.PercentOutput, -OI.manipRightY)
            }
            /* if Talon is in position closed-loop, print some more info */
            if (ArmPID.masterArm.controlMode == ControlMode.Position) {
                /* append more signals to print when in speed mode. */
                _sb.append("\terr:");
                _sb.append(ArmPID.masterArm.getClosedLoopError(0))
                _sb.append("u"); /* units */

                _sb.append("\ttrg:");
                _sb.append(targetPositionRotations)
                _sb.append("u"); /* units */
            }
            /*
             * print every ten loops, printing too much too fast is generally bad
             * for performance
             */
            if (++_loops >= 10) {
                _loops = 0
                System.out.println(_sb.toString())
            }
            _sb.setLength(0)
            /* save button state for on press detect */
            _lastButton1 = OI.manipA

    }
    override fun isFinished(): Boolean {
        return false
    }


}