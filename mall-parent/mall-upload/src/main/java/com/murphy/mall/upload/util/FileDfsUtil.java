package com.murphy.mall.upload.util;

import com.github.tobato.fastdfs.domain.fdfs.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 文件上传 - 工具类
 *
 * @author murphy
 * @since 2021/9/17 5:31 下午
 */
@Component
public class FileDfsUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileDfsUtil.class);

    @Resource
    private FastFileStorageClient storageClient;

    /**
     * 文件上传
     * @param multipartFile
     * @return
     */
    public String upload(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename().
                substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(),
                multipartFile.getSize(), originalFilename, null);
        return storePath.getFullPath();
    }

    /**
     * 删除文件
     * @param fileUrl
     */
    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            LOGGER.info("fileUrl == >>文件路径为空...");
            return;
        }
        try {
            StorePath storePath = StorePath.parseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
        } catch (Exception e) {
            LOGGER.info(e.getMessage());
        }
    }

}
