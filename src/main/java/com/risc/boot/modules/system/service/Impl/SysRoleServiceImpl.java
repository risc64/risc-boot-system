package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysRole;
import com.risc.boot.modules.system.dao.SysRoleDao;
import com.risc.boot.modules.system.service.SysRoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色表(SysRole)表服务实现类
 * @author 李良发
 * @since 2023-05-08 11:00:08
 */
@Service
@Transactional
public class SysRoleServiceImpl implements SysRoleService {

    @Resource
    private SysRoleDao sysRoleDao;

    /**
     * 通过ID查询单条数据
     * @param roleUid 主键
     * @return 实例对象
     */
    @Override
    public SysRole selectByKey(String roleUid) {
        return sysRoleDao.selectByKey(roleUid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysRole record) {
        return sysRoleDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysRole record) {
        return sysRoleDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param roleUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String roleUid) {
        return sysRoleDao.deleteByKey(roleUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysRole> list) {
        return sysRoleDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysRoleDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysRole> selectByProperty(SysRole record) {
        return sysRoleDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysRole> selectPage(Page page, SysRole record) {
        page.setSearchCount(false);
        page.setTotal(sysRoleDao.selectByPropertyCount(record));
        return sysRoleDao.selectByProperty(page, record);
    }

}
