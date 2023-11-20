package com.example.coach.controleur;

import android.content.Context;
import android.util.Log;

import com.example.coach.modele.AccesDistant;
import com.example.coach.modele.Profil;
import com.example.coach.outils.Serializer;
import com.example.coach.vue.CalculActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Classe singleton Controle : répond aux attentes de l'activity
 */
public final class Controle {
    private static Controle instance = null;
    public static String nomFic = "saveprofil";
    //public AccesLocal accesLocal;
    private static AccesDistant accesDistant;

    private ArrayList<Profil> lesProfils = new ArrayList<Profil>();

    private Controle() {

        super();
        //recupSerialize(context);
        //accesLocal = AccesLocal.getInstance(context);
        //profil = accesLocal.recupDernier();
    }

    /**
     * Création d'une instance unique de la classe
     * @return l'instance unique
     */
    public final static Controle getInstance() {
        if (Controle.instance == null){
            Controle.instance = new Controle();
            accesDistant = AccesDistant.getInstance();
            accesDistant.envoi("tous", new JSONObject());
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
        Profil unProfil = new Profil(new Date(), poids, taille, age, sexe);
        lesProfils.add(unProfil);
        accesDistant.envoi("enreg", unProfil.convertToJSONObject());
        //Serializer.serialize(nomFic, profil, context);
        //accesLocal.ajout(profil);
    }

    /**public void setProfil(Profil profil){
        Controle.profil = profil;
        ((CalculActivity)context).recupProfil();
    }
     */

    /**
     * getter sur le résultat du calcul de l'img pour le profil
     * @return img du profil
     */
    public float getImg(){
        if(lesProfils.size() > 0){
            return lesProfils.get(lesProfils.size()-1).getImg();
        }else{
            return 0;
        }
    }

    /**
     * getter sur le message correspondant à l'img du profil
     * @return message du profil
     */
    public String getMessage(){
        if(lesProfils.size() > 0){
            return lesProfils.get(lesProfils.size()-1).getMessage();
        }else{
            return "";
        }
    }

    public ArrayList<Profil> getLesProfils() {

        Log.d("ControleGet", String.valueOf(lesProfils.size()));
        return lesProfils;
    }

    public void setLesProfils(ArrayList<Profil> lesProfils) {

        this.lesProfils = lesProfils;
        Log.d("ControleSet", String.valueOf(lesProfils.size()));
    }

    /**
     * Suppression d'un profil
     * @param profil
     */
    public void delProfil(Profil profil){
        accesDistant.envoi("suppr", profil.convertToJSONObject());
        Log.d("profil", "************** profil json = "+profil.convertToJSONObject());
        lesProfils.remove(profil);
    }


    /**private static void recupSerialize(Context context){
        profil = (Profil)Serializer.deSerialize(nomFic, context);
    }
     */
}
