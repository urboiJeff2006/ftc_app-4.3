package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;


@Autonomous(name = "DriveAutonomous")
public class JeffMoveMotor extends LinearOpMode {
    private DcMotor Left;
    private DcMotor Right;
    private DcMotor Elevator;
    private double wheelDiameter = 10.2;
    private int ticks_per_rotation = 1440;
    @Override
    public void runOpMode(){
        Right = hardwareMap.get(DcMotor.class,"right");
        Left = hardwareMap.get(DcMotor.class,"left");
        Elevator = hardwareMap.get(DcMotor.class,"elevator");

        waitForStart();
        //RunMotorsForTime(0.1,0.1,30);
        Elevator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Elevator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Elevator.setPower(0.1);
        while(Elevator.getCurrentPosition()<7900/2){

        }
        Elevator.setPower(0);

        ElapsedTime xed = new ElapsedTime();
        while(xed.milliseconds()<2000){

        }

        Elevator.setPower(0.1);
        xed.reset();
        while (xed.milliseconds() < 2000){

        }

        Elevator.setPower(0);




        while(true){
            double y = this.gamepad1.left_stick_y;
            Elevator.setPower(y);
            telemetry.addData("MotorDegrees",Elevator.getCurrentPosition());
            telemetry.update();
        }


    }
    public int cm_to_ticks(int distance){
        double rotations = distance/(Math.PI*wheelDiameter);
        double ticks = rotations*ticks_per_rotation;
        return (int)ticks;
    }
    public void RunMotorsForTime(double l, double r,int cm){
        //1 cm = 11.28
        // 1degree = 4 ticks
        //1cm = 45.12ticks
        Right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        time = time*1000;
        Left.setPower(-l);
        Right.setPower(r);
        double ticks = cm*ticks_per_rotation/(Math.PI*wheelDiameter);
        while(Right.getCurrentPosition()<=(int)ticks){

        }
        StopMotors();


    }
    public void StopMotors(){
        Left.setPower(0);
        Right.setPower(0);
    }


}
