package edu.psu.fauxdango.Util;

import java.util.List;

public class ListExtention {
    public static <T> void print(List<T> list) {
        for (T item : list) {
            System.out.println(item.toString());
        }
    }
}
