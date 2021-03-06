/*
 * @(#)Assert.java	1.0 2010-11-8 下午09:03:07
 *
 * Copyright 2004-2010 Client Service International, Inc. All rights reserved.
 * CSII PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.csii.bop.pcommon.signature;

import java.util.Collection;
import java.util.Map;

/**
 * Assert.java
 * 断言工具�?
 *
 * @author cuiyi
 * <p>
 * Created on 2010-11-8
 * Modification history
 * </p>
 * <p>
 * IBS Product Expert Group, CSII
 * Powered by CSII PowerEngine 6.0
 * </p>
 * @version 1.0
 * @since 1.0
 */
public abstract class Assert {

    /**
     * 判断输入对象是否为null，如果不为null抛出<code>IllegalArgumentException</code>异常
     *
     * @param object  输入对象
     * @param message 异常信息
     */
    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断输入对象是否为null，如果不为null抛出<code>IllegalArgumentException</code>异常
     *
     * @param object 输入对象
     */
    public static void isNull(Object object) {
        isNull(object, "断言失败，参数必须为null");
    }

    /**
     * 判断输入对象是否不为null，如果为null抛出<code>IllegalArgumentException</code>异常
     *
     * @param object  输入对象
     * @param message 异常信息
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断输入对象是否不为null，如果为null抛出<code>IllegalArgumentException</code>异常
     *
     * @param object 输入对象
     */
    public static void notNull(Object object) {
        notNull(object, "断言失败，参数不能为null");
    }

    /**
     * 判断表达式是否为true，如果为false抛出<code>IllegalArgumentException</code>异常
     *
     * @param expression 表达�?
     * @param message    异常信息
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断表达式是否为true，如果为false抛出<code>IllegalArgumentException</code>异常
     *
     * @param expression 表达�?
     */
    public static void isTrue(boolean expression) {
        isTrue(expression, "断言失败，表达式为false");
    }

    /**
     * 判断数组是否非空，如果为空抛�?<code>IllegalArgumentException</code>异常
     *
     * @param array   数组
     * @param message 异常信息
     */
    public static void notEmpty(Object[] array, String message) {
        if (Util.isEmpty(array)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断数组是否非空，如果为空抛�?<code>IllegalArgumentException</code>异常
     *
     * @param array 数组
     */
    public static void notEmpty(Object[] array) {
        notEmpty(array, "断言失败，数组为�?");
    }

    /**
     * 判断集合是否非空，如果为空抛�?<code>IllegalArgumentException</code>异常
     *
     * @param collection 集合
     * @param message    异常信息
     */
    public static void notEmpty(Collection collection, String message) {
        if (Util.isEmpty(collection)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断集合是否非空，如果为空抛�?<code>IllegalArgumentException</code>异常
     *
     * @param collection 集合
     */
    public static void notEmpty(Collection collection) {
        notEmpty(collection, "断言失败，集合为�?");
    }

    /**
     * 判断集合是否非空，如果为空抛�?<code>IllegalArgumentException</code>异常
     *
     * @param map     键�?�对
     * @param message 异常信息
     */
    public static void notEmpty(Map map, String message) {
        if (Util.isEmpty(map)) {
            throw new IllegalArgumentException(message);
        }
    }

    /**
     * 判断键�?�对是否非空，如果为空抛�?<code>IllegalArgumentException</code>异常
     *
     * @param map 键�?�对
     */
    public static void notEmpty(Map map) {
        notEmpty(map, "[Assertion failed] - this map must not be empty; it must contain at least one entry");
    }

    /**
     * 判断输入对象是否不为null，如果为null抛出<code>IllegalArgumentException</code>异常
     *
     * @param object 输入字符�?
     */
    public static void notNullorEmpty(String object) {
        if (Util.isEmptywithTrim(object)) {
            throw new IllegalArgumentException("断言失败，参数不能为null或空");
        }
    }

    /**
     * 判断输入对象是否不为null，如果为null抛出<code>IllegalArgumentException</code>异常
     *
     * @param object 输入字符�?
     */
    public static void notNullorEmpty(String object, String message) {
        if (Util.isEmptywithTrim(object)) {
            throw new IllegalArgumentException(message);
        }
    }
}
