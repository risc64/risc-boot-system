package com.risc.boot.modules.system.dao;

import com.risc.boot.common.bo.Token;
import com.risc.boot.modules.system.bo.SysUser;
import org.apache.ibatis.annotations.Param;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * 用户表(SysUser)表数据库访问层
 * @author 李良发
 * @since 2023-05-08 10:59:18
 */
public interface SysUserDao {

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
    List<SysUser> selectByProperty(@Param("record") SysUser record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<SysUser> selectByProperty(Page page, @Param("record") SysUser record);
    
    /**
     * 分页查询 Count
     * @param record 实例对象
     * @return 总页数
     */
    Long selectByPropertyCount(@Param("record") SysUser record);

    /**
     * 通过用户名查询单条数据
     * @param userName
     * @return
     */
    SysUser selectByUserName(@Param("userName") String userName);

    /**
     * 通过用户名查询Token
     * @param userName
     * @return
     */
    Token selectTokenByUserName(@Param("userName") String userName);
}
