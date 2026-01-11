package org.firstinspires.ftc.teamcode.Utilities;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

public class VoltageMonitor {

    private VoltageSensor _voltSensor;
    private final double _minVoltThreshold;

    public VoltageMonitor(HardwareMap hardwareMap, double minVoltThreshold) {
        _voltSensor = hardwareMap.voltageSensor.iterator().next();
        _minVoltThreshold = minVoltThreshold;
    }
    public double GetCurrentVoltage(){
        return _voltSensor.getVoltage();
    }

    public boolean IsUnderVolt(){
        return _voltSensor.getVoltage() < _minVoltThreshold;
    }
}
