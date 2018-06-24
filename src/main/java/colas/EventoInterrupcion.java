package colas;

import java.util.Random;

public class EventoInterrupcion extends Evento {

    private final double TIEMPO_PURGA = 0.62;

    private Random random = new Random();
    private Servidor servidor; // El servidor que va a interrumpir.
    private Tipo tipo = Tipo.INICIO;

    public EventoInterrupcion(String nombre, Servidor servidor) {
        super(nombre);
        this.servidor = servidor;
        proximaEjecucion = generarProximaInterrupcion();
    }

    private double generarProximaInterrupcion() {
        double rnd = random.nextDouble();
        double proxima = 0.0;
        // TODO: Valores de prueba. Poner los resultados al resolver la ecuacion diferencial.
        if (rnd < 0.20) {
            proxima += 0.240;
        } else if (rnd < 0.5) {
            proxima += 0.275;
        } else {
            proxima += 0.310;
        }

        return proxima;
    }

    @Override
    public void ejecutar(double reloj) {
        if (tipo == Tipo.INICIO) {
            tipo = Tipo.FIN;
            servidor.interrumpir(reloj);
            proximaEjecucion = reloj + TIEMPO_PURGA;
        } else { // tipo == Tipo.FIN
            tipo = Tipo.INICIO;
            servidor.reanudar(reloj);
            proximaEjecucion = reloj + generarProximaInterrupcion();
        }
    }

    private enum Tipo {
        INICIO,
        FIN
    }
}
