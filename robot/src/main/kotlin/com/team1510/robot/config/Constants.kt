package com.team1510.robot.config

//Intake:
const val intakeSpeed: Double = 1.0;
const val outtakeSpeed: Double = -1.0;

//Units

const val ENC_TO_IN = 18.850 / 4096 //Multiply native units by circumference of wheel (18.850) and divide by units per rotation (4096)
const val IN_TO_ENC:Double = 4096 / 18.850
//Drive:
const val Kp = 0.0
const val Ki = 0.0
const val Kd = 0.0
const val Kf = 0.3148 //.3148 1510 bot

const val timeoutMs = 0

const val CONT_MAX_AMPS = 30
const val PEAK_MAX_AMPS = 50
const val PEAK_MAX_AMPS_DUR_MS = 500
