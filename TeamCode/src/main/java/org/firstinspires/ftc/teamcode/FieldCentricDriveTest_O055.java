package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
@TeleOp
public class FieldCentricDriveTest_O055 extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backRight, backLeft, blueIntake, Bluelift;
    BNO055IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        Bluelift = hardwareMap.get(DcMotorEx.class, " Bluelift");
        blueIntake = hardwareMap.get(DcMotorEx.class, " blueIntake");
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        imu.initialize(parameters);



        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Degree (First): ", imu.getAngularOrientation().firstAngle);
            telemetry.addData("Degree (Second): ", imu.getAngularOrientation().secondAngle);
            telemetry.addData("Degree (Third): ", imu.getAngularOrientation().thirdAngle);
            telemetry.addData("Parameters: ", imu.getParameters());
            telemetry.update();
        }

    }
}
