package com.tkerambloch.github.util;

import org.apache.commons.beanutils.PropertyUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by tkerambloch on 19/02/2016.
 */
public class MyUtils {

    public static void partialUpdate(Object to, Object from) {
        try {
            for (Field field : from.getClass().getDeclaredFields()) {
                if (!field.getName().equals("serialVersionUID") && !field.getName().equalsIgnoreCase("id")) {
                    Object property = PropertyUtils.getProperty(from, field.getName());
                    if (property != null) {
                        PropertyUtils.setProperty(to, field.getName(), property);
                    }
                }
            }
        } catch (Exception e) {e.printStackTrace(); }
    }

    public static boolean listStringContainsIgnoreCase(ArrayList<String> list, String str){
        for(String i : list){
            if(i.equalsIgnoreCase(str))
                return true;
        }
        return false;
    }

}
