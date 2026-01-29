package com.example;

import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;

/**
 * This class demonstrates breaking changes across Java versions:
 * 
 * BREAKS JAVA 8 → 11:
 *   sun.misc.BASE64Encoder/Decoder - internal APIs encapsulated by JPMS
 *   Error: "package sun.misc does not exist"
 * 
 * BREAKS JAVA 11 → 17:
 *   java.rmi.activation.Activatable - RMI Activation removed (JEP 407)
 *   Error: "package java.rmi.activation does not exist"
 * 
 * BREAKS JAVA 17 → 21:
 *   Thread.stop() - throws UnsupportedOperationException at runtime (JEP 449)
 *   SecurityManager usage - disabled by default, throws at runtime
 */
public class JavaBreakingTest extends Activatable {
    
    // === BREAKS JAVA 8 → 11: sun.misc APIs removed ===
    private final BASE64Encoder encoder = new BASE64Encoder();
    private final BASE64Decoder decoder = new BASE64Decoder();
    
    public String encodeData(byte[] data) {
        return encoder.encode(data);
    }
    
    public byte[] decodeData(String data) throws Exception {
        return decoder.decodeBuffer(data);
    }
    
    // === BREAKS JAVA 11 → 17: RMI Activation removed ===
    public JavaBreakingTest(ActivationID id, MarshalledObject<?> data) 
            throws RemoteException {
        super(id, 0);
    }
    
    // === BREAKS JAVA 17 → 21: Thread.stop() throws UnsupportedOperationException ===
    @SuppressWarnings("deprecation")
    public void unsafeThreadStop(Thread thread) {
        // Works in Java 8-19, throws UnsupportedOperationException in Java 20+
        thread.stop();
    }
    
    // === BREAKS JAVA 17 → 21: SecurityManager disabled by default ===
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
