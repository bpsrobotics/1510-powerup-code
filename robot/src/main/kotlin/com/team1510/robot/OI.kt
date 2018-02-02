package com.team1510.robot

import edu.wpi.first.wpilibj.Joystick

object OI {

     fun deadzone(input: Double): Double{
        if(Math.abs(input) < 0.15) return 0.0
        return input
    }

    fun square(input: Double): Double = Math.pow(input, 2.0) * if (input > 0) 1 else -1
    fun convertVelocity (input: Double): Double {
        return input * 4096 * 500.0 / 600
    }
    val driverController: Joystick = Joystick(0)
    val manipController: Joystick = Joystick(1)

    val intakeExtend: Boolean
        get() = manipController.getRawAxis(2) != 0.0

    val throttle
        get() = deadzone(driverController.getRawAxis(1))
        //up and down on the left joystick

    val turn
        get() = deadzone(driverController.getRawAxis(4))
        //left and right on the left

    val quickTurn: Boolean
        get() = (Math.max(driverController.getRawAxis(2) , driverController.getRawAxis(3))) != 0.0

    val leftTrigger
        get() = driverController.getRawAxis(2)

    val rightTrigger
        get() = driverController.getRawAxis(3)

    val leftBumper
        get() = driverController.getRawButton(5)

    val rightBumper
        get() = driverController.getRawButton(6)

    val aButton
        get() = driverController.getRawButton(1)

    val bButton
        get() = driverController.getRawButton(2)

    val xButton
        get() = driverController.getRawButton(3)

    val yButton
        get() = driverController.getRawButton(4)

    val deployRamp
        get() = driverController.getRawButton(7)

}