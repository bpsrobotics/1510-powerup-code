package com.team1510.robot.commands

import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import edu.wpi.first.wpilibj.command.Command

class Autonomous : Command() {

    override fun initialize() {

    }

    override fun execute() {
        //If robot is in the middle
        if(Drivetrain.gameInfo.length >0) {
            if(Drivetrain.gameInfo.get(0) == 'L') {
                Drivetrain.moveDistance(100.0, 100.0)
                Drivetrain.moveDistance(-50.0, 50.0)
                Drivetrain.moveDistance(-50.0, 50.0)
            }
        }
    }

    override fun isFinished(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}