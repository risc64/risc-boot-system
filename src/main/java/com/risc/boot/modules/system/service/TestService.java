package com.risc.boot.modules.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.risc.boot.modules.system.bo.Test;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 测试表(Test)表服务接口
 * @author 李良发
 * @since 2023-05-06 17:44:58
 */
public interface TestService {

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    Test selectByKey(String uid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(Test record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(Test record);

    /**
     * 通过主键删除数据
     * @param uid 主键
     * @return 影响行数
     */
    int deleteByKey(String uid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<Test> list);
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    int deleteBatch(List<String> list);
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    List<Test> selectByProperty(Test record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<Test> selectPage(Page page, Test record);


}
