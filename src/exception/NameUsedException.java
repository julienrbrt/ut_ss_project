package exception;

public class NameUsedException extends Exception {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = -8668123252971416445L;	
	
	public NameUsedException() {
		super("Name already in used.");
	}
	
}
