package com.risc.boot.modules.system.bo;

import java.util.Date;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 组织表(SysOrganization)实体类
 *
 * @author 李良发
 * @since 2023-05-08 11:00:35
 */
@Data
public class SysOrganization implements Serializable {

    private static final long serialVersionUID = 123333706843951691L;
    
    /**
    * uid
    */
    private String uid;
    
    /**
    * 组织名称
    */
    private String organizationName;
    
    /**
    * 组织编码
    */
    private String organizationCode;
    
    /**
    * 上级组织uid
    */
    private String parentUid;
    
    /**
    * 统一社会信用代码
    */
    private String registId;
    
    /**
    * 法定代表人/负责人
    */
    private String artificialPerson;
    
    /**
    * 住址/住所地
    */
    private String address;
    
    /**
    * 电话
    */
    private String phone;
    
    /**
    * 联系人
    */
    private String contact;
    
    /**
    * 描述
    */
    private String description;
    
    /**
    * 所在地区（省）
    */
    private String province;
    
    /**
    * 所在地区（市）
    */
    private String city;
    
    /**
    * 所在地区（区、县）
    */
    private String area;
    
    /**
    * 创建人uid
    */
    private String createUserUid;
    
    /**
    * 创建时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date createTime;
    
    /**
    * 修改人uid
    */
    private String editUserUid;
    
    /**
    * 修改时间
    */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date editTime;
    

    private List<SysOrganization> children;
    
    private String childrenUidStr;
    
}
