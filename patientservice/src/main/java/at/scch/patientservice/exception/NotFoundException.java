package at.scch.patientservice.exception;

public class NotFoundException  extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3488440909497147137L;

	public NotFoundException() {
		super();
	}

	public NotFoundException(String message) {
		super(message);
	}

}
