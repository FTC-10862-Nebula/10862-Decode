package org.firstinspires.ftc.teamcode.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@TeleOp

public class motorTest extends OpMode {
    private DcMotorEx motor1, motor2;

    /**
     * User defined init method
     * <p>
     * This method will be called once when the INIT button is pressed.
     */
    @Override
    public void init() {
//        motor1 = hardwareMap.get(DcMotorEx.class, "intake");
//        motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        motor2 = hardwareMap.get(DcMotorEx.class, "shooter");
        motor2.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    /**
     * User defined loop method
     * <p>
     * This method will be called repeatedly in a loop while this op mode is running
     */
    @Override
    public void loop() {
        if(gamepad1.dpad_down){
         //   motor1.setPower(-1);
            motor2.setPower(-1);
        }
        else if(gamepad1.dpad_up){
           // motor1.setPower(1);
            motor2.setPower(1);
        }
        else{
           // motor1.setPower(0);
            motor2.setPower(0);
        }
        telemetry.addData("Motor2: ", motor2.getCurrentPosition());telemetry.update();

    }
}