package com.risc.boot.modules.base.service;

import com.risc.boot.common.bo.Result;
import com.risc.boot.modules.base.bo.BaseFile;
import java.util.List;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件表(BaseFile)表服务接口
 * @author makejava
 * @since 2025-08-06 09:32:11
 */
public interface BaseFileService {

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    BaseFile selectByKey(String uid);

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    Result<BaseFile> insert(BaseFile record, MultipartFile file);

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    int update(BaseFile record);

    /**
     * 通过主键删除数据
     * @param uid 主键
     * @return 影响行数
     */
    int deleteByKey(String uid);
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    int insertBatch(List<BaseFile> list);
    
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
    List<BaseFile> selectByProperty(BaseFile record);
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    IPage<BaseFile> selectPage(Page page, BaseFile record);


}
