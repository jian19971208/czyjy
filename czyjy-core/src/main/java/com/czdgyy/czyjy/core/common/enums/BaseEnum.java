package com.czdgyy.czyjy.core.common.enums;

public interface BaseEnum {

    String getCode();

    String getName();

    static <E extends Enum<E> & BaseEnum> E getByCode(Class<E> enumClass, String code) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getCode().equals(code)) {
                return e;
            }
        }
        return null;
    }

    static  <E extends Enum<E> & BaseEnum> E getByName(Class<E> enumClass, String name) {
        for (E e : enumClass.getEnumConstants()) {
            if (e.getName().equals(name)) {
                return e;
            }
        }
        return null;

    }
}
