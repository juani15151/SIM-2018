/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author juani
 */
public class Round {
    public static double truncate(double value, int cantidad_decimales) {
        if (cantidad_decimales < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(cantidad_decimales, RoundingMode.DOWN);
        return bd.doubleValue();
    }
}
