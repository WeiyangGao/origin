package com.tcps.origin.appcore.file;

import com.tcps.origin.appcore.exception.AppException;
import com.tcps.origin.appcore.utils.Result;
import com.tcps.origin.appcore.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
public class DemoAPI {

    @Autowired
    private FileConfiguration fileConfiguration;
    @Autowired
    private FileService fileService;
    @Autowired
    private FileRepository fileRepository;
    @Resource
    private ResourceLoader resourceLoader;

    //headers:Content-Type:multipart/form-data
    @PostMapping("/upload/demo")
    public Result upload(MultipartFile[] files) throws IOException {
        List<Long> ids = fileService.upload(files, fileConfiguration.getHeadSculpture());
        return ResultUtil.successResult(ids);
    }

    @GetMapping("/download/demo/{fileid}")
    public void download(HttpServletResponse response, @PathVariable Long fileid) throws IOException {
        List<FileBean> fileBeanList = (List<FileBean>) fileRepository.findEntityById(fileid);
        if (fileBeanList.isEmpty()) {
            throw new AppException("未根据提供的Id在数据库中找到文件信息");
        }
        FileBean fileBean = fileBeanList.get(0);
        System.out.println("找到的文件名：" + fileBean.getWriteName());
        File file = new File(fileConfiguration.getHeadSculpture() + "/" + fileBean.getWriteName());
        fileService.download(response, file);
    }

    @GetMapping("/picture/{writename}")
    public ResponseEntity getPicture(@PathVariable String writename) {
        String picturePath = fileConfiguration.getHeadSculpture() + File.separator + writename;
        try {
            return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(picturePath)));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
