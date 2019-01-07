package com.tcps.origin.appcore.file;

import com.tcps.origin.appcore.annotations.Table;
import lombok.Data;

import java.util.Date;

/**
 * @author Gaowy
 * @date 2018/9/2
 * @mail 317326646@qq.com
 */
@Data
@Table(name = "T_FILE")
public class FileBean {
    private Long id;
    private String originalName;
    private String writeName;
    private String contentType;
    private Date createDate;
    private String createUser;
    private String folder;

}
