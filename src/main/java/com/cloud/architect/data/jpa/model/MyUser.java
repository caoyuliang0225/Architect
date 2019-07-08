package com.cloud.architect.data.jpa.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by Cao Yuliang on 2019-07-04.
 */
@Getter
@Setter
public class MyUser implements Serializable {

    private static final long serialVersionUID = 1360752113727832101L;

    private Long id;

    private String name;

    private Integer age;
}
