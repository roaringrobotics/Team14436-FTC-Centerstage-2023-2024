
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;


@TeleOp

public class  Palo extends LinearOpMode {
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor blueLift;
    DcMotor inTake;
    Servo blueBar;
    Servo blackBar;
    Servo intakeFlip;

    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;
    double denominator;
    double rotX;
    double rotY;

    double botHeading;
    double y;
    double x;
    double rx;

    double slow;

static slide slide=Palo.slide.collapsed;

    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
        DcMotor frontLeft = hardwareMap.dcMotor.get("frontLeft");
        DcMotor backLeft = hardwareMap.dcMotor.get("backLeft");
        DcMotor frontRight = hardwareMap.dcMotor.get("frontRight");
        DcMotor backRight = hardwareMap.dcMotor.get("backRight");
        DcMotor inTake = hardwareMap.dcMotor.get("inTake");
        DcMotor blueLift = hardwareMap.dcMotor.get("blueLift");
        Servo intakeFlip = hardwareMap.servo.get("intakeFlip");
        Servo blackBar = hardwareMap.servo.get("blackBar");
        Servo blueBar = hardwareMap.servo.get("blackBar");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        IMU imu = hardwareMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        // CHange Motors
        Blueflip.setPosition(0.9);
        Blackflip.setPosition(0.9);
        OutFlip.setPosition(1);
        Flap.setPosition(0);
        MecanumDriveTrain.slide = MecanumDriveTrain.slide.collapsed;

        ArrayList<Boolean> booleanArray = new ArrayList<>();

        int booleanIncrementer = 0;

        boolean y2Pressed;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;
            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;

            if (gamepad1.options) {
                imu.resetYaw();
            }

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);


            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            rotX = rotX * 1.1;
            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            frontLeft.setPower(frontLeftPower);
            backLeft.setPower(backLeftPower);
            frontRight.setPower(frontRightPower);
            backRight.setPower(backRightPower);

            if (gamepad1.right_trigger>0.5){
            inTake.setPower(1);
            } else if (gamepad2.right_trigger<=0.5){
              inTake.setPower(0);
             }

            if (y2Pressed){
            switch (slide){
                case extended:
                    Blackflip.setPosition(0);
                    Blueflip.setPosition(0);
                    OutFlip.setPosition(0.1);
                    slide=MecanumDriveTrain.slide.tip;
                    break;
                case collapsed:
                    Blackflip.setPosition(0.9);
                    Blueflip.setPosition(0.9);
                    OutFlip.setPosition(1);
                    slide=MecanumDriveTrain.slide.tip;
                    break;
                case tip:
                    Blackflip.setPosition(0);
                    Blueflip.setPosition(0);
                    OutFlip.setPosition(0.5);
                    slide=MecanumDriveTrain.slide.collapsed;
                    break;

            }


            if (gamepad2.a){
                Flap.setPosition(1);
            } else {
                Flap.setPosition(0);
            }

            if(gamepad2.dpad_down){
                Bluelift.setPower(1);
                BlackLift.setPower(1);
            } else if (gamepad2.dpad_up) {
                Bluelift.setPower(-1);
                BlackLift.setPower(-1);
            } else {
                Bluelift.setPower(0);
                BlackLift.setPower(0);
            }

     y2Pressed = ifPressed(gamepad2.y);
    ALWAYS have boolean Incrementer as last.
    booleanIncrementer = 0;
    }
             //LIST:[true]

            private boolean ifPressed(boolean button) {
                boolean output = false;
                if (booleanArray.size() == booleanIncrementer) {
                    booleanArray.add(false);
                }
                boolean buttonWas = booleanArray.get(booleanIncrementer);
                //noinspection PointlessBooleanExpression
                if (button != buttonWas && button == true) {
                    output = true;
                }
                booleanArray.set(booleanIncrementer, button);
                booleanIncrementer = booleanIncrementer + 1;

                return output;
            }
            private enum slide{
                extended,collapsed,tip
            }
        }