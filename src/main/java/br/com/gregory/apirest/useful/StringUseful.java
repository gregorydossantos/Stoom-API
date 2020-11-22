package br.com.gregory.apirest.useful;

public class StringUseful {

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || obj.toString().isEmpty();
    }
}
