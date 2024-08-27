import axiosutil from '@/utils/axios'
import {AxiosPromise} from "axios";
import {Result, PageInfo} from "@/api/common/ResultType.ts"

/**
 * 用户表(SysUser)实体类
 *
 * @author makejava
 * @since 2024-08-05 17:01:34
 */
export interface SysUser {
    
    /**
    * 用户uid
    */
    uid: string
    /**
    * 用户账号
    */
    userName: string
    /**
    * 用户名称
    */
    userNick: string
    /**
    * 密码
    */
    passWord: string
    /**
    * 盐值
    */
    salt: string
    /**
    * 公开uid
    */
    openUid: string
    /**
    * 性别（0男1女）
    */
    sex: number
    /**
    * 状态（-1删除、0激活、1禁用、2待验证）
    */
    userStatus: number
    /**
    * 邮箱
    */
    email: string
    /**
    * 头像
    */
    profilePicture: string
    /**
    * 手机号
    */
    mobile: string
    /**
    * 所在地区（省）
    */
    province: string
    /**
    * 所在地区（市）
    */
    city: string
    /**
    * 所在地区（区、县）
    */
    area: string
    /**
    * 联系地址
    */
    address: string
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
    updateUserUid: string
    /**
    * 修改时间
    */
    updateTime: string

}

// 分页
export function getSysUserByPage(pageInfo: PageInfo<SysUser>,data: SysUser): AxiosPromise<Result<PageInfo<SysUser>>> {
  return axiosutil.post( `sysUser/query/page?curent=${pageInfo.current}&size=${pageInfo.size}`, data)
}

// 条件查询
export function getSysUserByProperty(data: SysUser): AxiosPromise<Result<SysUser>> {
  return axiosutil.post( 'sysUser/query/property', data)
}

// 查询单个
export function getSysUserOne(data: SysUser): AxiosPromise<Result<SysUser>> {
  return axiosutil.post( 'sysUser/query/one', data)
}


//新增
export function addSysUser(data: SysUser): AxiosPromise<Result<null>> {
  return axiosutil.post('sysUser/add', data)
}

// 修改
export function updateSysUser(data: SysUser): AxiosPromise<Result<null>> {
  return axiosutil.post('sysUser/update', data)
}

// 删除
export function deleteSysUser(data: SysUser): AxiosPromise<Result<null>> {
  return axiosutil.post('sysUser/delete', data)
}
