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
import com.example.cadastroalunos.adapters.DisciplinaAdapter;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

;

public class ListaDisciplinaActivity extends AppCompatActivity {

    private RecyclerView rvListaDisciplina;
    private LinearLayout lnLista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_disciplina);

        lnLista = findViewById(R.id.lnListaDisciplina);

        atualizaLista();
    }

    public void atualizaLista() {
        List<Disciplina> listaDisciplina = new ArrayList<>();

        listaDisciplina = DisciplinaDAO.retornaDisciplina("", new String[]{}, "");

        rvListaDisciplina = (RecyclerView) findViewById(R.id.rvListaDisciplina);

        DisciplinaAdapter adapter = new DisciplinaAdapter(listaDisciplina, this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaDisciplina.setLayoutManager(llm);
        rvListaDisciplina.setAdapter(adapter);
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

                abrirCadatroDisciplina();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadatroDisciplina() {
        Intent intent = new Intent(this, CadastroDisciplinaActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Util.customSnakeBar(lnLista, "Disciplina salva com sucesso !", 1);
            atualizaLista();
        }
    }
}