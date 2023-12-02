package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class MecanumDriveTrain extends LinearOpMode{
    DcMotor backLeftMotor;
    DcMotor frontLeftMotor;
    DcMotor frontRightMotor;
    DcMotor backRightMotor;
    DcMotor Bluelift;
    DcMotor blueIntake;


@Override
 public void runOpMode() throws InterruptedException{
blueIntake = hardwareMap.get(DcMotor.class,"blueIntake");
Bluelift = hardwareMap.get(DcMotor.class,"Bluelift");
backRightMotor = hardwareMap.get(DcMotor.class,"backRightMotor");
backLeftMotor= hardwareMap.get(DcMotor.class,"backLeftMotor");
frontLeftMotor= hardwareMap.get(DcMotor.class,"frontLeftMotor");
frontRightMotor= hardwareMap.get(DcMotor.class,"frontRightMotor");

frontRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);
backRightMotor.setDirection(DcMotorSimple.Direction.REVERSE);

    IMU imu = hardwareMap.get(IMU.class,"imu");

    IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.LEFT,
            RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD));
    imu.initialize(parameters);


waitForStart();
while (opModeIsActive()){
    double y = -gamepad1.left_stick_y;
    double x = gamepad1.left_stick_x;
    double rx = gamepad1.right_stick_x;

    double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

    double rotx = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
    double roty = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
    rotx = rotx * 1.1;

    double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

    double frontLeftMotorPower = (rotY + rotX + rx) / denominator;
    double frontRightMotorPower = (rotY - rotX - rx) / denominator;
    double backLeftMotorPower = (rotY - rotX + rx) / denominator;
    double backRightMotorPower = (rotY + rotX - rx) / denominator;

    y = -gamepad1.left_stick_y;
    x = gamepad1.left_stick_x;
    rx = gamepad2.right_stick_x;

    frontLeftMotor.setPower(y +);

}

