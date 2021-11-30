package com.tecmilenio.actividad12;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //Campo nombre del usuario
    EditText editUser;
    //Campo contraseña
    EditText editPassword;

    //Botón inicia sesión
    Button login;
    //Botón de registro de usuario
    Button register;

    //Manejador de la base de datos
    DataBaseHandler dataBaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editUser = findViewById(R.id.editTextTextPersonName);
        editPassword = findViewById(R.id.editTextTextPassword);

        login = findViewById(R.id.btn_create_account);
        login.setOnClickListener(this);

        register = findViewById(R.id.btn_back);
        register.setOnClickListener(this);

        dataBaseHandler = new DataBaseHandler(this,null,null,1);
    }

    @Override
    public void onClick(View view) {
        if(view.equals(login)){
            goToPrincipalScreen();
        }else if(view.equals(register)){
            goToRegisterScreen();
        }
    }

    public void goToPrincipalScreen(){
        //Obtengo la información de los campos
        String user = editUser.getText().toString().trim();
        String password = editPassword.getText().toString().trim();
        
        //Veo la información, lo puedes omitir
        System.out.println("Usuario: "+user);
        System.out.println("Contraseña: "+password);
        
        //Lo pongo en un trycatch por trauma de Sosa no por otra cosa, lo puede quitar y funciona
        //recuerdo que estaba haciendo pruebas y por eso lo puse, porque no se estaban registrando
        //porque mi handler estaba en otro paquete
        try {
            //Si los campos tienen contenido
            if(!user.equals("") && password.equals("")){
                //Si el usuario y contraseña existen en la base de datos
                if(dataBaseHandler.getAccess(user,password)){
                    //Avanzo a la siguiente pantalla
                    Intent intent = new Intent(this, Home.class); //Cambias Home por tu clase Lulis <3
                    startActivity(intent);
                }else{
                    //Si no existe
                    Toast fail =
                            Toast.makeText(getApplicationContext(),"No existe este usuario",Toast.LENGTH_SHORT);
                    fail.show();
                }
            }else{
                //Si mis campos están vacíos e intento iniciar sesión
                Toast emptyFields =
                        Toast.makeText(getApplicationContext(),"Rellene los campos primero",Toast.LENGTH_SHORT);
                emptyFields.show();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void goToRegisterScreen(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }

}
