package com.czdgyy.czyjy.core.common.request;

import cn.hutool.core.collection.CollectionUtil;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @author lcj
 * @since 2025/11/6/周四
 */
@Data
public class QueryOption {
	/**
	 * 当前页码，默认为1
	 */
	private Integer current = 1;
	/**
	 * 每页记录数，默认为10
	 */
	private Integer size = 10;
	/**
	 * 排序字段
	 */
	private List<OrderField> orderFieldList;
    /**
     * 扩展查询参数
     */
    private Map<String, Object> extraQueryMap;

	public <T> Page<T> toPage() {
		return Page.of(current, size);
	}

	public QueryWrapper getDefaultQuery() {
		QueryWrapper queryWrapper = QueryWrapper.create();
		if (CollectionUtil.isNotEmpty(orderFieldList)) {
			for (OrderField orderField : orderFieldList) {
				queryWrapper.orderBy(orderField.getFieldName(), Boolean.TRUE.equals(orderField.getAscFlag()));
			}
		}
		return queryWrapper;
	}

    @Data
    public static class AdvancedQuery {
        private String fieldName;
        private Object value;
        private String operator;
    }

}
