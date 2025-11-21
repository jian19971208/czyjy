package com.czdgyy.czyjy.core.dto.req.sys;

import cn.hutool.core.collection.CollectionUtil;
import com.czdgyy.czyjy.core.common.request.QueryOption;
import com.czdgyy.czyjy.core.entity.sys.SysUser;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Accessors(chain = true)
public class QuerySysUserReqDto extends QueryOption {

	private List<Long> deptIdList;

	private List<String> usernameList;

	private List<String> adminStatusCodeList;

	private List<String> nicknameList;

	private List<String> avatarList;

	private List<String> phoneList;

	private List<String> emailList;

	private List<String> genderCodeList;

	private LocalDateTime lastLoginTimeStart;

	private LocalDateTime lastLoginTimeEnd;

	private List<String> lastLoginIpList;

	private LocalDateTime createTimeStart;

	private LocalDateTime createTimeEnd;

	private List<Long> creatorIdList;

	private LocalDateTime updateTimeStart;

	private LocalDateTime updateTimeEnd;

	private List<Long> updaterIdList;

	public QueryWrapper toQuery() {
		QueryWrapper queryWrapper = this.getOrderQuery();
		queryWrapper
				.in(SysUser::getDeptId, deptIdList, CollectionUtil.isNotEmpty(deptIdList))
				.in(SysUser::getUsername, usernameList, CollectionUtil.isNotEmpty(usernameList))
				.in(SysUser::getAdminStatusCode, adminStatusCodeList, CollectionUtil.isNotEmpty(adminStatusCodeList))
				.in(SysUser::getNickname, nicknameList, CollectionUtil.isNotEmpty(nicknameList))
				.in(SysUser::getAvatar, avatarList, CollectionUtil.isNotEmpty(avatarList))
				.in(SysUser::getPhone, phoneList, CollectionUtil.isNotEmpty(phoneList))
				.in(SysUser::getEmail, emailList, CollectionUtil.isNotEmpty(emailList))
				.in(SysUser::getGenderCode, genderCodeList, CollectionUtil.isNotEmpty(genderCodeList))
				.in(SysUser::getLastLoginIp, lastLoginIpList, CollectionUtil.isNotEmpty(lastLoginIpList))
				.in(SysUser::getCreatorId, creatorIdList, CollectionUtil.isNotEmpty(creatorIdList))
				.in(SysUser::getUpdaterId, updaterIdList, CollectionUtil.isNotEmpty(updaterIdList))
				.ge(SysUser::getLastLoginTime, lastLoginTimeStart, lastLoginTimeStart != null)
				.le(SysUser::getLastLoginTime, lastLoginTimeEnd, lastLoginTimeEnd != null)
				.ge(SysUser::getCreateTime, createTimeStart, createTimeStart != null)
				.le(SysUser::getCreateTime, createTimeEnd, createTimeEnd != null)
				.ge(SysUser::getUpdateTime, updateTimeStart, updateTimeStart != null)
				.le(SysUser::getUpdateTime, updateTimeEnd, updateTimeEnd != null);
		return queryWrapper;
	}

}
