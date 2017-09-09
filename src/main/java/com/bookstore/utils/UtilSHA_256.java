package com.bookstore.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class UtilSHA_256 {

    public static void main(String[] args) {
        String sha = Hashing.sha256().hashString("111111", StandardCharsets.UTF_8).toString();
        System.out.println(sha);
                
    }

}
