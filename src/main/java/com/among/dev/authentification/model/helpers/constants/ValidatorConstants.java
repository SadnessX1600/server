package com.among.dev.authentification.model.helpers.constants;

import java.util.regex.Pattern;

/*
    NOT SURE
    BUT WE CAN KEEP THERE ALL REGEXP THAT WE NEED IN THIS PROGRAMM
    JUST AS STATIC PROPERTIES
*/
public class ValidatorConstants {
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
}
