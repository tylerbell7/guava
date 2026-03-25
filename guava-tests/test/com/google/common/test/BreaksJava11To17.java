package com.example;

import java.rmi.activation.Activatable;
import java.rmi.activation.ActivationID;
import java.rmi.MarshalledObject;
import java.rmi.RemoteException;

/**
 * This class uses the RMI Activation API which was:
 * - Deprecated for removal in Java 15 (JEP 385)
 * - Removed in Java 17 (JEP 407)
 * 
 * Java 11: Compiles successfully
 * Java 17: Fails with "package java.rmi.activation does not exist"
 */
public class BreaksJava11To17 extends Activatable {
    
    public BreaksJava11To17(ActivationID id, MarshalledObject<?> data) 
            throws RemoteException {
        super(id, 0);
    }
    
    public String getStatus() {
        return "RMI Activation is available";
    }
    
    public static void main(String[] args) {
        System.out.println("This code compiles on Java 11 but not Java 17");
        System.out.println("The java.rmi.activation package was removed in Java 17");
    }
}
