package cibertec.edu.pe.examenfinal;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rvTasks;
    TaskAdapter adapter;
    List<Task> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvTasks = findViewById(R.id.rvTasks);
    }

    private void loadItems() {
        new GetAllTasksTask().execute();
    }

    private class GetAllTasksTask extends AsyncTask<Void, Void, List<Task>> {

        @Override
        protected List<Task> doInBackground(Void... voids) {
            items = AppDatabase.getInstance(MainActivity.this).taskDao().getAll();
            return items;
        }

        @Override
        protected void onPostExecute(List<Task> tasks) {
            super.onPostExecute(tasks);
            items = tasks;
            adapter = new TaskAdapter(items);
            rvTasks.setAdapter(adapter);
            rvTasks.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        }
    }
}
