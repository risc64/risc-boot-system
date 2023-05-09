package com.risc.boot.modules.system.controller;

import com.risc.boot.modules.system.bo.SysUserRole;
import com.risc.boot.modules.system.service.SysUserRoleService;
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
 * 用户角色关系表(SysUserRole)表控制层
 * @author 李良发
 * @since 2023-05-08 11:01:48
 */
@RestController
public class SysUserRoleController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private SysUserRoleService sysUserRoleService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PostMapping(value = "sysUserRole/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysUserRole>> selectPage(@ModelAttribute Page page, @RequestBody SysUserRole record) {
        Result<IPage<SysUserRole>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysUserRole> t = sysUserRoleService.selectPage(page, record);
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
    @PostMapping(value = "sysUserRole/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysUserRole>> selectByProperty(@RequestBody SysUserRole record) {
        Result<List<SysUserRole>> result = new Result<>();
        try {
            List<SysUserRole> list = sysUserRoleService.selectByProperty(record);
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
     * @param {userUid} 主键
     * @return 单条数据
     */
    @PostMapping(value = "sysUserRole/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysUserRole> selectByKey(@RequestBody SysUserRole record) {
        Result<SysUserRole> result = new Result<>();
        try {
            SysUserRole record1 = sysUserRoleService.selectByKey(record.getUserUid());
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
    @PostMapping(value = "sysUserRole/add", produces = "application/json;charset=UTF-8")
    public Result<SysUserRole> insert(@RequestBody SysUserRole record) {
        Result<SysUserRole> result = new Result<>();
                record.setUserUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = sysUserRoleService.insert(record);
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
    @PostMapping(value = "sysUserRole/update", produces = "application/json;charset=UTF-8")
    public Result<SysUserRole> update(@RequestBody SysUserRole record) {
        Result<SysUserRole> result = new Result<>(); 
        try {
            int changeRow = sysUserRoleService.update(record);
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
    @PostMapping(value = "sysUserRole/delete", produces = "application/json;charset=UTF-8")
    public Result<SysUserRole> deleteBatch(@RequestBody List<String> list) {
        Result<SysUserRole> result = new Result<>();
        try {
            int changeRow = sysUserRoleService.deleteBatch(list);
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
