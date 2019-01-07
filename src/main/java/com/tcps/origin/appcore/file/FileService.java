package com.tcps.origin.appcore.file;

import com.tcps.origin.appcore.exception.AppException;
import com.tcps.origin.appcore.utils.FileNameUtil;
import com.tcps.origin.appcore.utils.GetSnowFlake;
import com.tcps.origin.config.constant.HttpContentTypeCons;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Gaowy
 * @date 2018/9/2
 * @mail 317326646@qq.com
 */
@Service
@Slf4j
public class FileService {

    // 默认文件上传路径
    private static final String UPLOAD_DIR = "./uploads";

    @Autowired
    private FileRepository fileRepository;

    /**
     * @describe 上传多个文件到硬盘与数据库
     * @author gaowy @date 2018/9/1
     */
    public List<Long> upload(MultipartFile[] files, String folder) throws IOException {
        if (files == null || files.length == 0) {
            throw new AppException("未获得需要上传的文件");
        }
        //如果程序员没有配置文件路径那么使用默认路径。
        if (StringUtils.isEmpty(folder)) {
            folder = UPLOAD_DIR;
        }
        File appFolder = new File(folder);
        // 如果存放文件夹不存在，那么创建他们
        if (!appFolder.exists()) {
            appFolder.mkdirs();
        }
        //返回所有成功插入数据库的Id。
        List<Long> ids = new ArrayList<>();
        for (MultipartFile file : files) {
            Long id = GetSnowFlake.getId();
            ids.add(id);
            String originalFileName = file.getOriginalFilename();
            String contentType = file.getContentType();
            String fileExtention = FileNameUtil.getExtention(originalFileName);
            String writeFileName = String.valueOf(System.currentTimeMillis()) + fileExtention;
            Files.copy(file.getInputStream(),
                    Paths.get(folder, writeFileName),
                    StandardCopyOption.REPLACE_EXISTING);
            FileBean fileBean = new FileBean();
            fileBean.setId(id);
            fileBean.setContentType(contentType);
            fileBean.setCreateDate(new Date());
            fileBean.setFolder(folder);
            // todo 获取系统的当前登陆用户
            fileBean.setCreateUser("somebody");
            fileBean.setOriginalName(originalFileName);
            fileBean.setWriteName(writeFileName);
            fileRepository.simpleSave(fileBean);
        }
        return ids;
    }

    /**
     * @describe 下载文件
     * @author gaowy @date 2018/9/2
     */
    public void download(HttpServletResponse response, File file) throws IOException {
        if (!file.exists()) {
            throw new AppException("未找到要下载的文件");
        }
        try (InputStream inputStream = new FileInputStream(file)) {
            OutputStream outputStream = response.getOutputStream();
            response.setContentType(HttpContentTypeCons.APPLICATION_DOWN_LOAD);
            log.info("下载的文件名：" + file.getName());
            response.addHeader("Content-Disposition", "attachment;filename=" + file.getName());
            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
        } catch (FileNotFoundException e) {
            log.error("文件下载出错：", e);
            throw e;
        }
    }

}

