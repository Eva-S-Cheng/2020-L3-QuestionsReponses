package projet_java;

public class Chrono implements Runnable
{
    private Thread chronometre;                                 // Ce qui va permettre de run
    private long temps = 0;                                     // Stocke le temps écoulé
    private boolean running = true;                             // Chronomètre en marche
    public void start ()
    {
        chronometre = new Thread (this);
        chronometre.start ();                                   // Démarrage du chronomètre
    }

    public void run ()
    {
        try
        {
            while (running && chronometre.isAlive())            // Pas sur pause et alive
            {
                temps++;
                Thread.sleep (1);
            }
        }
        catch (InterruptedException e) {
            System.out.println("Erreur");
        }
    }

    public void stop ()
    {
        chronometre.stop ();                                    // Arrêt complet
    }

    public void pause() {
        running = false;                                        // Mise en suspens
    }

    public synchronized  void resume() {
        running = true;                                         // Reprend
    }

    public long getTemps() {
        return temps;                                           // Retourne le temps écoulé
    }
}
