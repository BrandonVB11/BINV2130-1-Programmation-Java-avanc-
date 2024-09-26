package domaine;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Plat {
    private String nom;
    private int nbPersonnes;
    private Difficulte niveauDeDifficulte;
    private Cout cout;
    private Duration dureeEnMinutes;
    private List<Instruction> list;
    private Set<IngredientQuantifie> set;

    public Plat(String nom,int nbrPersonnes,Difficulte niveau,Cout cout){
        this.nom = nom;
        this.nbPersonnes = nbrPersonnes;
        this.niveauDeDifficulte = niveau;
        this.cout = cout;
        this.dureeEnMinutes = Duration.ZERO;
        //initialisation de la liste d'instruction
        list = new ArrayList<>();
        //initialisation de la liste d'ingredient

    }

    public String getNom() {
        return nom;
    }

    public int getNbrPersonnes() {
        return nbPersonnes;
    }

    public Difficulte getNiveauDeDifficulte() {
        return niveauDeDifficulte;
    }

    public Cout getCout() {
        return cout;
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void insererInstruction(int position, Instruction instruction){
        //insère l’instruction à la position précisée, position commençant à 1.
        if(position <= 0){
            throw new IllegalArgumentException("Position passée en parametre est inferieur ou egal à 0");
        }
        if(position > list.size()){
            throw new IllegalArgumentException("Position passée en parametre est superieur à la taille de la liste");
        }
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        list.add(position,instruction);
    }

    public void ajouterInstruction (Instruction instruction){
        //ajoute l’instruction en dernier.
        this.dureeEnMinutes = this.dureeEnMinutes.plus(instruction.getDureeEnMinutes());
        list.addLast(instruction);
    }

    public Instruction remplacerInstruction (int position, Instruction instruction){
        //remplace l’instruction à la position précisée par celle en paramètre.
        //renvoie l’instruction qui a été remplacée
        if(position <= 0){
            throw new IllegalArgumentException("Position passée en parametre est inferieur ou egal à 0");
        }
        Instruction remplace = list.get(position);
        this.dureeEnMinutes = this.dureeEnMinutes.minus(remplace.getDureeEnMinutes()).plus(instruction.getDureeEnMinutes());
        list.set(position,instruction);
        return remplace;
    }

    public Instruction supprimerInstruction (int position){
        //supprimer l’instruction à la position précisée
        //renvoie l’instruction qui a été supprimée
        if(position <= 0){
            throw new IllegalArgumentException("Position passée en parametre est inferieur ou egal à 0");
        }
        Instruction supprime = list.get(position);
        this.dureeEnMinutes = this.dureeEnMinutes.minus(supprime.getDureeEnMinutes());
        list.remove(position);
        return supprime;
    }

    public List<Instruction> instructions(){
        // fournit une collection non-modifiable contenant les instructions du plat
        //considéré.
        return List.copyOf(list);
    }


    @Override
    public String toString() {
        String hms = String.format("%d h %02d m", dureeEnMinutes.toHours(), dureeEnMinutes.toMinutes()%60);
        String res = this.nom + "\n\n";
        res += "Pour " + this.nbPersonnes + " personnes\n";
        res += "Difficulté : " + this.niveauDeDifficulte + "\n";
        res += "Coût : " + this.cout + "\n";
        res += "Durée : " + hms + " \n\n";
        /*res += "Ingrédients :\n";
        for (IngredientQuantifie ing : this.ingredients) {
            res += ing + "\n";
        }
        int i = 1;
        res += "\n";
        for (Instruction instruction : this.recette) {
            res += i++ + ". " + instruction + "\n";
        }*/
        return res;
    }


    public enum Difficulte {
        X, XX, XXX, XXXX, XXXXX;
        @Override
        public String toString() {
            return "*".repeat(ordinal() + 1);
        }
    }

    public enum Cout{
        $, $$, $$$, $$$$,$$$$$;
        public String toString() {
            return "€".repeat(ordinal()+1);
        }
    }
}
