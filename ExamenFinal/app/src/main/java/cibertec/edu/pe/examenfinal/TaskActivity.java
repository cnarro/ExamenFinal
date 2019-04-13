package cibertec.edu.pe.examenfinal;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class TaskActivity extends AppCompatActivity {

    TextInputEditText etName;
    Task task;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        etName = findViewById(R.id.etName);

        int id = getIntent().getIntExtra("id", -1);

        if (id > -1) {
            String name = getIntent().getStringExtra("name");
            task = new Task(id, name);
            etName.setText(name);
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.optionSave:
                String name = etName.getText().toString();
                if (task != null) {
                    task.setName(name);
                    new UpdateTaskTask().execute(task);
                } else {
                    task = new Task(name);
                    new AddTaskTask().execute(task);
                }
                break;
            case R.id.optionDelete:
                new DeleteTaskTask().execute(task);
                break;
        }
        finish();
        return true;
    }

    private class UpdateTaskTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            AppDatabase.getInstance(TaskActivity.this).taskDao().update(task);
            return null;
        }
    }

    private class AddTaskTask extends AsyncTask<Task, Void, Void> {
        @Override
        protected Void doInBackground(Task... tasks) {
            AppDatabase.getInstance(TaskActivity.this).taskDao().insert(task);
            return null;
        }

    }

    private class DeleteTaskTask extends AsyncTask<Task, Void, Void>{
        @Override
        protected Void doInBackground(Task... tasks) {
            AppDatabase.getInstance(TaskActivity.this).taskDao().delete(task);
            return null;
        }
    }

}
