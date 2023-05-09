package com.risc.boot.modules.system.service;

import com.risc.boot.modules.system.bo.SysUserRole;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * 用户角色关系表(SysUserRole)表服务接口
 * @author 李良发
 * @since 2023-05-08 11:01:49
 */
public interface SysUserRoleService {

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
    List<SysUserRole> selectByProperty(SysUserRole record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysUserRole> selectPage(Page page, SysUserRole record);


}
