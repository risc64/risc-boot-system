package com.risc.boot.modules.base.service.Impl;

import com.risc.boot.common.bo.Result;
import com.risc.boot.common.bo.StatusEnum;
import com.risc.boot.common.constant.SystemConstants;
import com.risc.boot.common.util.ExceptionUtil;
import com.risc.boot.modules.base.bo.BaseFile;
import com.risc.boot.modules.base.dao.BaseFileDao;
import com.risc.boot.modules.base.service.BaseFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.List;

/**
 * 文件表(BaseFile)表服务实现类
 * @author makejava
 * @since 2025-08-06 09:32:14
 */
@Service
@Transactional
@Slf4j
public class BaseFileServiceImpl implements BaseFileService {

    @Resource
    private BaseFileDao baseFileDao;

    /**
     * 通过ID查询单条数据
     * @param uid 主键
     * @return 实例对象
     */
    @Override
    public BaseFile selectByKey(String uid) {
        return baseFileDao.selectByKey(uid);
    }

    /**
     * 新增数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public Result<BaseFile> insert(BaseFile record, MultipartFile file) {
        Result<BaseFile> result = new Result<>();
        ApplicationHome h = new ApplicationHome(this.getClass());
        String basePath = h.getSource().getParent().toString()+ SystemConstants.FILE_ROOT_PATH + "/";
        String fileName = file.getOriginalFilename();
        int index = fileName.lastIndexOf(".");
        String suffix = "";
        if (index > 0) {
            suffix = fileName.substring(index);
        }
        String filePath = SystemConstants.BASE_FILE_PATH + "/" + record.getUid() + suffix;
        File dest = new File(basePath + filePath);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            record.setFilePath(filePath);
            record.setFileName(fileName);
            int changeRow = baseFileDao.insert(record);
            if (changeRow > 0) {
                result.setStatusEnum(StatusEnum.OK, record);
            } else {
                result.setStatusEnum(StatusEnum.ERROR, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error("文件上传失败" + ExceptionUtil.getErrorString(e));
            result.exception(e);
        }
        return result;
    }

    /**
     * 修改数据
     * @param record 实例对象
     * @return 影响行数
     */
    @Override
    public int update(BaseFile record) {
        return baseFileDao.update(record);
    }

    /**
     * 通过主键删除数据
     * @param uid 主键
     * @return 影响行数
     */
    @Override
    public int deleteByKey(String uid) {
        return baseFileDao.deleteByKey(uid);
    }
    
    /**
     * 批量新增数据
     * @param list 实例集合
     * @return 影响行数
     */
    @Override
    public int insertBatch(List<BaseFile> list) {
        return baseFileDao.insertBatch(list);
    }
    
    /**
     * 通过主键删除数据
     * @param list 主键集合
     * @return 影响行数
     */
    @Override
    public int deleteBatch(List<String> list) {
        return baseFileDao.deleteBatch(list);
    }
    
    /**
     * 条件查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public List<BaseFile> selectByProperty(BaseFile record) {
        return baseFileDao.selectByProperty(record);
    }
    
    /**
     * 分页查询
     * @param record 实例对象
     * @return 对象列表
     */
    @Override
    public IPage<BaseFile> selectPage(Page page, BaseFile record) {
        page.setSearchCount(false);
        page.setTotal(baseFileDao.selectByPropertyCount(record));
        return baseFileDao.selectByProperty(page, record);
    }

}
