package com.how2java.tmall;

import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.Map;

/**
 * the class is create by @Author:oweson
 *
 * @Date：2020/3/22 19:08
 */
public class BreakPointTest {
    public static final String demo1() {
        for (int i = 0; i < 5; i++) {
            System.out.println(i);

        }
        return "";
    }

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();
        map.put(1, "hello");
        map.put(2, "world");
        System.out.println(100);
        int i=1;
        i++;
        System.out.println(100);
        demo1();
        System.out.println(100);
        System.out.println(100);
        System.out.println(100);
        demo1();

    }
}
