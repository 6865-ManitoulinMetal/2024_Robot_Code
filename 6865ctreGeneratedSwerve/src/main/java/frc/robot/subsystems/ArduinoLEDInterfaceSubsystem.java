/*package frc.robot.subsystems;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;


public class ArduinoLEDInterfaceSubsystem extends SubsystemBase 
{
    DigitalOutput[] ports;
    DigitalOutput powerPort;

    // Takes array of DIO channels and a single DIO channel
    public ArduinoLEDInterfaceSubsystem(int[] encodedDIOPorts, int powerDIOPort) 
    {
        // Create the power toggle port
        this.powerPort = new DigitalOutput(powerDIOPort);

        // Check the # of DIO ports to encode data into
        int encodedPorts = encodedDIOPorts.length;
        this.ports = new DigitalOutput[encodedPorts];
        // Do this for each avalible port:
        for (int port = 0; port < encodedPorts; port++)
        {
            // create a new DO channel and ensure it is off.
            this.ports[port] = new DigitalOutput(encodedDIOPorts[port]);
            this.ports[port].set(false);
        }
    }
    

    public void setPort(int port, boolean state)
    {
        this.ports[port].set(state);
    }

    public boolean encodeBinaryData(int value)
    {
        // calculate and check if the value is in allowable range
        // 2^n -1, where n = the # of open ports 
        int maxValue = (int) Math.pow(2, this.ports.length)-1;
        if (maxValue < value || value < 0)
        {
            return false;
        }
        // For each avalible port:
        for(int port = 0; port < this.ports.length; port++)
        {
            // find the value of the port
            int binValue = (int) Math.pow(2, this.ports.length - 1 - port);
            if (value - binValue < 0)
            {
                continue;
            }
            this.ports[port].set(true);
            value -= binValue;
        }
        return true;
    }

    @Override
    public void periodic()
    { 
        
    }
   
}*/