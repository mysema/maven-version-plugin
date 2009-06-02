package com.mysema.maven.version;


/**
 * @author tiwe
 * @goal set
 * 
 */
public class SetVersionMojo extends AbstractVersionMojo {

    @Override
    protected String createValue(String value) {
        return System.getProperty("version");
    }
            
}
