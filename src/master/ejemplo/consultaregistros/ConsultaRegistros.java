package master.ejemplo.consultaregistros;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ConsultaRegistros extends Activity{
	Bundle extras;

	EditText usuario_dni;
	EditText usuario_nom;
	EditText usuario_ap;
	EditText usuario_dir;
	EditText usuario_telf;
	EditText usuario_equipo;

	String respuesta;
	private JSONArray datos;
	
	private int posicion_actual;
	//http://demo.calamar.eui.upm.es/dasmapi/v1/miw11/fichas
	private int limiteRegistros;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta);
		extras = getIntent().getExtras();

		usuario_dni = (EditText)findViewById(R.id.usu_dni);
		usuario_nom = (EditText)findViewById(R.id.usu_nom);
		usuario_ap = (EditText)findViewById(R.id.usu_ap);
		usuario_dir = (EditText)findViewById(R.id.usu_dir);
		usuario_telf = (EditText)findViewById(R.id.usu_telf);
		usuario_equipo = (EditText)findViewById(R.id.usu_eq);
		
		//Recupera del jsonArray la respuesta ()
		
		if(extras!=null){
		respuesta = extras.getString("respuesta");
		 
		
		try {
			datos =  new JSONArray(respuesta);
			posicion_actual = 1; 
			visualizarRegistro(posicion_actual);
			limiteRegistros = datos.getJSONObject(0).getInt("NUMREG");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		}
	
	}

	private void visualizarRegistro(int pos) {
		JSONObject jsonObj;
		//Muestra con getObjetc() la posicion
		try {
			jsonObj = datos.getJSONObject(pos);
			usuario_dni.setText(jsonObj.getString("DNI"));
			usuario_nom.setText(jsonObj.getString("Nombre"));
			usuario_ap.setText(jsonObj.getString("Apellidos"));
			usuario_dir.setText(jsonObj.getString("Direccion"));
			usuario_telf.setText( jsonObj.getString("Telefono"));
			usuario_equipo.setText(jsonObj.getString("Equipo"));

		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}
	
	public void siguiente(View v){
		if(posicion_actual < limiteRegistros)
			posicion_actual++;
		visualizarRegistro(posicion_actual);
		
	}
	
	public void irPrimero(View v){
		posicion_actual = 1;
		visualizarRegistro(posicion_actual);
	}
	
	public void anterior(View v){
		if(posicion_actual>1)
			posicion_actual--;
		visualizarRegistro(posicion_actual);
	}
	
	public void irUltimo(View v){
		posicion_actual = limiteRegistros;
		visualizarRegistro(posicion_actual);
	}

	
}
