package com.pragma.foodcourt.core.domain.util;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DomainUtils {

    private DomainUtils() {
    }

    public static boolean isValidPhone(String phone, int length) {
        if (phone == null) {
            return false;
        }
        String trimmedPhone = phone.trim();
        final String PHONE_REGEX = "^\\+?\\d+$";

        return trimmedPhone.length() <= length && Pattern.matches(PHONE_REGEX, trimmedPhone) &&
                (trimmedPhone.indexOf('+') == -1 || trimmedPhone.indexOf('+') == 0);
    }

    public static boolean isAdult(LocalDate birthdate, int age) {
        LocalDate today = LocalDate.now(java.time.Clock.systemDefaultZone());
        Period period = Period.between(birthdate, today);
        return period.getYears() >= age;
    }

    public static boolean validateEmail(String email){

        if (DomainUtils.isNullOrEmpty(email)) {
            return false;
        }

        final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";

        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isNumber(String number){
        return number.matches("\\d+");
    }

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static boolean isIntegerPositive(BigDecimal numero) {
        if (numero.compareTo(BigDecimal.ZERO) <= 0) {
            return false;
        }
        return numero.scale() <= 0 || numero.stripTrailingZeros().scale() <= 0;
    }
}
