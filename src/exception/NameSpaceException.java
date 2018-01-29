package exception;

/**
 * Exception to be thrown when a name is typed in the inputfield that contains at least one space
 * @author Richard
 * @author Julien
 */

public class NameSpaceException extends Exception {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 3461535333680139264L;
	
	/**
	 * Constructor for NameSpaceException
	 */
	
	public NameSpaceException() {
		super("No spaces allowed in names.");
	}

}
