package com.cloud.architect.controller;

import com.cloud.architect.data.jpa.model.MyUser;
import com.cloud.architect.exception.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Cao Yuliang on 2019-07-04.
 */
@RestController
public class MyUserController {

    @RequestMapping(value = "/user/{type}", method = RequestMethod.GET)
    public ResponseEntity<MyUser> get(@PathVariable Integer type) {

        MyUser user = new MyUser();
        user.setId(1L);
        user.setName("Wa Haha");
        user.setAge(16);

        HttpStatus status = HttpStatus.OK;
        if (type == 1) {
            throw new CustomException(0, "错误测试");
        }

        return new ResponseEntity<>(user, status);
    }
}
