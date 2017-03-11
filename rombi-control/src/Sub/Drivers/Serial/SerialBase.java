package Sub.Drivers.Serial;

/**
 * Interface that defines common functions for our serial drivers.
 * 
 * @author Vaughn Dorsey
 *
 */
public interface SerialBase {
	public String open();
	public String read();
	public void close();
}
