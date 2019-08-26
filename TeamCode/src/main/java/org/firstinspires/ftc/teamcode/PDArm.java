package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name = "PD arm")
public class PDArm extends LinearOpMode {
    private DcMotor arm;



    @Override

    public void runOpMode(){
        arm = hardwareMap.get(DcMotor.class,"elevator");
        waitForStart();
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        double kp = 0.01;
        double kd = 0.005;
        double desired = 300;
        double error;
        double correctionP;
        double correctionD;
        double lasterror = 0;
        while(true){
            error = desired - arm.getCurrentPosition();
            correctionP = error*kp;


            correctionD = (lasterror-error)*kd;
            lasterror = error;


            arm.setPower(correctionD+correctionP);
        }

}
}
