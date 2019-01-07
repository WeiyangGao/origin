package com.tcps.origin.appcore.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class AuthException extends RuntimeException {

	private static final long serialVersionUID = 4955966216630325147L;
	private String messasge;
    private String code;

    public AuthException(String messasge) {
        this.messasge = messasge;
    }

    public AuthException(String messasge, String code) {
        this.messasge = messasge;
        this.code = code;
    }
}
