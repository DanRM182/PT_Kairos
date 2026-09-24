package com.ramirezmontoya.tvmaze_middleware.utils;

public class ValoresNumericosUtils {
    public  static <N extends Number> void validarNumeroRequerido(N numero) {
        if(numero == null)
            throw new IllegalArgumentException("El valor numérico es requerido");
    }

    public static void validarLongPositivo(Long numero, String mensaje) {
        validarNumeroRequerido(numero);

        if(numero <= 0)
            throw new IllegalArgumentException(mensaje);
    }

    public static void validarRangoInteger(Integer numero, Integer min, Integer max, String mensaje) {
        validarNumeroRequerido(numero);

        if(numero < min || numero > max)
            throw new IllegalArgumentException(mensaje);
    }
}
