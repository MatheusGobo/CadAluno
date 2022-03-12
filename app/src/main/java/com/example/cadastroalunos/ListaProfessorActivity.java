package com.example.cadastroalunos;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastroalunos.adapters.ProfessorAdapter;;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Professor;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaProfessorActivity extends AppCompatActivity {

    private RecyclerView rvListaProfessor;
    private LinearLayout lnLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_professor);

        lnLista = findViewById(R.id.lnListaProfessor);

    }

    public void atualizaListaProfessor() {
        List<Professor> listaProfessor = new ArrayList<>();
        listaProfessor = ProfessorDAO.retornaProfessor("", new String[]{}, "nome asc");

        rvListaProfessor = (RecyclerView) findViewById(R.id.rvListaProfessor);

        ProfessorAdapter adapter = new ProfessorAdapter(listaProfessor, this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaProfessor.setLayoutManager(llm);
        rvListaProfessor.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_lista, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add:

                abrirCadatroProfessor();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadatroProfessor() {
        Intent intent = new Intent(this, CadastroProfessorActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Util.customSnakeBar(lnLista, "Professor salvo com sucesso !", 1);
            atualizaListaProfessor();
        }
    }
}