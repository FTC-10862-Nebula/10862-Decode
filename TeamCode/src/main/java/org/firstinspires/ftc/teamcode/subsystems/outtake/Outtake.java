package org.firstinspires.ftc.teamcode.subsystems.outtake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Outtake {
    private Telemetry telemetry;
    private final Shooter shooter;
    private final Pivot pivot;

    public enum Value {
        START(0, 0),
        SHOOT(0,0),
        LOW(0, 0);

        public double shooterPos, pivotPos;

        Value(double shooterPos, double pivotPos) {
            this.shooterPos = shooterPos;
            this.pivotPos = pivotPos;
        }
    }

    public Outtake(Shooter shooter, Pivot pivot, Telemetry telemetry) {
        this.shooter = shooter;
        this.pivot = pivot;
        this.telemetry = telemetry;
    }

    public CommandBase setPosition(Value value) {
        return new InstantCommand(() -> setValue(value));
    }

    public void setValue(Value value) {
       // shooter.setVelocity(Shooter.Value);
        pivot.setSetPoint(value.pivotPos);
        telemetry.addData("Outtake set to", value);
        telemetry.update();
    }
}