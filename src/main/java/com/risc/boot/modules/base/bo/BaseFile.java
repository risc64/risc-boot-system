package com.risc.boot.modules.base.bo;

import java.util.Date;
import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 文件表(BaseFile)实体类
 *
 * @author makejava
 * @since 2025-08-06 09:31:41
 */
@Data
public class BaseFile implements Serializable {

    private static final long serialVersionUID = -59966085112792694L;
    
    /**
    * 主键uid
    */
    private String uid;
    
    /**
    * 显示名
    */
    private String showName;
    
    /**
    * 文件名
    */
    private String fileName;
    
    /**
    * 文件路径
    */
    private String filePath;
    
    /**
    * dfs文件路径
    */
    private String dfsPath;
    
    /**
    * 临时文件标识（0 否   1是）
    */
    private Integer tempFlag;
    
    /**
    * 文件类型（0 通用文件  ）
    */
    private Integer fileType;
    
    /**
    * 创建人uid
    */
    private String createUserUid;
    
    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    
    /**
    * 修改人uid
    */
    private String updateUserUid;
    
    /**
    * 修改时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date updateTime;
    


}
