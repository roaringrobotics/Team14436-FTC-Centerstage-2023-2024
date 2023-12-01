package org.firstinspires.ftc.teamcode;// Import the necessary libraries
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

// Define the name of the op mode
@TeleOp(name = "Mecanum Robot", group = "Linear Opmode")

// Extend the LinearOpMode class
 public class MecanumDriveTrain extends LinearOpMode {

    // Declare the motor objects
    private DcMotor frontLeftMotor;
    private DcMotor frontRightMotor;
    private DcMotor backLeftMotor;
    private DcMotor backRightMotor;
    private static final String FRONT_LEFT_MOTOR = "frontLeftMotor";
    private static final String FRONT_RIGHT_MOTOR = "frontRightMotor";
    private static final String BACK_LEFT_MOTOR = "backLeftMotor";
    private static final String BACK_RIGHT_MOTOR = "backRightMotor";

    private static final DcMotorSimple.Direction FRONT_LEFT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction FRONT_RIGHT_DIRECTION = DcMotorSimple.Direction.REVERSE;
    private static final DcMotorSimple.Direction BACK_LEFT_DIRECTION = DcMotorSimple.Direction.FORWARD;
    private static final DcMotorSimple.Direction BACK_RIGHT_DIRECTION = DcMotorSimple.Direction.REVERSE;

    @Override
    public void runOpMode() {

        // Initialize the motors
        frontLeftMotor = hardwareMap.get(DcMotor.class, FRONT_LEFT_MOTOR);
        frontRightMotor = hardwareMap.get(DcMotor.class, FRONT_RIGHT_MOTOR);
        backLeftMotor = hardwareMap.get(DcMotor.class, BACK_LEFT_MOTOR);
        backRightMotor = hardwareMap.get(DcMotor.class, BACK_RIGHT_MOTOR);

        // Set the motor directions
        frontLeftMotor.setDirection(FRONT_LEFT_DIRECTION);
        frontRightMotor.setDirection(FRONT_RIGHT_DIRECTION);
        backLeftMotor.setDirection(BACK_LEFT_DIRECTION);
        backRightMotor.setDirection(BACK_RIGHT_DIRECTION);

        // Set the motor modes
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Wait for the start button to be pressed
        waitForStart();

        // Loop until the op mode is stopped
        while (opModeIsActive()) {

          if(gamepad1.a){

          }

            // Get the joystick values
            double x = -gamepad1.left_stick_x; // Strafe axis
            double y = -gamepad1.left_stick_y; // Forward axis
            double z = -gamepad1.right_stick_x; // Rotate axis

            // Calculate the wheel powers
            double frontLeftPower = y + x + z;
            double frontRightPower = y - x - z;
            double backLeftPower = y - x + z;
            double backRightPower = y + x - z;

            // Set the motor powers
            frontLeftMotor.setPower(frontLeftPower);
            frontRightMotor.setPower(frontRightPower);
            backLeftMotor.setPower(backLeftPower);
            backRightMotor.setPower(backRightPower);

            // Display the telemetry data
            telemetry.addData("Front Left Power", frontLeftPower);
            telemetry.addData("Front Right Power", frontRightPower);
            telemetry.addData("Back Left Power", backLeftPower);
            telemetry.addData("Back Right Power", backRightPower);
            telemetry.update();
        }
    }

    // A helper method to apply the dead zone to a joystick value
    private double applyDeadZone(double value, double deadZone) {
        if (Math.abs(value) < deadZone) {
            return 0.0;
        } else {
            return value;
        }
    }
}

