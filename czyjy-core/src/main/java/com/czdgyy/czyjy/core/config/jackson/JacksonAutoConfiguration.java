package com.czdgyy.czyjy.core.config.jackson;


import cn.hutool.core.date.DatePattern;
import com.czdgyy.czyjy.core.common.response.ResponseResult;
import com.czdgyy.czyjy.core.utils.JsonUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier;
import com.fasterxml.jackson.databind.ser.std.NullSerializer;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URI;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;

/**
 * jackson的配置
 * @author lcj
 * @since 2024-01-12 9:22
 */
@Configuration
@ConditionalOnClass(ObjectMapper.class)
@AutoConfigureBefore(org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration.class)
public class JacksonAutoConfiguration {

    private static final String ASIA_SHANGHAI = "Asia/Shanghai";

    /**
     * @see  NullArrayJsonConfiguration
     */
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ASIA_SHANGHAI));
            builder.failOnEmptyBeans(false);
            builder.failOnUnknownProperties(false);
            // 处理日期时间
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            builder.serializerByType(LocalDateTime.class, new JsonUtil.LocalDateTime.Serializer());
            builder.serializerByType(LocalDate.class, new JsonUtil.LocalDate.Serializer());
            builder.serializerByType(LocalTime.class, new JsonUtil.LocalTime.Serializer());
            builder.deserializerByType(LocalDateTime.class, new JsonUtil.LocalDateTime.DeSerializer());
            builder.deserializerByType(LocalDate.class, new JsonUtil.LocalDate.DeSerializer());
            builder.deserializerByType(LocalTime.class, new JsonUtil.LocalTime.DeSerializer());
            //处理前端精度缺失问题
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            builder.serializerByType(Double.class, ToStringSerializer.instance);
            builder.serializerByType(Double.TYPE, ToStringSerializer.instance);
            builder.serializerByType(Float.class, ToStringSerializer.instance);
            builder.serializerByType(Float.TYPE, ToStringSerializer.instance);

            builder.serializerByType(BigDecimal.class, ToStringSerializer.instance);

            builder.annotationIntrospector(new CustomAnnotationIntrospector());
        };
    }

    @Configuration
    @AutoConfigureAfter(JacksonAutoConfiguration.class)
    public static class NullArrayJsonConfiguration extends MappingJackson2HttpMessageConverter {
        public NullArrayJsonConfiguration() {
            ObjectMapper objectMapper = getObjectMapper();
            //时间
            objectMapper.setLocale(Locale.CHINA);
            objectMapper.setTimeZone(TimeZone.getTimeZone(ASIA_SHANGHAI));
            objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN));

            //配置
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

            objectMapper.setAnnotationIntrospector(new CustomAnnotationIntrospector());

            //格式化
            SimpleModule simpleModule = new SimpleModule();

            simpleModule.addSerializer(LocalDateTime.class, new JsonUtil.LocalDateTime.Serializer());
            simpleModule.addSerializer(LocalDate.class, new JsonUtil.LocalDate.Serializer());
            simpleModule.addSerializer(LocalTime.class, new JsonUtil.LocalTime.Serializer());
            simpleModule.addDeserializer(LocalDateTime.class, new JsonUtil.LocalDateTime.DeSerializer());
            simpleModule.addDeserializer(LocalDate.class, new JsonUtil.LocalDate.DeSerializer());
            simpleModule.addDeserializer(LocalTime.class, new JsonUtil.LocalTime.DeSerializer());

            simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Double.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Double.TYPE, ToStringSerializer.instance);
            simpleModule.addSerializer(Float.class, ToStringSerializer.instance);
            simpleModule.addSerializer(Float.TYPE, ToStringSerializer.instance);
            simpleModule.addSerializer(BigDecimal.class, ToStringSerializer.instance);
            objectMapper.registerModule(simpleModule);

            //null对象、null数组 返回 {}、[]
            objectMapper.setSerializerFactory(objectMapper.getSerializerFactory()
                    .withSerializerModifier(new MyBeanSerializerModifier()));

        }
    }

    static class NullArrayJsonSerializer extends JsonSerializer<Object> {
        public static final NullArrayJsonSerializer INSTANCE = new NullArrayJsonSerializer();
        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (null == value) {
                jsonGenerator.writeStartArray();
                jsonGenerator.writeEndArray();
            }
        }
    }

    static class NullObjectJsonSerializer extends JsonSerializer<Object> {
        public static final NullObjectJsonSerializer INSTANCE = new NullObjectJsonSerializer();
        @Override
        public void serialize(Object value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
            if (null == value) {
                jsonGenerator.writeStartObject();
                jsonGenerator.writeEndObject();
            }
        }
    }

    static class MyBeanSerializerModifier extends BeanSerializerModifier {
        @Override
        public List<BeanPropertyWriter> changeProperties(SerializationConfig config, BeanDescription beanDesc, List<BeanPropertyWriter> beanProperties) {
            for (BeanPropertyWriter beanPropertyWriter : beanProperties) {
                if (ResponseResult.class.isAssignableFrom(beanDesc.getBeanClass())
                        && "data".equals(beanPropertyWriter.getName())) {
                    // 针对响应体data为空的处理
                    beanPropertyWriter.assignNullSerializer(NullSerializer.instance);
                } else if (isArrayType(beanPropertyWriter)) {
                    beanPropertyWriter.assignNullSerializer(NullArrayJsonSerializer.INSTANCE);
                }
                else if (isObjectType(beanPropertyWriter)) {
                    beanPropertyWriter.assignNullSerializer(NullObjectJsonSerializer.INSTANCE);
                }
            }
            return super.changeProperties(config, beanDesc, beanProperties);
        }
    }

    private static boolean isArrayType(BeanPropertyWriter writer) {
        Class<?> rawClass = writer.getType().getRawClass();
        return rawClass.isArray() || Collection.class.isAssignableFrom(rawClass);
    }

    /**
     * 判定类型为非对象类型
     */
    public static boolean isObjectType(BeanPropertyWriter writer){
        Class<?> rawClass = writer.getType().getRawClass();
        return !notObjectType(rawClass);

    }

    /**
     * 对应前端非Object的对象类型
     */
    private static boolean notObjectType(Class<?> clazz){
        // 基本类型相关
        return null == clazz || clazz.isPrimitive() || clazz.isEnum() ||
                String.class.isAssignableFrom(clazz) ||
                Character.class.isAssignableFrom(clazz) ||
                Boolean.class.isAssignableFrom(clazz) ||
                Number.class.isAssignableFrom(clazz) ||
                CharSequence.class.isAssignableFrom(clazz) ||
                // 时间相关
                TemporalAccessor.class.isAssignableFrom(clazz) ||
                Date.class.isAssignableFrom(clazz) ||
                // 其它
                URI.class.equals(clazz) ||
                URL.class.equals(clazz) ||
                Locale.class.equals(clazz) ||
                Class.class.equals(clazz);
    }
}
