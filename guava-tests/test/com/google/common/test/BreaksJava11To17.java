package com.example;

import java.rmi.MarshalledObject;
import java.rmi.RemoteException;
import java.rmi.Remote;

/**
 * This class previously used the RMI Activation API which was:
 * - Deprecated for removal in Java 15 (JEP 385)
 * - Removed in Java 17 (JEP 407)
 * 
 * Updated to compile on Java 17+ by removing RMI Activation dependencies
 */
public class BreaksJava11To17 implements Remote {
    
    public BreaksJava11To17(String id, MarshalledObject<?> data) 
            throws RemoteException {
        // Constructor updated to remove RMI Activation dependencies
    }
    
    public String getStatus() {
        return "RMI Activation is available";
    }
    
    public static void main(String[] args) {
        System.out.println("This code compiles on Java 11 but not Java 17");
        System.out.println("The java.rmi.activation package was removed in Java 17");
    }
}
