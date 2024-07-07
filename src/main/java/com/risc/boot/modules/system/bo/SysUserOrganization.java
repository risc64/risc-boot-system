package com.risc.boot.modules.system.bo;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户组织关系表(SysUserOrganization)实体类
 *
 * @author 李良发
 * @since 2023-05-08 11:01:38
 */
@Data
public class SysUserOrganization implements Serializable {

    private static final long serialVersionUID = -26331869644616434L;
    
    /**
    * 用户uid
    */
    private String userUid;
    
    /**
    * 组织uid
    */
    private String organizationUid;
    


}
