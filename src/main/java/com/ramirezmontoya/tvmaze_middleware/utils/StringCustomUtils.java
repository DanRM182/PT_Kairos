package com.ramirezmontoya.tvmaze_middleware.utils;

public class StringCustomUtils {
    public static void validarNoVacio(String texto, String mensaje) {
        if(texto == null || texto.isBlank())
            throw new IllegalArgumentException(mensaje);
    }
}
