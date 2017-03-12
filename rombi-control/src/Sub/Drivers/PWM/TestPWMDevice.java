package Sub.Drivers.PWM;

import java.io.IOException;

/**
 * This is a stub driver used for running the code on PCs.
 * It doesn't really do much at this point.
 * 
 * @author Vaughn Dorsey
 *
 */
public class TestPWMDevice implements PWMBase{
	public TestPWMDevice(){
		
	}

	@Override
	public void setAllPWM(int on, int off) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setPWMFreqency(double frequency) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PWMChannelBase getChannel(int channel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getAddress() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBus() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	/**
	 * A test PWM channel to appease the test driver.
	 * 
	 * @author Vaughn Dorsey
	 *
	 */
	public class TestPWMChannel extends PWMChannelBase{
		protected TestPWMChannel(int channel){
			super(channel);
		}
	}
}
