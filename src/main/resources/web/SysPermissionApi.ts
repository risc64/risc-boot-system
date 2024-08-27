import axiosutil from '@/utils/axios'
import {AxiosPromise} from "axios";
import {Result, PageInfo} from "@/api/common/ResultType.ts"

/**
 * 权限表(SysPermission)实体类
 *
 * @author makejava
 * @since 2024-08-09 14:43:30
 */
export interface SysPermission {
    
    /**
    * 权限uid
    */
    uid: string
    /**
    * 上一级权限uid
    */
    parentUid: string
    /**
    * 权限名
    */
    permissionName: string
    /**
    * 权限编码
    */
    permissionCode: string
    /**
    * 权限类型（0菜单、1操作）
    */
    permissionType: number
    /**
    * 请求方式(如GET,POST,PUT,DELETE)
    */
    requestMethod: string
    /**
    * 菜单路径（前端路由）
    */
    menuUrl: string
    /**
    * 前端页面
    */
    menuComponent: string
    /**
    * 权限等级
    */
    permissionLevel: number
    /**
    * 排序
    */
    sort: number
    /**
    * 图标样式
    */
    iconStyle: string
    /**
    * 创建人uid
    */
    createUserUid: string
    /**
    * 创建时间
    */
    createTime: string
    /**
    * 修改人uid
    */
    editUserUid: string
    /**
    * 修改时间
    */
    editTime: string

}

// 分页
export function getSysPermissionByPage(pageInfo: PageInfo<SysPermission>,data: SysPermission): AxiosPromise<Result<PageInfo<SysPermission>>> {
  return axiosutil.post( `sysPermission/query/page?current=${pageInfo.current}&size=${pageInfo.size}`, data)
}

// 条件查询
export function getSysPermissionByProperty(data: SysPermission): AxiosPromise<Result<SysPermission[]>> {
  return axiosutil.post( 'sysPermission/query/property', data)
}

// 查询单个
export function getSysPermissionOne(data: SysPermission): AxiosPromise<Result<SysPermission>> {
  return axiosutil.post( 'sysPermission/query/one', data)
}


//新增
export function addSysPermission(data: SysPermission): AxiosPromise<Result<null>> {
  return axiosutil.post('sysPermission/add', data)
}

// 修改
export function updateSysPermission(data: SysPermission): AxiosPromise<Result<null>> {
  return axiosutil.post('sysPermission/update', data)
}

// 删除
export function deleteSysPermission(data: SysPermission): AxiosPromise<Result<null>> {
  return axiosutil.post('sysPermission/delete', data)
}
