package com.mysema.maven.version;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.junit.Test;

import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class SetVersionMojoTest {

    @Test
    public void Execute() throws MojoExecutionException, MojoFailureException, IOException {
        File sourcePom = new File("src/test/resources/project-to-test/pom.xml");
        File targetPom = new File("target/project-to-test2/pom.xml");        
        targetPom.getParentFile().mkdir();
        Files.copy(sourcePom, targetPom);        
        
        SetVersionMojo mojo = new SetVersionMojo();
        mojo.setBasedir(new File("target/project-to-test2"));
        System.setProperty("version", "2.0.1");
        mojo.execute();
        assertTrue(Files.toString(targetPom, Charsets.UTF_8).contains("<version>2.0.1</version>"));
    }
    
    @Test
    public void ExecuteModule() throws IOException, MojoExecutionException, MojoFailureException {
        File sourcePom = new File("src/test/resources/module-to-test/pom.xml");
        File targetPom = new File("target/module-to-test2/pom.xml");        
        targetPom.getParentFile().mkdir();
        Files.copy(sourcePom, targetPom);        
        
        SetVersionMojo mojo = new SetVersionMojo();
        mojo.setBasedir(new File("target/module-to-test2"));
        System.setProperty("version", "2.0.1");
        mojo.execute();
        assertTrue(Files.toString(targetPom, Charsets.UTF_8).contains("<version>2.0.1</version>"));
    }
    
}

