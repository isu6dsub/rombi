package Sub.Drivers.PWM;

import java.io.IOException;

public abstract class PWMChannelBase {
	protected final int channel;

	protected PWMChannelBase(int channel) {
		if (channel < 0 || channel > 15) {
			throw new IllegalArgumentException("There is no channel "
					+ channel + " on the board.");
		}
		this.channel = channel;
	}
	
	public void setPWM(int on, int off) throws IOException{
		//Do nothing, as test PWM class won't need it.
	}
}
