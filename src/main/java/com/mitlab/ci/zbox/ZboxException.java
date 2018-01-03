package com.mitlab.ci.zbox;

public class ZboxException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ZboxException(String message, Throwable t) {
        super(message, t);
    }
}
