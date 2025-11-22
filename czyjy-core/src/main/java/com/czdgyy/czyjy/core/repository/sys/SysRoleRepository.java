package com.czdgyy.czyjy.core.repository.sys;

import com.czdgyy.czyjy.core.mapper.sys.SysRoleMapper;
import com.czdgyy.czyjy.core.entity.sys.SysRole;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Repository;

import static com.czdgyy.czyjy.core.common.constants.SysConstants.ROLE_SUPER_ADMIN;

/**
 * @author lcj
 * @since 2025/11/21/周四
 */
@Repository
public class SysRoleRepository extends ServiceImpl<SysRoleMapper, SysRole> {

    public SysRole getSuperAdminRole() {
        return this.queryChain()
                .eq(SysRole::getRoleCode, ROLE_SUPER_ADMIN)
                .one();
    }
}

