package com.example;

import java.util.Base64;

/**
 * This class now uses the standard java.util.Base64 API that is available
 * in Java 8+ and is the recommended approach for Base64 encoding/decoding.
 * 
 * Migrated from deprecated sun.misc APIs to java.util.Base64
 */
class JavaBreakingTest {
    
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();
    
    public String encode(byte[] data) {
        return encoder.encodeToString(data);
    }
    
    public byte[] decode(String data) throws Exception {
        return decoder.decode(data);
    }
    
    public static void main(String[] args) throws Exception {
        JavaBreakingTest handler = new JavaBreakingTest();
        
        String original = "Hello, Java 8!";
        String encoded = handler.encode(original.getBytes());
        byte[] decoded = handler.decode(encoded);
        
        System.out.println("Original: " + original);
        System.out.println("Encoded: " + encoded);
        System.out.println("Decoded: " + new String(decoded));
    }
}
