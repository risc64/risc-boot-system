package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysRolePermission;
import com.risc.boot.modules.system.dao.SysRolePermissionDao;
import com.risc.boot.modules.system.service.SysRolePermissionService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色权限表(SysRolePermission)表服务实现类
 * @author 李良发
 * @since 2023-05-08 11:01:20
 */
@Service
@Transactional
public class SysRolePermissionServiceImpl implements SysRolePermissionService {

    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    /**
     * 通过ID查询单条数据
     * @param roleUid 主键
     * @return 实例对象
     */
    @Override
    public SysRolePermission selectByKey(String roleUid) {
        return sysRolePermissionDao.selectByKey(roleUid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysRolePermission record) {
        return sysRolePermissionDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysRolePermission record) {
        return sysRolePermissionDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param roleUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String roleUid) {
        return sysRolePermissionDao.deleteByKey(roleUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysRolePermission> list) {
        return sysRolePermissionDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysRolePermissionDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysRolePermission> selectByProperty(SysRolePermission record) {
        return sysRolePermissionDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysRolePermission> selectPage(Page page, SysRolePermission record) {
        page.setSearchCount(false);
        page.setTotal(sysRolePermissionDao.selectByPropertyCount(record));
        return sysRolePermissionDao.selectByProperty(page, record);
    }

}
