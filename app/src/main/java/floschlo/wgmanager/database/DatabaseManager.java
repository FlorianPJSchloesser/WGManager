package floschlo.wgmanager.database;

/**
 * Created by Florian Schlösser on 20.10.2016.
 */
public class DatabaseManager {

    public final static String TAG = DatabaseManager.class.getSimpleName();

    private final static String TABLE_GROCERIES = "groceries";

    private final static class GroceriesColumns {
        public final static String ID = "id";
        public final static String NAME = "name";
        public final static String CATEGORY = "category";
    }

}
