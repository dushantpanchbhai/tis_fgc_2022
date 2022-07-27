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
@TeleOp(name = "joystick control 3",group = "control")
public class JoystickControl3 extends LinearOpMode {

    DcMotorEx lifter1,lifter2,intake1,intake2;
    Servo holder;
    public static double intake_power = 1;
    public static int inc = 10;
    public int pos = 0;

    public static double hold_position = 0.6;
    public static double release_position = 0.2;

    public static int factor1 = 1;
    public static int factor2 = 1;

    @Override
    public void runOpMode() throws InterruptedException {

        SampleTankDrive drive = new SampleTankDrive(hardwareMap);
        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake1 = hardwareMap.get(DcMotorEx.class,"intake1");
        intake2 = hardwareMap.get(DcMotorEx.class,"intake2");
        intake1.setPower(0);
        intake2.setPower(0);

        lifter1 = hardwareMap.get(DcMotorEx.class,"lifter1");
        lifter1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lifter1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lifter1.setTargetPosition(0);
        lifter1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lifter1.setPower(1);


        lifter2 = hardwareMap.get(DcMotorEx.class,"lifter2");
        lifter2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lifter2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lifter2.setTargetPosition(0);
        lifter2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lifter2.setPower(1);

        holder = hardwareMap.get(Servo.class,"holder");

        waitForStart();

        while(opModeIsActive())
        {

            drive.setWeightedDrivePower(
                    new Pose2d(
                            -gamepad1.left_stick_y,
                            0,
                            -gamepad1.right_stick_x
                    )
            );
            drive.update();

            if(gamepad1.x)
            {
                holder.setPosition(hold_position);
            }
            else if(gamepad1.a)
            {
                holder.setPosition(release_position);
            }

            if(gamepad1.y)
            {
                pos = pos + inc;
                moveTo(pos);
            }
            else if(gamepad1.b)
            {
                pos = pos - inc;
                moveTo(pos);
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

            telemetry.addData("lifter1 position",lifter1.getCurrentPosition());
            telemetry.addData("lifter2 position",lifter2.getCurrentPosition());
            telemetry.update();
        }
    }

    void moveTo(int pos)
    {

        lifter1.setTargetPosition(factor1*pos);
        lifter2.setTargetPosition(factor2*pos);
    }
}
