package com.czdgyy.czyjy.admin.service.iface.impl;

import com.czdgyy.czyjy.admin.BaseTest;
import com.czdgyy.czyjy.admin.service.iface.sys.SysRoleService;
import com.czdgyy.czyjy.core.dto.req.sys.SysRoleAddReqDto;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static com.czdgyy.czyjy.core.common.constants.SysConstants.ROLE_SUPER_ADMIN;

@Slf4j
public class SysRoleServiceImplTest extends BaseTest {

    @Resource
    private SysRoleService sysRoleService;

    @Test
    public void addSysRole_test() {
        SysRoleAddReqDto req = new SysRoleAddReqDto();
        req.setRoleCode(ROLE_SUPER_ADMIN);
        req.setRoleName("超级管理员");
        req.setRemark("超管权限,只有一个账号");
        sysRoleService.addSysRole(req);
    }
}
