//$Id: PropertiesHelper.java,v 1.6 2005/12/12 11:30:33 steveebersole Exp $
package com.hwp.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
/*
 * ===========================================================================
 * Copyright 2007 WEBTRANING Corp. All Rights Reserved.
 * WEBTRANING PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * ===========================================================================
 * @version 1.0, 2007-8-11
 * @author  Jack Chen
 * ===========================================================================
 *
 */

public final class PropertiesHelper {

    private static final String PLACEHOLDER_START = "${";
    private static final String PLACEHOLDER_END = "}";

    private static Properties prop = null;

    static {
        prop = new Properties();
        InputStream is = PropertiesHelper.class.getResourceAsStream("/application.properties");
        try {
            prop.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private PropertiesHelper() {
    }

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static boolean getBoolean(String property, Properties properties) {
        String setting = properties.getProperty(property);
        return setting != null && Boolean.valueOf(setting.trim()).booleanValue();
    }

    public static boolean getBoolean(String property, Properties properties, boolean defaultValue) {
        String setting = properties.getProperty(property);
        return setting == null ? defaultValue : Boolean.valueOf(setting.trim()).booleanValue();
    }

    public static int getInt(String property, Properties properties, int defaultValue) {
        String propValue = properties.getProperty(property);
        return propValue == null ? defaultValue : Integer.parseInt(propValue.trim());
    }

    public static String getString(String property, Properties properties, String defaultValue) {
        String propValue = properties.getProperty(property);
        return propValue == null ? defaultValue : propValue;
    }

    public static Integer getInteger(String property, Properties properties) {
        String propValue = properties.getProperty(property);
        return propValue == null ? null : Integer.valueOf(propValue.trim());
    }

    public static Map toMap(String property, String delim, Properties properties) {
        Map map = new HashMap();
        String propValue = properties.getProperty(property);
        if (propValue != null) {
            StringTokenizer tokens = new StringTokenizer(propValue, delim);
            while (tokens.hasMoreTokens()) {
                map.put(tokens.nextToken(), tokens.hasMoreElements() ? tokens.nextToken() : "");
            }
        }
        return map;
    }

    public static String[] toStringArray(String property, String delim, Properties properties) {
        return toStringArray(properties.getProperty(property), delim);
    }

    public static String[] toStringArray(String propValue, String delim) {
        if (propValue != null) {
            return StringHelper.split(delim, propValue);
        } else {
            return ArrayHelper.EMPTY_STRING_ARRAY;
        }
    }

    /**
     * replace a property by a starred version
     *
     * @param props properties to check
     * @param key   proeprty to mask
     * @return cloned and masked properties
     */
    public static Properties maskOut(Properties props, String key) {
        Properties clone = (Properties) props.clone();
        if (clone.get(key) != null) {
            clone.setProperty(key, "****");
        }
        return clone;
    }

    public static void resolvePlaceHolders(Properties properties) {
        Iterator itr = properties.entrySet().iterator();
        while (itr.hasNext()) {
            final Map.Entry entry = (Map.Entry) itr.next();
            final String value = (String) entry.getValue();
            if (value != null) {
                final String resolved = resolvePlaceHolder(value);
                if (!value.equals(resolved)) {
                    if (resolved == null) {
                        itr.remove();
                    } else {
                        entry.setValue(resolved);
                    }
                }
            }
        }
    }

    public static String resolvePlaceHolder(String property) {
        if (property.indexOf(PLACEHOLDER_START) < 0) {
            return property;
        }
        StringBuffer buff = new StringBuffer();
        char[] chars = property.toCharArray();
        for (int pos = 0; pos < chars.length; pos++) {
            if (chars[pos] == '$') {
                // peek ahead
                if (chars[pos + 1] == '{') {
                    // we have a placeholder, spin forward till we find the end
                    String systemPropertyName = "";
                    int x = pos + 2;
                    for (; x < chars.length && chars[x] != '}'; x++) {
                        systemPropertyName += chars[x];
                        // if we reach the end of the string w/o finding the
                        // matching end, that is an exception
                        if (x == chars.length - 1) {
                            throw new IllegalArgumentException("unmatched placeholder start [" + property + "]");
                        }
                    }
                    String systemProperty = extractFromSystem(systemPropertyName);
                    buff.append(systemProperty == null ? "" : systemProperty);
                    pos = x + 1;
                    // make sure spinning forward did not put us past the end of
                    // the buffer...
                    if (pos >= chars.length) {
                        break;
                    }
                }
            }
            buff.append(chars[pos]);
        }
        String rtn = buff.toString();
        return StringHelper.isEmpty(rtn) ? null : rtn;
    }

    private static String extractFromSystem(String systemPropertyName) {
        try {
            return System.getProperty(systemPropertyName);
        } catch (Throwable t) {
            return null;
        }
    }
}
