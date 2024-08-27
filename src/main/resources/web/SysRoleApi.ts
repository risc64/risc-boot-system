import axiosutil from '@/utils/axios'
import {AxiosPromise} from "axios";
import {Result, PageInfo} from "@/api/common/ResultType.ts"

/**
 * 角色表(SysRole)实体类
 *
 * @author makejava
 * @since 2024-08-05 17:01:25
 */
export interface SysRole {
    
    /**
    * 角色uid
    */
    uid: string
    /**
    * 角色名称
    */
    roleName: string
    /**
    * 角色编码
    */
    roleCode: string
    /**
    * 描述
    */
    description: string
    /**
    * (0表示不可删除 1表示可删除）
    */
    deleteableFlag: number
    /**
    * 角色类型(0代表超级管理员  1其他)
    */
    roleType: number
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
export function getSysRoleByPage(pageInfo: PageInfo<SysRole>,data: SysRole): AxiosPromise<Result<PageInfo<SysRole>>> {
  return axiosutil.post( `sysRole/query/page?curent=${pageInfo.current}&size=${pageInfo.size}`, data)
}

// 条件查询
export function getSysRoleByProperty(data: SysRole): AxiosPromise<Result<SysRole>> {
  return axiosutil.post( 'sysRole/query/property', data)
}

// 查询单个
export function getSysRoleOne(data: SysRole): AxiosPromise<Result<SysRole>> {
  return axiosutil.post( 'sysRole/query/one', data)
}


//新增
export function addSysRole(data: SysRole): AxiosPromise<Result<null>> {
  return axiosutil.post('sysRole/add', data)
}

// 修改
export function updateSysRole(data: SysRole): AxiosPromise<Result<null>> {
  return axiosutil.post('sysRole/update', data)
}

// 删除
export function deleteSysRole(data: SysRole): AxiosPromise<Result<null>> {
  return axiosutil.post('sysRole/delete', data)
}
