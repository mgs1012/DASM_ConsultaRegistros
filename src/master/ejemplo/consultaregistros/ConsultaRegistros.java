package master.ejemplo.consultaregistros;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ConsultaRegistros extends Activity{

	Bundle extras;
	TextView numReg;
	TextView num;
	EditText usuario_dni;
	EditText usuario_nom;
	EditText usuario_ap;
	EditText usuario_dir;
	EditText usuario_telf;
	EditText usuario_equipo;
	

	String respuesta;
	private JSONArray datos;
	
	Button btnSiguiente, btnAnterior, btnUltimo, btnPrimero;
	boolean vacio = false;
	
	private int posicion_actual;
	//http://demo.calamar.eui.upm.es/dasmapi/v1/miw11/fichas
	private int limiteRegistros;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_consulta);
		extras = getIntent().getExtras();

		numReg = (TextView)findViewById(R.id.numReg);
		num = (TextView)findViewById(R.id.numero);
		usuario_dni = (EditText)findViewById(R.id.usu_dni);
		usuario_nom = (EditText)findViewById(R.id.usu_nom);
		usuario_ap = (EditText)findViewById(R.id.usu_ap);
		usuario_dir = (EditText)findViewById(R.id.usu_dir);
		usuario_telf = (EditText)findViewById(R.id.usu_telf);
		usuario_equipo = (EditText)findViewById(R.id.usu_eq);
		
		btnSiguiente = (Button)findViewById(R.id.btnSiguiente);
		
		//Recupera del jsonArray la respuesta ()
		
		if(extras!=null){
		respuesta = extras.getString("respuesta");
		vacio = extras.getBoolean("VACIO");
		Log.d("Practicas", ""+vacio);
		try {
			datos =  new JSONArray(respuesta);
			if(vacio==false){
				Toast.makeText(getBaseContext(), "No Vacio", Toast.LENGTH_SHORT).show(); 
				posicion_actual = 0;
				numReg.setText(datos.getJSONObject(posicion_actual).getString("NUMREG"));
			
				posicion_actual = 1;
				num.setText("Registro " + posicion_actual +" ");
				visualizarRegistro(posicion_actual);	
				limiteRegistros = datos.getJSONObject(0).getInt("NUMREG");
				Log.d("Practicas", usuario_dni.toString());
				
				//btnSiguiente.setVisibility(visibility);
				
			}
			else{	
		
				Toast.makeText(getBaseContext(), "DNI vacio", Toast.LENGTH_SHORT).show();
				posicion_actual = 0;
				numReg.setText(datos.getJSONObject(posicion_actual).getString("NUMREG"));
			
				posicion_actual = 1;
				num.setText("Registro " + posicion_actual +" ");
				//Log.d("Practicas", num.toString());
				visualizarRegistro(posicion_actual);	
			
				limiteRegistros = datos.getJSONObject(0).getInt("NUMREG");
				Log.d("Practicas", usuario_dni.toString());
				
				
				
			}
		} catch (JSONException e) {
			e.printStackTrace();
			}
		}
	
	}

	private void visualizarRegistro(int pos) {
		JSONObject jsonObj;
		//Muestra con getObjetc() la posicion
		try {
			num.setText("Registro " + posicion_actual +" ");
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
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		String mensaje = "Consulta finalizada.";
		Toast.makeText(getBaseContext(), mensaje, Toast.LENGTH_SHORT).show();
	}

	
}
