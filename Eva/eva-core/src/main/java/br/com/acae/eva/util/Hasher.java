package br.com.acae.eva.util;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;

/**
 *
 * @author Vitor
 */
@RequestScoped
public class Hasher implements Serializable {
    
    @Inject private Event<Exception> event;
    
    public String md5Hash(String text) {
        try {
            return apply("MD5", text);
        } catch (NoSuchAlgorithmException ex) {
            event.fire(ex);
            return null;
        }
    }
    
    public String sha1Hash(String text) {
        try {
            return apply("SHA-1", text);
        } catch (NoSuchAlgorithmException ex) {
            event.fire(ex);
            return null;
        }
    }
    
    private String apply(String algorithm, String text) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(text.getBytes());
        
        return parse(md);
    }
    
    private String parse(MessageDigest md) {
        byte[] hash = md.digest();
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            sb.append(Integer.toHexString(hash[i]));
        }
        
        return sb.toString();
    }
}