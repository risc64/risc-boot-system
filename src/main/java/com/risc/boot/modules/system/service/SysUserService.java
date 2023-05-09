package com.risc.boot.modules.system.service;

import com.risc.boot.common.bo.Token;
import com.risc.boot.modules.system.bo.SysUser;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 * 用户表(SysUser)表服务接口
 * @author 李良发
 * @since 2023-05-08 10:59:18
 */
public interface SysUserService {

    /**
     * 通过ID查询单条数据
     * @param userUid 主键
     * @return 实例对象
     */
    SysUser selectByKey(String userUid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    int insert(SysUser record);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(SysUser record);

    /**
     * 通过主键删除数据
     * @param userUid 主键
     * @return 影响行数
     */
    int deleteByKey(String userUid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<SysUser> list);
    
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
    List<SysUser> selectByProperty(SysUser record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysUser> selectPage(Page page, SysUser record);


    /**
     * 通过用户名查询单条数据
     * @param userName
     * @return
     */
    SysUser selectByUserName(String userName);

    /**
     * 通过用户名查询Token
     * @param userName
     * @return
     */
    Token selectTokenByUserName(String userName);
}
