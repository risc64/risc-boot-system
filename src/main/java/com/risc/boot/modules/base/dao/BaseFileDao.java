package com.risc.boot.modules.base.dao;

import com.risc.boot.modules.base.bo.BaseFile;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 文件表(BaseFile)表数据库访问层
 * @author makejava
 * @since 2025-08-06 09:32:01
 */
public interface BaseFileDao {

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    BaseFile selectByKey(String uid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(BaseFile record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(BaseFile record);

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
    int insertBatch(List<BaseFile> list);
    
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
    List<BaseFile> selectByProperty(@Param("record") BaseFile record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<BaseFile> selectByProperty(Page page, @Param("record") BaseFile record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") BaseFile record);

    
}
