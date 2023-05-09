package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysOrganization;
import com.risc.boot.modules.system.dao.SysOrganizationDao;
import com.risc.boot.modules.system.service.SysOrganizationService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 组织表(SysOrganization)表服务实现类
 * @author 李良发
 * @since 2023-05-08 11:00:35
 */
@Service
@Transactional
public class SysOrganizationServiceImpl implements SysOrganizationService {

    @Resource
    private SysOrganizationDao sysOrganizationDao;

    /**
     * 通过ID查询单条数据
     * @param organizationUid 主键
     * @return 实例对象
     */
    @Override
    public SysOrganization selectByKey(String organizationUid) {
        return sysOrganizationDao.selectByKey(organizationUid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysOrganization record) {
        return sysOrganizationDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysOrganization record) {
        return sysOrganizationDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param organizationUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String organizationUid) {
        return sysOrganizationDao.deleteByKey(organizationUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysOrganization> list) {
        return sysOrganizationDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysOrganizationDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysOrganization> selectByProperty(SysOrganization record) {
        return sysOrganizationDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysOrganization> selectPage(Page page, SysOrganization record) {
        page.setSearchCount(false);
        page.setTotal(sysOrganizationDao.selectByPropertyCount(record));
        return sysOrganizationDao.selectByProperty(page, record);
    }

}
