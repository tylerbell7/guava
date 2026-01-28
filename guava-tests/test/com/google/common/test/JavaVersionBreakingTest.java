package com.google.common.test;

// Java 11 breaking imports - these packages are removed in Java 11
import javax.xml.bind.DatatypeConverter;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;

// Java 17 breaking import - this package is removed in Java 17
import java.security.acl.Acl;
import java.security.acl.Group;

import java.lang.reflect.Field;

/**
 * Test class with code that intentionally breaks at different Java version upgrades.
 * Used to test the Java upgrade agent's error detection and fixing capabilities.
 *
 * Breaking points:
 * - Java 11: JAXB removal, sun.misc encapsulation
 * - Java 17: java.security.acl removal, strong encapsulation
 * - Java 21: Thread.stop/suspend/resume throw exceptions
 */
public class JavaVersionBreakingTest {

    // ============================================================
    // BREAKS AT JAVA 11 - Removed EE modules and encapsulated internals
    // ============================================================

    /**
     */
    public String testJaxbBase64Removed() {
        byte[] data = new byte[]{1, 2, 3, 4, 5};
        return DatatypeConverter.printBase64Binary(data);
    }

    /**
     */
    public String testJaxbHexRemoved() {
        byte[] data = new byte[]{0x0A, 0x0B, 0x0C};
        return DatatypeConverter.printHexBinary(data);
    }

    /**
     */
    public String testSunMiscBase64Encoder() {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(new byte[]{1, 2, 3});
    }

    /**
     */
    public byte[] testSunMiscBase64Decoder() throws Exception {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer("AQID");
    }

    // ============================================================
    // BREAKS AT JAVA 17 - Stronger encapsulation and removed APIs
    // ============================================================

    /**
     * Uses java.security.acl package which was removed in Java 17.
     */
    public void testSecurityAclRemoved() {
        Acl acl = null;
        Group group = null;
        System.out.println("ACL: " + acl + ", Group: " + group);
    }

    /**
     * Uses illegal deep reflection on JDK internals.
     */
    public void testIllegalReflectionOnStringInternals() throws Exception {
        String testString = "Hello World";
        Field valueField = String.class.getDeclaredField("value");
        valueField.setAccessible(true);  // Throws InaccessibleObjectException in Java 17+
        Object internalValue = valueField.get(testString);
        System.out.println("Internal value type: " + internalValue.getClass());
    }

    // ============================================================
    // BREAKS AT JAVA 21 - Deprecated thread methods now throw
    // ============================================================

    /**
     * Thread.stop() throws UnsupportedOperationException in Java 21.
     */
    @SuppressWarnings("deprecation")
    public void testThreadStopRemoved() {
        Thread worker = new Thread(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        worker.start();
        worker.stop();  // Throws UnsupportedOperationException in Java 21
    }

    /**
     * Thread.suspend() throws UnsupportedOperationException in Java 21.
     */
    @SuppressWarnings("deprecation")
    public void testThreadSuspendRemoved() {
        Thread worker = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                // Work
            }
        });
        worker.start();
        worker.suspend();  // Throws UnsupportedOperationException in Java 21
    }

    /**
     * Thread.resume() throws UnsupportedOperationException in Java 21.
     */
    @SuppressWarnings("deprecation")
    public void testThreadResumeRemoved(Thread t) {
        t.resume();  // Throws UnsupportedOperationException in Java 21
    }

    /**
     * finalize() is deprecated for removal starting Java 9, with stronger warnings in 18+.
     */
    @Override
    @SuppressWarnings("deprecation")
    protected void finalize() throws Throwable {
        System.out.println("JavaVersionBreakingTest is being garbage collected");
        super.finalize();
    }
}
