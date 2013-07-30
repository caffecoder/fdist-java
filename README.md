Simple JAVA file distribution library.
======================================

## Description:

Simple library that allows organize distribution of files within hex based tree.

## Download file_distribution.jar:

JAR files can be download from [here](http://download.kaizen-step.com/?dir=fdist-java).

## Example of usage:

```java
    FileDistribution fd = new FileDistribution("/tmp/storage");

    // default extensions
    int database_id = 1;
    fd.hexPath(database_id);
    fd.renameFrom("/tmp/upload/file1.txt");

    System.out.println( fd.getPath() ); // saved file path

    // set all extensions to .pdf
    database_id = 256;
    fd.setExtension(".pdf");
    fd.hexPath(database_id);
    fd.renameFrom("/tmp/upload/file2.txt");
    
    System.out.println( fd.getPath() ); // saved file path
```

Files should be stored in /tmp/storage/01.dat and /tmp/storage/01/00.pdf
