import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit test for FileDistribution
 * 
 * @author Adam Kubica (caffecoder) <caffecoder@kaizen-step.com>
 */
public class FileDistributionTest {
    FileDistribution fd;

    static public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return path.delete();
    }

    @Before
    public void setUp() throws Exception {
        File dstDir = new File("/tmp/storage");

        if (!dstDir.exists()) {
            dstDir.mkdirs();
        }

        this.fd = new FileDistribution("/tmp/storage");
    }

    @Test
    public void testPath() {
        assertEquals(this.fd.getPath(), "/tmp/storage");
    }

    @Test
    public void testCase1() {
        this.fd.setExtension("tmp");
        this.fd.setExtension(".dat");
        this.fd.hexPath(102423);
        assertEquals(this.fd.getPath(), "/tmp/storage/01/90/17.dat");
    }

    @Test
    public void testCase2() {
        this.fd.setExtension("dat");
        this.fd.hexPath(256);
        assertEquals(this.fd.getPath(), "/tmp/storage/01/00.dat");
    }

    @Test
    public void testCase3() {
        this.fd.setExtension("");
        this.fd.hexPath(256);
        assertEquals(this.fd.getPath(), "/tmp/storage/01/00");
    }

    @Test
    public void testCase4() {
        this.fd.hexPath(1);
        assertEquals(this.fd.getPath(), "/tmp/storage/01.dat");
    }

    @Test
    public void testCase5() {
        File fo = new File("/tmp/test.txt");

        try {
            fo.createNewFile();
        } catch (IOException e) {
        }

        assertTrue(fo.exists());

        this.fd.setExtension(".dat");
        this.fd.hexPath(256);
        this.fd.renameFrom("/tmp/test.txt");

        fo = new File("/tmp/storage/01/00.dat");

        assertTrue(fo.exists());
    }

    @Test
    public void testCase6() {
        File fo;

        fo = new File("/tmp/test1.txt");

        try {
            fo.createNewFile();
        } catch (IOException e) {
        }

        assertTrue(fo.exists());

        fo = new File("/tmp/test2.txt");

        try {
            fo.createNewFile();
        } catch (IOException e) {
        }

        assertTrue(fo.exists());

        this.fd.setExtension(".dat");

        this.fd.hexPath(1);
        this.fd.renameFrom("/tmp/test1.txt");

        fo = new File("/tmp/storage/01.dat");
        assertTrue(fo.exists());

        this.fd.hexPath(256);
        this.fd.renameFrom("/tmp/test2.txt");

        fo = new File("/tmp/storage/01/00.dat");
        assertTrue(fo.exists());
    }

    @After
    public void tearDown() throws Exception {
        File dstDir = new File("/tmp/storage");

        if (dstDir.exists()) {
            deleteDirectory(dstDir);
        }
    }
}
