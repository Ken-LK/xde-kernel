package com.xde.test.juc;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Map;

/**
 * @author <a href="mailto:liukailk.ken@gmail.com"> Ken </a>
 * @date 2020/10/26 9:21 上午
 **/
public class TestHashMap {


    public static void main(String[] args) {

        Map<String, String> m = new HashMap<>();

        m.put("a", "a");

        String value1 = m.put("a", "b");
        String value2 = m.put("b", "c");

        System.out.println("value1===" + value1);
        System.out.println("value2===" + value2);
        System.out.println("map===" + JSON.toJSONString(m));


    }
}
