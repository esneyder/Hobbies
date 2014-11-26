package com.example.hobbies;

import android.os.Bundle;
import android.app.Activity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener{
	EditText txtid,txthobbie;
	Button btninsertar,btnconsultar,btneliminar,btnactualizar;
	TextView lblresultado;
	SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtid=(EditText)findViewById(R.id.codigohobbie);
        txthobbie=(EditText)findViewById(R.id.nomhobbie);
        btninsertar=(Button)findViewById(R.id.insertar);
        btneliminar=(Button)findViewById(R.id.btnElliminar);
        btnactualizar=(Button)findViewById(R.id.btnactualizar);
        btnconsultar=(Button)findViewById(R.id.btnConsulta);
        lblresultado=(TextView)findViewById(R.id.listado);
        btninsertar.setOnClickListener(this);
        btnconsultar.setOnClickListener(this);
        btneliminar.setOnClickListener(this);
        btnactualizar.setOnClickListener(this);
        //crear la base de datos
        HobbiesSQLite hsql=new HobbiesSQLite(this,"DBHobbies",null,1);
        //se abre en modo escritura
        db=hsql.getWritableDatabase();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View arg0) {
		if(arg0==btninsertar){
			int id=Integer.parseInt(txtid.getText().toString());
			String hobbie=txthobbie.getText().toString();
			try{
				//db.execSQL("insert  into tblHobbies (idHobbie,hobbie) values("+id+",'"+hobbie+"')");
				ContentValues registro=new ContentValues();
				registro.put("idHobbie", id);
				registro.put("Hobbie", hobbie);
				db.insert("tblHobbies", null, registro);
				Toast.makeText(this, "se inserto correctamente el hobbie", Toast.LENGTH_LONG).show();
			}
			catch(Exception e){
				Toast.makeText(this, "no se pudo insertar el hobbie", Toast.LENGTH_LONG).show();
			}
		}
		else{ 
			if(arg0==btnconsultar){
				//se crea el cursor
				Cursor datos=db.rawQuery("select idHobbie,hobbie from tblHobbies", null);
				lblresultado.setText("");
				//se recorre el primer registro
				if(datos.moveToFirst()){
					do{
						lblresultado.append(datos.getString(0)+" - "+datos.getString(1)+"\n");
					}while(datos.moveToNext());
					
				}
			}
				
			else{ 
					if(arg0==btneliminar){
						try{
							int id=Integer.parseInt(txtid.getText().toString());
							db.delete("tblHobbies", "idHobbie="+id,null);
							Toast.makeText(this, "se elimino correctamente el hobbie", Toast.LENGTH_LONG).show();
						}
						catch(Exception e){
							Toast.makeText(this, "error al eliminar el hobbie", Toast.LENGTH_LONG).show();
						}
					}
					else{
						if(arg0==btnactualizar){
							try{
								ContentValues registro=new ContentValues();
								int id=Integer.parseInt(txtid.getText().toString());
								String hobbie=txthobbie.getText().toString();
								registro.put("Hobbie", hobbie);
								db.update("tblHobbies", registro, "idHobbie="+id, null);
								Toast.makeText(this, "se actualizo correctamente el registro", Toast.LENGTH_LONG).show();
							}
							catch(Exception e){
								Toast.makeText(this, "error al actualizar el registro", Toast.LENGTH_LONG).show();
							}
						}
					}
				}
			
		
		
		}
		
	}
		
}



	
    

