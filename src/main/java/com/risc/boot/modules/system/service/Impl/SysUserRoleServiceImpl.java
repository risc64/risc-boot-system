package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysUserRole;
import com.risc.boot.modules.system.dao.SysUserRoleDao;
import com.risc.boot.modules.system.service.SysUserRoleService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色关系表(SysUserRole)表服务实现类
 * @author 李良发
 * @since 2023-05-08 11:01:49
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements SysUserRoleService {

    @Resource
    private SysUserRoleDao sysUserRoleDao;

    /**
     * 通过ID查询单条数据
     * @param userUid 主键
     * @return 实例对象
     */
    @Override
    public SysUserRole selectByKey(String userUid) {
        return sysUserRoleDao.selectByKey(userUid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysUserRole record) {
        return sysUserRoleDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysUserRole record) {
        return sysUserRoleDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param userUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String userUid) {
        return sysUserRoleDao.deleteByKey(userUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysUserRole> list) {
        return sysUserRoleDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysUserRoleDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysUserRole> selectByProperty(SysUserRole record) {
        return sysUserRoleDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysUserRole> selectPage(Page page, SysUserRole record) {
        page.setSearchCount(false);
        page.setTotal(sysUserRoleDao.selectByPropertyCount(record));
        return sysUserRoleDao.selectByProperty(page, record);
    }

}
