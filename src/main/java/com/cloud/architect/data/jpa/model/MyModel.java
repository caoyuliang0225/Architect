package com.cloud.architect.data.jpa.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Cao Yuliang on 2019-07-08.
 */
@Data
@Entity
@Table(name = "my_model")
public class MyModel extends AbstractModel {

    private static final long serialVersionUID = -198090179258531687L;

    private String name;

    private Integer age;
}
