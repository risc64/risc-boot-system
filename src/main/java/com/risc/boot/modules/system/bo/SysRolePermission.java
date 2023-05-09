package com.risc.boot.modules.system.bo;

import java.io.Serializable;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 角色权限表(SysRolePermission)实体类
 *
 * @author 李良发
 * @since 2023-05-08 11:01:20
 */
@Data
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = -64630217244934266L;
    
    /**
    * 角色uid
    */
    private String roleUid;
    
    /**
    * 权限uid
    */
    private String permissionUid;
    


}
