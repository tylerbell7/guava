package com.example;

import java.util.Base64;
import java.io.IOException;

/**
 * This class demonstrates breaking changes across Java versions:
 * 
 * 
 */
public class JavaBreakingTest {
    
    // === BREAKS JAVA 8 → 11: sun.misc APIs removed ===
    // Replaced with java.util.Base64 which is the standard API in Java 8+
    
    public String encodeData(byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }
    
    public byte[] decodeData(String data) throws Exception {
        return Base64.getDecoder().decode(data);
    }
    
    public JavaBreakingTest() {
    }
    
    @SuppressWarnings("deprecation")
    public void unsafeThreadStop(Thread thread) {
        // Works in Java 8-19, throws UnsupportedOperationException in Java 20+
        thread.stop();
    }
    
    @SuppressWarnings("deprecation")  
    public void trySetSecurityManager() {
        // Works in Java 8-17, throws UnsupportedOperationException in Java 18+
        // (unless -Djava.security.manager=allow is set)
        System.setSecurityManager(new SecurityManager());
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("JavaBreakingTest - Multi-version compatibility test");
        System.out.println("This class has breaking changes at Java 11, 17, and 21");
    }
}
