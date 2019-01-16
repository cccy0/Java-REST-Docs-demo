package com.example.demo.controller;

import com.example.demo.User;
import io.swagger.annotations.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/users")
public class UserController {

    // 获取所有用户列表
    @ApiOperation(value = "获取用户列表", notes = "这好像是详细介绍")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity list() {
        List<String> users = new ArrayList<>();
        users.add("{\"name\":\"张三\", \"age\":18}");
        users.add("{\"name\":\"王五\", \"age\":19}");
        users.add("{\"name\":\"李四\", \"age\":20}");
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    // 按id获取用户
    @ApiOperation(value = "按id获取用户", notes = "这好像是详细介绍222")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable Long id) {
        return new ResponseEntity<>("{\"name\":\"李四\", \"age\":20}", HttpStatus.OK);
    }

    // 添加新用户
    @ApiOperation(value = "添加新用户", notes = "这好像是详细介绍3333")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户姓名", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "age", value = "用户年龄", required = true, dataType = "Integer", paramType = "path")
    })
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody Map body) throws Exception {
        return new ResponseEntity<>("{\"name\":\"新用户\", \"age\":22}", HttpStatus.OK);

    }

    // 更新用户
    @ApiOperation(value = "更新用户", notes = "通过Id来更新用户信息的接口")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户的Id", required = true, dataType = "Long", paramType = "path", example = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 200, message = "更新成功"),
            @ApiResponse(code = 400, message = "更新失败")
    })
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable Long id,@ApiParam @RequestBody User body) throws Exception {
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // 修改指定用户密码
    @RequestMapping(value = "/{id}/password", method = RequestMethod.PUT)
    public ResponseEntity changePassword(@PathVariable Long id,
                                         @RequestBody Map body) throws Exception {
        return new ResponseEntity<>("{\"name\":\"已修改密码的用户\", \"age\":22}", HttpStatus.OK);
    }

    // 删除用户
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable Long id) {
        return new ResponseEntity<>("删除成功", HttpStatus.OK);
    }
}
