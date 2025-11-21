package com.czdgyy.czyjy.core.utils;


import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.czdgyy.czyjy.core.exception.UnCheckedException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

/**
 * json处理
 * @author lcj
 */
public final class JsonUtil {

    public static final SimpleModule JAVA_TIME_MODULES = new SimpleModule()
            .addSerializer(java.time.LocalDateTime.class, new LocalDateTime.Serializer())
            .addSerializer(java.time.LocalDate.class, new LocalDate.Serializer())
            .addSerializer(java.time.LocalTime.class, new LocalTime.Serializer())
            .addDeserializer(java.time.LocalDateTime.class, new LocalDateTime.DeSerializer())
            .addDeserializer(java.time.LocalDate.class, new LocalDate.DeSerializer())
            .addDeserializer(java.time.LocalTime.class, new LocalTime.DeSerializer());

    private static volatile ObjectMapper MAPPER = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .setDateFormat(new SimpleDateFormat(DatePattern.NORM_DATETIME_PATTERN))
            .registerModule(JAVA_TIME_MODULES);

    /**
     * 序列化对象
     *
     * @param value 值
     * @return Json 字符串
     */
    public static String serialize(Object value) {
        if (value == null) {
            return null;
        }
        // 序列化
        try {
            return MAPPER.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new UnCheckedException(e);
        }
    }
    /**
     * Json 反序列化为对象
     *
     * @param string      Json 字符串
     * @param classObject 反序列化的类型
     * @param <T>         类型
     * @return 反序列化后的对象
     */
    public static <T> T deserialize(String string, Class<T> classObject) {
        // 反序列化
        try {
            return MAPPER.readValue(string, classObject);
        } catch (IOException e) {
            throw new UnCheckedException(e);
        }
    }

    /**
     * 行为与 {@link #deserialize(String, Class)} 一致.
     * 反序列化类型使用 TypeReference 定义, 以便处理泛型类型.
     *
     * @return 反序列化后的对象
     */
    public static <T> T deserialize(String string, TypeReference<T> reference) {
        // 反序列化
        try {
            return MAPPER.readValue(string, reference);
        } catch (IOException e) {
            throw new UnCheckedException(e);
        }
    }

    public static <T> T toBean(String jsonStr, Class<T> clazz) {
        return deserialize(jsonStr, clazz);
    }

    /**
     * jsonString转为复杂泛型对象
     */
    public static <T> T toBean(String jsonStr, Class<?> parametrized, Class<?>... parameterClasses) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(parametrized, parameterClasses);
            return MAPPER.readValue(jsonStr, javaType);
        } catch (Exception e) {
            throw new UnCheckedException(e);
        }
    }

    public static <T> List<T> toList(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, clazz);
            return MAPPER.readValue(jsonStr, javaType);
        } catch (Exception e) {
            throw new UnCheckedException(e);
        }
    }

    public static <T> List<T> toSet(String jsonStr, Class<T> clazz) {
        try {
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(Set.class, clazz);
            return MAPPER.readValue(jsonStr, javaType);
        } catch (Exception e) {
            throw new UnCheckedException(e);
        }
    }

    public static String toJsonString(Object obj) {
        return serialize(obj);
    }
    /**
     * 序列化对象并美化 Json 字符串
     *
     * @param value 值
     * @return Json 字符串
     */
    public static String serializeWithPrettyPrint(Object value) {
        // 序列化并美化 Json 格式
        try {
            return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(value);

        } catch (JsonProcessingException e) {
            throw new UnCheckedException(e);
        }
    }

    public static ObjectMapper getSource() {
        return MAPPER;
    }

    public static void setSource(ObjectMapper mapper) {
        MAPPER = mapper;
    }

    /**
     * 针对时间的序列化与反序列化器
     */
    public static final class LocalDateTime {
        public static class Serializer extends JsonSerializer<java.time.LocalDateTime> {
            @Override
            public void serialize(java.time.LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(LocalDateTimeUtil.format(value, DatePattern.NORM_DATETIME_PATTERN));
            }
        }

        public static class DeSerializer extends JsonDeserializer<java.time.LocalDateTime> {

            @Override
            public java.time.LocalDateTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
                String value = p.getValueAsString();
                return StrUtil.isBlank(value) ? null : LocalDateTimeUtil.parse(value, DatePattern.NORM_DATETIME_PATTERN);
            }
        }
    }


    public static final class LocalDate {
        public static class Serializer extends JsonSerializer<java.time.LocalDate> {
            @Override
            public void serialize(java.time.LocalDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                gen.writeString(LocalDateTimeUtil.format(value,DatePattern.NORM_DATE_PATTERN));
            }
        }

        public static class DeSerializer extends JsonDeserializer<java.time.LocalDate> {

            @Override
            public java.time.LocalDate deserialize(JsonParser p, DeserializationContext context) throws IOException {
                String value = p.getValueAsString();
                return StrUtil.isBlank(value) ? null : LocalDateTimeUtil.parseDate(value, DatePattern.NORM_DATE_PATTERN);
            }
        }
    }


    public static final class LocalTime {
        public static class Serializer extends JsonSerializer<java.time.LocalTime> {
            @Override
            public void serialize(java.time.LocalTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);
                gen.writeString(formatter.format(value));
            }
        }

        public static class DeSerializer extends JsonDeserializer<java.time.LocalTime> {

            @Override
            public java.time.LocalTime deserialize(JsonParser p, DeserializationContext context) throws IOException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DatePattern.NORM_TIME_PATTERN);
                String value = p.getValueAsString();
                return StrUtil.isBlank(value)? null : java.time.LocalTime.parse(value, formatter);
            }
        }
    }

}
