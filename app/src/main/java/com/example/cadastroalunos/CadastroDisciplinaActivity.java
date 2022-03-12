package com.example.cadastroalunos;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;
import fr.ganfra.materialspinner.MaterialSpinner;

import java.util.*;

public class CadastroDisciplinaActivity extends AppCompatActivity {

    private TextInputEditText edNomeDisciplina;
    private MaterialSpinner spProfessor;

    List<Professor> lisPro = new ArrayList<>();
    Professor profSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplina);

        edNomeDisciplina = findViewById(R.id.edNomeDisciplina);

        iniciaSpinners();
    }

    private void iniciaSpinners() {
        spProfessor = findViewById(R.id.spProfessor);

        lisPro = ProfessorDAO.retornaProfessor("", new String[]{}, "");

        ArrayAdapter adapterProfessor = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lisPro);

        spProfessor.setAdapter(adapterProfessor);

        spProfessor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               if (position > 0) {
                   profSelecionado = lisPro.get(position - 1);
               }else {
                   profSelecionado = null;
               }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.menu_cadastro, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_limpar:

                limparCampor();

                return true;
            case R.id.mn_save:

                validaCampos();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampor() {
        edNomeDisciplina.setText("");
        spProfessor.setSelection(0);

    }

    private void validaCampos(){
        //Valida o campo RA do Aluno
        if (edNomeDisciplina.getText().toString().isEmpty()) {
            edNomeDisciplina.setError("Informe o nome da Disciplina");
            edNomeDisciplina.requestFocus();

            return;
        }

        if (profSelecionado == null) {
            spProfessor.setError("Informe o professor");
            spProfessor.requestFocus();

            return;
        }
    }

}