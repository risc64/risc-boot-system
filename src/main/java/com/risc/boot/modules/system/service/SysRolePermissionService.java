package com.risc.boot.modules.system.service;

import com.risc.boot.modules.system.bo.SysRolePermission;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.risc.boot.modules.system.dto.SysRolePermissionDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * 角色权限表(SysRolePermission)表服务接口
 * @author 李良发
 * @since 2023-05-08 11:01:20
 */
public interface SysRolePermissionService {

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
    List<SysRolePermission> selectByProperty(SysRolePermission record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysRolePermission> selectPage(Page page, SysRolePermission record);
    
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
     * 角色权限更新
     * @return
     */
    int sysRolePermissionReplace(SysRolePermissionDto record);
}
