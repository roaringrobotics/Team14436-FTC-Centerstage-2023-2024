package org.firstinspires.ftc.teamcode;

import android.bluetooth.BluetoothDevice;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.ImuOrientationOnRobot;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;

@TeleOp
public class FieldCentricDriveTest_IMU extends LinearOpMode {

    DcMotorEx frontLeft, frontRight, backRight, backLeft, blueIntake; //Bluelift;
    IMU imu;

    @Override
    public void runOpMode() throws InterruptedException {
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        //Bluelift = hardwareMap.get(DcMotorEx.class, " Bluelift");
        //blueIntake = hardwareMap.get(DcMotorEx.class, " blueIntake");
        imu = hardwareMap.get(IMU.class, "imu");



        waitForStart();

        while(opModeIsActive() && !isStopRequested()) {
            telemetry.addData("Degree Internal: ", imu.getRobotOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES));
            telemetry.addData("Degree External: ", imu.getRobotOrientation(AxesReference.EXTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES));
            telemetry.update();
        }

    }
}
