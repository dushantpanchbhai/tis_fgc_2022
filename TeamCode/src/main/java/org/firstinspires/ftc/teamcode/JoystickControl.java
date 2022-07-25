package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@Config
@TeleOp(name = "joystick control",group = "control")
public class JoystickControl extends LinearOpMode {

    DcMotorEx lifter,intake1,intake2;
    public static double intake_power = 1;
    public static int inc = 50;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleTankDrive drive = new SampleTankDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

//        intake1 = hardwareMap.get(DcMotorEx.class,"intake1");
//        intake2 = hardwareMap.get(DcMotorEx.class,"intake2");
//        intake1.setPower(0);
//        intake2.setPower(0);

        lifter = hardwareMap.get(DcMotorEx.class,"lifter");
        lifter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lifter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lifter.setTargetPosition(0);
        lifter.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lifter.setPower(1);

        waitForStart();

        while(opModeIsActive())
        {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            gamepad1.left_stick_y,
                            0,
                            -gamepad1.right_stick_x
                    )
            );
            drive.update();

            if(gamepad1.y)
            {
                lifter.setTargetPosition(lifter.getCurrentPosition() + inc);
            }
            else if(gamepad1.b)
            {
                lifter.setTargetPosition(lifter.getCurrentPosition() - inc);

            }

//            if(gamepad1.right_bumper)
//            {
//                intake1.setPower(intake_power);
//                intake2.setPower(-intake_power);
//            }
//            else if(gamepad1.left_bumper)
//            {
//                intake1.setPower(-intake_power);
//                intake2.setPower(intake_power);
//            }
//            else
//            {
//                intake1.setPower(0);
//                intake2.setPower(0);
//            }

            telemetry.addData("lifter position",lifter.getCurrentPosition());
            telemetry.update();
        }
    }
}
