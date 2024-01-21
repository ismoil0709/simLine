package uz.pdp.simline.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Validations {
    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
