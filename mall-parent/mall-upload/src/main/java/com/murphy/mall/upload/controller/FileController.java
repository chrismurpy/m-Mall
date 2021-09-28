package com.murphy.mall.upload.controller;

import com.murphy.mall.upload.util.FileDfsUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 图片上传
 *
 * @author murphy
 * @since 2021/9/17 7:55 下午
 */
@RestController
@CrossOrigin // 跨域访问
public class FileController {

    @Resource
    private FileDfsUtil fileDfsUtil;

    /**
     * http://localhost:7010/swagger-ui.html
     * http://192.168.2.180/group1/M00/00/00/**.png
     * @param file
     * @return
     */
    @ApiOperation(value = "上传文件", notes = "测试FastDFS文件上传")
    @RequestMapping(value = "/uploadFile", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String result;
        try {
            String path = fileDfsUtil.upload(file);
            if (!StringUtils.isEmpty(path)) {
                result = path;
            } else {
                result = "上传失败";
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = "服务异常！";
        }
        return  ResponseEntity.ok(result);
    }

    /**
     * 文件上传
     * @param fileName
     * @return
     */
    @RequestMapping(value = "/deleteByPath", method = RequestMethod.GET)
    public ResponseEntity<String> deleteByPath(String fileName) {
        fileDfsUtil.deleteFile(fileName);
        return ResponseEntity.ok("SUCCESS");
    }

}
