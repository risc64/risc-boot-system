package com.risc.boot.controller;

import com.risc.boot.bo.Result;
import com.risc.boot.bo.StatusEnum;
import com.risc.boot.bo.Test;
import com.risc.boot.service.TestService;
import com.risc.boot.util.ExceptionUtil;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.annotation.Resource;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


/**
 * 测试表(Test)表控制层
 * @author 李良发
 * @since 2023-04-05 00:08:48
 */
@RestController
public class TestController {

    private Logger logger =  LoggerFactory.getLogger(this.getClass());

    /**
     * 服务对象
     */
    @Resource
    private TestService testService;

    /**
     * 分页查询
     * @param page 分页信息
     * @param record 实例对象
     * @return 集合
     */
    @PostMapping(value = "test/page", produces = "application/json;charset=UTF-8")
    public Result<IPage<Test>> selectPage(@ModelAttribute Page page, @RequestBody Test record) {
        Result<IPage<Test>> result = new Result<>();
        try {
            if (page.getSize() == 0 ) {
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
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 集合
     */
    @PostMapping(value = "test/property", produces = "application/json;charset=UTF-8")
    public Result<List<Test>> selectByProperty(@RequestBody Test record) {
        Result<List<Test>> result = new Result<>();
        try {
            List<Test> list = testService.selectByProperty(record);
            if (list != null && list.size() > 0)  {
                result.setStatusEnum(StatusEnum.OK, list);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    /**
     * 根据key查询
     * @param {uid} 主键
     * @return 单条数据
     */
    @GetMapping(value = "test/{uid}", produces = "application/json;charset=UTF-8")
    public Result<Test> selectByKey(@PathVariable String uid) {
        Result<Test> result = new Result<>();
        try {
            Test record = testService.selectByKey(uid);
            if (record != null)  {
                result.setStatusEnum(StatusEnum.OK, record);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    /**
     * 新增
     * @param record 实例对象
     * @return 新增个数
     */
    @PostMapping(value = "test", produces = "application/json;charset=UTF-8")
    public Result<Test> insert(@RequestBody Test record) {
        Result<Test> result = new Result<>();
                record.setUid(UUID.randomUUID().toString().replace("-", ""));
                try {
            int changeRow = testService.insert(record);
            if (changeRow > 0)  {
                result.setStatusEnum(StatusEnum.OK, null);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    /**
     * 修改
     * @param record 实例对象
     * @return 修改个数
     */
    @PutMapping(value = "test", produces = "application/json;charset=UTF-8")
    public Result<Test> update(@RequestBody Test record) {
        Result<Test> result = new Result<>(); 
        try {
            int changeRow = testService.update(record);
            if (changeRow > 0)  {
                result.setStatusEnum(StatusEnum.OK, null);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }

    /**
     * 批量删除
     * @param list 实例对象主键集合
     * @return 删除个数
     */
    @DeleteMapping(value = "test/batch", produces = "application/json;charset=UTF-8")
    public Result<Test> deleteBatch(@RequestBody List<String> list) {
        Result<Test> result = new Result<>();
        try {
            int changeRow = testService.deleteBatch(list);
            if (changeRow > 0)  {
                result.setStatusEnum(StatusEnum.OK, null);
            } else {
                result.setStatusEnum(StatusEnum.NOT_DATA, null);
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
}
