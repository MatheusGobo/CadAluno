package com.example.cadastroalunos;


import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;
import com.google.android.material.textfield.TextInputEditText;
import fr.ganfra.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class CadastroNotas extends AppCompatActivity {


    private TextInputEditText edNota;
    private AutoCompleteTextView edAluno;
    private MaterialSpinner spRegime;
    private TextInputEditText edDtNasc;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas);

        edNota = findViewById(R.id.edNotaAluno);

        List<Aluno> listaAluno = new ArrayList<>();
        listaAluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");

        String[] nomes = new String[listaAluno.size()];
        for (int i = 0; i < listaAluno.size(); i++) {
            Aluno aluno = listaAluno.get(i);
            Log.e("teste", aluno.getNome());
            nomes[i] = aluno.getRa() + "-" + aluno.getNome();
            Log.e("nones: ", nomes[i]);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nomes);
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.edNomeAluno);
        textView.setAdapter(adapter);


    }

    //Validação dos Campos
    private void validaCampos() {
        //Valida o campo RA do Aluno
        if (edAluno.getText().toString().isEmpty()) {
            edAluno.setError("Informe o Nome do Aluno");
            edAluno.requestFocus();

            return;
        }


    }


}