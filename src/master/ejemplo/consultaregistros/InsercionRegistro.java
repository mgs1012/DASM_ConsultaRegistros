package master.ejemplo.consultaregistros;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class InsercionRegistro extends Activity {

	Bundle extras;
	String dni;
	EditText usuario_dni;
	
	@Override
	public void setContentView(View view) {

		super.setContentView(R.layout.activity_insertar);
		extras = getIntent().getExtras();
		
		//usuario_dni = (EditText)findViewById(R.id.in_dni);
		
		if(extras!=null){
			dni = extras.getString("DNI_USUARIO");
	}
	
	
	}
}
