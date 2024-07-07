package com.risc.boot.modules.system.controller;

import com.risc.boot.common.bo.Token;
import com.risc.boot.modules.system.bo.SysPermission;
import com.risc.boot.modules.system.service.SysPermissionService;
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
 * 权限表(SysPermission)表控制层
 * @author 李良发
 * @since 2023-05-08 11:01:01
 */
@RestController
public class SysPermissionController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private SysPermissionService sysPermissionService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('sysPermission:query')")
    @PostMapping(value = "sysPermission/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysPermission>> selectPage(@ModelAttribute Page page, @RequestBody SysPermission record) {
        Result<IPage<SysPermission>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysPermission> t = sysPermissionService.selectPage(page, record);
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
    @PreAuthorize("hasAuthority('sysPermission:query')")
    @PostMapping(value = "sysPermission/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysPermission>> selectByProperty(@RequestBody SysPermission record) {
        Result<List<SysPermission>> result = new Result<>();
        try {
            List<SysPermission> list = sysPermissionService.selectByProperty(record);
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
     * @param {permissionUid} 主键
     * @return 单条数据
     */
    @PreAuthorize("hasAuthority('sysPermission:query')")
    @PostMapping(value = "sysPermission/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysPermission> selectByKey(@RequestBody SysPermission record) {
        Result<SysPermission> result = new Result<>();
        try {
            SysPermission record1 = sysPermissionService.selectByKey(record.getUid());
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
    @PreAuthorize("hasAuthority('sysPermission:add')")
    @PostMapping(value = "sysPermission/add", produces = "application/json;charset=UTF-8")
    public Result<SysPermission> insert(@RequestBody SysPermission record) {
        Result<SysPermission> result = new Result<>();
                record.setUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = sysPermissionService.insert(record);
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
    @PreAuthorize("hasAuthority('sysPermission:update')")
    @PostMapping(value = "sysPermission/update", produces = "application/json;charset=UTF-8")
    public Result<SysPermission> update(@RequestBody SysPermission record) {
        Result<SysPermission> result = new Result<>(); 
        try {
            int changeRow = sysPermissionService.update(record);
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
    @PreAuthorize("hasAuthority('sysPermission:delete')")
    @PostMapping(value = "sysPermission/delete", produces = "application/json;charset=UTF-8")
    public Result<SysPermission> deleteBatch(@RequestBody List<String> list) {
        Result<SysPermission> result = new Result<>();
        try {
            int changeRow = sysPermissionService.deleteBatch(list);
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
     * 根据角色uid 获取菜单
     * @param token 实例对象主键集合
     * @return 删除个数
     */
    @PostMapping(value = "sysPermission/getMenu", produces = "application/json;charset=UTF-8")
    public Result<List<SysPermission>> deleteBatch(@RequestBody Token token) {
        return sysPermissionService.getMenuByRoleUid(token.getRoleUid());
    }
}
