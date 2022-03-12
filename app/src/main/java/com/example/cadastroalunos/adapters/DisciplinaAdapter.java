package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastroalunos.R;
import com.example.cadastroalunos.dao.ProfessorDAO;
import com.example.cadastroalunos.model.Disciplina;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class DisciplinaAdapter extends RecyclerView.Adapter<DisciplinaAdapter.ProfessorViewHolder> {
    private List<Disciplina> listaDisciplina;
    private Context context;

    public DisciplinaAdapter(List<Disciplina> listaDisciplina, Context context) {
        this.listaDisciplina = listaDisciplina;
        this.context = context;
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_disciplina, parent, false);
        ProfessorViewHolder viewHolder = new ProfessorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Disciplina disciplina = listaDisciplina.get(position);
        holder.edNomeDisciplina .setText(String.valueOf(disciplina.getNome()));

        holder.edNomeProfessor.setText(ProfessorDAO.retornaPorRA(disciplina.getRaProfessor()).getNome());
    }

    @Override
    public int getItemCount() {
        return listaDisciplina.size();
    }

    public class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edNomeDisciplina;
        TextInputEditText edNomeProfessor;
        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);

            edNomeDisciplina   = (TextInputEditText)itemView.findViewById(R.id.edNomeDisciplina);
            edNomeProfessor    = (TextInputEditText)itemView.findViewById(R.id.edNomeProfessor);
        }
    }
}
