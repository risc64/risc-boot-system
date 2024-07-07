package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysUserOrganization;
import com.risc.boot.modules.system.dao.SysUserOrganizationDao;
import com.risc.boot.modules.system.service.SysUserOrganizationService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户组织关系表(SysUserOrganization)表服务实现类
 * @author 李良发
 * @since 2023-05-08 11:01:38
 */
@Service
@Transactional
public class SysUserOrganizationServiceImpl implements SysUserOrganizationService {

    @Resource
    private SysUserOrganizationDao sysUserOrganizationDao;

    /**
     * 通过ID查询单条数据
     * @param userUid 主键
     * @return 实例对象
     */
    @Override
    public SysUserOrganization selectByKey(String userUid) {
        return sysUserOrganizationDao.selectByKey(userUid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysUserOrganization record) {
        return sysUserOrganizationDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysUserOrganization record) {
        return sysUserOrganizationDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param userUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String userUid) {
        return sysUserOrganizationDao.deleteByKey(userUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysUserOrganization> list) {
        return sysUserOrganizationDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysUserOrganizationDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysUserOrganization> selectByProperty(SysUserOrganization record) {
        return sysUserOrganizationDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysUserOrganization> selectPage(Page page, SysUserOrganization record) {
        page.setSearchCount(false);
        page.setTotal(sysUserOrganizationDao.selectByPropertyCount(record));
        return sysUserOrganizationDao.selectByProperty(page, record);
    }

}
