package com.example.coach.vue;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.coach.R;
import com.example.coach.controleur.Controle;

public class MainActivity extends AppCompatActivity {

    private Controle controle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    /**
     * Initialisations
     */
    private void init(){
        controle = Controle.getInstance();
        creerMenu();
    }

    private void creerMenu(){
        ecouteMenu((ImageButton)findViewById(R.id.btnMonIMG), CalculActivity.class);
        ecouteMenu((ImageButton)findViewById(R.id.btnMonHistorique), HistoActivity.class );
    }

    private void ecouteMenu(ImageButton btn, final Class classe){
        btn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, classe);
                startActivity(intent);
            }
        });
    }
}