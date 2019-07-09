package com.cloud.architect.data.jpa.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MyUserDTO implements Serializable {

    private static final long serialVersionUID = 1622210149926626077L;

    private Long id;

    private String name;

    private Integer age;

}
