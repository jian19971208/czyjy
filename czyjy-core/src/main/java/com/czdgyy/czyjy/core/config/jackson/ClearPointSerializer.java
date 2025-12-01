package com.czdgyy.czyjy.core.config.jackson;

import cn.hutool.core.util.NumberUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;

import java.io.IOException;
import java.io.Serial;
import java.util.Objects;

/**
 * @author lcj
 */
public class ClearPointSerializer extends StdScalarSerializer<Number> {

    @Serial
    private static final long serialVersionUID = -1404070010013282747L;

    protected ClearPointSerializer(Class<Number> t) {
        super(t);
    }

    public ClearPointSerializer() {
        super(Number.class);
    }

    @Override
    public void serialize(Number number, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        if(Objects.isNull(number)){
            return;
        }

        jsonGenerator.writeNumber(NumberUtil.toStr(number));
    }

}
