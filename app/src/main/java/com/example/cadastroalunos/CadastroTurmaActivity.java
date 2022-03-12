package com.example.cadastroalunos;

import android.graphics.Color;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cadastroalunos.dao.DisciplinaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import fr.ganfra.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edNomeTurma;
    private TextInputEditText edDisciplinaTurma;
    private TextInputEditText edPeriodoTurma;
    private TextInputEditText edQtAlunos;
    private MaterialSpinner   spRegime;
    private MaterialSpinner   spDisciplina;
    private MaterialSpinner   spPeriodo;

    private LinearLayout lnPrincipal;

    List<Disciplina> lisDisc = new ArrayList<>();
    Disciplina disciplinaSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edNomeTurma       = findViewById(R.id.edNomeTurma);
        edDisciplinaTurma = findViewById(R.id.edDisciplinaTurma);
        edPeriodoTurma    = findViewById(R.id.edPeriodoTurma);
        edQtAlunos        = findViewById(R.id.edQtAlunos);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        iniciaSpinners();
    }
    private void iniciaSpinners() {
        spDisciplina = findViewById(R.id.spDisciplinaTurma);
        spPeriodo    = findViewById(R.id.spPeriodoTurma);
        spRegime     = findViewById(R.id.spRegime);

        lisDisc = DisciplinaDAO.retornaDisciplina("", new String[]{}, "");

        String periodos[] = new String[]{"Matutino",
                "Vespertino",
                "Noturno",};

        String regimes[] = new String[]{"Anual",
                                       "Semestral"
        };

        ArrayAdapter adapterPeriodo    = new ArrayAdapter(this, android.R.layout.simple_list_item_1, periodos);
        ArrayAdapter adapterDisciplina = new ArrayAdapter(this, android.R.layout.simple_list_item_1, lisDisc);
        ArrayAdapter adapterRegime     = new ArrayAdapter(this, android.R.layout.simple_list_item_1, regimes);

        spPeriodo.setAdapter(adapterPeriodo);
        spDisciplina.setAdapter(adapterDisciplina);
        spRegime.setAdapter(adapterRegime);

        spDisciplina.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    disciplinaSelecionada = lisDisc.get(position - 1);
                }else {
                    disciplinaSelecionada = null;
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

                limparCampos();

                return true;
            case R.id.mn_save:

                validaCampos();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void limparCampos() {
        edNomeTurma.setText("");
        edQtAlunos.setText("");
        spPeriodo.setSelection(0);
        spDisciplina.setSelection(0);
        spRegime.setSelection(0);
    }

    private void validaCampos() {

        spDisciplina = findViewById(R.id.spDisciplinaTurma);
        spPeriodo    = findViewById(R.id.spPeriodoTurma);

        if (edNomeTurma.getText().toString().isEmpty()) {
            edNomeTurma.setError("Informe o Nome da Turma");
            edNomeTurma.requestFocus();

            return;
        }

        if (spPeriodo.getSelectedItem() == null){
            TextView errorText = (TextView)spPeriodo.getSelectedView();
            errorText.setError("Informe o Período!");
            errorText.setTextColor(Color.RED);
            errorText.setText("Informe o Período!");
            errorText.requestFocus();
            return;
        }
/*
        if (spDisciplina.getSelectedItem() == null){
            TextView errorText = (TextView)spDisciplina.getSelectedView();
            errorText.setError("Informe a Disciplina!");
            errorText.setTextColor(Color.RED);
            errorText.setText("Informe a Disciplina!");
            errorText.requestFocus();
            return;
        }*/

        if (edQtAlunos.getText().toString().isEmpty()) {
            edQtAlunos.setError("Informe a quantidade de Alunos na Turma");
            edQtAlunos.requestFocus();

            return;
        }
        salvarTurma();
    }

    public void salvarTurma() {
        Turma turma = new Turma();
        turma.setNome(edNomeTurma.getText().toString());
        turma.setPeriodo(spPeriodo.getSelectedItem().toString());
        turma.setQtAlunos(Integer.parseInt(edQtAlunos.getText().toString()));
        turma.setRegime(spRegime.getSelectedItem().toString());
        //turma.setDisciplina(spDisciplina.getSelectedItem().toString());

        if (TurmaDAO.salvar(turma) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnakeBar(lnPrincipal, "Erro ao salvar a Turma (" + turma.getNome() + ") verifique o log", 0);
        }
    }
}