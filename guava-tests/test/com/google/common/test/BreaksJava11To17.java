package com.example;

/**
 * This class previously used the RMI Activation API which was:
 * - Deprecated for removal in Java 15 (JEP 385)
 * - Removed in Java 17 (JEP 407)
 * 
 * Updated to compile with Java 17+ by removing RMI activation dependencies
 */
public class BreaksJava11To17 {
    
    public BreaksJava11To17() {
        // Constructor updated to remove RMI activation dependencies
    }
    
    public String getStatus() {
        return "Updated for Java 17+ compatibility";
    }
    
    public static void main(String[] args) {
        System.out.println("This code now compiles on Java 17+");
        System.out.println("RMI activation dependencies have been removed");
    }
}
