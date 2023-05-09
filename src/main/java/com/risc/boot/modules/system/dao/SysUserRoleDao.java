package com.risc.boot.modules.system.dao;

import com.risc.boot.modules.system.bo.SysUserRole;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 用户角色关系表(SysUserRole)表数据库访问层
 * @author 李良发
 * @since 2023-05-08 11:01:49
 */
public interface SysUserRoleDao {

    /**
     * 通过ID查询单条数据
     * @param userUid 主键
     * @return 实例对象
     */
    SysUserRole selectByKey(String userUid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysUserRole record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysUserRole record);

    /**
     * 通过主键删除数据
     * @param userUid 主键
     * @return 影响行数
     */
    int deleteByKey(String userUid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<SysUserRole> list);
    
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
    List<SysUserRole> selectByProperty(@Param("record") SysUserRole record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysUserRole> selectByProperty(Page page, @Param("record") SysUserRole record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") SysUserRole record);

    
}
