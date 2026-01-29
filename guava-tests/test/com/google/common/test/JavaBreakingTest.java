package com.example;

import java.util.Base64;

/**
 * This class has been updated to use java.util.Base64 instead of deprecated sun.misc APIs
 * that were removed in Java 11 due to the module system (JPMS).
 * 
 * Java 8+: Uses standard java.util.Base64 API
 * Java 11+: Compatible with module system
 */
class LegacyBase64Handler {
    
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();
    
    public String encode(byte[] data) {
        return encoder.encodeToString(data);
    }
    
    public byte[] decode(String data) throws Exception {
        return decoder.decode(data);
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
