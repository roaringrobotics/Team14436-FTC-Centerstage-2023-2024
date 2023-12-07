package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;


/* Here is the simplified version of your code in the way that I would write it. Now I don't want you
    to copy it without thinking, but rather try to understand why I wrote the code in the way that I do.
    This removes all of the lines of code that were useless and only made the robot slower.
    This program also features two important principles of Java programming: Encapsulation and abstraction
    from the "motor" method.
    */
public class MecanumDriveTrainEXTIM extends LinearOpMode {
    /* I decided to combine all of the drive motors, lift motors, and intake motor together into
        their own statement, as it is both clean and easy to read.
        Notice I also used DcMotorEx and not DcMotor.
        This is so you can use more advanced features such as getting velocities and such
        While it is not needed, I think by having the methods available to you will improve your understanding
        of what you can do with motors.
     */
    DcMotorEx backLeft, frontLeft, frontRight, backRight;
    DcMotorEx Bluelift, BlackLift;
    DcMotorEx blueIntake;

    @Override
    public void runOpMode() throws InterruptedException {
        blueIntake = hardwareMap.get(DcMotorEx.class, "blueIntake");
        Bluelift = hardwareMap.get(DcMotorEx.class, "Bluelift");
        backRight = hardwareMap.get(DcMotorEx.class, "backRight");
        backLeft = hardwareMap.get(DcMotorEx.class, "backLeft");
        frontLeft = hardwareMap.get(DcMotorEx.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotorEx.class, "frontRight");
        BlackLift = hardwareMap.get(DcMotorEx.class, "Blacklift");

        // I reversed the Bluelift so motor powers are the same.
        // This is so messing up negative values is harder and
        // by extension not mess up the lift.
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        Bluelift.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive() && !isStopRequested()) {

            /* This single line of code physically does the same thing as your 19 lines of code.
                This method allows the driver to actually drive the robot.
                
                Just as I have stated in around line 80, the parameters x, y, and rx are then put in as
                the stick inputs. Think of the x, y, and rx as abstract variables in math and you are now
                putting values to those variables.
            */
            motor(gamepad1.left_stick_x, -gamepad1.left_stick_y, gamepad1.right_stick_x);

            if (gamepad2.a){
                blueIntake.setPower(1);
            } else {
                blueIntake.setPower(0);
            }

            // This uses the if, if else, else statement
            // This is so only one of these statements will set the power depending on what you press
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
        }
    }

    /* This is a method. Methods are used to basically contain your code into different sections.
        This is important for troubleshooting; if one method breaks then you know what section it is in.
        Moreover, it makes your code just look so much nicer and cool. Plus, you can call this whenever
        you need it, rather than rewrite everything again.

        This method, motor, has the parameters x, y, and rx. We use these parameters to calculate
        the motor powers.

        While this is robot-centric, your code was already written in a robot-centric way soooo.
        Plus, this is way faster, as you only call the powers once per cycle, rather than call them,
        do math that doesn't help the robot, and call the same powers again and then assign their power
        to the motors.
     */

    private void motor(double x, double y, double rx) {
        frontLeft.setPower(y + x + rx);
        backLeft.setPower(y - x + rx);
        frontRight.setPower(y - x - rx);
        backRight.setPower(y + x - rx);
    }
}