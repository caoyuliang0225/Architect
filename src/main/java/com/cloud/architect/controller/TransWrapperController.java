package com.cloud.architect.controller;

import com.cloud.architect.data.jpa.dto.MyUserDTO;
import com.cloud.architect.data.jpa.model.MyUser;
import com.cloud.architect.utils.TransWrapper;
import com.cloud.architect.utils.dto.ResultDTO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransWrapperController extends TransWrapper {

    @RequestMapping(value = "/transwrapper/user", method = RequestMethod.POST)
    public ResultDTO<MyUserDTO> create(@RequestBody MyUserDTO myUserDTO) {

        MyUser model = this.toModel(myUserDTO, MyUser.class);

        return this.toResultDTO(model, MyUserDTO.class);
    }

}
