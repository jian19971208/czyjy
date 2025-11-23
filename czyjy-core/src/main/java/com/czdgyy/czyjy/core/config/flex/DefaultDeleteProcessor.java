package com.czdgyy.czyjy.core.config.flex;

import com.mybatisflex.core.FlexGlobalConfig;
import com.mybatisflex.core.dialect.IDialect;
import com.mybatisflex.core.logicdelete.AbstractLogicDeleteProcessor;
import com.mybatisflex.core.logicdelete.LogicDeleteProcessor;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.table.TableInfo;

import static com.mybatisflex.core.constant.SqlConsts.EQUALS;

public class DefaultDeleteProcessor extends AbstractLogicDeleteProcessor {

    @Override
    public String buildLogicDeletedSet(String logicColumn, TableInfo tableInfo, IDialect dialect) {
        //设置删除时间
        return dialect.wrap(logicColumn) + "," + dialect.wrap("deleteTime") + EQUALS + "now()";
    }

    @Override
    public Object getLogicNormalValue() {
        return FlexGlobalConfig.getDefaultConfig().getNormalValueOfLogicDelete();
    }

    @Override
    public Object getLogicDeletedValue() {
        return FlexGlobalConfig.getDefaultConfig().getDeletedValueOfLogicDelete();
    }


}
