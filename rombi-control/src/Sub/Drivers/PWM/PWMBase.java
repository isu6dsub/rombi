package Sub.Drivers.PWM;

import java.io.IOException;

/**
 * Interface providing common methods for the PWM devices.
 * 
 * @author Vaughn Dorsey
 *
 */
public interface PWMBase {
	public void setAllPWM(int on, int off) throws IOException;
	public void setPWMFreqency(double frequency) throws IOException;
	public PWMChannelBase getChannel(int channel);
	public int getAddress();
	public int getBus();
}
