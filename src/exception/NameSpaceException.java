package exception;

public class NameSpaceException extends Exception {

	/**
	 * Generated UID.
	 */
	private static final long serialVersionUID = 3461535333680139264L;
	
	public NameSpaceException() {
		super("No spaces allowed in names.");
	}

}
