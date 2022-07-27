package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@Config
@TeleOp(name = "shooter_intake",group = "control")
public class Shooter_Intake extends LinearOpMode {

    DcMotorEx intake1,intake2,intake3;
    public static double intake_power = 1;
    public static int inc = 10;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleTankDrive drive = new SampleTankDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake1 = hardwareMap.get(DcMotorEx.class,"intake1");
        intake2 = hardwareMap.get(DcMotorEx.class,"intake2");
        intake3 = hardwareMap.get(DcMotorEx.class,"intake3");
        intake1.setPower(0);
        intake2.setPower(0);
        intake3.setPower(0);

        waitForStart();

        while(opModeIsActive())
        {
            if(gamepad1.x)
            {
                intake3.setPower(1);
            }
            else if(gamepad1.a)
            {
                intake3.setPower(-1);
            }
            else
            {
                intake3.setPower(0);
            }

            if(gamepad1.right_bumper)
            {
                intake1.setPower(intake_power);
                intake2.setPower(-intake_power);
            }
            else if(gamepad1.left_bumper)
            {
                intake1.setPower(-intake_power);
                intake2.setPower(intake_power);
            }
            else
            {
                intake1.setPower(0);
                intake2.setPower(0);
            }

            telemetry.update();
        }
    }
}
