package com.techmojo.trend.exception;

/**
 * The Class ServiceException.
 */
public class ServiceException extends Exception{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Instantiates a new service exception.
	 *
	 * @param message the message
	 */
	private String errorMessage;
	 
	public String getErrorMessage() {
		return errorMessage;
	}
	public ServiceException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	public ServiceException() {
		super();
	}
}
