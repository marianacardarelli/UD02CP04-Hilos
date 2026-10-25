/*
Pistas es el recurso compartido, como variableCompartida en el ejercicio 3.
Vehículo es el hilo. Pide una pista, la usa y la libera.
Main crea 1 objeto Pistas y 4 vehículos que lo comparten.
La sincronización va dentro de Pistas.
* */
public class Pistas {
        private boolean[] pistasDisponibles= new boolean[2];

        public Pistas() {
            pistasDisponibles[0]=true;
            pistasDisponibles[1]=true;
        }

        public synchronized int ocuparPista(){
            int pista= buscarLibre();

            while (pista == -1){
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
                pista= buscarLibre();
            }
            pistasDisponibles[pista]=false; //la marco como ocupada
            return pista;
        }

    private int buscarLibre() {
            for (int i=0; i<pistasDisponibles.length; i++){
                if (pistasDisponibles[i]){
                    return i;
                }
            }
            return -1;      //ninguna libre
    }

    public synchronized void liberarPista(int pista){
            pistasDisponibles[pista]=true;
            notifyAll();
        }

}
