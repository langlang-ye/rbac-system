package com.langlang.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Util {

    public static String encode(String json) {
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        String result = Base64.getEncoder().encodeToString(bytes);
        return result;
    }

    public static String decode(String json) {
        byte[] bytes = Base64.getDecoder().decode(json);
        String result = new String(bytes, StandardCharsets.UTF_8);
        return result;
    }
}
