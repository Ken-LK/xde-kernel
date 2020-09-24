package com.xde.kernel.controller;

import cn.hutool.json.JSONObject;
import com.xde.kernel.tools.MongoTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Mongo测试
 *
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/9/23 10:05 上午
 **/
@Slf4j
@RestController
@RequestMapping("/mongoTest")
public class MongoDBTestController {

    @Autowired
    MongoTool mongoTool;

    @PostMapping("/insert")
    public String insert(@RequestParam String msg) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.putOpt("msg", msg);
        mongoTool.insert(jsonObject);
        if ("error".equals(msg)) {
            throw new RuntimeException("哎呀，报错了！");
        }
        return "OK";
    }
}
