package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class yes extends LinearOpMode {
    //Hardware and variables
    DcMotor testMotor;
// Yo tim does it work
    DcMotor testMotor1;


    @Override
    public void runOpMode() throws InterruptedException {
        //All movement happen
        testMotor = hardwareMap.dcMotor.get("testMotor");
        testMotor1 = hardwareMap.dcMotor.get("testMotor1");

        testMotor1.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        testMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ElapsedTime run = new ElapsedTime();

        waitForStart();
        while (run.time() < 200) {
            testMotor1.setPower(1);

        }
        while (run.time() < 550) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
            // Score action 1
        }
        while (run.time() < 550) {
            testMotor1.setPower(-1);
            testMotor.setPower(-1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(1);
        }
        while (run.time() < 255) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(1);
        }
        while (run.time() < 120) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        // Intake of pixels (2).1
        while (run.time() < 120) {
            testMotor.setPower(-1);
            testMotor1.setPower(-1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(-1);
        }
        while (run.time() < 255) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(-1);
        }

        while (run.time() < 550) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
            // Score action 2
        }
        while (run.time() < 550) {
            testMotor1.setPower(-1);
            testMotor.setPower(-1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(1);
        }
        while (run.time() < 255) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(1);
        }
        while (run.time() < 120) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        // Intake of pixels (2).2  //*
        while (run.time() < 120) {
            testMotor.setPower(-1);
            testMotor1.setPower(-1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(-1);
        }

        while (run.time() < 200) {
            testMotor1.setPower(1);

        }
        while (run.time() < 550) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
            // Score action 1
        }
        while (run.time() < 550) {
            testMotor1.setPower(-1);
            testMotor.setPower(-1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(1);
        }
        while (run.time() < 255) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(1);
        }
        while (run.time() < 120) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
        // Intake of pixels (2).1  //*
        while (run.time() < 120) {
            testMotor.setPower(-1);
            testMotor1.setPower(-1);
        }
        while (run.time() < 200) {
            testMotor1.setPower(-1);
        }
        while (run.time() < 255) {
            testMotor.setPower(1);
            testMotor1.setPower(1);
        }
    }

}
