package com.school.exceptions;

@SuppressWarnings("serial")
public class SaveException extends RuntimeException {

	private String message;

	public SaveException(String message) {
		super(String.format("Unable to save %s entity!!", message));
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public void setMessage(String message) {
		this.message=message;
	}
}
