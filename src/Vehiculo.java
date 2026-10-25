public class Vehiculo implements Runnable {
        private String nombre;
        private Pistas pistas;

        public Vehiculo(String nombre, Pistas pistas) {
                this.nombre = nombre;
                this.pistas = pistas;
        }

        @Override
        public void run() {
                int pista = pistas.ocuparPista();
                if(pista==-1){
                        return;         //lo interrumpimos mientras esperaba
                }
                System.out.println(nombre + "ha entrado en la pista" + (pista+1));
                long inicio=System.currentTimeMillis();
                try{
                        long duracion = 2000 + (long) (Math.random()*3000); //entre 2 y 5 seg
                        Thread.sleep(duracion);
                }catch(InterruptedException e){
                        Thread.currentThread().interrupt();
                }
                double segundos= (System.currentTimeMillis()-inicio)/1000.0;
                System.out.println(nombre + "completó la prueba en " + String.format("%.3f", segundos) + "segundos" );
                pistas.liberarPista(pista);
                System.out.println(nombre + "ha salido de la pista"+ (pista+1));
        }
}
