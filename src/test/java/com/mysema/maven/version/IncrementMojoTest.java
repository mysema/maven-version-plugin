package com.mysema.maven.version;

import static org.junit.Assert.*;

import org.junit.Test;

public class IncrementMojoTest {

    @Test
    public void testIncrement() {
        assertEquals("0.2", IncrementMojo.increment("0.1"));
        assertEquals("0.1.2", IncrementMojo.increment("0.1.1"));
        assertEquals("0.1.2-SNAPSHOT", IncrementMojo.increment("0.1.1-SNAPSHOT"));
    }

}
