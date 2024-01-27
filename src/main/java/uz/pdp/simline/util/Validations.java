package uz.pdp.simline.util;

import lombok.experimental.UtilityClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@UtilityClass

public class Validations {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
  
    public static <T> T requireNonNullElse(T obj,T defaultValue){
        if (obj == null){
            return defaultValue;
        }
            String className = obj.getClass().getSimpleName();
        if (className.equals("String")) {
            if (isNullOrEmpty(obj.toString())){
                return defaultValue;
            }
        } else if (className.equals("Integer") || className.equals("Double")){
            Double integer = (Double) obj;
            if (integer < 0){
                return defaultValue;
            }
        }
        return obj;
    }
 }