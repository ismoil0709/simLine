package uz.pdp.simline.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validations {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
    public static <T> T requireNonNullElse(T obj,T defaultValue){
        if (obj.getClass().getSimpleName().equals("String")) {
            if (isNullOrEmpty(obj.toString())){
                return defaultValue;
            }
        }else {
            if (obj == null){
                return defaultValue;
            }
        }
        return obj;
    }
 }