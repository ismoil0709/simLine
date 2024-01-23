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
        if (obj.getClass().getSimpleName().equals("String")) {
            if (isNullOrEmpty(obj.toString())){
                return defaultValue;
            }
        }
        return obj;
    }
 }