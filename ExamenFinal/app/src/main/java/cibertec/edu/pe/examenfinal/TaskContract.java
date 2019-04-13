package cibertec.edu.pe.examenfinal;

public class TaskContract {

    public static final String AUTHORITY = "cibertec.edu.pe.examenfinal.provider";

    public static final String CONTENT_PATH = "task";

    public static final String CONTENT_URI = "content://" + AUTHORITY + "/" + CONTENT_PATH;

    public static final String MULTIPLE_ITEMS = "vnd.android.cursor.dir/vnd."
            + AUTHORITY + "."
            + CONTENT_PATH;

    public static final String SINGLE_ITEM = "vnd.android.cursor.id/vnd."
            + AUTHORITY + "."
            + CONTENT_PATH;

}
