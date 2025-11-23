package com.czdgyy.czyjy.admin.service.iface.impl;

import com.czdgyy.czyjy.admin.BaseTest;
import com.czdgyy.czyjy.admin.service.iface.sys.SysUserService;
import com.czdgyy.czyjy.core.dto.req.sys.SysUserAddReqDto;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class SysUserServiceTest extends BaseTest {
    @Resource
    private SysUserService sysUserService;

    @Test
    public void addSysUser_test() {
        SysUserAddReqDto req = new SysUserAddReqDto();
        req.setUsername("admin");
        req.setPassword("admin");

        //sysUserService.addSysUser();
    }
}
