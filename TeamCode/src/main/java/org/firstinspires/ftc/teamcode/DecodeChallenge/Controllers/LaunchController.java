package org.firstinspires.ftc.teamcode.DecodeChallenge.Controllers;

import com.qualcomm.robotcore.hardware.DcMotor;

public class LaunchController {
    private final DcMotor _motor;

    private double _maxPower = 1;
    private double _minPower = 0.5;

    public LaunchController(DcMotor motor) {
        _motor = motor;
    }

    public void RunMaxPower(){
        _motor.setPower(_maxPower);
    }

    public void RunMinPower(){
        _motor.setPower(_minPower);
    }

    public void RunPower(double power){
        _motor.setPower(power);
    }

    public void Stop(){
        _motor.setPower(0);
    }

    public void SetMaxPower(double power){
        _maxPower = power;
    }

    public void SetLowPower(double power){
        _minPower = power;
    }
}
