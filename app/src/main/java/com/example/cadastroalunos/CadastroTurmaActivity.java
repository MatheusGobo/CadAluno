package com.example.cadastroalunos;

import android.widget.ArrayAdapter;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.material.textfield.TextInputEditText;
import fr.ganfra.materialspinner.MaterialSpinner;

public class CadastroTurmaActivity extends AppCompatActivity {

    private TextInputEditText edNomeTurma;
    private TextInputEditText edDisciplinaTurma;
    private TextInputEditText edPeriodoTurma;
    private TextInputEditText edQtAlunos;
    private MaterialSpinner   spDisciplina;
    private MaterialSpinner   spPeriodo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        edNomeTurma       = findViewById(R.id.edNomeTurma);
        edDisciplinaTurma = findViewById(R.id.edDisciplinaTurma);
        edPeriodoTurma    = findViewById(R.id.edPeriodoTurma);
        edQtAlunos        = findViewById(R.id.edQtAlunos);

        iniciaSpinners();
    }
    private void iniciaSpinners() {
        spDisciplina = findViewById(R.id.spDisciplinaTurma);
        spPeriodo    = findViewById(R.id.spPeriodoTurma);

        String periodos[] = new String[]{"Matutino",
                "Vespertino",
                "Noturno",};

        ArrayAdapter adapterPeriodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, periodos);

        spPeriodo.setAdapter(adapterPeriodo);

    }
}