package cibertec.edu.pe.examenfinal;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskPrototype> {

    List<Task> items;

    public TaskAdapter(List<Task> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public TaskPrototype onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.prototype_task, viewGroup, false);
        TaskPrototype taskPrototype = new TaskPrototype(view);
        return taskPrototype;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskPrototype taskPrototype, final int position) {
        taskPrototype.tvName.setText(items.get(position).getName());

        taskPrototype.cvTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Task task = items.get(position);

                long id = task.getId();
                String name = task.getName();

                Intent intent = new Intent(v.getContext(), TaskAdapter.class);

                intent.putExtra("id", id);
                intent.putExtra("name", name);

                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class TaskPrototype extends RecyclerView.ViewHolder{
        CardView cvTask;
        TextView tvName;

        public TaskPrototype(@NonNull View itemView){
            super(itemView);
            cvTask = itemView.findViewById(R.id.cvTask);
            tvName = itemView.findViewById(R.id.tvName);
        }

    }
}
