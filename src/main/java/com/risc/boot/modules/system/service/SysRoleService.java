package com.risc.boot.modules.system.service;

import com.risc.boot.modules.system.bo.SysRole;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * 角色表(SysRole)表服务接口
 * @author 李良发
 * @since 2023-05-08 11:00:08
 */
public interface SysRoleService {

    /**
     * 通过ID查询单条数据
     * @param roleUid 主键
     * @return 实例对象
     */
    SysRole selectByKey(String roleUid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysRole record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysRole record);

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
    int insertBatch(List<SysRole> list);
    
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
    List<SysRole> selectByProperty(SysRole record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysRole> selectPage(Page page, SysRole record);


}
