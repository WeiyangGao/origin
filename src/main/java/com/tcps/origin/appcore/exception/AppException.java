package com.tcps.origin.appcore.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义异常类
 * 20180101 gaoweiyang
 */
@EqualsAndHashCode(callSuper = true)
@Getter
@Setter
public class AppException extends RuntimeException {

	private static final long serialVersionUID = -8879952713643826659L;
	private String messasge;
    private String code;

    public AppException(String messasge) {
        this.messasge = messasge;
    }

    public AppException(String messasge, String code) {
        this.messasge = messasge;
        this.code = code;
    }
}
