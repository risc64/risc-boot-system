package com.risc.boot.modules.system.service;

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
     * @param permissionUid 主键
     * @return 实例对象
     */
    SysPermission selectByKey(String permissionUid);

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
     * @param permissionUid 主键
     * @return 影响行数
     */
    int deleteByKey(String permissionUid);
    
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
}
