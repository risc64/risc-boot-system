package com.risc.boot.modules.base.controller;

import com.risc.boot.common.constant.SystemConstants;
import com.risc.boot.modules.base.bo.BaseFile;
import com.risc.boot.modules.base.service.BaseFileService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.util.ExceptionUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表(BaseFile)表控制层
 * @author makejava
 * @since 2025-08-06 09:31:47
 */
@RestController
public class BaseFileController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private BaseFileService baseFileService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('baseFile:query')")
    @PostMapping(value = "baseFile/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<BaseFile>> selectPage(@ModelAttribute Page page, @RequestBody BaseFile record) {
        Result<IPage<BaseFile>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<BaseFile> t = baseFileService.selectPage(page, record);
            if (t != null) {
                result.setStatusEnum(StatusEnum.OK, t);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('baseFile:query')")
    @PostMapping(value = "baseFile/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<BaseFile>> selectByProperty(@RequestBody BaseFile record) {
        Result<List<BaseFile>> result = new Result<>();
        try {
            List<BaseFile> list = baseFileService.selectByProperty(record);
            if (list != null && list.size() > 0)  {
                result.setStatusEnum(StatusEnum.OK, list);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    /**
     * 根据key查询
     * @param {uid} 主键
     * @return 单条数据
     */
    @PreAuthorize("hasAuthority('baseFile:query')")
    @PostMapping(value = "baseFile/query/one", produces = "application/json;charset=UTF-8")
    public Result<BaseFile> selectByKey(@RequestBody BaseFile record) {
        Result<BaseFile> result = new Result<>();
        try {
            BaseFile record1 = baseFileService.selectByKey(record.getUid());
            if (record1 != null)  {
                result.setStatusEnum(StatusEnum.OK, record1);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    /**
     * 新增
     * @param
     * @return 新增个数
     */
    @PreAuthorize("hasAuthority('baseFile:add')")
    @PostMapping(value = "baseFile/add", produces = "application/json;charset=UTF-8")
    public Result<BaseFile> insert(@RequestParam("file") MultipartFile file, @RequestParam("fileName") String fileName,
                                   @RequestParam("showName") String showName, @RequestParam("fileType") Integer fileType,
                                   @RequestParam("tempFlag") Integer tempFlag, @RequestParam("createUserUid") String createUserUid) {
        Result<BaseFile> result = new Result<>();
        BaseFile record = new BaseFile();
        record.setUid(UUID.randomUUID().toString().replace("-", ""));
        record.setFileName(fileName);
        record.setShowName(showName);
        record.setFileType(fileType);
        record.setTempFlag(tempFlag);
        record.setCreateUserUid(createUserUid);
        try {
            result = baseFileService.insert(record, file);
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }

    /**
     * 替换
     * @param
     * @return 新增个数
     */
    @PreAuthorize("hasAuthority('baseFile:update')")
    @PostMapping(value = "baseFile/replate", produces = "application/json;charset=UTF-8")
    public Result<BaseFile> replate(@RequestParam("file") MultipartFile file, @RequestParam("uid") String uid,
                                    @RequestParam("updateUserUid") String updateUserUid) {
        Result<BaseFile> result = new Result<>();
        if (StringUtils.isBlank(uid) || file == null) {
            result.setStatusEnum(StatusEnum.PARAM_ERROR, null);
            return result;
        }
        ApplicationHome h = new ApplicationHome(this.getClass());
        String basePath = h.getSource().getParent().toString()+ SystemConstants.FILE_ROOT_PATH + "/";
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String suffix = "";
        if (index > 0) {
            suffix = fileName.substring(index);
        }
        String filePath = SystemConstants.BASE_FILE_PATH + "/" + uid + suffix;
        File dest = new File(basePath + filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            BaseFile baseFile = baseFileService.selectByKey(uid);
            if (baseFile ==  null) {
                result.error("文件记录不存在");
                return result;
            }
            file.transferTo(dest);
            baseFile.setFileName(fileName);
            baseFile.setFilePath(filePath);
            baseFile.setUpdateUserUid(updateUserUid);
            int updateRow = baseFileService.update(baseFile);
            if (updateRow > 0) {
                result.setStatusEnum(StatusEnum.OK, null);
            } else {
                result.setStatusEnum(StatusEnum.ERROR, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }

    
    /**
     * 修改
     * @param record 实例对象
     * @return 修改个数
     */
    @PreAuthorize("hasAuthority('baseFile:update')")
    @PostMapping(value = "baseFile/update", produces = "application/json;charset=UTF-8")
    public Result<BaseFile> update(@RequestBody BaseFile record) {
        Result<BaseFile> result = new Result<>(); 
        try {
            int changeRow = baseFileService.update(record);
            if (changeRow > 0)  {
                result.setStatusEnum(StatusEnum.OK, null);
            } else {
                result.setStatusEnum(StatusEnum.ERROR, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }

    /**
     * 批量删除
     * @param list 实例对象主键集合
     * @return 删除个数
     */
    @PreAuthorize("hasAuthority('baseFile:delete')")
    @PostMapping(value = "baseFile/delete", produces = "application/json;charset=UTF-8")
    public Result<BaseFile> deleteBatch(@RequestBody List<String> list) {
        Result<BaseFile> result = new Result<>();
        try {
            int changeRow = baseFileService.deleteBatch(list);
            if (changeRow > 0)  {
                result.setStatusEnum(StatusEnum.OK, null);
            } else {
                result.setStatusEnum(StatusEnum.ERROR, null);
            }
        } catch (Exception e) {
            result.exception(StatusEnum.EXCEPTION);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
}
