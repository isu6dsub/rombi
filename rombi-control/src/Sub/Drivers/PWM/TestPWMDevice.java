package Sub.Drivers.PWM;

import java.io.IOException;

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
	
	public class TestPWMChannel extends PWMChannelBase{
		protected TestPWMChannel(int channel){
			super(channel);
		}
	}
}
