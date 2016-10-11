package mzs.libtools.utils;

/**
 * Created by 24275 on 2016/7/5.
 */
public class StringUtils {

    public static String joinIfNotNull(String... strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (str != null) {
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String addEveryGroup(String text, String add, int goupNum) {
        StringBuilder sb = new StringBuilder(text);
        int i = goupNum;
        int addLen = add.length();
        while (i < sb.length()) {
            sb.insert(i, add);
            System.out.println("add");
            i = i + goupNum + addLen;
        }
        return sb.toString();
    }

    public static String addAtIndex(String text, String add, int... num) {
        StringBuilder sb = new StringBuilder(text);
        int count = 0;
        int addLen = add.length();
        for (int i : num) {
            count += i;
            if (count > sb.length()) {
                break;
            } else {
                sb.insert(count, add);
                count = count + addLen - i;
            }

        }
        return sb.toString();
    }

    public static String jsonJoin(String... jsons) {
        if (jsons == null) {
            throw new NullPointerException("jsons == null");
        }
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        boolean isFirst = true;
        for (int i = 0; i < jsons.length; i++) {
            if (jsons[i] == null) {
                continue;
            }
            String json = jsons[i].trim();
            json = json.substring(1, json.length() - 1);
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(",");
            }
            sb.append(json);
        }
        sb.append("}");
        return sb.toString();
    }

    public static String valueOfFloat(float d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

}
