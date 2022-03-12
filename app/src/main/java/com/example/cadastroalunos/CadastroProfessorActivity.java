package com.example.cadastroalunos;

import android.view.View;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cadastroalunos.util.CpfMask;
import com.google.android.material.textfield.TextInputEditText;
;import java.util.Calendar;

public class CadastroProfessorActivity extends AppCompatActivity {


    private TextInputEditText edRaProfessor;
    private TextInputEditText edNomeProfessor;
    private TextInputEditText edCpfProfessor;
    private TextInputEditText edDtNasc;
    private TextInputEditText edDtContratacao;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_professor);

        edRaProfessor = findViewById(R.id.edRaProfessor);
        edNomeProfessor = findViewById(R.id.edNomeProfessor);
        edCpfProfessor = findViewById(R.id.edCpfProfessor);
        edDtNasc = findViewById(R.id.edDtNasc);
        edDtContratacao = findViewById(R.id.edDtContratacao);

        edDtNasc.setFocusable(false);
        edDtContratacao.setFocusable(false);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        edCpfProfessor.addTextChangedListener(CpfMask.insert(edCpfProfessor));

        setDataAtual();
    }

    private void setDataAtual() {
        final Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    //Validação dos Campos
    private void validaCampos() {
        //Valida o campo RA do Aluno
        if (edRaAluno.getText().toString().isEmpty()) {
            edRaAluno.setError("Informe o RA do Aluno");
            edRaAluno.requestFocus();

            return;
        }

        //Valida o campo Nome do Aluno
        if (edNomeAluno.getText().toString().isEmpty()) {
            edNomeAluno.setError("Informe o Nome do Aluno");
            edNomeAluno.requestFocus();

            return;
        }

        //Valida o campo CPF do Aluno
        if (edCpfAluno.getText().toString().isEmpty()) {
            edCpfAluno.setError("Informe o CPF do Aluno");
            edCpfAluno.requestFocus();

            return;
        }

        //Valida o campo Data de Nascimento do Aluno
        if (edDtNasc.getText().toString().isEmpty()) {
            edDtNasc.setError("Informe a Data de Nascimento do Aluno");
            edDtNasc.requestFocus();

            return;
        }

        //Valida o campo Data de Matricula do Aluno
        if (edDtMatricula.getText().toString().isEmpty()) {
            edDtMatricula.setError("Informe a Data de Matricula do Aluno");
            edDtMatricula.requestFocus();

            return;
        }

        salvarAluno();
    }

}