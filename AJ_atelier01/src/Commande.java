import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Commandes {
    private static int numeroSuivant = 1;
    private int numero;
    private Client client;
    private LocalDateTime date;
    private ArrayList<LigneDeCommande> ligneDeCommandes;

    public Commandes(Client client) {
        this.client = client;
        this.date = LocalDateTime.now();
        this.ligneDeCommandes = new ArrayList<>();
        this.numero = numeroSuivant;
        numeroSuivant++;
    }

    public int getNumero() {
        return numero;
    }

    public Client getClient() {
        return client;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Iterator<LigneDeCommande> iterator() {
        return ligneDeCommandes.iterator();
    }


}
