package com.risc.boot.modules.system.dao;

import com.risc.boot.modules.system.bo.SysOrganization;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 组织表(SysOrganization)表数据库访问层
 * @author 李良发
 * @since 2023-05-08 11:00:35
 */
public interface SysOrganizationDao {

    /**
     * 通过ID查询单条数据
     * @param organizationUid 主键
     * @return 实例对象
     */
    SysOrganization selectByKey(String organizationUid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysOrganization record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysOrganization record);

    /**
     * 通过主键删除数据
     * @param organizationUid 主键
     * @return 影响行数
     */
    int deleteByKey(String organizationUid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<SysOrganization> list);
    
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
    List<SysOrganization> selectByProperty(@Param("record") SysOrganization record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysOrganization> selectByProperty(Page page, @Param("record") SysOrganization record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") SysOrganization record);

    
}
