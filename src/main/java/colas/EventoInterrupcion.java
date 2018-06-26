package colas;

import java.util.Random;

public class EventoInterrupcion extends Evento {

    private final double TIEMPO_PURGA = 5;

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
        if (rnd < 0.20) {
            proxima += 7.1;
        } else if (rnd < 0.5) {
            proxima += 8.5;
        } else {
            proxima += 10;
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

    @Override
    public String nombre() {
        // El Tipo actual es para la siguiente ejecución, hay que usar el anterior (se invierten)
        String nombre;
        switch (tipo) {
            case INICIO:
                nombre = String.format(super.nombre(), "Fin");
                break;
            case FIN:
                nombre = String.format(super.nombre(), "Inicio");
                break;
            default:
                nombre = super.nombre();
        }
        return nombre;
    }

    private enum Tipo {
        INICIO,
        FIN
    }
}
