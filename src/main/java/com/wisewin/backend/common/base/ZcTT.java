package com.wisewin.backend.common.base;

import com.wisewin.backend.entity.param.GiftParam;
import com.wisewin.backend.util.RandomUtils;

import java.lang.reflect.Array;
import java.util.*;

public class ZcTT {
    public static void main(String[] args) {
        String[] strings={"21","22","23","24"};
        int[] ints= new int [strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i]=Integer.parseInt(strings[i]);
        }

        System.out.println(strings);
        System.out.println(ints[0]);
    }


}