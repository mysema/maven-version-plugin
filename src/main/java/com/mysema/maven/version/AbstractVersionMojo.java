package com.mysema.maven.version;

import java.io.File;
import java.io.FileOutputStream;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.Serializer;
import nu.xom.XPathContext;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * 
 * @author tiwe
 * 
 */
public abstract class AbstractVersionMojo extends AbstractMojo {
   
    private static final String NS = "http://maven.apache.org/POM/4.0.0";

    /**
     * @parameter expression="${basedir}"
     * @required
     * @readonly
     */
    protected File basedir;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            Builder builder = new Builder();
            File pomFile = new File(basedir, "pom.xml");
            Document doc = builder.build(pomFile);
            XPathContext context = new XPathContext("mvn",NS);
            Nodes nodes = doc.query("/mvn:project/mvn:version", context);
            if (nodes.size() == 0){
                nodes = doc.query("/mvn:project/mvn:parent/mvn:version", context);
                if (nodes.size() == 0){
                    getLog().error("No version found");
                    return;
                }
            }
            if (update(nodes)){
                Serializer serializer = new Serializer(new FileOutputStream(pomFile),"UTF-8");
                serializer.write(doc);
            }
            
        } catch (Exception e) {
            getLog().error(e.getMessage(), e);
        }
    }

    private boolean update(Nodes nodes) {
        Element node = (Element) nodes.get(0);
        String newValue = createValue(node.getValue());
        if (newValue != null){
            node.removeChildren();
            node.appendChild(newValue);
            return true;
        }else{
            return false;
        }
    }
    
    protected abstract String createValue(String value);
}
