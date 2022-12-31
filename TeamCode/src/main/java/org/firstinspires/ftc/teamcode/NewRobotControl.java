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

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="New Robot Control", group="Linear Opmode")
//@Disabled
public class NewRobotControl extends LinearOpMode {

    //========================================
    // DECLARE OPMODE MEMBERS
    //========================================

    // Misc
    private final ElapsedTime runtime = new ElapsedTime();

    // Motors
    private DcMotor frontLeftDrive = null;
    private DcMotor frontRightDrive = null;
    private DcMotor backLeftDrive = null;
    private DcMotor backRightDrive = null;

    // We created a servo object an a variable to hold its position
   /* Servo clawServo;
    double clawServo_position = 0.0;

    Servo clawServoHand1;
    double clawServoHand1_position = 0.0;

    Servo clawServo2;
    double clawServo2_position = 0.0;

    Servo clawServoHand2;
    double clawServoHand2_position = 0.0;
*/
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        //========================================
        // HARDWARE MAPPING
        //========================================

        // We have the hardware mapped to the servo object to the actual servo
   /*     clawServo = hardwareMap.servo.get("clawServo");
        clawServoHand1 = hardwareMap.servo.get("ClawServoHand");

        clawServo2 = hardwareMap.servo.get("ClawServo2");
        clawServoHand2 = hardwareMap.servo.get("clawServoHand2");

        // Reset the servo's position to 0 degrees
        clawServo.setPosition(clawServo_position);
        clawServoHand1.setPosition(clawServoHand1_position);

        clawServo2.setPosition(clawServo2_position);
        clawServoHand2.setPosition(clawServoHand2_position);
*/
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        frontRightDrive = hardwareMap.get(DcMotor.class, "frontRightDrive");
        backLeftDrive = hardwareMap.get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");

        // Most robots need the motor on one side to be reversed to drive forward
        // Reverse the motor that runs backwards when connected directly to the battery
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double frontLeftPower;
            double frontRightPower;
            double backLeftPower;
            double backRightPower;

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            double vertical = -gamepad1.left_stick_y;
            double horizontal  =  gamepad1.left_stick_x;
            double turn = gamepad1.right_stick_x;
            frontRightPower = Range.clip(- turn + (vertical - horizontal), -1.0, 1.0);
            backRightPower = Range.clip(- turn + (vertical + horizontal), -1.0, 1.0) ;
            frontLeftPower = Range.clip(turn + (vertical + horizontal), -1.0, 1.0) ;
            backLeftPower = Range.clip(turn + (vertical - horizontal), -1.0, 1.0) ;


            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            // leftPower  = -gamepad1.left_stick_y ;
            // rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            frontLeftDrive.setPower(frontLeftPower);
            frontRightDrive.setPower(frontRightPower);
            backLeftDrive.setPower(backLeftPower);
            backRightDrive.setPower(backRightPower);

            // A button to break instantly
            if (gamepad1.right_stick_button) {
                frontLeftDrive.setPower(0);
                frontRightDrive.setPower(0);
                backLeftDrive.setPower(0);
                backRightDrive.setPower(0);
            }

            /*
             * Creating a variable to switch between profiles to control different servos
             */

            /*
             * The System.out.println() commands will only print on the computer's console and not
             * on the driver control station. To achieve this you will need to use telemetry
             * */

            int DriverProfile = 1;

            if(gamepad1.right_bumper && DriverProfile == 1){
                DriverProfile = DriverProfile + 1;
                System.out.println("We are currently on Driver Profile #" + DriverProfile);
            }
            if(gamepad1.left_bumper && DriverProfile == 2){
                DriverProfile = DriverProfile - 1;
                System.out.println("We are currently on Driver Profile #" + DriverProfile);
            }

            /*
             * Servo STUFF
             * */
/*
            if (DriverProfile == 1){
                if(gamepad1.x) {
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

            if (DriverProfile ==2){
                if(gamepad1.x) {
                    clawServo2.setPosition(1);
                } else if (gamepad1.y) {
                    clawServo2.setPosition(0.0);
                }

                if(gamepad1.a) {
                    clawServoHand2.setPosition(1);
                } else if (gamepad1.b) {
                    clawServoHand2.setPosition(0.0);
                }

            }

            // Both of them return and receive a value from 0.0 - 1.0
            clawServo.setPosition(gamepad1.right_trigger);
*/
            /*
             * Telemetry
             * */

            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", frontLeftPower, frontRightPower);
            telemetry.addData("We are currently on Driver Profile #", DriverProfile);
            telemetry.update();
        }
    }
}

