package Sub.Drivers.Serial;

/**
 * This is only a test stub driver so that the software
 * can be tested on a system other than the Pi.
 * 
 * Could use some improvments to allow for the stabilization code
 * to be tested.
 * 
 * @author Vaughn Dorsey
 *
 */
public class TestSerial implements SerialBase{
	
	public TestSerial(String dev, int baud, int line) {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String open() {
		return null;
		// TODO Auto-generated method stub
		
	}

	/**
	 * Test read function. Returns only a single value.
	 */
	@Override
	public String read() {
		// TODO Auto-generated method stub
		return "#YPR=+0.00,+1.00,+0.00";
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
