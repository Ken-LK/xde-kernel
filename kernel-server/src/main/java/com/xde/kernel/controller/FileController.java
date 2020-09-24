package com.xde.kernel.controller;

import com.xde.kernel.tools.TianYiYunOssTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ken
 * @mailto <a href="liukailk.ken@gmail.com"/>
 * @date 2020/8/28 3:41 下午
 **/
@RestController
public class FileController {

    @Autowired
    private TianYiYunOssTool tianYiYunOssTool;

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam MultipartFile file){
        return  tianYiYunOssTool.uploadMultipartFile(file);
    }

}
