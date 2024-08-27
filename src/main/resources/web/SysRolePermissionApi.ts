import axiosutil from '@/utils/axios'
import {AxiosPromise} from "axios";
import {Result, PageInfo} from "@/api/common/ResultType.ts"

/**
 * 角色权限表(SysRolePermission)实体类
 *
 * @author makejava
 * @since 2024-08-16 14:06:12
 */
export interface SysRolePermission {
    
    /**
    * 角色uid
    */
    roleUid: string
    /**
    * 权限uid
    */
    permissionUid: string

}

// 分页
export function getSysRolePermissionByPage(pageInfo: PageInfo<SysRolePermission>,data: SysRolePermission): AxiosPromise<Result<PageInfo<SysRolePermission>>> {
  return axiosutil.post( `sysRolePermission/query/page?current=${pageInfo.current}&size=${pageInfo.size}`, data)
}

// 条件查询
export function getSysRolePermissionByProperty(data: SysRolePermission): AxiosPromise<Result<SysRolePermission[]>> {
  return axiosutil.post( 'sysRolePermission/query/property', data)
}

// 查询单个
export function getSysRolePermissionOne(data: SysRolePermission): AxiosPromise<Result<SysRolePermission>> {
  return axiosutil.post( 'sysRolePermission/query/one', data)
}


//新增
export function addSysRolePermission(data: SysRolePermission): AxiosPromise<Result<null>> {
  return axiosutil.post('sysRolePermission/add', data)
}

// 修改
export function updateSysRolePermission(data: SysRolePermission): AxiosPromise<Result<null>> {
  return axiosutil.post('sysRolePermission/update', data)
}

// 删除
export function deleteSysRolePermission(data: SysRolePermission): AxiosPromise<Result<null>> {
  return axiosutil.post('sysRolePermission/delete', data)
}
