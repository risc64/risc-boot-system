package com.risc.boot.modules.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 角色权限数据传输类
 * @author liliangfa
 * @date 2024/8/16 下午3:24
 * @since V 1.0
 */
@Data
public class SysRolePermissionDto implements Serializable {
    
    private String roleUid;
    
    private List<String> permissionUidList;
    
    
}
