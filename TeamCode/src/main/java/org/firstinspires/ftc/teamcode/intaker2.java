package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;

@TeleOp(name = "intaker2",group = "tests")
public class intaker2 extends LinearOpMode {

    DcMotorEx intake1,intake2;
    public static double intake_velocity = 200;

    @Override
    public void runOpMode() throws InterruptedException {

        intake1 = hardwareMap.get(DcMotorEx.class,"intake1");
        intake2 = hardwareMap.get(DcMotorEx.class,"intake2");

//        intake1.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//        intake1.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

//        intake2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
//        intake2.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        intake1.setVelocity(0);
        intake2.setVelocity(0);

        waitForStart();

        while(opModeIsActive())
        {
            if(gamepad1.right_bumper)
            {
                telemetry.addData("right_bumper", gamepad1.right_bumper);
                intake1.setVelocity(intake_velocity);
                intake2.setVelocity(-intake_velocity);
            }
            else if(gamepad1.left_bumper)
            {
                telemetry.addData("left_bumper", gamepad1.left_bumper);
                intake1.setVelocity(-intake_velocity);
                intake2.setVelocity(intake_velocity);
            }
            else
            {
                intake1.setVelocity(0);
                intake2.setVelocity(0);
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
