package com.czdgyy.czyjy.core.dto.res.sys;

import com.czdgyy.czyjy.core.common.response.CommonResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysRoleQueryResDto extends CommonResult {
    /**
     * 角色编码
     */
    private String roleCode;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 备注
     */
    private String remark;
}
