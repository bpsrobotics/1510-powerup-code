package com.team1510.robot.commands

//import com.team1510.robot.subsystems.Arm
import com.team1510.robot.subsystems.Drivetrain
import com.team1510.robot.subsystems.Intake
import edu.wpi.first.wpilibj.command.Command

class Autonomous : Command() {

    override fun initialize() {
        Intake.intakeRetract()
    }

    override fun execute() {
        //If robot is in the middle
        /*if(Drivetrain.gameInfo.length >0) {
            if(Drivetrain.gameInfo.get(0) == 'L') {
                Drivetrain.moveDistance(100.0, 100.0)
                Drivetrain.moveDistance(-50.0, 50.0)
                Drivetrain.moveDistance(86.75, 86.75)
                Drivetrain.moveDistance(96.0, 96.0)
                Drivetrain.moveDistance(9.0, 9.0)
                Intake.updateIntake(false, true)//first cube to switch
                Intake.intakeExtend()

            }
        }*/

        Drivetrain.moveDistance(40.0 , -40.0)
    }

    override fun isFinished(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}