package com.cloud.architect.controller;

import com.cloud.architect.data.jpa.model.MyModel;
import com.cloud.architect.data.jpa.model.MyUser;
import com.cloud.architect.data.jpa.repository.MyModelRepository;
import com.cloud.architect.exception.CustomException;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Cao Yuliang on 2019-07-04.
 */
@RestController
public class MyUserController {

    @Autowired
    private MyModelRepository myModelRepository;

    // 测试统一异常管理
    @RequestMapping(value = "/user/{type}", method = RequestMethod.GET)
    public ResponseEntity<MyUser> get(@PathVariable Integer type) {

        MyUser user = new MyUser();
        user.setId(1L);
        user.setName("Wa Haha");
        user.setAge(16);

        HttpStatus status = HttpStatus.OK;
        if (type == 1) {
            throw new CustomException("0", "错误测试");
        }

        return new ResponseEntity<>(user, status);
    }

    // 测试数据库基类
    @RequestMapping(value = "/model", method = RequestMethod.GET)
    public ResponseEntity<MyModel> getModel() {

        MyModel user = new MyModel();
        user.setName("Wa Haha");
        user.setAge(16);

        this.myModelRepository.save(user);
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<>(user, status);
    }

    // 测试数据库基类
    @RequestMapping(value = "/model/list", method = RequestMethod.GET)
    public ResponseEntity<List<MyModel>> getModelList() {

        Iterable<MyModel> iterable = this.myModelRepository.findAll();
        List list = Lists.newArrayList(iterable);
        HttpStatus status = HttpStatus.OK;

        return new ResponseEntity<List<MyModel>>(list, status);
    }
}
