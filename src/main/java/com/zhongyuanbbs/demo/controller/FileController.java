package com.zhongyuanbbs.demo.controller;

import com.zhongyuanbbs.demo.dto.FileDto;
import com.zhongyuanbbs.demo.provider.TencentFileProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {

    @Autowired
    private TencentFileProvider tencentFileProvider;

    @RequestMapping("/file/image")
    @ResponseBody
    public FileDto imageUpload(HttpServletRequest request){
        tencentFileProvider.fileUpload(request);
        FileDto fileDto = new FileDto();
        fileDto.setSuccess(1);
        fileDto.setUrl("/images/background.png");
        return fileDto;
    }

}
