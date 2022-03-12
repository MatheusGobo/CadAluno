package com.example.cadastroalunos.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cadastroalunos.R;
import com.example.cadastroalunos.model.Professor;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class ProfessorAdapter extends RecyclerView.Adapter<ProfessorAdapter.ProfessorViewHolder> {
    private List<Professor> listaProfessores;
    private Context context;

    public ProfessorAdapter(List<Professor> listaProfessores, Context context) {
        this.listaProfessores = listaProfessores;
        this.context = context;
    }

    @Override
    public ProfessorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_professor, parent, false);
        ProfessorViewHolder viewHolder = new ProfessorViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfessorViewHolder holder, int position) {
        Professor professor = listaProfessores.get(position);
        holder.edRaProfessor     .setText(String.valueOf(professor.getRa()));
        holder.edCpfProfessor    .setText(professor.getCpf());
        holder.edNomeProfessor   .setText(professor.getNome());
        holder.edDtNascimento    .setText(professor.getDtNascimento());
    }

    @Override
    public int getItemCount() {
        return listaProfessores.size();
    }

    public class ProfessorViewHolder extends RecyclerView.ViewHolder {
        TextInputEditText edRaProfessor;
        TextInputEditText edNomeProfessor;
        TextInputEditText edCpfProfessor;
        TextInputEditText edDtNascimento;
        public ProfessorViewHolder(@NonNull View itemView) {
            super(itemView);

            edRaProfessor      = (TextInputEditText)itemView.findViewById(R.id.edRaProfessorView);
            edNomeProfessor    = (TextInputEditText)itemView.findViewById(R.id.edNomeProfessorView);
            edCpfProfessor     = (TextInputEditText)itemView.findViewById(R.id.edCPFProfessorView);
            edDtNascimento     = (TextInputEditText)itemView.findViewById(R.id.edDtNascimentoProfessorView);
        }
    }
}
