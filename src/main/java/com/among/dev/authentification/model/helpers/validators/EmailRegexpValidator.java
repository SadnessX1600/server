package com.among.dev.authentification.model.helpers.validators;

import com.among.dev.authentification.model.helpers.constants.ValidatorConstants;

import java.util.regex.Matcher;

public class EmailRegexpValidator implements IEmailRegexpValidator{
    public boolean validateEmail(String emailString) {
        Matcher matcher = ValidatorConstants.VALID_EMAIL_ADDRESS_REGEX.matcher(emailString);
        return matcher.find();
    }
}
