package test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.IntStream;

public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        int totalPage = (int) Math.ceil((double)21/4);
        double ceil = Math.ceil((double)21/4);
        System.out.println(ceil);
    }
}
