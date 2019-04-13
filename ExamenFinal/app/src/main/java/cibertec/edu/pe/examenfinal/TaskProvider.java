package cibertec.edu.pe.examenfinal;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class TaskProvider extends ContentProvider {

    public static final int TASKS = 1;
    public static final int TASK = 2;

    private static UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.CONTENT_PATH, TASKS);
        uriMatcher.addURI(TaskContract.AUTHORITY, TaskContract.CONTENT_PATH + "/#", TASK);
    }

    public boolean onCreate() { return true; }

    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        TaskDao taskDao = AppDatabase.getInstance(getContext()).taskDao();
        switch (uriMatcher.match(uri)){
            case TASKS:
                return taskDao.getAllCursor();
            case TASK:
                return taskDao.getByIdCursor(ContentUris.parseId(uri));
        }
        return null;
    }

    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case TASKS:
                return TaskContract.MULTIPLE_ITEMS;
            case TASK:
                return TaskContract.SINGLE_ITEM;
        }
        return null;
    }


    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {

        Task task = new Task();

        String name = values.getAsString("name");
        task.setName(name);

        TaskDao taskDao = AppDatabase.getInstance(getContext()).taskDao();
        Long id = taskDao.insertCP(task);

        return ContentUris.withAppendedId(uri, id);
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {

        TaskDao contactDao = AppDatabase.getInstance(getContext()).taskDao();
        switch (uriMatcher.match(uri)){
            case TASKS:
                return contactDao.deleteAllCursor();
            case TASK:
                return contactDao.deleteByIdCursor(ContentUris.parseId(uri));
        }
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }


}
