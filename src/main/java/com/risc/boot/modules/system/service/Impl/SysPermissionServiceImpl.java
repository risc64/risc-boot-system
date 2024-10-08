package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.util.ExceptionUtil;
import com.risc.boot.modules.system.bo.SysPermission;
import com.risc.boot.modules.system.dao.SysPermissionDao;
import com.risc.boot.modules.system.dao.SysRolePermissionDao;
import com.risc.boot.modules.system.service.SysPermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 权限表(SysPermission)表服务实现类
 * @author 李良发
 * @since 2023-05-08 11:01:01
 */
@Service
@Transactional
public class SysPermissionServiceImpl implements SysPermissionService {
    
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Resource
    private SysPermissionDao sysPermissionDao;
    
    @Resource
    private SysRolePermissionDao sysRolePermissionDao;

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public SysPermission selectByKey(String uid) {
        return sysPermissionDao.selectByKey(uid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(SysPermission record) {
        return sysPermissionDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(SysPermission record) {
        return sysPermissionDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param permissionUid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String permissionUid) {
        return sysPermissionDao.deleteByKey(permissionUid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<SysPermission> list) {
        return sysPermissionDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return sysPermissionDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysPermission> selectByProperty(SysPermission record) {
        return sysPermissionDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysPermission> selectPage(Page page, SysPermission record) {
        page.setSearchCount(false);
        page.setTotal(sysPermissionDao.selectByPropertyCount(record));
        return sysPermissionDao.selectByProperty(page, record);
    }
    
    /**
     * 分页查询-层级结构
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<SysPermission> selectPageTree(Page page, SysPermission record) {
        page.setSearchCount(false);
        long total = sysPermissionDao.selectByPropertyCountTree(record);
        page.setTotal(total);
        if (total != 0) {
            return sysPermissionDao.selectByPropertyTree(page, record);
        } else {
            return page;
        }
    }
    
    /**
     * 条件查询-层级结构
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<SysPermission> selectByPropertyTree(SysPermission record) {
        return sysPermissionDao.selectByPropertyTree(record);
    }

    @Override
    public List<SysPermission> selectByUserUid(String userUid) {
        List<String> permissionUidList = sysRolePermissionDao.selectPermissionUidByUserUid(userUid);
        if (permissionUidList != null && permissionUidList.size() > 0) {
            List<SysPermission> sysPermissionList = sysPermissionDao.selectByKeyList(permissionUidList);
            if (sysPermissionList != null && sysPermissionList.size() > 0) {
                return  sysPermissionList;
            }
        }
        return null;
    }

    @Override
    public List<SysPermission> selectByRoleUid(String roleUid) {
        List<String> permissionUidList = sysRolePermissionDao.selectPermissionUidByRoleUid(roleUid);
        if (permissionUidList != null && permissionUidList.size() > 0) {
            List<SysPermission> sysPermissionList = sysPermissionDao.selectByKeyList(permissionUidList);
            if (sysPermissionList != null && sysPermissionList.size() > 0) {
                return  sysPermissionList;
            }
        }
        return null;
    }
    
    @Override
    public Result<List<SysPermission>> getMenuByRoleUid(String roleUid) {
        Result<List<SysPermission>> result = new Result<>();
        try {
            List<SysPermission> list = sysPermissionDao.selectMenuByRoleUid(roleUid);
            if (list != null && list.size() > 0) {
                result.success(list);
            } else {
                result.noData();
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    /**
     * 分页查询--层级结构
     *
     * @param page
     * @param record
     * @return
     */
    @Override
    public IPage<SysPermission> selectTreePage(Page<SysPermission> page, SysPermission record) {
        return sysPermissionDao.selectTreePage(page, record);
    }
    
    @Override
    public List<SysPermission> selectByName(String permissionName) {
        return sysPermissionDao.selectByName(permissionName);
    }
    
    @Override
    public Result<List<SysPermission>> selectParent(SysPermission record) {
        Result<List<SysPermission>> result = new Result<>();
        try {
            List<SysPermission> list = sysPermissionDao.selectParent(record);
            if (list != null && list.size() > 0) {
                result.success(list);
            } else {
                result.noData();
            }
        } catch (Exception e) {
            result.exception(e);
            logger.error(ExceptionUtil.getErrorString(e));
        }
        return result;
    }
    
    @Override
    public SysPermission selectByNameAndParentUid(String permissionName, String parentUid) {
        return sysPermissionDao.selectByNameAndParentUid(permissionName, parentUid);
    }
    
    
}
