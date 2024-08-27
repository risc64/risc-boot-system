import axiosutil from '@/utils/axios'
import {AxiosPromise} from "axios";
import {Result, ResultPageInfo} from "@/api/common/ResultType.ts"

/**
 * 组织表(SysOrganization)实体类
 *
 * @author makejava
 * @since 2024-08-05 15:56:43
 */
export interface SysOrganization {
    
    /**
    * uid
    */
    uid: string
    /**
    * 组织名称
    */
    organizationName: string
    /**
    * 组织编码
    */
    organizationCode: string
    /**
    * 上级组织uid
    */
    parentUid: string
    /**
    * 统一社会信用代码
    */
    registId: string
    /**
    * 法定代表人/负责人
    */
    artificialPerson: string
    /**
    * 住址/住所地
    */
    address: string
    /**
    * 电话
    */
    phone: string
    /**
    * 联系人
    */
    contact: string
    /**
    * 描述
    */
    description: string
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
export function getSysOrganizationByPage(data = {}): AxiosPromise<Result<ResultPageInfo<SysOrganization>>> {
  return axiosutil.post( 'sysOrganization/query/page', data)
}

// 条件查询
export function getSysOrganizationByProperty(data = {}): AxiosPromise<Result<SysOrganization>> {
  return axiosutil.post( 'sysOrganization/query/property', data)
}

// 查询单个
export function getSysOrganizationOne(data = {}): AxiosPromise<Result<SysOrganization>> {
  return axiosutil.post( 'sysOrganization/query/one', data)
}


//新增
export function addSysOrganization(data = {}): AxiosPromise<Result<null>> {
  return axiosutil.post('sysOrganization/add', data)
}

// 修改
export function updateSysOrganization(data: unknown): AxiosPromise<Result<null>> {
  return axiosutil.post('sysOrganization/update', data)
}

// 删除
export function deleteSysOrganization(data: unknown): AxiosPromise<Result<null>> {
  return axiosutil.post('sysOrganization/delete', data)
}
