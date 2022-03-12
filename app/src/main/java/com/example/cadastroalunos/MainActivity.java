package com.example.cadastroalunos;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void cadastroAluno(View view) {
        Intent intent = new Intent(this, ListaAlunoActivity.class);
        startActivity(intent);
    }


    public void cadastroDisciplina(View view) {
        Intent intent = new Intent(this, ListaDisciplinaActivity.class);
        startActivity(intent);
    }

    public void cadastroProfessor(View view) {
        Intent intent = new Intent(this, ListaProfessorActivity.class);
        startActivity(intent);
    }
}