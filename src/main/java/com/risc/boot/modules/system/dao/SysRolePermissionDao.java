package com.risc.boot.modules.system.dao;

import com.risc.boot.modules.system.bo.SysRolePermission;
import com.risc.boot.modules.system.dto.SysRolePermissionDto;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 角色权限表(SysRolePermission)表数据库访问层
 * @author 李良发
 * @since 2023-05-08 11:01:20
 */
public interface SysRolePermissionDao {

    /**
     * 通过ID查询单条数据
     * @param roleUid 主键
     * @return 实例对象
     */
    SysRolePermission selectByKey(String roleUid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysRolePermission record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysRolePermission record);

    /**
     * 通过主键删除数据
     * @param roleUid 主键
     * @return 影响行数
     */
    int deleteByKey(String roleUid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<SysRolePermission> list);
    
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
    List<SysRolePermission> selectByProperty(@Param("record") SysRolePermission record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysRolePermission> selectByProperty(Page page, @Param("record") SysRolePermission record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") SysRolePermission record);

    /**
     * 根据用户uid获取权限uid集合
     * @param userUid
     * @return
     */
    List<String> selectPermissionUidByUserUid(String userUid);

    /**
     * 根据角色uid获取权限uid集合
     * @param roleUid
     * @return
     */
    List<String> selectPermissionUidByRoleUid(String roleUid);
    
    /**
     * 通过角色uid删除
     * @return
     */
    int deleteByRoleUid(String roleUid);
    
    /**
     * 根据角色uid查询
     * @param roleUid 实例对象
     * @return 对象列表
     */
    List<String> selectByRoleUid(String roleUid);
    
    /**
     * 角色权限新增
     * @return
     */
    int replaceAdd(@Param("record") SysRolePermissionDto record);
}
