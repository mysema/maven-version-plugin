package com.mysema.maven.version;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class IncrementMojoTest {

    @Test
    public void Execute() throws MojoExecutionException, MojoFailureException, IOException {
        File sourcePom = new File("src/test/resources/project-to-test/pom.xml");
        File targetPom = new File("target/project-to-test1/pom.xml");        
        targetPom.getParentFile().mkdir();
        Files.copy(sourcePom, targetPom);
        
        IncrementMojo mojo = new IncrementMojo();
        mojo.setBasedir(new File("target/project-to-test1"));
        mojo.execute();
        assertTrue(Files.toString(targetPom, Charsets.UTF_8).contains("<version>0.1.1</version>"));
    }
    
    @Test
    public void Snapshot() throws MojoExecutionException, MojoFailureException, IOException {
        File sourcePom = new File("src/test/resources/snapshot/pom.xml");
        File targetPom = new File("target/snapshot/pom.xml");        
        targetPom.getParentFile().mkdir();
        Files.copy(sourcePom, targetPom);
        
        IncrementMojo mojo = new IncrementMojo();
        mojo.setBasedir(new File("target/snapshot"));
        mojo.execute();
        assertTrue(Files.toString(targetPom, Charsets.UTF_8).contains("<version>0.1.1-SNAPSHOT</version>"));
    }

}
