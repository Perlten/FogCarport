/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FunctionLayer.passwordHashing;

import FunctionLayer.FOGException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
}
