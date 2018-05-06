/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import generadores.IGenerador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author eric
 */
public class PruebaChiCuadradoNormal extends PruebaChiCuadrado {

    public PruebaChiCuadradoNormal(IGenerador generador, int cantidadIntervalos) {
        super(generador, cantidadIntervalos);
    }

    @Override
    int cantidadValoresEmpiricos() {
        return 2;
    }

    @Override
    double probabilidad(Intervalo intervalo, Muestra muestra) {
        return probabilidadIntervalo(intervalo.getInicio(), intervalo.getFin(), muestra.media(), muestra.desviacion());
    }

    private double probabilidadIntervalo(double inicio, double fin, double media, double desv) {
        double probInicio;
        double probFin;

        if (inicio == Double.NEGATIVE_INFINITY) {
            probInicio = 0.0;
        } else {
            probInicio = probabilidadAcumulada(zEstandar(inicio, media, desv));
        }

        if (fin == Double.POSITIVE_INFINITY) {
            probFin = 1.0;
        } else {
            probFin = probabilidadAcumulada(zEstandar(fin, media, desv));
        }

        return probFin - probInicio;
    }

    private double zEstandar(double x, double media, double desviacion) {
        return (x - media) / desviacion;
    }

    private double probabilidadAcumulada(double zEstandar) {
        // Probabilidades tabuladas.
        double zAbsoluto = Math.abs(zEstandar);  // Es simetrico asique buscamos solo 1 mitad.
        Map<Double, Double> tabla = tablaProbabilidadAcumulada();
        Double acum = null;
        for (Double key : tabla.keySet()) {
            if (key >= zAbsoluto) {
                acum = tabla.get(key);
                break;
            }
        }
        if (acum == null) {
            acum = 0.9999; // Excede la tabla.
        }
        if (zEstandar < 0.0) {
            acum = 1 - acum; // Porque sacamos el valor por simetria.        
        }
        return acum;
    }

