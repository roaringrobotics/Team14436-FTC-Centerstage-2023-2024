package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class RedAutoTriangleParking extends LinearOpMode {
    //Hardware and variables
    DcMotor fL;
    // Yo tim does it work
    DcMotor fR;
    DcMotor bL;
    DcMotor bR;


    @Override
    public void runOpMode() throws InterruptedException {
        //All movement happen
        fL = hardwareMap.dcMotor.get("frontLeft");
        fR = hardwareMap.dcMotor.get("frontRight");
        bL = hardwareMap.dcMotor.get("backLeft");
        bR = hardwareMap.dcMotor.get("backRight");

        fL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        ElapsedTime run = new ElapsedTime();


            waitForStart();

            while (run.startTimeNanoseconds() > 0.75) {
                fL.setPower(1);
                fR.setPower(1);
                bL.setPower(-1);
                bR.setPower(-1);
            }
                   if (run.nanoseconds() == 0) {
                       fL.setPower(0);
                       fR.setPower(0);
                       bL.setPower(0);
                       bR.setPower(0);
                   }
                   }

    }


