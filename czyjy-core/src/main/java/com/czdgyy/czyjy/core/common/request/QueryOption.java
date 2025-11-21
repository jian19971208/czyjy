package com.czdgyy.czyjy.core.common.request;

import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.Data;

import java.util.List;

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

	public <T> Page<T> toPage() {
		return Page.of(current, size);
	}

	public QueryWrapper getOrderQuery() {
		QueryWrapper queryWrapper = QueryWrapper.create();
		if (orderFieldList != null) {
			for (OrderField orderField : orderFieldList) {
				queryWrapper.orderBy(orderField.getFieldName(), Boolean.TRUE.equals(orderField.getAscFlag()));
			}
		}
		return queryWrapper;
	}

}
