package com.risc.boot.modules.system.dao;

import com.risc.boot.modules.system.bo.SysUserOrganization;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 用户组织关系表(SysUserOrganization)表数据库访问层
 * @author 李良发
 * @since 2023-05-08 11:01:38
 */
public interface SysUserOrganizationDao {

    /**
     * 通过ID查询单条数据
     * @param uerUid 主键
     * @return 实例对象
     */
    SysUserOrganization selectByKey(String uerUid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysUserOrganization record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysUserOrganization record);

    /**
     * 通过主键删除数据
     * @param uerUid 主键
     * @return 影响行数
     */
    int deleteByKey(String uerUid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<SysUserOrganization> list);
    
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
    List<SysUserOrganization> selectByProperty(@Param("record") SysUserOrganization record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysUserOrganization> selectByProperty(Page page, @Param("record") SysUserOrganization record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") SysUserOrganization record);

    
}
