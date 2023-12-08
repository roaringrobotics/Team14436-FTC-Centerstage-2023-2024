package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.apache.commons.math3.analysis.function.Power;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;

@TeleOp
public class MecanumDriveTrain extends LinearOpMode {
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor Bluelift;
    DcMotor blueIntake;
    DcMotor BlackLift;
    Servo Flap;
    Servo Blueflip;
    Servo Blackflip;
    Servo OutFlip;

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

    ArrayList<Boolean> booleanArray = new ArrayList<>();
    int booleanIncrementer = 0;
    boolean y2Pressed;

    @Override
    public void runOpMode() throws InterruptedException {
        blueIntake = hardwareMap.get(DcMotor.class, "blueIntake");
        Bluelift = hardwareMap.get(DcMotor.class, "Bluelift");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        BlackLift = hardwareMap.get(DcMotor.class, "Blacklift");
        OutFlip = hardwareMap.get(Servo.class,"OutFlip");
        Blackflip = hardwareMap.get(Servo.class,"Blackflip");
        Blueflip = hardwareMap.get(Servo.class,"Blueflip");
        Flap = hardwareMap.get(Servo.class,"Flap");

        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        Bluelift.setDirection(DcMotorSimple.Direction.REVERSE);
        Blackflip.setDirection(Servo.Direction.REVERSE);

        blueIntake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Bluelift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        BlackLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        IMU imu = hardwareMap.get(IMU.class, "imu");

        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));

        imu.initialize(parameters);

        Blueflip.setPosition(1);
        Blackflip.setPosition(1);
        OutFlip.setPosition(1);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            drive(gamepad1.left_stick_y,-gamepad1.left_stick_x,gamepad1.right_stick_x,
                imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS));

            if(gamepad1.right_bumper){

            }

            if (gamepad2.a){
                blueIntake.setPower(1);
            } else {
                blueIntake.setPower(0);
            }

            if (y2Pressed){
              Blueflip.setPosition(0);
              Blackflip.setPosition(0);
            } else {
                Blueflip.setPosition(1);
                Blackflip.setPosition(1);
            }

            if(gamepad2.dpad_down){
                Bluelift.setPower(-1);
                BlackLift.setPower(-1);
            } else if (gamepad2.dpad_up) {
                Bluelift.setPower(1);
                BlackLift.setPower(1);
            } else {
                Bluelift.setPower(0);
                BlackLift.setPower(0);
            }

            y2Pressed = ifPressed(gamepad2.y);
           //ALWAYS have boolean Incrementer as last.
            booleanIncrementer = 0;
        }
    }

    private void drive(double x, double y, double rx, double botHeading){

        if (gamepad1.left_bumper) {
            slow = 0.75;
        } else {
            slow = 0;
        }

        rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
        rotX = rotX * 1.1;

        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        frontLeftPower = (rotY + rotX + rx) / denominator;
        frontRightPower = (rotY - rotX - rx) / denominator;
        backLeftPower = (rotY - rotX + rx) / denominator;
        backRightPower = (rotY + rotX - rx) / denominator;


        frontLeft.setPower(frontLeftPower * slow);
        backLeft.setPower(backLeftPower * slow);
        frontRight.setPower(frontRightPower * slow);
        backRight.setPower(backRightPower * slow);

    }

    // LIST: [true, ]

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
}