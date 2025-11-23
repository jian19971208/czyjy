package com.czdgyy.czyjy.core.dto.req.sys;

import com.czdgyy.czyjy.core.common.request.QueryOption;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class SysRoleQueryReqDto extends QueryOption {

    private List<Long> roleIdList;

    private List<String> roleCodeList;

    private List<String> roleNameList;

    public QueryWrapper toQuery() {
        QueryWrapper queryWrapper = this.getOrderQuery();

    }
}
