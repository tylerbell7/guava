package com.example;

import java.util.Base64;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * This class demonstrates breaking changes across Java versions:
 * 
 * 
 */
public class JavaBreakingTest extends UnicastRemoteObject {
    
    // === FIXED JAVA 11: Using java.util.Base64 instead of sun.misc APIs ===
    private final Base64.Encoder encoder = Base64.getEncoder();
    private final Base64.Decoder decoder = Base64.getDecoder();
    
    public String encodeData(byte[] data) {
        return encoder.encodeToString(data);
    }
    
    public byte[] decodeData(String data) throws Exception {
        return decoder.decode(data);
    }
    
    public JavaBreakingTest() 
            throws RemoteException {
        super();
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
