package com.intere.rcp.boggle.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.intere.rcp.boggle.core.BoggleCorePlugin;

/**
 * This class is used to read files.
 * 
 * @author <a href="mailto:intere@gmail.com">Eric Internicola</a>
 */
public class FileReader {

    /**
     * This method reads the provided filename into a Properties object. This
     * Properties object is then returned to you.
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static Properties readBundlePropertyFile(String filename) throws IOException {
        Properties props = new Properties();

        InputStream in = BoggleCorePlugin.getDefault().getBundle().getResource(filename).openStream();
        props.load(in);
        in.close();

        return props;
    }

    /**
     * This method reads the provided file into a String buffer. Note that the
     * filename you provide is a relative path to the bundle; this does not open
     * an absolute filesystem path.
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static StringBuffer readBundleFile(String filename) throws IOException {

        if (BoggleCorePlugin.getDefault() == null) {
            throw new IOException("Couldn't get default BoggleCorePlugin");
        }

        InputStream in = BoggleCorePlugin.getDefault().getBundle().getResource(filename).openStream();
        StringBuffer buff = readStream(in);

        in.close();

        return buff;
    }

    /**
     * This method reads the provided file into a String Buffer. The filename
     * you provided will be loaded from the classpath (rather than from the
     * bundle). This is a typical "Java only" implementation rather than the
     * {@link #readBundleFile(String)} variant that uses the OSGI container.
     * 
     * @param filename
     * @return
     * @throws IOException
     */
    public static StringBuffer readClasspathFile(String filename) throws IOException {
        InputStream in = FileReader.class.getResourceAsStream(filename);
        StringBuffer buff = null;

        if (in != null) {
            buff = readStream(in);
            in.close();
        }

        return buff;
    }

    /**
     * This method reads the provided file from the provided stream into a
     * {@link StringBuffer} and returns it to you.
     * 
     * @param in
     * @return
     * @throws IOException
     */
    public static StringBuffer readStream(InputStream in) throws IOException {
        StringBuffer buff = new StringBuffer();
        byte[] buffer = new byte[1024];
        int bytes;

        while ((bytes = in.read(buffer)) != -1) {
            buff.append(new String(buffer, 0, bytes));
        }

        return buff;
    }
}
