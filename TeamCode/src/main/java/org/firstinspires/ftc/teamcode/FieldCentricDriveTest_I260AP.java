package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BHI260IMU;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
@TeleOp
public class FieldCentricDriveTest_I260AP extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backRight, backLeft, blueIntake, Bluelift;
    BHI260IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        Bluelift = hardwareMap.get(DcMotorEx.class, " Bluelift");
        blueIntake = hardwareMap.get(DcMotorEx.class, " blueIntake");
        imu = hardwareMap.get(BHI260IMU.class, "imu");
        

        
        waitForStart();

        while(opModeIsActive() && !opModeIsActive()) {
            telemetry.addData("Degree: ", imu.getRobotAngularVelocity(AngleUnit.DEGREES));
            telemetry.addData("Parameters: ", imu.getParameters());
            telemetry.update();
        }

    }
}
