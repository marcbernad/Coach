package com.example.coach.modele;

import android.util.Log;

import com.example.coach.outils.MesOutils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;

/**
 * Classe Profil : regroupe les informations du profil
 */
public class Profil implements Serializable, Comparable {


    // constantes
    private static final Integer minFemme = 15; // maigre si en dessous
    private static final Integer maxFemme = 30; // gros si au dessus
    private static final Integer minHomme = 10; // maigre si en dessous
    private static final Integer maxHomme = 25; // gros si au dessus

    private int poids;
    private int taille;
    private int age;
    private int sexe;


    private Date dateMesure;
    private float img = 0;
    private String message = "";

    /**
     * Constructeur
     * @param poids
     * @param taille en cm
     * @param age
     * @param sexe 1 pour homme, 0 pour femme
     */
    public Profil(Date dateMesure, int poids, int taille, int age, int sexe) {
        this.poids = poids;
        this.taille = taille;
        this.age = age;
        this.sexe = sexe;
        this.dateMesure = dateMesure;
    }

    /**
     * Getter sur le poids
     * @return poids
     */
    public Integer getPoids() {
        return poids;
    }

    /**
     * getter sur la taille
     * @return taille
     */
    public Integer getTaille() {
        return taille;
    }

    /**
     * getter sur l'âge
     * @return age
     */
    public Integer getAge() {
        return age;
    }

    /**
     * getter sur le sexe
     * @return sexe
     */
    public Integer getSexe() {
        return sexe;
    }

    /**
     * getter sur la date de mesure
     * @return dateMesure
     */
    public Date getDateMesure() {
        return dateMesure;
    }

    /**
     * méthode qui calcule l'img
     * @return img
     */
    public float getImg() {
        if (img == 0){
            float tailleMetre = ((float)taille)/100;
            img = (float)((1.2 * poids/(tailleMetre * tailleMetre)) + (0.23 * age) - (10.83 * sexe) - 5.4);
        }
        return img;
    }

    /**
     * Méthode qui renvoie un message en fonction de l'img
     * @return message
     */
    public String getMessage() {
        if (message == ""){
            img = getImg();
            if (sexe == 0){
                if (img < minFemme){
                    message = "trop faible";
                } else if (img >maxFemme) {
                    message = "trop élevé";
                }
                else{
                    message = "normal";
                }
            }else{
                if (img < minHomme){
                    message = "trop faible";
                } else if (img > maxHomme) {
                    message = "trop élevé";
                }else{
                    message = "normal";
                }
            }
        }
        return message;
    }
    public JSONObject convertToJSONObject(){
        JSONObject jsonProfil = new JSONObject();
        try {
            jsonProfil.put("datemesure", MesOutils.convertDateToString(dateMesure));
            jsonProfil.put("poids", poids);
            jsonProfil.put("taille", taille);
            jsonProfil.put("age", age);
            jsonProfil.put("sexe", sexe);
        } catch (JSONException e) {
            Log.d("erreur", "************* classe Profil, méthode convertToJSONObject, erreur de conversion");
        }
        return jsonProfil;
    }

    @Override
    public int compareTo(Object o) {
        return dateMesure.compareTo(((Profil)o).getDateMesure());
    }
}
