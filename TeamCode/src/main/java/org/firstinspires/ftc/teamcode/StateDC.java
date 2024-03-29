
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.Servo;

 import org.apache.commons.math3.analysis.function.Power;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

import java.util.ArrayList;


@TeleOp

public class StateDC extends LinearOpMode {
    int lowerLimit = 30;
    int upperLimit = 1500;
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor blackHook;
    DcMotor blueHook;
    DcMotor blueLift;
    DcMotor inTake;
    Servo blueBar;
    Servo blackBar;
    Servo outtakeFlip;
    Servo blueFlap;
    Servo blackFlap;
    Servo blackIntake;
    Servo blueIntake;
    Servo Liftservo;


    double frontLeftPower;
    double frontRightPower;
    double backLeftPower;
    double backRightPower;
    double blueHookPower;
    double blackHookPower;
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
    boolean x2Pressed;
    boolean a2Pressed;
    boolean y2Pressed;

    static Score score = Score.collapsed;
     static DropoffServos dropoffServos = DropoffServos.Closed;
    Intake intake = Intake.In;



    @Override
    public void runOpMode() throws InterruptedException {
        // Declare our motors
        // Make sure your ID's match your configuration
         frontLeft = hardwareMap.dcMotor.get("frontLeft");
         backLeft = hardwareMap.dcMotor.get("backLeft");
         frontRight = hardwareMap.dcMotor.get("frontRight");
         backRight = hardwareMap.dcMotor.get("backRight");
         inTake = hardwareMap.dcMotor.get("inTake");
         blueLift = hardwareMap.dcMotor.get("blueLift");
         outtakeFlip = hardwareMap.servo.get("outtakeFlip");
         blackBar = hardwareMap.servo.get("blackBar");
         blueBar = hardwareMap.servo.get("blackBar");
         blueHook = hardwareMap.dcMotor.get("blueHook");
         blackHook = hardwareMap.dcMotor.get("blackHook");
         blueFlap = hardwareMap.servo.get("blueFlap");
         blackFlap = hardwareMap.servo.get("blackFlap");
         blueIntake = hardwareMap.servo.get("blueIntake");
         blackIntake = hardwareMap.servo.get("blackIntake");
         Liftservo = hardwareMap.servo.get("Liftservo");

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
        outtakeFlip.setPosition(0.95);
        Liftservo.setPosition(0.18);
        blackIntake.setPosition(0.45);
        blueIntake.setPosition(0.55);
        blackBar.setPosition(0.67);
        blueBar.setPosition(0.33);
        blueFlap.setPosition(0.55);
        blackFlap.setPosition(0);

        StateDC.dropoffServos = DropoffServos.Closed;
        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            if (isStopRequested()) return;

            drive(-gamepad1.left_stick_y,gamepad1.left_stick_x, gamepad1.right_stick_x, imu);

            if (gamepad1.options) {
                imu.resetYaw();
            }

            if (gamepad2.left_trigger > 0.5) {
                inTake.setPower(1);
            } else if (gamepad2.right_trigger >= 0.1) {
                inTake.setPower(-1);
            } else {
                inTake.setPower(0);
            }

            if (gamepad2.dpad_right) {// Out
                blackBar.setPosition(0);
                blueBar.setPosition(1);
                blackIntake.setPosition(0.75);
                blueIntake.setPosition(0.25);
            } else if (gamepad2.dpad_left) { //In
                blackIntake.setPosition(0.45);
                blueIntake.setPosition(0.55);
                blackBar.setPosition(0.7);
                blueBar.setPosition(0.3);
            }
            if (gamepad2.x){
                blueFlap.setPosition(0);
                blackFlap.setPosition(0.55);
            } else {
                blackFlap.setPosition(0);
                blueFlap.setPosition(0.55);
            }

            if (gamepad2.dpad_up){
                blueLift.setPower(1);
            } else if (gamepad2.dpad_down) {
                blueLift.setPower(-1);
            } else {
                blueLift.setPower(0);
            }
            if (gamepad2.y) {//out
                Liftservo.setPosition(0.8);
            }else {
                Liftservo.setPosition(0.18);
            }
            if (gamepad2.b){
                outtakeFlip.setPosition(0.5);
            } else {
                outtakeFlip.setPosition(0.95);
            }
            if (gamepad2.right_bumper){
                blueHook.setPower(1);
                blackHook.setPower(1);
            } else if (gamepad2.left_bumper) {
                blueHook.setPower(-1);
                blackHook.setPower(-1);
            } else {
                blueHook.setPower(0);
                blackHook.setPower(0);
            }


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


    private enum DropoffServos{
        Closed,Open
    }
    private enum Score {
        collapsed,
        tip,
        extended,
    }
    private enum Intake{
        In,Out,
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