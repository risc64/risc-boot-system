package com.risc.boot.modules.system.controller;

import com.risc.boot.modules.system.bo.SysRolePermission;
import com.risc.boot.modules.system.service.SysRolePermissionService;
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
 * 角色权限表(SysRolePermission)表控制层
 * @author 李良发
 * @since 2023-05-08 11:01:20
 */
@RestController
public class SysRolePermissionController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private SysRolePermissionService sysRolePermissionService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PostMapping(value = "sysRolePermission/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysRolePermission>> selectPage(@ModelAttribute Page page, @RequestBody SysRolePermission record) {
        Result<IPage<SysRolePermission>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysRolePermission> t = sysRolePermissionService.selectPage(page, record);
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
    @PostMapping(value = "sysRolePermission/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysRolePermission>> selectByProperty(@RequestBody SysRolePermission record) {
        Result<List<SysRolePermission>> result = new Result<>();
        try {
            List<SysRolePermission> list = sysRolePermissionService.selectByProperty(record);
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
    @PostMapping(value = "sysRolePermission/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysRolePermission> selectByKey(@RequestBody SysRolePermission record) {
        Result<SysRolePermission> result = new Result<>();
        try {
            SysRolePermission record1 = sysRolePermissionService.selectByKey(record.getRoleUid());
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
    @PostMapping(value = "sysRolePermission/add", produces = "application/json;charset=UTF-8")
    public Result<SysRolePermission> insert(@RequestBody SysRolePermission record) {
        Result<SysRolePermission> result = new Result<>();
                record.setRoleUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = sysRolePermissionService.insert(record);
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
    @PostMapping(value = "sysRolePermission/update", produces = "application/json;charset=UTF-8")
    public Result<SysRolePermission> update(@RequestBody SysRolePermission record) {
        Result<SysRolePermission> result = new Result<>(); 
        try {
            int changeRow = sysRolePermissionService.update(record);
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
    @PostMapping(value = "sysRolePermission/delete", produces = "application/json;charset=UTF-8")
    public Result<SysRolePermission> deleteBatch(@RequestBody List<String> list) {
        Result<SysRolePermission> result = new Result<>();
        try {
            int changeRow = sysRolePermissionService.deleteBatch(list);
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
