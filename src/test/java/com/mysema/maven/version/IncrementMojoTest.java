package com.mysema.maven.version;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class IncrementMojoTest {

    private File sourcePom, targetPom;
    
    @Before
    public void setUp() throws IOException {
        sourcePom = new File("src/test/resources/project-to-test/pom.xml");
        targetPom = new File("target/project-to-test1/pom.xml");        
        targetPom.getParentFile().mkdir();
        Files.copy(sourcePom, targetPom);        
    }
    
    @Test
    public void Execute() throws MojoExecutionException, MojoFailureException, IOException {        
        IncrementMojo mojo = new IncrementMojo();
        mojo.setBasedir(new File("target/project-to-test1"));
        mojo.execute();
        assertTrue(Files.toString(targetPom, Charsets.UTF_8).contains("<version>0.1.1</version>"));
    }

}
