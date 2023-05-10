package com.risc.boot.modules.system.controller;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.modules.system.bo.SysOrganization;
import com.risc.boot.modules.system.service.SysOrganizationService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.risc.boot.common.util.ExceptionUtil;

/**
 * 组织表(SysOrganization)表控制层
 * @author 李良发
 * @since 2023-05-08 11:00:35
 */
@RestController
public class SysOrganizationController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private SysOrganizationService sysOrganizationService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('sysOrganization:query')")
    @PostMapping(value = "sysOrganization/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<SysOrganization>> selectPage(@ModelAttribute Page page, @RequestBody SysOrganization record) {
        Result<IPage<SysOrganization>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<SysOrganization> t = sysOrganizationService.selectPage(page, record);
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
    @PreAuthorize("hasAuthority('sysOrganization:query')")
    @PostMapping(value = "sysOrganization/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<SysOrganization>> selectByProperty(@RequestBody SysOrganization record) {
        Result<List<SysOrganization>> result = new Result<>();
        try {
            List<SysOrganization> list = sysOrganizationService.selectByProperty(record);
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
     * @param {organizationUid} 主键
     * @return 单条数据
     */
    @PreAuthorize("hasAuthority('sysOrganization:query')")
    @PostMapping(value = "sysOrganization/query/one", produces = "application/json;charset=UTF-8")
    public Result<SysOrganization> selectByKey(@RequestBody SysOrganization record) {
        Result<SysOrganization> result = new Result<>();
        try {
            SysOrganization record1 = sysOrganizationService.selectByKey(record.getOrganizationUid());
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
    @PreAuthorize("hasAuthority('sysOrganization:add')")
    @PostMapping(value = "sysOrganization/add", produces = "application/json;charset=UTF-8")
    public Result<SysOrganization> insert(@RequestBody SysOrganization record) {
        Result<SysOrganization> result = new Result<>();
                record.setOrganizationUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = sysOrganizationService.insert(record);
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
    @PreAuthorize("hasAuthority('sysOrganization:update')")
    @PostMapping(value = "sysOrganization/update", produces = "application/json;charset=UTF-8")
    public Result<SysOrganization> update(@RequestBody SysOrganization record) {
        Result<SysOrganization> result = new Result<>(); 
        try {
            int changeRow = sysOrganizationService.update(record);
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
    @PreAuthorize("hasAuthority('sysOrganization:delete')")
    @PostMapping(value = "sysOrganization/delete", produces = "application/json;charset=UTF-8")
    public Result<SysOrganization> deleteBatch(@RequestBody List<String> list) {
        Result<SysOrganization> result = new Result<>();
        try {
            int changeRow = sysOrganizationService.deleteBatch(list);
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
