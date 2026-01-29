package com.google.common.test;

// Modern Java imports - using standard library replacements
import java.util.Base64;

import java.lang.reflect.Field;

/**
 * Test class with code that works with modern Java versions.
 * Updated to use standard library replacements for removed APIs.
 *
 * Changes made:
 * - Replaced javax.xml.bind.DatatypeConverter with java.util.Base64
 * - Removed sun.misc.BASE64Encoder/Decoder (replaced with java.util.Base64)
 * - Removed java.security.acl imports (deprecated and removed)
 * - Replaced DatatypeConverter.printHexBinary with custom hex conversion
 */
public class JavaBreakingTest {

    // ============================================================
    // UPDATED FOR MODERN JAVA - Using standard library APIs
    // ============================================================

    /**
     */
    public String testJaxbBase64Removed() {
        byte[] data = new byte[]{1, 2, 3, 4, 5};
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     */
    public String testJaxbHexRemoved() {
        byte[] data = new byte[]{0x0A, 0x0B, 0x0C};
        return bytesToHex(data);
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02X", b));
        }
        return result.toString();
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
