import axiosutil from '@/utils/axios'
import {Result, PageInfo} from "@/api/common/ResultType.ts"

/**
 * 文件表(BaseFile)实体类
 *
 * @author makejava
 * @since 2025-08-06 11:20:36
 */
export interface BaseFile {
    
    /**
    * 主键uid
    */
    uid: string | null
    /**
    * 显示名
    */
    showName: string | null
    /**
    * 文件名
    */
    fileName: string | null
    /**
    * 文件路径
    */
    filePath: string | null
    /**
    * dfs文件路径
    */
    dfsPath: string | null
    /**
    * 临时文件标识（0 否   1是）
    */
    tempFlag: number | null
    /**
    * 文件类型（0 通用文件  ）
    */
    fileType: number | null
    /**
    * 创建人uid
    */
    createUserUid: string | null
    /**
    * 创建时间
    */
    createTime: string | null
    /**
    * 修改人uid
    */
    updateUserUid: string | null
    /**
    * 修改时间
    */
    updateTime: string | null

}

// 分页
export function getBaseFileByPage(pageInfo: PageInfo<BaseFile>,data: BaseFile): Promise<Result<PageInfo<BaseFile>>> {
  return axiosutil.post( `baseFile/query/page?current=${pageInfo.current}&size=${pageInfo.size}`, data)
}

// 条件查询
export function getBaseFileByProperty(data: BaseFile): Promise<Result<BaseFile[]>> {
  return axiosutil.post( 'baseFile/query/property', data)
}

// 查询单个
export function getBaseFileOne(data: BaseFile): Promise<Result<BaseFile>> {
  return axiosutil.post( 'baseFile/query/one', data)
}


//新增
export function addBaseFile(data: BaseFile): Promise<Result<null>> {
  return axiosutil.post('baseFile/add', data)
}

// 修改
export function updateBaseFile(data: BaseFile): Promise<Result<null>> {
  return axiosutil.post('baseFile/update', data)
}

// 删除
export function deleteBaseFile(data: BaseFile): Promise<Result<null>> {
  return axiosutil.post('baseFile/delete', data)
}
