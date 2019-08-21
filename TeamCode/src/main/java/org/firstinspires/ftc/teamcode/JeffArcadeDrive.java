package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "simple teleop",group="Linear OpMode")
public class JeffArcadeDrive extends LinearOpMode {
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    @Override
    public void runOpMode(){
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        boolean tank = true;
        boolean prevmode = true;
        telemetry.addData("Status","Running");
        telemetry.update();

        waitForStart();
        while(opModeIsActive()) {
            double front = this.gamepad1.left_stick_y;
            double turn = this.gamepad1.right_stick_x;
            double left = this.gamepad1.left_stick_y;
            double right = -this.gamepad1.right_stick_y;
            if(this.gamepad1.a && prevmode==tank){
                tank = !tank;
                prevmode = tank;
            }
            if(!tank){
                leftMotor.setPower(front+turn);
                rightMotor.setPower(-front+turn);
            }else{
                rightMotor.setPower(right);
                leftMotor.setPower(left);
            }

            telemetry.addData("Status","Running");
            telemetry.update();
        }
    }


}

