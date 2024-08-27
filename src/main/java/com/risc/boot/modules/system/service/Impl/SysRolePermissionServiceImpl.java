package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.SysRolePermission;
import com.risc.boot.modules.system.dao.SysPermissionDao;
import com.risc.boot.modules.system.dao.SysRolePermissionDao;
import com.risc.boot.modules.system.dto.SysRolePermissionDto;
import com.risc.boot.modules.system.service.SysRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private SysPermissionDao sysPermissionDao;
    
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
    
    /**
     * 通过角色uid删除
     * @return
     */
    @Override
    public int deleteByRoleUid(String roleUid) {
        return sysRolePermissionDao.deleteByRoleUid(roleUid);
    }
    
    /**
     * 通过角色uid查询
     * 查询角色的权限uid，去掉父级的uid，前端显示时，如果子集全选父级会自动勾选上
     * @param roleUid 实例对象
     * @return
     */
    @Override
    public List<String> selectByRoleUid(String roleUid) {
        List<String> permissionUidList = sysRolePermissionDao.selectByRoleUid(roleUid);
        List<String> parentUidList = sysPermissionDao.selectParentUid(permissionUidList);
        parentUidList = new ArrayList<>(new HashSet<>(parentUidList));
        permissionUidList.removeAll(parentUidList);
        return permissionUidList;
    }
    
    /**
     * 角色权限更新
     * 递归遍历父节点进入权限
     * @param record
     * @return
     */
    @Override
    public int sysRolePermissionReplace(SysRolePermissionDto record) {
        int delRow = sysRolePermissionDao.deleteByRoleUid(record.getRoleUid());
        List<String> permissionUidList = record.getPermissionUidList();
        List<String> permissionUidList1 = new ArrayList<>();
        List<String> permissionUidList2 = new ArrayList<>();
        if (permissionUidList != null && permissionUidList.size() > 0) {
            permissionUidList1 = sysPermissionDao.selectParentUid(permissionUidList);
            permissionUidList1 = new ArrayList<>(new HashSet<>(permissionUidList1));
            permissionUidList1 = permissionUidList1.stream().filter(java.util.Objects::nonNull).collect(Collectors.toList());
            if (permissionUidList1.size() > 0) {
                permissionUidList.addAll(permissionUidList1);
            }
        }
        while (permissionUidList1.size() > 0) {
            permissionUidList2 = sysPermissionDao.selectParentUid(permissionUidList1);
            permissionUidList2 = new ArrayList<>(new HashSet<>(permissionUidList2));
            permissionUidList2 = permissionUidList2.stream().filter(java.util.Objects::nonNull).collect(Collectors.toList());
            if (permissionUidList2.size() > 0) {
                permissionUidList.addAll(permissionUidList2);
            } else {
                permissionUidList2.clear();
            }
            permissionUidList1 = permissionUidList2;
        }
        permissionUidList = new ArrayList<>(new HashSet<>(permissionUidList));
        record.setPermissionUidList(permissionUidList);
        int addRow = sysRolePermissionDao.replaceAdd(record);
        return (delRow+addRow);
    }
    
    
}
