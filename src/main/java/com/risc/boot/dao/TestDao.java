package com.risc.boot.dao;

import com.risc.boot.bo.Test;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 测试表(Test)表数据库访问层
 * @author 李良发
 * @since 2023-04-05 00:08:48
 */
public interface TestDao {

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
    List<Test> selectByProperty(@Param("record") Test record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<Test> selectByProperty(Page page, @Param("record") Test record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") Test record);

    
}
