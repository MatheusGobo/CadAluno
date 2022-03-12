package com.example.cadastroalunos;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.util.CpfMask;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import fr.ganfra.materialspinner.MaterialSpinner;

import java.util.Calendar;

public class CadastroAlunoActivity extends AppCompatActivity {

    private TextInputEditText edRaAluno;
    private TextInputEditText edNomeAluno;
    private TextInputEditText edCpfAluno;
    private TextInputEditText edDtNasc;
    private TextInputEditText edDtMatricula;
    private MaterialSpinner spCursos;
    private MaterialSpinner spPeriodo;

    private int vAno;
    private int vMes;
    private int vDia;
    private View dataSelecionada;

    private LinearLayout lnPrincipal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_aluno);

        edRaAluno = findViewById(R.id.edRaAluno);
        edNomeAluno = findViewById(R.id.edNomeAluno);
        edCpfAluno = findViewById(R.id.edCpfAluno);
        edDtNasc = findViewById(R.id.edDtNasc);
        edDtMatricula = findViewById(R.id.edDtMatricula);

        edDtNasc.setFocusable(false);
        edDtMatricula.setFocusable(false);

        lnPrincipal = findViewById(R.id.lnPrincipal);

        edCpfAluno.addTextChangedListener(CpfMask.insert(edCpfAluno));

        iniciaSpinners();

        setDataAtual();
    }

    private void setDataAtual() {
        final Calendar calendar = Calendar.getInstance();
        vDia = calendar.get(Calendar.DAY_OF_MONTH);
        vMes = calendar.get(Calendar.MONTH);
        vAno = calendar.get(Calendar.YEAR);
    }

    private void iniciaSpinners() {
        spCursos = findViewById(R.id.spCursos);
        spPeriodo = findViewById(R.id.spPeriodo);

        String cursos[] = new String[]{"Análise e Desenv. Sistemas",
                "Administração",
                "Ciências Contábeis",
                "Direito",
                "Farmácia",
                "Nutrição"};

        String periodos[] = new String[]{"1ª Série",
                "2ª Série",
                "3ª Série",
                "4ª Série"};

        ArrayAdapter adapterCursos = new ArrayAdapter(this, android.R.layout.simple_list_item_1, cursos);

        ArrayAdapter adapterPeriodo = new ArrayAdapter(this, android.R.layout.simple_list_item_1, periodos);


        spCursos.setAdapter(adapterCursos);
        spPeriodo.setAdapter(adapterPeriodo);

        //Ação ao Selecionar o Item da Lista

        //spPeriodo.setVisibility(View.GONE);
        /*
        spCursos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                if (i == 0){

                    Button btADS = new Button(getBaseContext());
                    btADS.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                    btADS.setText("Botão ADS");
                    btADS.setBackgroundColor(getColor(R.color.teal_200));

                    llPrincipal.addView(btADS);


                    spPeriodo.setVisibility(View.VISIBLE);
                } else
                    spPeriodo.setVisibility(View.GONE);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });*/
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

    public void salvarAluno() {
        Aluno aluno = new Aluno();
        aluno.setRa(Integer.parseInt(edRaAluno.getText().toString()));
        aluno.setNome(edNomeAluno.getText().toString());
        aluno.setCpf(edCpfAluno.getText().toString());
        aluno.setDtNasc(edDtNasc.getText().toString());
        aluno.setDtMatricula(edDtMatricula.getText().toString());
        aluno.setCurso(spCursos.getSelectedItem().toString());
        aluno.setPeriodo(spPeriodo.getSelectedItem().toString());

        if (AlunoDAO.salvar(aluno) > 0) {
            setResult(RESULT_OK);
            finish();
        } else {
            Util.customSnakeBar(lnPrincipal, "Erro ao salvar o aluno (" + aluno.getNome() + ") verifique o log", 0);
        }
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
        edRaAluno.setText("");
        edNomeAluno.setText("");
        edCpfAluno.setText("");
        edDtNasc.setText("");
        edDtMatricula.setText("");

    }

    public void selecionarData(View view) {

        dataSelecionada = view;
        setDataAtual();
        showDialog(0);

    }

    private DatePickerDialog.OnDateSetListener setDatePicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            vAno = year;
            vMes = month;
            vDia = day;

            atualizaData();
        }
    };

    private void atualizaData() {
        TextInputEditText edit = (TextInputEditText) dataSelecionada;
        edit.setText(new StringBuilder().append(Util.padLeftZeros(String.valueOf(vDia), 2)).append("/").append(Util.padLeftZeros(String.valueOf(vMes + 1), 2)).append("/").append(vAno));
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, setDatePicker, vAno, vMes, vDia);
    }

}