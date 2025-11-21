package com.czdgyy.czyjy.core.config.jackson;

import com.czdgyy.czyjy.core.annotation.jackson.ClearPoint;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.io.Serial;
import java.util.Objects;

/**
 * @author lcj
 */
public class CustomAnnotationIntrospector extends JacksonAnnotationIntrospector {
    @Serial
    private static final long serialVersionUID = -2820685801101821177L;

    @Override
    public Object findSerializer(Annotated annotated) {
        ClearPoint clearPoint = annotated.getAnnotation(ClearPoint.class);

        if(Objects.nonNull(clearPoint)){
            return new ClearPointSerializer();
        }

        return super.findSerializer(annotated);

    }
}
