package com.cloud.architect.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Cao Yuliang on 2019-07-04.
 */
@AllArgsConstructor
@Getter
@Setter
public class Error {

    private int code;

    private String message;
}
