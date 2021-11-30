package com.tecmilenio.actividad12;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "libraryDB.db";

    //Tabla de usuarios
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID_USERS = "id";
    private static final String COLUMN_NAME_USERS = "user_name";
    private static final String COLUMN_EMAIL_USERS = "email";
    private static final String COLUMN_PASSWORD_USERS = "password";

    //Tabla de clientes
    private static final String TABLE_CLIENTS = "clients";
    private static final String COLUMN_ID_CLIENTS = "id";
    private static final String COLUMN_NAME_CLIENTS = "name";
    private static final String COLUMN_FIRST_NAME_CLIENTS = "first_name";
    private static final String COLUMN_SECOND_NAME_CLIENTS = "second_name";
    private static final String COLUMN_EMAIL_CLIENTS = "email";
    private static final String COLUMN_PHONE_CLIENTS = "phone";

    //Tabla de libros
    private static final String TABLE_BOOKS = "books";
    private static final String COLUMN_ID_BOOKS = "id";
    private static final String COLUMN_NAME_BOOKS = "name";
    private static final String COLUMN_AUTOR_BOOKS = "autor";
    private static final String COLUMN_CATEGORY_BOOKS = "category";
    private static final String COLUMN_EDITORIAL_BOOKS = "editorial";
    private static final String COLUMN_RELEASE_YEAR_BOOKS = "release_year";



    public DataBaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version){
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tabla de usuarios
        String CREATE_TABLE_USERS = "CREATE TABLE " + TABLE_USERS + "("+
                COLUMN_ID_USERS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME_USERS + " TEXT, "+
                COLUMN_EMAIL_USERS + " TEXT, "+
                COLUMN_PASSWORD_USERS + " TEXT "+
                ")";

        //Tabla de clientes
        String CREATE_TABLE_CLIENTS = "CREATE TABLE " + TABLE_CLIENTS + "("+
                COLUMN_ID_CLIENTS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME_CLIENTS + " TEXT, "+
                COLUMN_FIRST_NAME_CLIENTS + " TEXT, "+
                COLUMN_SECOND_NAME_CLIENTS + " TEXT, "+
                COLUMN_EMAIL_CLIENTS + " TEXT, "+
                COLUMN_PHONE_CLIENTS + " TEXT "+
                ")";

        //Tabla de libros
        String CREATE_TABLE_BOOKS = "CREATE TABLE " + TABLE_BOOKS + "("+
                COLUMN_ID_BOOKS + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                COLUMN_NAME_BOOKS + " TEXT, "+
                COLUMN_AUTOR_BOOKS + " TEXT, "+
                COLUMN_CATEGORY_BOOKS + " TEXT, "+
                COLUMN_EDITORIAL_BOOKS + " TEXT, "+
                COLUMN_RELEASE_YEAR_BOOKS + " TEXT "+
                ")";

        //Ejecutamos la creación de las tablas
        sqLiteDatabase.execSQL(CREATE_TABLE_USERS);
        sqLiteDatabase.execSQL(CREATE_TABLE_CLIENTS);
        sqLiteDatabase.execSQL(CREATE_TABLE_BOOKS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //Borramos las tablas de la base de datos si ya existen
        String DROP_TABLE_USERS = "DROP TABLE IF EXISTS " + TABLE_USERS;
        sqLiteDatabase.execSQL(DROP_TABLE_USERS);

        String DROP_TABLE_CLIENTS = "DROP TABLE IF EXISTS " + TABLE_CLIENTS;
        sqLiteDatabase.execSQL(DROP_TABLE_CLIENTS);

        String DROP_TABLE_BOOKS = "DROP TABLE IF EXISTS " + TABLE_BOOKS;
        sqLiteDatabase.execSQL(DROP_TABLE_BOOKS);

        onCreate(sqLiteDatabase);
    }

    /**
     * Agregamos un nuevo usuario
     * @param user Objeto de tipo User
     * @return Inserción de un usuario a la base de datos Library a la tabla Users
     */
    public long addUser(User user){
        //Creamos el contenedor de valores.
        ContentValues values = new ContentValues();

        //Agregamos cada dato requerido a las columnas de la tabla Users
        values.put(COLUMN_NAME_USERS, user.getUser_name());
        values.put(COLUMN_EMAIL_USERS, user.getEmail());
        values.put(COLUMN_PASSWORD_USERS,user.getPassword());

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Insertamos el registro en la tabla
        long result = database.insert(TABLE_USERS,null,values);

        //Cerramos la base de datos
        database.close();

        return result;
    }

    /**
     * Agregamos un nuevo usuario
     * @param clients Objeto de tipo Clients
     * @return Inserción de un usuario a la base de datos Library a la tabla Clients
     */
    public long addClients(Clients clients){
        //Creamos el contenedor de valores.
        ContentValues values = new ContentValues();

        //Agregamos cada dato requerido a las columnas de la tabla Users
        values.put(COLUMN_NAME_CLIENTS, clients.getName());
        values.put(COLUMN_FIRST_NAME_CLIENTS, clients.getFirstName());
        values.put(COLUMN_SECOND_NAME_CLIENTS, clients.getSecondName());
        values.put(COLUMN_EMAIL_CLIENTS, clients.getEmail());
        values.put(COLUMN_PHONE_CLIENTS,clients.getPhone());

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Insertamos el registro en la tabla
        long result = database.insert(TABLE_CLIENTS,null,values);

        //Cerramos la base de datos
        database.close();

        return result;
    }

    /**
     * Agregamos un nuevo libro
     * @param book Objeto de tipo Book
     * @return Inserción de un usuario a la base de datos Library a la tabla Books
     */
    public long addBooks(Book book) {
        //Creamos el contenedor de valores.
        ContentValues values = new ContentValues();

        //Agregamos cada dato requerido a las columnas de la tabla Users
        values.put(COLUMN_NAME_BOOKS, book.getName());
        values.put(COLUMN_AUTOR_BOOKS, book.getAutor());
        values.put(COLUMN_CATEGORY_BOOKS, book.getCategory());
        values.put(COLUMN_EDITORIAL_BOOKS, book.getEditorial());
        values.put(COLUMN_RELEASE_YEAR_BOOKS, book.getReleaseYear());

        //Obtenemos el objeto de la base de datos
        SQLiteDatabase database = this.getWritableDatabase();

        //Insertamos el registro en la tabla
        long result = database.insert(TABLE_BOOKS, null, values);

        //Cerramos la base de datos
        database.close();

        return result;
    }

    /**
     * Conceder acceso a un usuario a la aplicación
     */
    public boolean getAccess(String user, String password){

        boolean result = false;

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " +
                COLUMN_NAME_USERS + " = " + "\"" + user + "\"" +" AND "+
                COLUMN_PASSWORD_USERS+ " = " + "\""+ password + "\"";

        // Obtenemos el objeto de la base de datos.
        SQLiteDatabase database = this.getWritableDatabase();
        // Realizamos la búsqueda.
        Cursor cursor = database.rawQuery(query, null);

        //Si existe un usuario con esas credenciales regresamos verdadero 
        if(cursor.moveToFirst()){
            result = true;
            cursor.close();
        }else{
            //De lo contrario falso
            result = false;
        }

        database.close();

        return result;
    }
}
