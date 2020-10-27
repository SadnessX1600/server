package com.among.dev.authentification.utilities.security;

import org.apache.log4j.Logger;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hasher implements IMD5 {
    Logger LOGGER = Logger.getLogger(this.getClass());
    private MessageDigest md;

    public MD5Hasher() {
        try {
            this.md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            LOGGER.debug(e.getMessage());
        }
    }

    @Override
    public String calculateMD5(String password) {
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }
}
