package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

@TeleOp
public class MecanumDriveTrain extends LinearOpMode {
    DcMotor backLeft;
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backRight;
    DcMotor Bluelift;
    DcMotor blueIntake;
    DcMotor BlackLift;


    @Override
    public void runOpMode() throws InterruptedException {
        blueIntake = hardwareMap.get(DcMotor.class, "blueIntake");
        Bluelift = hardwareMap.get(DcMotor.class, "Bluelift");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        BlackLift = hardwareMap.get(DcMotor.class, "Blacklift");

        // TODO: Reverse the Bluelift motor so the motors goes the same way.
        //  this is so you don't mess up 1 and -1 and break the lift.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        // TODO: Add "*motorName*.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // Without brake enabled, the robot could in theory not stay in the position you want it to.

        IMU imu = hardwareMap.get(IMU.class, "imu");

        // I didn't know that these were the parameters for the IMU, so that's cool.
        // Use this from now on lmao.
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));

        imu.initialize(parameters);


        waitForStart();

        // TODO: You forgot "isStopRequested()"
        //  Is is more to make sure the code actually stops when requested
        while (opModeIsActive()) {

            // TODO: TBH, I would have put this into its own method,
            //  there should be a code called " " that goes over it.
            double y = -gamepad1.left_stick_y;
            double x = gamepad1.left_stick_x;
            double rx = gamepad1.right_stick_x;

            // This is an entirely new thing this year, this was never how you were supposed to get
            // The angles. Roadrunner is gonna be need to be changed if it doesn't update.
            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

            // TODO: Everything from here to line 73 is useless as you don't even use calculated powers
            //  to drive the thing. I would either remove it or use the powers for the motors
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
            rotX = rotX * 1.1;

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

            // Like you don't even use the values here, and you're creating a new variable everytime
            // the code loops.
            // TODO: Declare the motor powers where you declare everything else and remove double
            //  EX: in line 21 you put "double frontLeftPower" and then here you just put "frontLeftPower"
            double frontLeftPower = (rotY + rotX + rx) / denominator;
            double frontRightPower = (rotY - rotX - rx) / denominator;
            double backLeftPower = (rotY - rotX + rx) / denominator;
            double backRightPower = (rotY + rotX - rx) / denominator;

            // TODO: You repeated lines 55 - 57. There is no need to recall the statement here.
            y = -gamepad1.left_stick_y;
            x = gamepad1.left_stick_x;
            rx = gamepad1.right_stick_x;

            frontLeft.setPower(y + x + rx);
            backLeft.setPower(y - x + rx);
            frontRight.setPower(y - x - rx);
            backRight.setPower(y + x - rx);

            // I cleaned up the code a little bit, but I didn't change anything here.
            // TODO: Change gamepad1 to gamepad2. Lines 96 and all the way down.
            // This is written so only one person drives the robot and does everything on it.
            // The second player (who does the lift) cannot do anything because you didn't set it as such
            if (gamepad1.a){
                blueIntake.setPower(1);
            }
            else {
                blueIntake.setPower(0);
            }

            // TODO: Make this an if, if else, else statement. Writing it like this could break the bot!
            // Because it's not a singular statement. The robot will fight itself.
            // For example: If you hold up on d-pad, the robot lift motors will swap between 0 and 1
            // This is because you set the lift motors to 0 because dPad down isn't being pressed,
            // no matter if dPad up is pressed.

            if(gamepad1.dpad_down){
                Bluelift.setPower(1);
                BlackLift.setPower(-1);
            }
            else {
                Bluelift.setPower(0);
                BlackLift.setPower(0);
            }
            if (gamepad1.dpad_up){
                Bluelift.setPower(-1);
                BlackLift.setPower(1);
            }
            else {
                Bluelift.setPower(0);
                BlackLift.setPower(0);
            }
        }
    }
}