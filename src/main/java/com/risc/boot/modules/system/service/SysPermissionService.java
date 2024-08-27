package com.risc.boot.modules.system.service;

import com.risc.boot.common.bo.Result;
import com.risc.boot.modules.system.bo.SysPermission;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * 权限表(SysPermission)表服务接口
 * @author 李良发
 * @since 2023-05-08 11:01:01
 */
public interface SysPermissionService {

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
    List<SysPermission> selectByProperty(SysPermission record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysPermission> selectPage(Page page, SysPermission record);
    
    /**
     * 分页查询-层级结构
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysPermission> selectPageTree(Page page, SysPermission record);
    
    /**
     * 条件查询-层级结构
     * @param record 实例对象
     * @return 对象列表
     */
    List<SysPermission> selectByPropertyTree(SysPermission record);


    /**
     * 根据用户uid获取所有权限
     * @param userUid
     * @return 对象列表
     */
    List<SysPermission> selectByUserUid(String userUid);

    /**
     * 根据角色uid获取所有权限
     * @param roleUid
     * @return 对象列表
     */
    List<SysPermission> selectByRoleUid(String roleUid);
    
    /**
     * 根据角色uid查询菜单--层级结构
     * @param roleUid
     * @return
     */
    Result<List<SysPermission>> getMenuByRoleUid(String roleUid);
    
    /**
     * 分页查询-层级结构
     *
     * @param page   分页信息
     * @param record 实例对象
     * @return 集合
     */
    IPage<SysPermission> selectTreePage(Page<SysPermission> page, SysPermission record);
    
    /**
     * 通过权限名称查询权限记录
     * @param permissionName
     * @return
     */
    List<SysPermission> selectByName(String permissionName);
    
    /**
     * 获取所有权限--层级结构
     *
     * @param record (对象可传permissionType)
     * @return
     */
    Result<List<SysPermission>> selectParent(SysPermission record);
    
    /**
     * 根据权限名和父uid查询权限
     * @param permissionName
     * @param parentUid
     * @return
     */
    SysPermission selectByNameAndParentUid(String permissionName, String parentUid);
}
