package com.example.cadastroalunos;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastroalunos.adapters.TurmaAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaTurmaActivity extends AppCompatActivity {


    private RecyclerView rvListaTurma;
    private LinearLayout lnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_turma);

        lnLista = findViewById(R.id.lnListaTurma);

        atualizaListaTurma();
    }

    public void atualizaListaTurma() {
        List<Turma> listaTurma = new ArrayList<>();

        listaTurma = TurmaDAO.retornaTurma("", new String[]{}, "nome asc");

        rvListaTurma = (RecyclerView) findViewById(R.id.rvListaTurma);

        TurmaAdapter adapter = new TurmaAdapter(listaTurma, this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaTurma.setLayoutManager(llm);
        rvListaTurma.setAdapter(adapter);
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

                abrirCadastroTurma();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroTurma() {
        Intent intent = new Intent(this, CadastroTurmaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnakeBar(lnLista, "Turma salva com sucesso !", 1);
            atualizaListaTurma();
        }
    }
}