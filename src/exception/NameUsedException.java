package exception;

/**
 * Exception to be thrown when a name typed in the inputfield is already in use somewhere
 * @author Richard
 * @author Julien
 */

public class NameUsedException extends Exception {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = -8668123252971416445L;	
	
	/**
	 * Constructor for NameUsedException
	 */
	
	public NameUsedException() {
		super("Name already in used.");
	}
	
}
