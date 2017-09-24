package com.bookstore.utils;

import com.google.common.hash.Hashing;
import java.nio.charset.StandardCharsets;

public class UtilSHA_256 {

    public static void main(String[] args) {
        String sha = Hashing.sha256().hashString("111111", StandardCharsets.UTF_8).toString();
//        bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a
        System.out.println(sha);

    }

}
