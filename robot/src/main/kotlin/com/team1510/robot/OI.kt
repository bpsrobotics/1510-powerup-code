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

    //Initiate controllers
    val driverController: Joystick = Joystick(0)
    val manipController: Joystick = Joystick(1)


    //Begin driver controls
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
        get() = manipController.getRawButton(5)

    val rightBumper
        get() = manipController.getRawButton(6)

    val aButton
        get() = manipController.getRawButton(1)

    val bButton
        get() = manipController.getRawButton(2)

    val xButton
        get() = manipController.getRawButton(3)

    val yButton
        get() = manipController.getRawButton(4)

    val driverDeployRamp
        get() = driverController.getRawButton(7)
        
    //Begin operator controls
    val manipRightTrigger :Boolean
        get() = deadzone(manipController.getRawAxis(2)) > 0

    val manipLeftTrigger:Boolean
        get() = deadzone(manipController.getRawAxis(3)) > 0

    val manipRightBumper : Boolean
        get() = manipController.getRawButton(5)

    val manipLeftBumper : Boolean
        get() = manipController.getRawButton(6)

    val manipA
        get() = manipController.getRawButton(1)
        //a button
    val manipB
        get() = manipController.getRawButton(2)
        //b button
    val manipRightY
            get() = deadzone(manipController.getRawAxis(5))/4
            //up and down on the left joystick
    val manipDeployRamp
        get() = manipController.getRawButton(7)

}