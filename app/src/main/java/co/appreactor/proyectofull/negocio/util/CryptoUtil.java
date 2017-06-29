package co.appreactor.proyectofull.negocio.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by lord_nightmare on 21/06/17.
 */

public final class CryptoUtil {

    public static String cifrarSha384(String password){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-384");
            md.update(password.getBytes());

            byte byteData[] = md.digest();

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch(NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "";
    }

}
