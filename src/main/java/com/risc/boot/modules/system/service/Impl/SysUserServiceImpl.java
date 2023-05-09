package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.common.bo.Token;
import com.risc.boot.modules.system.bo.SysUser;
import com.risc.boot.modules.system.dao.SysUserDao;
import com.risc.boot.modules.system.service.SysUserService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户表(SysUser)表服务实现类
 * @author 李良发
 * @since 2023-05-08 10:59:18
 */
@Service
@Transactional
public class SysUserServiceImpl implements SysUserService {

    @Resource
    private SysUserDao sysUserDao;

    /**
     * 通过ID查询单条数据
     * @param userUid 主键
     * @return 实例对象
     */
    @Override
    public SysUser selectByKey(String userUid) {
        return sysUserDao.selectByKey(userUid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysUser record) {
        return sysUserDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysUser record) {
        return sysUserDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param userUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String userUid) {
        return sysUserDao.deleteByKey(userUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysUser> list) {
        return sysUserDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysUserDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysUser> selectByProperty(SysUser record) {
        return sysUserDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysUser> selectPage(Page page, SysUser record) {
        page.setSearchCount(false);
        page.setTotal(sysUserDao.selectByPropertyCount(record));
        return sysUserDao.selectByProperty(page, record);
    }

    @Override
    public SysUser selectByUserName(String userName) {
        return sysUserDao.selectByUserName(userName);
    }

    @Override
    public Token selectTokenByUserName(String userName) {
        return sysUserDao.selectTokenByUserName(userName);
    }

}
