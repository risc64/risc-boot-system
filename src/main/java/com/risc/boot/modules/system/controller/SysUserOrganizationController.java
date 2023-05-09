package com.risc.boot.modules.system.controller;

import com.risc.boot.modules.system.bo.SysUserOrganization;
import com.risc.boot.modules.system.service.SysUserOrganizationService;
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
 * 用户组织关系表(SysUserOrganization)表控制层
 * @author 李良发
 * @since 2023-05-08 11:01:38
 */
@RestController
public class SysUserOrganizationController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private SysUserOrganizationService sysUserOrganizationService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PostMapping(value = "sysUserOrganization/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysUserOrganization>> selectPage(@ModelAttribute Page page, @RequestBody SysUserOrganization record) {
        Result<IPage<SysUserOrganization>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysUserOrganization> t = sysUserOrganizationService.selectPage(page, record);
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
    @PostMapping(value = "sysUserOrganization/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysUserOrganization>> selectByProperty(@RequestBody SysUserOrganization record) {
        Result<List<SysUserOrganization>> result = new Result<>();
        try {
            List<SysUserOrganization> list = sysUserOrganizationService.selectByProperty(record);
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
     * @param {uerUid} 主键
     * @return 单条数据
     */
    @PostMapping(value = "sysUserOrganization/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysUserOrganization> selectByKey(@RequestBody SysUserOrganization record) {
        Result<SysUserOrganization> result = new Result<>();
        try {
            SysUserOrganization record1 = sysUserOrganizationService.selectByKey(record.getUerUid());
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
    @PostMapping(value = "sysUserOrganization/add", produces = "application/json;charset=UTF-8")
    public Result<SysUserOrganization> insert(@RequestBody SysUserOrganization record) {
        Result<SysUserOrganization> result = new Result<>();
                record.setUerUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = sysUserOrganizationService.insert(record);
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
    @PostMapping(value = "sysUserOrganization/update", produces = "application/json;charset=UTF-8")
    public Result<SysUserOrganization> update(@RequestBody SysUserOrganization record) {
        Result<SysUserOrganization> result = new Result<>(); 
        try {
            int changeRow = sysUserOrganizationService.update(record);
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
    @PostMapping(value = "sysUserOrganization/delete", produces = "application/json;charset=UTF-8")
    public Result<SysUserOrganization> deleteBatch(@RequestBody List<String> list) {
        Result<SysUserOrganization> result = new Result<>();
        try {
            int changeRow = sysUserOrganizationService.deleteBatch(list);
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
