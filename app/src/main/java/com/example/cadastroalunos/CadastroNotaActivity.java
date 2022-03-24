package com.example.cadastroalunos;


import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.cadastroalunos.dao.AlunoDAO;
import com.example.cadastroalunos.dao.FrequenciaDAO;
import com.example.cadastroalunos.dao.NotaDAO;
import com.example.cadastroalunos.dao.TurmaDAO;
import com.example.cadastroalunos.model.Aluno;
import com.example.cadastroalunos.model.Frequencia;
import com.example.cadastroalunos.model.Nota;
import com.example.cadastroalunos.model.Turma;
import com.example.cadastroalunos.util.Util;
import com.google.android.material.textfield.TextInputEditText;
import fr.ganfra.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

public class CadastroNotaActivity extends AppCompatActivity {

    private MaterialSpinner spTurma;
    private AutoCompleteTextView edAluno;
    private TextInputEditText edNotaAluno;
    private LinearLayout lnPrincipal;

    List<Turma> listTurma = new ArrayList<>();
    List<Aluno> listaAluno = new ArrayList<>();
    String[] nomes;
    ArrayAdapter<String> adapter;
    Turma turmaSelecionada = new Turma();
    int raAlunoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_notas);

        lnPrincipal = findViewById(R.id.lnPrincipal);
        edAluno = findViewById(R.id.edAluno);
        edAluno.setVisibility(View.GONE);

        edNotaAluno = findViewById(R.id.edNotaAluno);

        iniciaSpinners();
        iniciaListaAlunos();
    }

    private void iniciaSpinners() {
        spTurma = findViewById(R.id.spTurma);

        listTurma = TurmaDAO.retornaTurma("", new String[]{}, "nome asc");

        ArrayAdapter adapterTurma = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listTurma);

        spTurma.setAdapter(adapterTurma);
        spTurma.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position < 0) {
                    turmaSelecionada = new Turma();
                    edAluno.setVisibility(View.GONE);
                } else {
                    turmaSelecionada = listTurma.get((int) (id) - 1);
                    edAluno.setVisibility(View.VISIBLE);
                    atualizaSelect();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void atualizaSelect() {
        if (turmaSelecionada.getId() == null) {
            listaAluno = AlunoDAO.retornaAlunos("", new String[]{}, "nome asc");
        } else {
            listaAluno = AlunoDAO.retornaAlunos("id_turma = ?", new String[]{String.valueOf(turmaSelecionada.getId())}, "nome asc");
        }

        nomes = new String[listaAluno.size()];
        for (int i = 0; i < listaAluno.size(); i++) {
            Aluno aluno = listaAluno.get(i);
            nomes[i] = aluno.getNome() + ", RA: " + aluno.getRa();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, nomes);
        edAluno.setAdapter(adapter);

    }

    private void iniciaListaAlunos() {
        atualizaSelect();
        edAluno.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                raAlunoSelecionado = Integer.parseInt(adapter.getItem(position).substring(adapter.getItem(position).indexOf("RA: ") + 4, adapter.getItem(position).length()));
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
        spTurma.setSelection(0);
        edAluno.setText("");
        edNotaAluno.setText("");
    }

    private void validaCampos() {
        //Valida Turma
        try {
            if (turmaSelecionada.getId() != null) {
                if (turmaSelecionada.getId() <= 0) {
                    spTurma.setError("Informe a Turma do Aluno");
                    spTurma.requestFocus();

                    return;
                }
            } else {
                spTurma.setError("Informe a Turma do Aluno");
                spTurma.requestFocus();

                return;
            }

        } catch (Exception e) {
            Log.e("Frequencia", "Erro ao buscar a turma selecionada");
        }
        try {
            if (edAluno.getText().toString().isEmpty()) {
                edAluno.setError("Informe o Aluno");
                edAluno.requestFocus();

                return;
            } else {
                raAlunoSelecionado = 0;

                String textCampoAluno = edAluno.getText().toString();
                raAlunoSelecionado = Integer.parseInt(textCampoAluno.substring(textCampoAluno.indexOf("RA: ") + 4, textCampoAluno.length()));

                Aluno aluno = AlunoDAO.retornaPorRA(raAlunoSelecionado);

                if (aluno == null) {
                    edAluno.setError("Não encontrado Aluno com RA (" + raAlunoSelecionado + "), Verifique !");
                    edAluno.requestFocus();

                    return;
                }
            }
        } catch (Exception e) {
            Log.e("Frequencia", "Erro ao extrair RA do campo de Aluno = " + e.getMessage());
        }

        if (raAlunoSelecionado == 0) {
            edAluno.setError("Informe o Aluno");
            edAluno.requestFocus();

            return;
        }

        //Valida o campo de Frequencia
        if (edNotaAluno.getText().toString().isEmpty()) {
            edNotaAluno.setError("Informe a Nota");
            edNotaAluno.requestFocus();

            return;
        } else if (Integer.parseInt(edNotaAluno.getText().toString()) > 100) {
            edNotaAluno.setError("A nota não pode ser maior que 100");
            edNotaAluno.requestFocus();

            return;
        }

        salvarFrequencia();
    }


    public void salvarFrequencia() {

        Aluno alunoSalvar = new Aluno();
        alunoSalvar = AlunoDAO.retornaPorRA(raAlunoSelecionado);

        List<Nota> notaExist = NotaDAO.retornaNota("id_aluno = ? and id_turma = ?", new String[]{String.valueOf(alunoSalvar.getId()),
                                                                                                                         String.valueOf(turmaSelecionada.getId())}, "");

        if (turmaSelecionada.getRegime().toUpperCase().equals("ANUAL")) {
            if (notaExist.size() >= 4) {
                edAluno.setError("Todas as notas para o aluno já foram lançadas");
                edAluno.requestFocus();

                return;
            }
        } else {
            if (notaExist.size() >= 2) {
                edAluno.setError("Todas as notas para o aluno já foram lançadas");
                edAluno.requestFocus();

                return;
            }
        }

        Nota nota = new Nota();

        nota.setIdAluno(alunoSalvar.getId());
        nota.setIdTurma(turmaSelecionada.getId());
        nota.setnota(Integer.parseInt(edNotaAluno.getText().toString()));

        if (NotaDAO.salvar(nota) > 0) {
            setResult(RESULT_OK);
            finish();
            Util.customSnakeBar(lnPrincipal, "Nota lançada com sucesso", 1);
        } else {
            Util.customSnakeBar(lnPrincipal, "Erro ao salvar a nota, verifique o log", 0);
        }
    }

}