/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.passwordHashing;

import FunctionLayer.FOGException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Perlt
 */
public class Hashing {

    public static String HashPassword(String password) throws FOGException {
        try {
            String ans = "";
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(password.getBytes());
            byte[] toHash = md.digest();
            ans = DatatypeConverter.printHexBinary(toHash);
            return ans;
        } catch (NoSuchAlgorithmException e) {
            throw new FOGException("Could not hash password");
        }
    }

    public static String makeSalt() {
        Random ra = new SecureRandom();
        String salt = "";
        for (int i = 0; i < 10; i++) {
            char ch = 'a';
            ch += ra.nextInt(25);
            salt += ch;
        }
        return salt;
    }
}
