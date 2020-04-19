package com.company;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class launcherTest {

    @Test
    void withoutKeys() throws IOException {
        IOException e = assertThrows(IOException.class, () -> Ciphxor.recode("text.txt", "text.txt.txt", "", ""));
        assertEquals(e.getMessage(), "Ключа нет");
    }

    @Test
    void withKeys() throws IOException {
        IOException e = assertThrows(IOException.class, () -> Ciphxor.recode("text.txt", "text.txt.txt", "4A", "4A"));
        assertEquals(e.getMessage(), "Задайте только один ключ");
    }

    @Test
    void encoding() throws IOException {
        Ciphxor.recode("text.txt", "", "4A", "");
        byte[] res = Files.readAllBytes(Paths.get("textencoding.txt"));

        assertEquals("EId6oIvSqg==", new String(res));
    }
}