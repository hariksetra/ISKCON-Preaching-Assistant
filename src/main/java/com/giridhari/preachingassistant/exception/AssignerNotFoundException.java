package com.giridhari.preachingassistant.exception;

public class AssignerNotFoundException extends Exception {

    private static final long serialVersionUID = -369508357515790821L;

	public AssignerNotFoundException(String assignerName) {
        super("Assigner strategy " + assignerName + " not found");
    }

}