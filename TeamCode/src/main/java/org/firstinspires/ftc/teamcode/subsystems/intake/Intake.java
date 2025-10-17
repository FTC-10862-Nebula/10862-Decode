package org.firstinspires.ftc.teamcode.subsystems.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.InstantCommand;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.outtake.Outtake;

public class Intake {

    private Telemetry telemetry;
    private PowerIntake powerIntake;
    private Indexer indexer;

    public enum Value{
        NOM(0,0);
        public double intakePos, indexerPos;
        Value(double intakePos, double indexerPos){
            this.intakePos = intakePos;
            this.indexerPos = indexerPos;
        }
    }

    public Intake(PowerIntake powerIntake, Indexer indexer){
        this.powerIntake = powerIntake;
        this.indexer = indexer;
        this.telemetry = telemetry;
    }

    public CommandBase setPosition(Value value) {
        return new InstantCommand(() -> setPosition(value));
    }

}