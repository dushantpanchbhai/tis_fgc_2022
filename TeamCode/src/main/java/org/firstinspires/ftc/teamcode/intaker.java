package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@Config
@TeleOp(name = "intaker",group = "tests")
public class intaker extends LinearOpMode {

    DcMotorEx intake1,intake2;
    public static double intake_power = 0.8;

    @Override
    public void runOpMode() throws InterruptedException {

        intake1 = hardwareMap.get(DcMotorEx.class,"intake1");
        intake2 = hardwareMap.get(DcMotorEx.class,"intake2");

        intake1.setPower(0);
        intake2.setPower(0);

        waitForStart();

        while(opModeIsActive())
        {
            if(gamepad1.right_bumper)
            {
                telemetry.addData("right_bumper", gamepad1.right_bumper);
                intake1.setPower(intake_power);
                intake2.setPower(-intake_power);
            }
            else if(gamepad1.left_bumper)
            {
                telemetry.addData("left_bumper", gamepad1.left_bumper);
                intake1.setPower(-intake_power);
                intake2.setPower(intake_power);
            }
            else
            {
                intake1.setPower(0);
                intake2.setPower(0);
            }

            telemetry.addData("intake 1 enabled",intake1.isMotorEnabled());
            telemetry.addData("intake 2 enabled",intake2.isMotorEnabled());
            telemetry.addData("intake 1 position",intake1.getCurrentPosition());
            telemetry.addData("intake 1 power",intake1.getCurrent(CurrentUnit.AMPS));
            telemetry.addData("intake 2 position",intake2.getCurrentPosition());
            telemetry.addData("intake 2 power",intake2.getCurrent(CurrentUnit.AMPS));
            telemetry.update();
        }
    }
}
