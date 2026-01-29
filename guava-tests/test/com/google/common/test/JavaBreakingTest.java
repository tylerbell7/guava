package com.example;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

/**
 * This class uses internal sun.misc APIs that were available in Java 8
 * but are encapsulated/removed in Java 11 due to the module system (JPMS).
 * 
 * Java 8: Compiles and runs fine
 * Java 11: Fails with "package sun.misc does not exist"
 */
public class LegacyBase64Handler {
    
    private final BASE64Encoder encoder = new BASE64Encoder();
    private final BASE64Decoder decoder = new BASE64Decoder();
    
    public String encode(byte[] data) {
        return encoder.encode(data);
    }
    
    public byte[] decode(String data) throws Exception {
        return decoder.decodeBuffer(data);
    }
    
    public static void main(String[] args) throws Exception {
        LegacyBase64Handler handler = new LegacyBase64Handler();
        
        String original = "Hello, Java 8!";
        String encoded = handler.encode(original.getBytes());
        byte[] decoded = handler.decode(encoded);
        
        System.out.println("Original: " + original);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + new String(decoded));
    }
}
