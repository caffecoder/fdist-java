import java.io.File;

/**
 * Class for manage hashed file distribution.
 * 
 * @author Adam Kubica (caffecoder) <caffecoder@kaizen-step.com>
 */
public class FileDistribution {
    private StringBuffer path = new StringBuffer();

    /**
     * Creates new default instance.
     */
    public FileDistribution() {
    }

    /**
     * Creates new instance with directory prefix.
     * 
     * @param prefix
     *            directory prefix.
     */
    public FileDistribution(String prefix) {
        this.path.append(prefix);

        if (this.path.lastIndexOf(File.separator) == this.path.length() - 1) {
            this.path.deleteCharAt(this.path.length() - 1);
        }
    }

    /**
     * @param ext
     *            file extension.
     */
    public void addExtension(String ext) {
        if (!ext.startsWith(".")) {
            this.path.append(".");
        }

        this.path.append(ext);
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
    }

    /**
     * @param path
     *            source file path.
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
