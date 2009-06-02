package com.mysema.maven.version;

/**
 * 
 * @author tiwe
 * @goal increment
 *
 */
public class IncrementMojo extends AbstractVersionMojo{

    @Override
    protected String createValue(String value) {
        return increment(value);
    }
    
    public static String increment(String value){
        String suffix = "";
        if (value.endsWith("-SNAPSHOT")){
            suffix = "-SNAPSHOT";
            value = value.substring(0, value.length()-suffix.length());
        }
        String prefix = "";
        if (value.contains(".")){
            prefix = value.substring(0, value.lastIndexOf('.')+1);
            value = value.substring(value.lastIndexOf('.')+1);
        }
        return prefix + (Integer.valueOf(value)+1) + suffix;        
    }

}
