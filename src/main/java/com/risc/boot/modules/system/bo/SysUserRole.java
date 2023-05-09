package com.risc.boot.modules.system.bo;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 用户角色关系表(SysUserRole)实体类
 *
 * @author 李良发
 * @since 2023-05-08 11:01:48
 */
@Data
public class SysUserRole implements Serializable {

    private static final long serialVersionUID = 663740660716164297L;
    
    /**
    * 用户uid
    */
    private String userUid;
    
    /**
    * 角色uid
    */
    private String roleUid;
    


}
