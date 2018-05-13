package abanoub.johnny.development.moviesapp.utils;

import java.util.ArrayList;

/**
 * Created by Abanoub Maher on 6/17/17.
 */

public class ArrayToStringUtils {
    public static String arrayToString(ArrayList<String> data) {
        if (data == null || data.size() == 0)
            return "[]";

        String str = "[";
        for (int i = 0; i < data.size(); i++) {
            if (i == data.size() - 1)
                str += data.get(i) + " ";
            else
                str += data.get(i) + ", ";
        }
        return str + "]";
    }

    public static String arrayToString(String[] data) {
        if (data == null || data.length == 0)
            return "[]";

        String str = "[";
        for (int i = 0; i < data.length; i++) {
            if (data[i] == null)
                continue;
            if (i == data.length - 1)
                str += data[i] + " ";
            else if (data[i + 1] == null)
                str += data[i] + " ";
            else
                str += data[i] + ", ";
        }
        return str + "]";
    }
}
