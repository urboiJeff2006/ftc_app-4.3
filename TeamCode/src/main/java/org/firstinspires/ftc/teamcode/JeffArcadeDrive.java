package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "simple teleop",group="Linear OpMode")
public class JeffArcadeDrive extends LinearOpMode {
    private DcMotor rightMotor;
    private DcMotor leftMotor;
    private Servo claw;
    @Override
    public void runOpMode(){
        rightMotor = hardwareMap.get(DcMotor.class,"right");
        leftMotor = hardwareMap.get(DcMotor.class,"left");
        claw = hardwareMap.get(Servo.class,"arm");


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
            if(this.gamepad1.left_trigger>0.4){
                claw.setPosition(0);
            }
            if(this.gamepad1.left_bumper){
                claw.setPosition(1);
            }



            telemetry.addData("Status","Running");
            telemetry.update();
        }
    }


}

