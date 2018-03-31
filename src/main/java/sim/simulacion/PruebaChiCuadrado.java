package sim.simulacion;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Camila
 */
public class PruebaChiCuadrado {
    
    // decimal forma permite que mostremos los numero en el formate que querramos
    DecimalFormat dec = new DecimalFormat ("#.####") ;
    //private double serie[];
    List<Double> serie = new ArrayList<>();
    private int cant_intervalos;
    private int frecObservada;
    private int frecEsperada;
    private double limiteInf ;
    private double limiteSup ;

    List <Integer> frecObs ;
    
    
    

    public PruebaChiCuadrado( ArrayList <Double>  serie, int cant_intervalos) {
        this.serie = serie;
        this.cant_intervalos = cant_intervalos;
        this.frecObservada = 0;
        this.frecEsperada =  serie.size() / cant_intervalos;
        this.frecObs = new ArrayList<>(cant_intervalos);

    }
    public int FrecObservada(double limiteInf, double limiteSup ) {
        int frec = 0;
        for (int i = 0; i < serie.size(); i++) {
            if (serie.get(i)>= limiteInf && serie.get(i)< limiteSup ) {
                frec++;
            }
        }
        return frec ;
    }
    public List <Integer> FrecObservada(ArrayList <Double>  serie, int cant_intervalos ) {
        float tamañoInter =  (float) 0.9999 / cant_intervalos;
        limiteInf = 0;
        limiteSup = 0;
        frecObservada = 0;
        
        for(int i=0;i< cant_intervalos ; i++){
            if (i < 1) {
                
                limiteSup = Double.parseDouble(dec.format(limiteInf + tamañoInter))  ;
                frecObservada = this.FrecObservada(limiteInf, limiteSup);
                frecObs.add(i,frecObservada) ;
                System.out.println("inf: " + limiteInf);
                System.out.println("sup: " + limiteSup);
                System.out.println("frec " + frecObservada);
                
                
            }
            
            else if (i>= 1) {
               
                limiteInf =  Double.parseDouble(dec.format(limiteSup)) ;
                limiteSup = Double.parseDouble(dec.format(limiteInf + tamañoInter)) ;
                frecObservada = this.FrecObservada(limiteInf, limiteSup); 
//                frecObs[i] = frecObservada ;
                frecObs.add(i,frecObservada) ;
                System.out.println("inf: " + limiteInf);
                System.out.println("sup: " + limiteSup);
                System.out.println("frec " + frecObservada);
            }
    }
        return frecObs;
}
    public double calcularChi(ArrayList  frecObs){
        double chiCalculado = 0;
        for (int i = 0; i < frecObs.size(); i++) {
            frecObservada = (int) frecObs.get(i);
            chiCalculado = chiCalculado + Double.parseDouble(dec.format(Math.pow(frecObservada - 
                    frecEsperada,2)/frecEsperada));
        }
        return chiCalculado ;
    } 
    public double tabularChi(){
        double chiTabulado = 0;
        int gradosLibertad = cant_intervalos -1 ;
        double nivelConfianza = 0.95 ;
        
        return chiTabulado;
    }
    public Boolean TestChiCuadrado(double chiCalculado, double chiTabulado){
        if (chiCalculado <= chiTabulado) 
            return true;
        else {
            return false;
        }
    }
}

