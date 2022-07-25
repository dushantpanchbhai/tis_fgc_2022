package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Config
@TeleOp(name = "lifter3",group = "tests")
public class Lifter3 extends LinearOpMode {

    DcMotorEx lifter;
    public static int inc = 50;
    public static int vel = 100;

    // -1890
    public static int lowest_pos = -1890;

    @Override
    public void runOpMode() throws InterruptedException {

        lifter = hardwareMap.get(DcMotorEx.class,"lifter");
        lifter.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        lifter.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        lifter.setTargetPosition(0);
        lifter.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lifter.setVelocity(vel);

        waitForStart();

        while(opModeIsActive())
        {
            if(gamepad1.y)
            {
                lifter.setTargetPosition(lifter.getCurrentPosition() + inc);
            }
            else if(gamepad1.b)
            {
                lifter.setTargetPosition(lifter.getCurrentPosition() - inc);

            }

            telemetry.addData("motor position",lifter.getCurrentPosition());
            telemetry.update();
        }
    }
}
