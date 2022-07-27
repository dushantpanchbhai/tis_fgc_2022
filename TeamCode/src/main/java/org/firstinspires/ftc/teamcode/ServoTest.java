package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@Config
@TeleOp(name = "Servo test",group = "servo")
public class ServoTest extends LinearOpMode {

    Servo holder;

    public static double inc = 0.0001;

    @Override
    public void runOpMode() throws InterruptedException {

        holder = hardwareMap.get(Servo.class,"holder");

        waitForStart();
        while (opModeIsActive())
        {
            if(gamepad1.y)
            {
                holder.setPosition(holder.getPosition() + inc);
            }
            else if(gamepad1.b)
            {
                holder.setPosition(holder.getPosition() - inc);
            }

            telemetry.addData("servo position ",holder.getPosition());
            telemetry.update();
        }
    }
}
