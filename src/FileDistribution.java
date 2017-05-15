import java.io.File;

/**
 * Class for manage hashed file distribution.
 *
 * @author Adam Kubica (xcdr) <xcdr@kaizen-step.com>
 */
public class FileDistribution {
    private String ext = ".dat";
    private String prefix;
    private StringBuffer path = new StringBuffer();

    /**
     * Creates new instance with directory prefix.
     *
     * @param prefix
     *            directory prefix.
     */
    public FileDistribution(String prefix) {
        StringBuffer sb = new StringBuffer(prefix);

        if (sb.lastIndexOf(File.separator) == sb.length() - 1) {
            sb.deleteCharAt(this.prefix.length() - 1);
        }

        this.prefix = sb.toString();

        this.path.append(this.prefix);
    }

    /**
     * @param ext
     *            file extension.
     */
    public void setExtension(String ext) {
        if (ext.length() > 0 && !ext.startsWith(".")) {
            this.ext = "." + ext;
        } else {
            this.ext = ext;
        }
    }

    /**
     * @return Returns Destination path.
     */
    public String getPath() {
        return this.path.toString();
    }

    /**
     * @param id
     *            database file ID etc.
     */
    public void hexPath(int id) {
        this.path = new StringBuffer(this.prefix);

        String hex = Integer.toHexString(id);

        int length = hex.length();

        if (length % 2 > 0) {
            hex = "0" + hex;
            length++;
        }

        for (int i = 0; i < length; i += 2) {
            this.path.append(File.separator);
            this.path.append(hex.substring(i, i + 2));
        }

        this.path.append(this.ext);
    }

    /**
     * @param path
     *            source file path.
     *
     * @return Returns true if rename was successful.
     */
    public boolean renameFrom(String path) {
        File src = new File(path);
        File dst = new File(this.path.toString());

        String baseDir = dst.getParent();

        if (baseDir != null) {
            File dstDir = new File(baseDir);
            dstDir.mkdirs();
        }

        return src.renameTo(dst);
    }
}
