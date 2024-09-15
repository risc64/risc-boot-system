package com.risc.boot.modules.system.controller;

import com.risc.boot.common.constant.SystemConstants;
import com.risc.boot.modules.system.bo.SysUser;
import com.risc.boot.modules.system.service.SysUserService;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.util.ExceptionUtil;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户表(SysUser)表控制层
 *
 * @author 李良发
 * @since 2023-05-08 10:59:18
 */
@RestController
public class SysUserController {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    
    
    /**
     * 分页查询
     *
     * @param page   分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('sysUser:query')")
    @PostMapping(value = "sysUser/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysUser>> selectPage(@ModelAttribute Page page, @RequestBody SysUser record) {
        Result<IPage<SysUser>> result = new Result<>();
        try {
            if (page.getSize() == 0) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysUser> t = sysUserService.selectPage(page, record);
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
     *
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('sysUser:query')")
    @PostMapping(value = "sysUser/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysUser>> selectByProperty(@RequestBody SysUser record) {
        Result<List<SysUser>> result = new Result<>();
        try {
            List<SysUser> list = sysUserService.selectByProperty(record);
            if (list != null && list.size() > 0) {
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
     *
     * @param {userUid} 主键
     * @return 单条数据
     */
    @PreAuthorize("hasAuthority('sysUser:query')")
    @PostMapping(value = "sysUser/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysUser> selectByKey(@RequestBody SysUser record) {
        Result<SysUser> result = new Result<>();
        try {
            SysUser record1 = sysUserService.selectByKey(record.getUid());
            if (record1 != null) {
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
     *
     * @param record 实例对象
     * @return 新增个数
     */
    @PreAuthorize("hasAuthority('sysUser:add')")
    @PostMapping(value = "sysUser/add", produces = "application/json;charset=UTF-8")
    public Result<SysUser> insert(@RequestBody SysUser record) {
        Result<SysUser> result = new Result<>();
        record.setUid(UUID.randomUUID().toString().replace("-", ""));
        record.setOpenUid(UUID.randomUUID().toString().replace("-", ""));
        try {
            
            int changeRow = sysUserService.insert(record);
            if (changeRow > 0) {
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
     *
     * @param record 实例对象
     * @return 修改个数
     */
    @PreAuthorize("hasAuthority('sysUser:update')")
    @PostMapping(value = "sysUser/update", produces = "application/json;charset=UTF-8")
    public Result<SysUser> update(@RequestBody SysUser record) {
        Result<SysUser> result = new Result<>();
        try {
            int changeRow = sysUserService.update(record);
            if (changeRow > 0) {
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
     *
     * @param record 实例对象主键集合
     * @return 删除个数
     */
    @PreAuthorize("hasAuthority('sysUser:delete')")
    @PostMapping(value = "sysUser/deleteBatchLogical", produces = "application/json;charset=UTF-8")
    public Result<SysUser> deleteBatchLogical(@RequestBody SysUser record) {
        Result<SysUser> result = new Result<>();
        try {
            int changeRow = sysUserService.deleteBatchLogical(record.getUidList(), record.getUpdateUserUid());
            if (changeRow > 0) {
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
     * 新增、编辑用户名验重
     *
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('sysUser:query')")
    @PostMapping(value = "sysUser/query/checkUserName", produces = "application/json;charset=UTF-8")
    public Result<List<SysUser>> checkUserName(@RequestBody SysUser record) {
        Result<List<SysUser>> result = new Result<>();
        try {
            SysUser sysUser = sysUserService.checkUserName(record.getUid(), record.getUserName());
            if (sysUser != null) {
                result.setStatusEnum(StatusEnum.OK, null);
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
     * 头像
     * @param file 实例对象
     * @return 修改个数
     */
    @PreAuthorize("hasAuthority('sysUser:update')")
    @PostMapping(value = "sysUser/upload/profilePicture", produces = "application/json;charset=UTF-8")
    public Result<SysUser> uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("uid") String uid,
                                      @RequestParam("updateUserUid") String updateUserUid) {
        Result<SysUser> result = new Result<>();
        ApplicationHome h = new ApplicationHome(getClass());
        String rootPath = h.getSource().getParentFile().toString() ;
        try {
            String fileName = file.getOriginalFilename();
            String fileType = "";
            if (fileName.contains(".")) {
                fileType = fileName.substring(fileName.lastIndexOf("."));
            }
            String fileUid = UUID.randomUUID().toString().replace("-", "");
            String filePath = SystemConstants.FILE_ROOT_PATH + SystemConstants.PROFILE_PICTURE_PATH + "/" + fileUid + fileType;
            File dest = new File(rootPath + filePath);
            if (!dest.getParentFile().exists()) {
                dest.mkdirs();
            } else {
                dest.delete();
            }
            file.transferTo(dest);
            SysUser sysUser = new SysUser();
            sysUser.setUid(uid);
            sysUser.setProfilePicture(filePath);
            sysUser.setUpdateUserUid(updateUserUid);
            int changeRow = sysUserService.updateProfilePicture(sysUser);
            if (changeRow > 0) {
                result.setStatusEnum(StatusEnum.OK, sysUser);
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
