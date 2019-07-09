package com.cloud.architect.data.jpa.repository;

import com.cloud.architect.data.jpa.model.MyModel;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Cao Yuliang on 2019-07-08.
 */
public interface MyModelRepository extends CrudRepository<MyModel, Long> {
}
