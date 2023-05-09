package com.risc.boot.modules.system.service.Impl;

import com.risc.boot.modules.system.bo.Test;
import com.risc.boot.modules.system.dao.TestDao;
import com.risc.boot.modules.system.service.TestService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 测试表(Test)表服务实现类
 * @author 李良发
 * @since 2023-05-06 17:44:59
 */
@Service
@Transactional
public class TestServiceImpl implements TestService {

    @Resource
    private TestDao testDao;

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public Test selectByKey(String uid) {
        return testDao.selectByKey(uid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int insert(Test record) {
        return testDao.insert(record);
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(Test record) {
        return testDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param uid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String uid) {
        return testDao.deleteByKey(uid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<Test> list) {
        return testDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return testDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<Test> selectByProperty(Test record) {
        return testDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<Test> selectPage(Page page, Test record) {
        page.setSearchCount(false);
        page.setTotal(testDao.selectByPropertyCount(record));
        return testDao.selectByProperty(page, record);
    }

}
