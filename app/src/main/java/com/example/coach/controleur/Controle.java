package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.AccesLocal;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.MainActivity;

import org.json.JSONObject;

import java.util.Date;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {
    private static Controle instance = null;
    private static Profil profil;
    public static String nomFic = "saveprofil";
    //public AccesLocal accesLocal;
    private static AccesDistant accesDistant;
    private static Context context;

    private Controle(Context context) {

        //recupSerialize(context);
        //accesLocal = AccesLocal.getInstance(context);
        //profil = accesLocal.recupDernier();
        if(context != null) {
            Controle.context = context;
        }
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance(Context context) {
        if (Controle.instance == null){
            Controle.instance = new Controle(context);
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("dernier", new JSONObject());
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
    public void creerProfil(Integer poids, Integer taille, Integer age, Integer sexe){
        profil = new Profil(new Date(), poids, taille, age, sexe);
        //Serializer.serialize(nomFic, profil, context);
        //accesLocal.ajout(profil);
        accesDistant.envoi("enreg", profil.convertToJSONObject());
    }

    public void setProfil(Profil profil){
        Controle.profil = profil;
        ((MainActivity)context).recupProfil();
        Log.d("affichage profil", "***********");
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
