package com.example;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;

/**
 * This class previously used the RMI Activation API which was:
 * - Deprecated for removal in Java 15 (JEP 385)
 * - Removed in Java 17 (JEP 407)
 * 
 * Updated to compile with Java 17+ by removing RMI Activation dependencies
 */
public class BreaksJava11To17 {
    
    public BreaksJava11To17(Object id, MarshalledObject<?> data) 
            throws RemoteException {
        // RMI Activation removed - constructor simplified
    }
    
    public String getStatus() {
        return "RMI Activation was removed in Java 17";
    }
    
    public static void main(String[] args) {
        System.out.println("This code now compiles on Java 17");
        System.out.println("The java.rmi.activation package was removed in Java 17");
    }
}
