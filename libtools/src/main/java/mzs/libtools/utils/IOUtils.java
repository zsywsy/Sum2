package mzs.libtools.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import mzs.libtools.functions.Action1;


public class IOUtils {

    public static final int DEFAULT_BUFFER_LENGTH = 8 * 1024;
    public static final String DEFAULT_CHARSET = "UTF-8";

    public static boolean is2os(InputStream is, OutputStream os, Action1<Long> action1, int bufferLen) {
        if (is == null || os == null) {
            return false;
        }
        int len;
        long count = 0;
        byte[] buffer = new byte[bufferLen];
        try {
            while ((len = is.read(buffer)) != -1) {
                os.write(buffer, 0, len);
                count += len;
                if (action1 != null) {
                    action1.call(count);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean is2os(InputStream is, OutputStream os, Action1 action1) {
        return is2os(is, os, action1, DEFAULT_BUFFER_LENGTH);
    }

    public static boolean is2os(InputStream is, OutputStream os) throws IOException {
        return is2os(is, os, null, DEFAULT_BUFFER_LENGTH);
    }

    public static String is2str(InputStream is, String charset) throws IOException {
        if (is == null) {
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, charset));
        StringBuilder stringBuilder = new StringBuilder();
        String curLine;
        while ((curLine = reader.readLine()) != null) {
            stringBuilder.append(curLine);
        }
        return stringBuilder.toString();
    }

    public static String is2str(InputStream is) throws IOException {
        return is2str(is, DEFAULT_CHARSET);
    }

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
