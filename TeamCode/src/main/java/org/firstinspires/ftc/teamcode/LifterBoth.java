package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;

@Config
@TeleOp(name = "Lifter Both",group = "control")
public class LifterBoth extends LinearOpMode {

    DcMotorEx lifter1,lifter2;
    public static int inc = 50;

    public static int factor1 = 1;
    public static int factor2 = 1;

    public static boolean reverse = false;

    @Override
    public void runOpMode() throws InterruptedException {

        lifter1 = hardwareMap.get(DcMotorEx.class,"lifter1");
        lifter1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lifter1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);


        PIDFCoefficients x = new PIDFCoefficients(3,5,6,6);
        lifter1.setPIDFCoefficients(DcMotor.RunMode.RUN_TO_POSITION,x);

        lifter1.setTargetPosition(0);
        lifter1.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lifter1.setPower(1);




        lifter2 = hardwareMap.get(DcMotorEx.class,"lifter2");
//        lifter2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lifter2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        if(reverse == true)
        {
            lifter2.setDirection(DcMotorSimple.Direction.REVERSE);
        }

        lifter2.setTargetPosition(0);
        lifter2.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lifter2.setPower(1);

        waitForStart();

        while(opModeIsActive())
        {
            if(gamepad1.y)
            {
                lifter1.setTargetPosition(lifter1.getCurrentPosition() + inc);
            }
            else if(gamepad1.b)
            {
                lifter1.setTargetPosition(lifter1.getCurrentPosition() - inc);
            }

            if(gamepad1.x)
            {
                lifter2.setTargetPosition(lifter2.getCurrentPosition() + inc);
            }
            else if(gamepad1.a)
            {
                lifter2.setTargetPosition(lifter2.getCurrentPosition() - inc);
            }

            telemetry.addData("lifter1 position",lifter1.getCurrentPosition());
            telemetry.addData("lifter2 position",lifter2.getCurrentPosition());
            telemetry.update();
        }
    }
}
