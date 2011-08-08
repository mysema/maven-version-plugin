maven-version-plugin
====================

To use maven-version-plugin include the following configuration in your POM :

    <plugin>
      <groupId>com.mysema.maven</groupId>
      <artifactId>maven-version-plugin</artifactId>
      <version>0.1.0</version>
    </plugin> 

Now you can update the version of your POM and of all the children via

    mvn version:set -Dversion=<NEW VERSION HERE>


