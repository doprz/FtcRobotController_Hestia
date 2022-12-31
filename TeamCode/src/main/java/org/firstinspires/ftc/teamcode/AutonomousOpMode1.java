/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Hardware;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

//Added Autonomous code here (I thought it was supposed the mirror Francis's code)
@Autonomous(name = "Autonomous OpMode1", group = "Linear Opmode")
//@Disabled
public class AutonomousOpMode1 extends LinearOpMode {

    //========================================
    // DECLARE OPMODE MEMBERS
    //========================================
    //NiceDay
    // Misc
    private final ElapsedTime runtime = new ElapsedTime();

    // Servos
    //We created a servo object an a variable to hold its position
    //Servo clawServo;
    //double clawServo_position = 0.0;

   // Servo clawServoHand1;
    //double clawServoHand1_position = 0.0;

    //Servo clawServo2;
    //double clawServo2_position = 0.0;

   // Servo clawServoHand2;
   // double clawServoHand2_position;

    // Motors
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        /* Hardware Mapping
         */

        // We have the hardware mapped to the servo object to the actual servo

        //clawServo = hardwareMap.servo.get("clawServo");
       // clawServoHand1 = hardwareMap.servo.get("ClawServoHand");
       // clawServo2 = hardwareMap.servo.get("ClawServo2");
       // clawServoHand2 = hardwareMap.servo.get("clawServoHand2");

        // Reset the servo's position to 0 degrees

        //clawServo.setPosition(clawServo_position);
        //clawServoHand1.setPosition(clawServoHand1_position);
        //clawServo2.setPosition(clawServo2_position);
       // clawServoHand2.setPosition(clawServoHand2_position);

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
            double power = 1.0;
            double rewop = 1.0;

        frontLeftDrive = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery

        frontLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(rewop);

        sleep(2000);
        power = 0.0;
        rewop = 0.0;
        frontRightDrive.setPower(rewop);
        frontLeftDrive.setPower(power);
        sleep(1500);

        power = 0.5;
        rewop = 0.5;
        frontRightDrive.setPower(rewop);
        backLeftDrive.setPower(power);

        power = 0.0;
        rewop = 0.0;

        frontLeftDrive.setPower(power);
        frontRightDrive.setPower(rewop);
        backLeftDrive.setPower(rewop);
        backRightDrive.setPower(power);
        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            start();



            //double leftPower:
            //double rightPower;
            // Since this is an autonomous Op Mode, you can't control the robot with gamepads
            /*
            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.
            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double drive = -gamepad1.left_stick_y;
            double turn = gamepad1.right_stick_x;
            leftPower = Range.clip(drive + turn, -1.0, 1.0);
            rightPower = Range.clip(drive - turn, -1.0, 1.0);
            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;
            // Send calculated power to wheels
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            // A button to break instantly
            if (gamepad1.right_stick_button == true) {
                leftDrive.setPower(0);
                rightDrive.setPower(0);
            }
            */

            /*
             * Creating a variable to switch between profiles to control different servos
             */

            /*
             * The System.out.println() commands will only print on the computer's console and not
             * on the driver control station. To achieve this you will need to use telemetry
             * */

            //int DriverProfile = 1;


            /*if (gamepad1.right_bumper && DriverProfile == 1) {
                DriverProfile = DriverProfile + 1;
                System.out.println("We are currently on Driver Profile #" + DriverProfile);
            } else if (gamepad1.left_bumper && DriverProfile == 2) {
                DriverProfile = DriverProfile - 1;
                System.out.println("We are currently on Driver Profile #" + DriverProfile);
            }
            /*
             * Servo STUFF
             * */

            /*if (DriverProfile == 1) {
                if (gamepad1.x) {
                    clawServo.setPosition(1);
                } else if (gamepad1.y) {
                    clawServo.setPosition(0.0);
                }
                if (gamepad1.a) {
                    clawServoHand1.setPosition(1);
                } else if (gamepad1.b) {
                    clawServoHand1.setPosition(0.0);
                }
            }
            if (DriverProfile == 2) {
                if (gamepad1.x) {
                    clawServo2.setPosition(1);
                } else if (gamepad1.y) {
                    clawServo2.setPosition(0.0);
                }
                if (gamepad1.a) {
                    clawServoHand2.setPosition(1);
                } else if (gamepad1.b) {
                    clawServoHand2.setPosition(0.0);
                }
            }
            // Both of them return and receive a value from 0.0 - 1.0
            // Remember, this is autonomous so we can't use gamepads
            //clawServo.setPosition(gamepad1.right_trigger);
            /*
             * Telemetry
             * */

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            //telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();
        }
    }
}
