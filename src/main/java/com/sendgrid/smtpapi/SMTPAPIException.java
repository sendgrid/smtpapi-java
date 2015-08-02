package com.sendgrid.smtpapi;

import org.json.JSONException;

public class SMTPAPIException extends RuntimeException {

	public SMTPAPIException(JSONException e) {
		super(e);
	}

	private static final long serialVersionUID = 6184897803477668763L;

}
