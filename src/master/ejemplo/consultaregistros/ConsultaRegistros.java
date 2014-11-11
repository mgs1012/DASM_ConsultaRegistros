package master.ejemplo.consultaregistros;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class ConsultaRegistros extends Activity{
	
	EditText usuario_dni;
	Bundle extras;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta);


		extras = getIntent().getExtras();

		  // parse the JSON passed as a string.
		  try {
			JSONObject json = new JSONObject( extras.getString("dni") );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			usuario_dni = (EditText)findViewById(R.id.usu_dni);
			usuario_dni.setText();

	}
	
}
