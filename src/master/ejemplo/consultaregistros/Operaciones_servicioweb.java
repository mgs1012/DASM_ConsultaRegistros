package master.ejemplo.consultaregistros;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.http.AndroidHttpClient;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Operaciones_servicioweb extends Activity {

	protected static final String NUMREG = "NUMREG";
	JSONArray datos ;
	
	Intent i1, i2;
	EditText dni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operaciones_servicioweb);

		dni = (EditText) findViewById(R.id.input_dni);
	}

	public void conectar(View v) {
		new ConsultaBD().execute(dni.getText().toString());
	}
	
	public void consultar(View v){
		new ConsultaBD().execute(dni.getText().toString());
	}
	public void insertarRegistro(View v){
		String dni_in = dni.toString();
		new ConsultaBD().execute(dni.getText().toString());
		i2 = new Intent(getApplicationContext(), InsercionRegistro.class);
		i2.putExtra("DNI_USUARIO", dni_in);
		startActivity(i2);
				
	}


	private class ConsultaBD extends AsyncTask<String, Void, String> {

		private ProgressDialog pDialog;

		// BD cada uno la suya : miw11
		private final String URL = "http://demo.calamar.eui.upm.es/dasmapi/v1/miw11/fichas";

		// En el hilo de ejecución
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(Operaciones_servicioweb.this);
			pDialog.setMessage(getString(R.string.progress_title));
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();

		}

		// Ocurre en segundo plano
		@Override
		protected String doInBackground(String... args) {
	        
			String respuesta = getString(R.string.sin_respuesta);
			String url_final = URL;
			if (!args[0].equals("")) {
				url_final += "/" + args[0];
			}
			try {
				AndroidHttpClient httpClient = AndroidHttpClient
						.newInstance("AndroidHttpClient");
				HttpGet httpget = new HttpGet(url_final);

				HttpResponse response = httpClient.execute(httpget);
				respuesta = EntityUtils.toString(response.getEntity());
				httpClient.close();
			} catch (Exception e) {
				Log.e("WebService", e.toString());
			}
			return respuesta;

		 
		}

		// En el hilo de ejecución
		@Override
		protected void onPostExecute(String respuesta) {
			pDialog.dismiss();

			i1 = new Intent(getApplicationContext(), ConsultaRegistros.class);
			i1.putExtra("respuesta", respuesta);
			startActivity(i1);
			//Toast.makeText(getBaseContext(), respuesta, Toast.LENGTH_SHORT).show();	 
	
		}
			
}
}
