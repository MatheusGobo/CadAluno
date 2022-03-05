package com.example.cadastroalunos;

import android.content.Intent;
import android.util.Log;
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
import com.example.cadastroalunos.adapters.AlunoAdapter;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.util.Util;

import java.util.ArrayList;
import java.util.List;

public class ListaAlunoActivity extends AppCompatActivity {

    private RecyclerView rvListaAlunos;
    private LinearLayout lnLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_aluno);

        lnLista = findViewById(R.id.lnLista);

        atualizaListaAluno();
    }

    public void atualizaListaAluno() {
        List<Aluno> listaAluno = new ArrayList<>();
        listaAluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");

        rvListaAlunos = (RecyclerView) findViewById(R.id.rvListaAlunos);

        AlunoAdapter adapter = new AlunoAdapter(this, listaAluno);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rvListaAlunos.setLayoutManager(llm);
        rvListaAlunos.setAdapter(adapter);
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

                abrirCadastroAluno();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void abrirCadastroAluno() {
        Intent intent = new Intent(this, CadastroAlunoActivity.class);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            Util.customSnakeBar(lnLista, "Aluno salvo com sucesso !", 1);
            atualizaListaAluno();
        }
    }
}