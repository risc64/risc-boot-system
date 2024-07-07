package com.risc.boot.modules.system.dao;

import com.risc.boot.modules.system.bo.SysPermission;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 权限表(SysPermission)表数据库访问层
 * @author 李良发
 * @since 2023-05-08 11:01:01
 */
public interface SysPermissionDao {

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    SysPermission selectByKey(String uid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysPermission record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysPermission record);

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
    int insertBatch(List<SysPermission> list);
    
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
    List<SysPermission> selectByProperty(@Param("record") SysPermission record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysPermission> selectByProperty(Page page, @Param("record") SysPermission record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") SysPermission record);

    /**
     * 通过uid集合查询所有
     * @return
     */
    List<SysPermission> selectByKeyList(List<String> list);
    
    /**
     * 分页查询--层级结构
     * @param page
     * @param record
     * @return
     */
    IPage<SysPermission> selectTreePage(Page<SysPermission> page, @Param("record") SysPermission record);
    
    /**
     * 根据角色获取-树形
     * @param roleUid
     * @return
     */
    List<SysPermission> selectMenuByRoleUid(@Param("roleUid") String roleUid);
    
    List<SysPermission> selectByName(String permissionName);
    
    /**
     * 条件查询查询菜单--层级结构
     * @return
     */
    List<SysPermission> selectParent(@Param("record") SysPermission record);
    
    /**
     * 根据uid集合查询父uid
     * @param list
     * @return
     */
    List<String> selectParentUid(@Param("list") List<String> list);
    
    /**
     * 通过权限名和父uid查询权限对象
     * @param permissionName
     * @param parentUid
     * @return
     */
    SysPermission selectByNameAndParentUid(@Param("permissionName") String permissionName, @Param("parentUid") String parentUid);
}
