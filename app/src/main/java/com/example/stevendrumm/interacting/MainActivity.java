package com.example.stevendrumm.interacting;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText campoTelefono;
    Button boton;
    EditText latitud;
    EditText longitud;
    Button boton2;
    EditText url;
    Button boton3;
    EditText etEmail;
    EditText etSubject;
    EditText etBody;
    CheckBox chkAttachment;
    Button boton4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoTelefono = (EditText) findViewById(R.id.editText);
        boton = (Button) findViewById(R.id.buttonLlamar);
        latitud = (EditText)findViewById(R.id.latitud);
        longitud = (EditText)findViewById(R.id.longitud);
        boton2 = (Button)findViewById(R.id.buttonbuscar);
        url = (EditText)findViewById(R.id.Url);
        boton3 = (Button) findViewById(R.id.buttonNavegar);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etSubject = (EditText) findViewById(R.id.etSubject);
        etBody = (EditText) findViewById(R.id.etBody);
        chkAttachment = (CheckBox) findViewById(R.id.chkAttachment);
        boton4 = (Button) findViewById(R.id.btnEnviar);



    }
    public void llamar(View v){
        Uri number = Uri.parse("tel:"+campoTelefono.getText().toString());
        Intent callIntent = new Intent(Intent.ACTION_DIAL, number);
        startActivity(callIntent);
    }
    public void buscarMapa(View v){
        // Map point based on address
        //Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
// Or map point based on latitude/longitude
        Uri location = Uri.parse("geo:"+latitud.getText().toString()+","+longitud.getText().toString()+"?z=14"); // z param is zoom level
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        startActivity(mapIntent);
    }
    public void AbrirUrl(View v){
        Uri webpage = Uri.parse(url.getText().toString());
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(webIntent);
    }
    public void enviarEmail(View v){

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {etEmail.getText().toString()}); // recipients
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, etSubject.getText().toString());
        emailIntent.putExtra(Intent.EXTRA_TEXT, etBody.getText());


        //revisamos si el checkbox está marcado enviamos el ícono de la aplicación como adjunto
        if (chkAttachment.isChecked()) {
            //colocamos el adjunto en el stream
            emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("android.resource://" + getPackageName() + "/" + R.mipmap.ic_launcher));

            //indicamos el tipo de dato
            emailIntent.setType("image/png");
        }

        //iniciamos la actividad
        startActivity(emailIntent);
        Toast.makeText(MainActivity.this, "email enviado", Toast.LENGTH_LONG).show();

    }


}
