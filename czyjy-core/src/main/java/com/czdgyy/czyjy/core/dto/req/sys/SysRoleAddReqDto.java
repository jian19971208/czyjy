package com.czdgyy.czyjy.core.dto.req.sys;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SysRoleAddReqDto {

    @NotEmpty(message = "角色编码不能为空")
    private String roleCode;

    @NotEmpty(message = "角色名称不能为空")
    private String roleName;

    private String remark;
}
