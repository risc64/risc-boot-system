package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.common.bo.Token;
import com.risc.boot.modules.system.bo.SysUser;
import com.risc.boot.modules.system.bo.SysUserOrganization;
import com.risc.boot.modules.system.bo.SysUserRole;
import com.risc.boot.modules.system.dao.SysUserDao;
import com.risc.boot.modules.system.dao.SysUserOrganizationDao;
import com.risc.boot.modules.system.dao.SysUserRoleDao;
import com.risc.boot.modules.system.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    
    @Resource
    private SysUserOrganizationDao sysUserOrganizationDao;
    
    @Resource
    private SysUserRoleDao sysUserRoleDao;
    
    @Resource
    PasswordEncoder passwordEncoder;

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
        if (StringUtils.isNotBlank(record.getPassWord())) {
            String passwd = passwordEncoder.encode(record.getPassWord());
            record.setPassWord(passwd);
        }
        int addRow = sysUserDao.insert(record);
        if (StringUtils.isNotBlank(record.getOrganizationUid())) {
            SysUserOrganization sysUserOrganization = new SysUserOrganization();
            sysUserOrganization.setOrganizationUid(record.getOrganizationUid());
            sysUserOrganization.setUserUid(record.getUid());
            addRow += sysUserOrganizationDao.insert(sysUserOrganization);
        }
        if (StringUtils.isNotBlank(record.getRoleUid())) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleUid(record.getRoleUid());
            sysUserRole.setUserUid(record.getUid());
            addRow += sysUserRoleDao.insert(sysUserRole);
        }
        return addRow;
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysUser record) {
        if (StringUtils.isNotBlank(record.getPassWord())) {
            String passwd = passwordEncoder.encode(record.getPassWord());
            record.setPassWord(passwd);
        }
        if (StringUtils.isNotBlank(record.getOrganizationUid())) {
            SysUserOrganization sysUserOrganization = new SysUserOrganization();
            sysUserOrganization.setOrganizationUid(record.getOrganizationUid());
            sysUserOrganization.setUserUid(record.getUid());
            sysUserOrganizationDao.deleteByKey(record.getUid());
            sysUserOrganizationDao.insert(sysUserOrganization);
       }
        if (StringUtils.isNotBlank(record.getRoleUid())) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleUid(record.getRoleUid());
            sysUserRole.setUserUid(record.getUid());
            sysUserRoleDao.deleteByKey(record.getUid());
            sysUserRoleDao.insert(sysUserRole);
       }
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
    
    @Override
    public int deleteBatchLogical(List<String> list, String updateUserUid) {
        return sysUserDao.deleteBatchLogical(list, updateUserUid);
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
    
    @Override
    public SysUser checkUserName(String uid, String userName) {
        return sysUserDao.checkUserName(uid, userName);
    }
    
    @Override
    public int updateProfilePicture(SysUser record) {
        return sysUserDao.updateProfilePicture(record);
    }
    
}
