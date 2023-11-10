package com.example.coach.controleur;

import android.content.Context;

import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    private Controle(Context context) {

        recupSerialize(context);
    }

    public static String nomFic = "saveprofil";

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context context) {
        if (Controle.instance == null){
            Controle.instance = new Controle(context);
        }
        return Controle.instance;
    }

    /**
     * Création du profil par rapport aux informations saisies
     * @param poids
     * @param taille
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe, Context context){
        profil = new Profil(poids, taille, age, sexe);
        Serializer.serialize(nomFic, profil, context);
    }

    /**
     * getter sur le résultat du calcul de l'img pour le profil
     * @return img du profil
     */
    public float getImg(){
        if(profil != null){
            return profil.getImg();
        }else{
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du profil
     * @return message du profil
     */
    public String getMessage(){
        if(profil != null){
            return profil.getMessage();
        }else{
            return "";
        }
    }

    /**
     * getter sur la taille du profil
     * @return taille du profil
     */
    public Integer getTaille(){
        if(profil != null){
            return profil.getTaille();
        }else{
            return null;
        }
    }

    /**
     * getter sur le poids du profil
     * @return poids du profil
     */
    public Integer getPoids(){
        if(profil != null){
            return profil.getPoids();
        }else{
            return null;
        }
    }

    /**
     * getter sur l'âge du profil
     * @return âge du profil
     */
    public Integer getAge(){
        if(profil != null){
            return profil.getAge();
        }else{
            return null;
        }
    }

    /**
     * getter sur le sexe du profil
     * @return sexe du profil
     */
    public Integer getSexe(){
        if(profil != null){
            return profil.getSexe();
        }else{
            return null;
        }
    }

    private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }
}
