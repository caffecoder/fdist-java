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

    @Before
    public void setUp() throws Exception {
        this.fd = new FileDistribution("/tmp/");
    }

    @Test
    public void testPath() {
        assertEquals(this.fd.getPath(), "/tmp");
    }

    @Test
    public void testCase1() {
        this.fd.setExtension("tmp");
        this.fd.setExtension(".dat");
        this.fd.hexPath(102423);
        assertEquals(this.fd.getPath(), "/tmp/01/90/17.dat");
    }

    @Test
    public void testCase2() {
        this.fd.setExtension("dat");
        this.fd.hexPath(256);
        assertEquals(this.fd.getPath(), "/tmp/01/00.dat");
    }

    @Test
    public void testCase3() {
        this.fd.setExtension("");
        this.fd.hexPath(256);
        assertEquals(this.fd.getPath(), "/tmp/01/00");
    }

    @Test
    public void testCase4() {
        this.fd.hexPath(1);
        assertEquals(this.fd.getPath(), "/tmp/01.dat");
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

        fo = new File("/tmp/01/00.dat");

        assertTrue(fo.exists());
    }

    @After
    public void tearDown() throws Exception {
    }
}
