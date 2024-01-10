
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

public class Palo extends LinearOpMode {
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

    ArrayList<Boolean> booleanArray = new ArrayList<>();
    int booleanIncrementer = 0;
    boolean y2Pressed;

    Slide slide = Slide.tip;

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

        // CHange servos.setPosition(0.9);
        blueBar.setPosition(0.9);
        blackBar.setPosition(1);
        intakeFlip.setPosition(0);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            if (isStopRequested()) return;

            drive(-gamepad1.left_stick_y,gamepad1.left_stick_x, gamepad1.right_stick_x, imu);

            if (gamepad1.options) {
                imu.resetYaw();
            }

            if (gamepad1.right_trigger > 0.5) {
                inTake.setPower(1);
            } else if (gamepad2.right_trigger >= 0.5) {
                inTake.setPower(0);
            }

            //Inside this statement, change the servos/motors to the correct ones
            //It is not correct due to i cant properly see the robot
            if (y2Pressed) {
                switch (slide) {
                    case extended:
                        blackBar.setPosition(0);
                        blueBar.setPosition(0);
                        intakeFlip.setPosition(0.1);
                        slide = Slide.tip;
                        break;
                    case collapsed:
                        blackBar.setPosition(0.9);
                        blueBar.setPosition(0.9);
                        intakeFlip.setPosition(1);
                        slide = Slide.tip;
                        break;
                    case tip:
                        blackBar.setPosition(0);
                        blueBar.setPosition(0);
                        intakeFlip.setPosition(0.5);
                        slide = Slide.collapsed;
                        break;
                }
            }

            if (gamepad2.a) {
                intakeFlip.setPosition(1);
            } else {
                intakeFlip.setPosition(0);
            }

            if (gamepad2.dpad_down) {
                blueLift.setPower(1);
                blueLift.setPower(1);
            } else if (gamepad2.dpad_up) {
                blueLift.setPower(-1);
                blueLift.setPower(-1);
            } else {
                blueLift.setPower(0);
                blueLift.setPower(0);
            }

            y2Pressed = ifPressed(gamepad2.y);
            //ALWAYS have boolean Incrementer as last.
            booleanIncrementer = 0;
        }
    }

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

    private enum Slide {
        collapsed,
        tip,
        extended,
    }

    private void drive(double y, double x, double rx, IMU imu) {

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
    }
}