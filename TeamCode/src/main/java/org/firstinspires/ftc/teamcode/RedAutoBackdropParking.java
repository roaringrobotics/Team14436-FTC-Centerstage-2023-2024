package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class RedAutoBackdropParking extends LinearOpMode {
    //Hardware and variables
    DcMotor fL;
    // Yo tim does it work
    DcMotor fR;
    DcMotor bL;
    DcMotor bR;

    Servo OF;
    Servo Blueflip;
    Servo Blackflip;



    @Override
    public void runOpMode() throws InterruptedException {
        //All movement happen
        fL = hardwareMap.dcMotor.get("frontLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bL = hardwareMap.dcMotor.get("backLeft");
        bR = hardwareMap.dcMotor.get("backRight");
        OF = hardwareMap.servo.get("OutFlip");
        Blackflip = hardwareMap.servo.get("Blackflip");
        Blueflip = hardwareMap.servo.get("Blueflip");

        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bL.setDirection(DcMotorSimple.Direction.REVERSE);
        fL.setDirection(DcMotorSimple.Direction.REVERSE);


        ElapsedTime run = new ElapsedTime();


        waitForStart();

        // Resets the timer upon starting
        run.reset();

        // While loop that moves to the left
        // run.time should equal seconds
        while (run.milliseconds() <575 && !isStopRequested()) {
            fL.setPower(-1);
            fR.setPower(-1);
            bL.setPower(-1);
            bR.setPower(-1);
        }

        run.reset();


        // Wait till outtake action
         while (run.milliseconds()<350 && !isStopRequested()){
            fL.setPower(0);
            fR.setPower(0);
            bL.setPower(0);
            bR.setPower(0);
        }
        run.reset();

         Blackflip.setPosition(0);
         Blueflip.setPosition(0);
         OF.setPosition(0.1);


        while (run.milliseconds()<350 && !isStopRequested()){
            OF.setPosition(0.5);
        }
run.reset();

       while (run.milliseconds()<150 && !isStopRequested()) {
           fL.setPower(-1);
           fR.setPower(-1);
           bL.setPower(-1);
           bR.setPower(-1);
       }
        run.reset();

        fL.setPower(0);
        fR.setPower(0);
        bL.setPower(0);
        bR.setPower(0);
    }




}


