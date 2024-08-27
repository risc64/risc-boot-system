package com.risc.boot.modules.system.controller;

import com.risc.boot.modules.system.bo.SysPermission;
import com.risc.boot.modules.system.bo.SysRole;
import com.risc.boot.modules.system.bo.SysRolePermission;
import com.risc.boot.modules.system.service.SysRolePermissionService;
import com.risc.boot.modules.system.service.SysRoleService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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

/**
 * 角色表(SysRole)表控制层
 * @author 李良发
 * @since 2023-05-08 11:00:08
 */
@RestController
public class SysRoleController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private SysRoleService sysRoleService;
    
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('sysRole:query')")
    @PostMapping(value = "sysRole/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysRole>> selectPage(@ModelAttribute Page page, @RequestBody SysRole record) {
        Result<IPage<SysRole>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysRole> t = sysRoleService.selectPage(page, record);
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
    @PreAuthorize("hasAuthority('sysRole:query')")
    @PostMapping(value = "sysRole/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysRole>> selectByProperty(@RequestBody SysRole record) {
        Result<List<SysRole>> result = new Result<>();
        try {
            List<SysRole> list = sysRoleService.selectByProperty(record);
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
     * @param {roleUid} 主键
     * @return 单条数据
     */
    @PreAuthorize("hasAuthority('sysRole:query')")
    @PostMapping(value = "sysRole/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysRole> selectByKey(@RequestBody SysRole record) {
        Result<SysRole> result = new Result<>();
        try {
            SysRole record1 = sysRoleService.selectByKey(record.getUid());
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
     * @param record 实例对象
     * @return 新增个数
     */
    @PreAuthorize("hasAuthority('sysRole:add')")
    @PostMapping(value = "sysRole/add", produces = "application/json;charset=UTF-8")
    public Result<SysRole> insert(@RequestBody SysRole record) {
        Result<SysRole> result = new Result<>();
                record.setUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = sysRoleService.insert(record);
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
     * 修改
     * @param record 实例对象
     * @return 修改个数
     */
    @PreAuthorize("hasAuthority('sysRole:update')")
    @PostMapping(value = "sysRole/update", produces = "application/json;charset=UTF-8")
    public Result<SysRole> update(@RequestBody SysRole record) {
        Result<SysRole> result = new Result<>(); 
        try {
            int changeRow = sysRoleService.update(record);
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
    @PreAuthorize("hasAuthority('sysRole:delete')")
    @PostMapping(value = "sysRole/delete", produces = "application/json;charset=UTF-8")
    public Result<SysRole> deleteBatch(@RequestBody List<String> list) {
        Result<SysRole> result = new Result<>();
        try {
            int changeRow = sysRoleService.deleteBatch(list);
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