    private Map<Double, Double> tablaProbabilidadAcumulada() {
        Map<Double, Double> tabla = new LinkedHashMap<>(360);
        tabla.put(0.00, 0.5000);
        tabla.put(0.01, 0.5040);
        tabla.put(0.02, 0.5080);
        tabla.put(0.03, 0.5120);
        tabla.put(0.04, 0.5160);
        tabla.put(0.05, 0.5199);
        tabla.put(0.06, 0.5239);
        tabla.put(0.07, 0.5279);
        tabla.put(0.08, 0.5319);
        tabla.put(0.09, 0.5359);
        tabla.put(0.10, 0.5398);
        tabla.put(0.11, 0.5438);
        tabla.put(0.12, 0.5478);
        tabla.put(0.13, 0.5517);
        tabla.put(0.14, 0.5557);
        tabla.put(0.15, 0.5596);
        tabla.put(0.16, 0.5636);
        tabla.put(0.17, 0.5675);
        tabla.put(0.18, 0.5714);
        tabla.put(0.19, 0.5753);
        tabla.put(0.20, 0.5793);
        tabla.put(0.21, 0.5832);
        tabla.put(0.22, 0.5871);
        tabla.put(0.23, 0.5910);
        tabla.put(0.24, 0.5948);
        tabla.put(0.25, 0.5987);
        tabla.put(0.26, 0.6026);
        tabla.put(0.27, 0.6064);
        tabla.put(0.28, 0.6103);
        tabla.put(0.29, 0.6141);
        tabla.put(0.30, 0.6179);
        tabla.put(0.31, 0.6217);
        tabla.put(0.32, 0.6255);
        tabla.put(0.33, 0.6293);
        tabla.put(0.34, 0.6331);
        tabla.put(0.35, 0.6368);
        tabla.put(0.36, 0.6406);
        tabla.put(0.37, 0.6443);
        tabla.put(0.38, 0.6480);
        tabla.put(0.39, 0.6517);
        tabla.put(0.40, 0.6554);
        tabla.put(0.41, 0.6591);
        tabla.put(0.42, 0.6628);
        tabla.put(0.43, 0.6664);
        tabla.put(0.44, 0.6700);
        tabla.put(0.45, 0.6736);
        tabla.put(0.46, 0.6772);
        tabla.put(0.47, 0.6808);
        tabla.put(0.48, 0.6844);
        tabla.put(0.49, 0.6879);
        tabla.put(0.50, 0.6915);
        tabla.put(0.51, 0.6950);
        tabla.put(0.52, 0.6985);
        tabla.put(0.53, 0.7019);
        tabla.put(0.54, 0.7054);
        tabla.put(0.55, 0.7088);
        tabla.put(0.56, 0.7123);
        tabla.put(0.57, 0.7157);
        tabla.put(0.58, 0.7190);
        tabla.put(0.59, 0.7224);
        tabla.put(0.60, 0.7257);
        tabla.put(0.61, 0.7291);
        tabla.put(0.62, 0.7324);
        tabla.put(0.63, 0.7357);
        tabla.put(0.64, 0.7389);
        tabla.put(0.65, 0.7422);
        tabla.put(0.66, 0.7454);
        tabla.put(0.67, 0.7486);
        tabla.put(0.68, 0.7517);
        tabla.put(0.69, 0.7549);
        tabla.put(0.70, 0.7580);
        tabla.put(0.71, 0.7611);
        tabla.put(0.72, 0.7642);
        tabla.put(0.73, 0.7673);
        tabla.put(0.74, 0.7704);
        tabla.put(0.75, 0.7734);
        tabla.put(0.76, 0.7764);
        tabla.put(0.77, 0.7794);
        tabla.put(0.78, 0.7823);
        tabla.put(0.79, 0.7852);
        tabla.put(0.80, 0.7881);
        tabla.put(0.81, 0.7910);
        tabla.put(0.82, 0.7939);
        tabla.put(0.83, 0.7967);
        tabla.put(0.84, 0.7995);
        tabla.put(0.85, 0.8023);
        tabla.put(0.86, 0.8051);
        tabla.put(0.87, 0.8078);
        tabla.put(0.88, 0.8106);
        tabla.put(0.89, 0.8133);
        tabla.put(0.90, 0.8159);
        tabla.put(0.91, 0.8186);
        tabla.put(0.92, 0.8212);
        tabla.put(0.93, 0.8238);
        tabla.put(0.94, 0.8264);
        tabla.put(0.95, 0.8289);
        tabla.put(0.96, 0.8315);
        tabla.put(0.97, 0.8340);
        tabla.put(0.98, 0.8365);
        tabla.put(0.99, 0.8389);
        tabla.put(1.00, 0.8413);
        tabla.put(1.01, 0.8438);
        tabla.put(1.02, 0.8461);
        tabla.put(1.03, 0.8485);
        tabla.put(1.04, 0.8508);
        tabla.put(1.05, 0.8531);
        tabla.put(1.06, 0.8554);
        tabla.put(1.07, 0.8577);
        tabla.put(1.08, 0.8599);
        tabla.put(1.09, 0.8621);
        tabla.put(1.10, 0.8643);
        tabla.put(1.11, 0.8665);
        tabla.put(1.12, 0.8686);
        tabla.put(1.13, 0.8708);
        tabla.put(1.14, 0.8729);
        tabla.put(1.15, 0.8749);
        tabla.put(1.16, 0.8770);
        tabla.put(1.17, 0.8790);
        tabla.put(1.18, 0.8810);
        tabla.put(1.19, 0.8830);
        tabla.put(1.20, 0.8849);
        tabla.put(1.21, 0.8869);
        tabla.put(1.22, 0.8888);
        tabla.put(1.23, 0.8907);
        tabla.put(1.24, 0.8925);
        tabla.put(1.25, 0.8944);
        tabla.put(1.26, 0.8962);
        tabla.put(1.27, 0.8980);
        tabla.put(1.28, 0.8997);
        tabla.put(1.29, 0.9015);
        tabla.put(1.30, 0.9032);        
        tabla.put(1.31, 0.9049);
        tabla.put(1.32, 0.9066);
        tabla.put(1.33, 0.9082);
        tabla.put(1.34, 0.9099);
        tabla.put(1.35, 0.9115);
        tabla.put(1.36, 0.9131);
        tabla.put(1.37, 0.9147);
        tabla.put(1.38, 0.9162);
        tabla.put(1.39, 0.9177);
        tabla.put(1.40, 0.9192);
        tabla.put(1.41, 0.9207);
        tabla.put(1.42, 0.9222);
        tabla.put(1.43, 0.9236);
        tabla.put(1.44, 0.9251);
        tabla.put(1.45, 0.9265);
        tabla.put(1.46, 0.9279);
        tabla.put(1.47, 0.9292);
        tabla.put(1.48, 0.9306);
        tabla.put(1.49, 0.9319);
        tabla.put(1.50, 0.9333);
        tabla.put(1.51, 0.9345);
        tabla.put(1.52, 0.9357);
        tabla.put(1.53, 0.9370);
        tabla.put(1.54, 0.9382);
        tabla.put(1.55, 0.9394);
        tabla.put(1.56, 0.9406);
        tabla.put(1.57, 0.9418);
        tabla.put(1.58, 0.9429);
        tabla.put(1.59, 0.9441);
        tabla.put(1.60, 0.9452);
        tabla.put(1.61, 0.9463);
        tabla.put(1.62, 0.9474);
        tabla.put(1.63, 0.9484);
        tabla.put(1.64, 0.9495);
        tabla.put(1.65, 0.9505);
        tabla.put(1.66, 0.9515);
        tabla.put(1.67, 0.9525);
        tabla.put(1.68, 0.9535);
        tabla.put(1.69, 0.9545);
        tabla.put(1.70, 0.9554);
        tabla.put(1.71, 0.9564);
        tabla.put(1.72, 0.9573);
        tabla.put(1.73, 0.9582);
        tabla.put(1.74, 0.9591);
        tabla.put(1.75, 0.9599);
        tabla.put(1.76, 0.9608);
        tabla.put(1.77, 0.9616);
        tabla.put(1.78, 0.9625);
        tabla.put(1.79, 0.9633);
        tabla.put(1.80, 0.9641);
        tabla.put(1.81, 0.9649);
        tabla.put(1.82, 0.9656);
        tabla.put(1.83, 0.9664);
        tabla.put(1.84, 0.9671);
        tabla.put(1.85, 0.9678);
        tabla.put(1.86, 0.9686);
        tabla.put(1.87, 0.9693);
        tabla.put(1.88, 0.9699);
        tabla.put(1.89, 0.9706);
        tabla.put(1.90, 0.9713);
        tabla.put(1.91, 0.9719);
        tabla.put(1.92, 0.9726);
        tabla.put(1.93, 0.9732);
        tabla.put(1.94, 0.9738);
        tabla.put(1.95, 0.9744);
        tabla.put(1.96, 0.9750);
        tabla.put(1.97, 0.9756);
        tabla.put(1.98, 0.9761);
        tabla.put(1.99, 0.9767);        
        tabla.put(2.00, 0.9772);
        tabla.put(2.01, 0.9778);
        tabla.put(2.02, 0.9783);
        tabla.put(2.03, 0.9788);
        tabla.put(2.04, 0.9793);
        tabla.put(2.05, 0.9798);
        tabla.put(2.06, 0.9803);
        tabla.put(2.07, 0.9808);
        tabla.put(2.08, 0.9812);
        tabla.put(2.09, 0.9817);
        tabla.put(2.10, 0.9821);
        tabla.put(2.11, 0.9826);
        tabla.put(2.12, 0.9830);
        tabla.put(2.13, 0.9834);
        tabla.put(2.14, 0.9838);
        tabla.put(2.15, 0.9842);
        tabla.put(2.16, 0.9846);
        tabla.put(2.17, 0.9850);
        tabla.put(2.18, 0.9854);
        tabla.put(2.19, 0.9857);
        tabla.put(2.20, 0.9861);        
        tabla.put(2.21, 0.9864);
        tabla.put(2.22, 0.9868);
        tabla.put(2.23, 0.9871);
        tabla.put(2.24, 0.9875);
        tabla.put(2.25, 0.9878);
        tabla.put(2.26, 0.9881);
        tabla.put(2.27, 0.9884);
        tabla.put(2.28, 0.9887);
        tabla.put(2.29, 0.9890);        
        tabla.put(2.30, 0.9893);
        tabla.put(2.31, 0.9896);
        tabla.put(2.32, 0.9898);
        tabla.put(2.33, 0.9901);
        tabla.put(2.34, 0.9904);
        tabla.put(2.35, 0.9906);
        tabla.put(2.36, 0.9909);
        tabla.put(2.37, 0.9911);
        tabla.put(2.38, 0.9913);
        tabla.put(2.39, 0.9916);        
        tabla.put(2.40, 0.9918);
        tabla.put(2.41, 0.9920);
        tabla.put(2.42, 0.9922);
        tabla.put(2.43, 0.9925);
        tabla.put(2.44, 0.9927);
        tabla.put(2.45, 0.9929);
        tabla.put(2.46, 0.9931);
        tabla.put(2.47, 0.9932);
        tabla.put(2.48, 0.9934);
        tabla.put(2.49, 0.9936);        
        tabla.put(2.50, 0.9938);
        tabla.put(2.51, 0.9940);
        tabla.put(2.52, 0.9941);
        tabla.put(2.53, 0.9943);
        tabla.put(2.54, 0.9945);
        tabla.put(2.55, 0.9946);
        tabla.put(2.56, 0.9948);
        tabla.put(2.57, 0.9949);
        tabla.put(2.58, 0.9951);
        tabla.put(2.59, 0.9952);        
        tabla.put(2.60, 0.9953);
        tabla.put(2.61, 0.9955);
        tabla.put(2.62, 0.9956);
        tabla.put(2.63, 0.9957);
        tabla.put(2.64, 0.9959);
        tabla.put(2.65, 0.9960);
        tabla.put(2.66, 0.9961);
        tabla.put(2.67, 0.9962);
        tabla.put(2.68, 0.9963);
        tabla.put(2.69, 0.9964);        
        tabla.put(2.70, 0.9965);
        tabla.put(2.71, 0.9966);
        tabla.put(2.72, 0.9967);
        tabla.put(2.73, 0.9968);
        tabla.put(2.74, 0.9969);
        tabla.put(2.75, 0.9970);
        tabla.put(2.76, 0.9971);
        tabla.put(2.77, 0.9972);
        tabla.put(2.78, 0.9973);
        tabla.put(2.79, 0.9974);        
        tabla.put(2.80, 0.9974);
        tabla.put(2.81, 0.9975);
        tabla.put(2.82, 0.9976);
        tabla.put(2.83, 0.9977);
        tabla.put(2.84, 0.9977);
        tabla.put(2.85, 0.9978);
        tabla.put(2.86, 0.9979);
        tabla.put(2.87, 0.9979);
        tabla.put(2.88, 0.9980);
        tabla.put(2.89, 0.9981);        
        tabla.put(2.90, 0.9981);        
        tabla.put(2.91, 0.9982);
        tabla.put(2.92, 0.9982);
        tabla.put(2.93, 0.9983);
        tabla.put(2.94, 0.9984);
        tabla.put(2.95, 0.9984);
        tabla.put(2.96, 0.9985);
        tabla.put(2.97, 0.9985);
        tabla.put(2.98, 0.9986);
        tabla.put(2.99, 0.9986);                
        // A partir de aqui las probabilidades se repiten asique pongo
        // solo donde cambia.
        tabla.put(3.00, 0.9987);        
        tabla.put(3.03, 0.9988);
        tabla.put(3.05, 0.9989);
        tabla.put(3.08, 0.9990);
        tabla.put(3.11, 0.9991);
        tabla.put(3.14, 0.9992);
        tabla.put(3.18, 0.9993);
        tabla.put(3.22, 0.9994);
        tabla.put(3.27, 0.9995);
        tabla.put(3.33, 0.9996);
        tabla.put(3.39, 0.9997);
        tabla.put(3.49, 0.9998);        

        return tabla;
    }

}
