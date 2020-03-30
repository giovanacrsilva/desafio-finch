package br.com.lanchonete.utils;

import java.text.Normalizer;

public class StringUtils {

    /**
     * @param texto variável do tipo String
     * @return Texto sem acentos
     */
    public static String unnaccent(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}