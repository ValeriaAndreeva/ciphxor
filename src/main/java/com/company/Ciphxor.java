package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ciphxor {
    private static byte[] keyToByte(String key) {
        String temp;
        byte[] res = new byte[(int)Math.ceil((float)key.length() / 2)];
        int pos = res.length - 1;
        for (int i = key.length() - 1; i >= 0; i -= 2) {
            if (i == 0)
                temp = Character.toString(key.charAt(0));
            else {
                temp = key.substring(i - 1, i + 1);
            }
            res[pos] = (byte)Integer.parseInt(temp, 16);
            pos--;
        }
        return res;
    }

    private static void recode(FileInputStream inputStream, String outFileName, String ckey, String dkey) throws IOException {
            FileOutputStream out = new FileOutputStream(outFileName);
            byte[] keyarr;

            if (ckey != null && dkey != null) {
                throw new IOException("Задайте только один ключ");
            }
            if (ckey != null || dkey != null) {
                if (ckey != null) keyarr = keyToByte(ckey);
                else keyarr = keyToByte(dkey);
                byte temp;
                byte res;
                int i = 0;
                while ((temp = (byte)inputStream.read()) != -1) {
                    res = (byte)(temp^keyarr[i]);
                    i++;
                    i = i % keyarr.length;
                    out.write(res);
                }
            } else {
                throw new IOException("Ключа нет");
            }
            out.flush();
            out.close();
            inputStream.close();
        }

        public static void recode(String inputFileName, String outputFileName, String ckey, String dkey) throws IOException {
        try (FileInputStream inputStream = new FileInputStream(inputFileName)) {
            Ciphxor.recode(inputStream, outputFileName, ckey, dkey);
        }
    }
}
