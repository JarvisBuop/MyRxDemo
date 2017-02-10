package com.zjy.baselib.component.util;

public class StringUtil {
    // 去除高级备注中的<x> </x>
    public static String removeTags(String remark) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < remark.length(); i++) {
            char c = remark.charAt(i);
            if (c == '<') {
                char n = remark.charAt(i + 1);
                if (n == '/') {
                    i += 3;
                } else {
                    i += 2;
                }
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
