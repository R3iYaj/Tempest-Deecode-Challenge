package org.firstinspires.ftc.teamcode.DecodeChallenge.Controllers;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.DecodeChallenge.Systems.RPMManager;

import java.security.InvalidParameterException;

public class LaunchController {

    private final DcMotorEx _motor;
    private final RPMManager _rpmMgr;
    private double _maxPower = 1;
    private double _targetRPM = 200;
    public LaunchController(DcMotor motor, RPMManager rpmMgr) {
        _motor = (DcMotorEx) motor;
        _rpmMgr = rpmMgr;

        if (_motor == null){
            throw new InvalidParameterException("Couldn't cast DcMotor to DcMotorEx");
        }
    }

    public void SpingUp(){
        _motor.setPower(_maxPower);
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

    public void SetTargetRPM(double newTargetRPM){
        _targetRPM = newTargetRPM;
    }

    public void SetRPM(DcMotor motor, double targetRPM, double maxRPM) {
        double power = targetRPM / maxRPM;
        motor.setPower(power);
    }
    public boolean IsReadyForLaunch(){
        RPMManager.RPMStatus status = _rpmMgr.Update(_targetRPM, 100, 5);

        switch(status){
            case REACHED:
                return true;

            case TIMEOUT:
                return true;
        }

        return false;
    }
}
