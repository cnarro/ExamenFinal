package cibertec.edu.pe.examenfinal;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;

import java.util.List;

public interface TaskDao {

    @Query("select * from task")
    List<Task> getAll();

    @Query("select * from task")
    Cursor getAllCursor();

    @Query("select * from task where id=:id")
    Task getById(int id);

    @Query("select * from task where id=:id")
    Cursor getByIdCursor(long id);

    @Insert
    void insert(Task... tasks);

    @Insert
    long insertCP(Task... tasks);

    @Update
    void update(Task... tasks);

    @Delete
    void delete(Task... tasks);

    @Query("delete from task where id=:id")
    int deleteByIdCursor(long id);

    @Query("delete from task")
    int deleteAllCursor();

}
