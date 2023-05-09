package com.risc.boot.modules.system.controller;

import com.risc.boot.modules.system.bo.Test;
import com.risc.boot.modules.system.service.TestService;
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
 * 测试表(Test)表控制层
 *
 * @author 李良发
 * @since 2023-05-06 17:44:57
 */
@RestController
public class TestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private TestService testService;

    /**
     * 分页查询
     *
     * @param page   分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PreAuthorize("hasAuthority('test:query')")
    @PostMapping(value = "test/query/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<Test>> selectPage(@ModelAttribute Page page, @RequestBody Test record) {
        Result<IPage<Test>> result = new Result<>();
        try {
            if (page.getSize() == 0) {
                page.setSize(10);
                page.setCurrent(1);
            }
            IPage<Test> t = testService.selectPage(page, record);
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
    @PreAuthorize("hasAuthority('test:query')")
    @PostMapping(value = "test/query/property", produces = "application/json;charset=UTF-8")
    public Result<List<Test>> selectByProperty(@RequestBody Test record) {
        Result<List<Test>> result = new Result<>();
        try {
            List<Test> list = testService.selectByProperty(record);
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
     * @param {uid} 主键
     * @return 单条数据
     */
    @PreAuthorize("hasAuthority('test:query')")
    @PostMapping(value = "test/query/one", produces = "application/json;charset=UTF-8")
    public Result<Test> selectByKey(@RequestBody Test record) {
        Result<Test> result = new Result<>();
        try {
            Test record1 = testService.selectByKey(record.getUid());
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
    @PreAuthorize("hasAuthority('test:add')")
    @PostMapping(value = "test/add", produces = "application/json;charset=UTF-8")
    public Result<Test> insert(@RequestBody Test record) {
        Result<Test> result = new Result<>();
        record.setUid(UUID.randomUUID().toString().replace("-", ""));
        try {
            int changeRow = testService.insert(record);
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
    @PreAuthorize("hasAuthority('test:update')")
    @PostMapping(value = "test/update", produces = "application/json;charset=UTF-8")
    public Result<Test> update(@RequestBody Test record) {
        Result<Test> result = new Result<>();
        try {
            int changeRow = testService.update(record);
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
     * @param list 实例对象主键集合
     * @return 删除个数
     */
    @PreAuthorize("hasAuthority('test:delete')")
    @PostMapping(value = "test/delete", produces = "application/json;charset=UTF-8")
    public Result<Test> deleteBatch(@RequestBody List<String> list) {
        Result<Test> result = new Result<>();
        try {
            int changeRow = testService.deleteBatch(list);
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
}
