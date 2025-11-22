package com.czdgyy.czyjy.core.dto.res.sys;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Data
public class SysUserLoginResDto {

    private UserResDto user;

    private RoleResDto role;

    private List<PermissionResDto> permissions;

    private String token;


    @Data
    public static class UserResDto {
        /**
         * 用户名
         */
        private String username;

        /**
         * 密码
         */
        private String password;

        /**
         * 状态编码
         */
        private String adminStatusCode;

        /**
         * 状态名称
         */
        private String adminStatusName;

        /**
         * 昵称
         */
        private String nickname;

        /**
         * 头像
         */
        private String avatar;

        /**
         * 手机号
         */
        private String phone;

        /**
         * 邮箱
         */
        private String email;

        /**
         * 性别编码
         */
        private String genderCode;

        /**
         * 性别名称
         */
        private String genderName;

        /**
         * 最后登录时间
         */
        private LocalDateTime lastLoginTime;

        /**
         * 最后登录IP
         */
        private String lastLoginIp;
    }

    @Data
    public static class RoleResDto {
        private Long id;

        private String roleCode;

        private String roleName;
    }

    @Data
    public static class PermissionResDto {
        private Long id;

        private String permissionCode;

        private String permissionName;
    }
}
