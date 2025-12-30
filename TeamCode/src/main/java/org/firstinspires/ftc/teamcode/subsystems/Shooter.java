package org.firstinspires.ftc.teamcode.subsystems;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.intake.Intake;
import org.firstinspires.ftc.teamcode.util.nebulaHardware.NebulaMotor;
import org.firstinspires.ftc.teamcode.util.templates.ShooterTemplate;

public class Shooter extends ShooterTemplate{

    public enum ShooterValue {
        SHOOT(0.75),
        REST(0.0);

        public final double power;
        ShooterValue(double power) {
            this.power = power;
        }
    }
    private final Telemetry telemetry;
    private final boolean isEnabled;
    public Shooter(HardwareMap hw, Telemetry telemetry, boolean isEnabled){
        super(
            new NebulaMotor[]{
                new NebulaMotor(hw, "shooterL",
                    DcMotorSimple.Direction.REVERSE,
                    DcMotor.ZeroPowerBehavior.FLOAT, isEnabled),
                new NebulaMotor(hw, "shooterR",
                    DcMotorSimple.Direction.FORWARD,
                    DcMotor.ZeroPowerBehavior.FLOAT, isEnabled),
            },
            telemetry,
            new PIDFController(0.004,0,0,0)
        );
        this.telemetry = telemetry;
        this.isEnabled = isEnabled;
    }
    
    @Override
    public void periodic() {
        super.periodic();
        telemetry.addData("Shooter Enabled", isEnabled);
    }
    
    public void setValue(ShooterValue intakeValue) {
        setVelocity(intakeValue.power);
    }

}
