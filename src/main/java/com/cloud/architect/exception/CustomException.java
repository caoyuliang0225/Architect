package com.cloud.architect.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Cao Yuliang on 2019-07-04.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 2177662359060666300L;

    private int code;

    private String message;
}
