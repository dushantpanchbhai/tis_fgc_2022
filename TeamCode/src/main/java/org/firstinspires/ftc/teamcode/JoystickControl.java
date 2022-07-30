package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

import java.util.Arrays;
import java.util.List;

@Config
@TeleOp(name = "joystick control",group = "control")
public class JoystickControl extends LinearOpMode {

    DcMotorEx lifter1,lifter2,shooter1,shooter2,intake;
    Servo holder;
    DcMotorEx leftRear,rightRear;


    public static double intake_power = 1;
    public static int inc = 10;
    public int pos = 0;

    public static int factor1 = 1;
    public static int factor2 = 1;

    double drive,turn,leftPower,rightPower;

    @Override
    public void runOpMode() throws InterruptedException {

        leftRear = hardwareMap.get(DcMotorEx.class, "leftRear");
        rightRear = hardwareMap.get(DcMotorEx.class, "rightRear");

        leftRear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        rightRear.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        leftRear.setDirection(DcMotorSimple.Direction.REVERSE);

        holder = hardwareMap.get(Servo.class,"holder");

        shooter1 = hardwareMap.get(DcMotorEx.class,"intake1");
        shooter2 = hardwareMap.get(DcMotorEx.class,"intake2");
        intake = hardwareMap.get(DcMotorEx.class,"intake3");
        shooter1.setPower(0);
        shooter2.setPower(0);
        intake.setPower(0);

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

        waitForStart();

        while(opModeIsActive())
        {
            // ####### motor localization code ##############
            drive = gamepad1.left_stick_y;
            turn  = -gamepad1.right_stick_x*0.75;
            leftPower    = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;
            leftRear.setPower(leftPower);
            rightRear.setPower(rightPower);
//            #################################################

            if(gamepad1.right_trigger > 0)
            {
                pos = Constants.lifter_final;
                moveTo(pos);
            }
            else if(gamepad1.left_trigger > 0)
            {
                pos = Constants.lifter_initial;
                moveTo(pos);
            }

            if(gamepad1.dpad_down)
            {
                holder.setPosition(Constants.servo_hold_position);
            }
            else if(gamepad1.dpad_up)
            {
                holder.setPosition(Constants.servo_release_position);
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

            if(gamepad1.x)
            {
                intake.setPower(1);
            }
            else if(gamepad1.a)
            {
                intake.setPower(-1);
            }
            else
            {
                intake.setPower(0);
            }

            if(gamepad1.right_bumper)
            {
                shooter1.setPower(intake_power);
                shooter2.setPower(-intake_power);
            }
            else if(gamepad1.left_bumper)
            {
                shooter1.setPower(-intake_power);
                shooter2.setPower(intake_power);
            }
            else
            {
                shooter1.setPower(0);
                shooter2.setPower(0);
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
