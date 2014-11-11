package master.ejemplo.consultaregistros;

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

	EditText dni;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_operaciones_servicioweb);

		dni = (EditText) findViewById(R.id.input_dni);
	}

	public void conectar() {
		new ConsultaBD().execute(dni.getText().toString());
	}

	public void consulta(View v) {
		new ConsultaBD().execute(dni.getText().toString());

		Intent intent1 = new Intent(this, ConsultaRegistros.class);
		intent1.putExtra("dni", dni.getText().toString());
		startActivity(intent1);
		
	}

	private class ConsultaBD extends AsyncTask<JSONObject, Void, String> {

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
		protected JSONObject doInBackground(String... args) {
			
			JSONParser jParser = new JSONParser();
	        // Getting JSON from URL
	        JSONObject json = jParser.getJSONFromUrl(url);
	        return json;
	        
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
			//return respuesta;
		}

		// En el hilo de ejecución
		@Override
		protected void onPostExecute(String respuesta) {
			pDialog.dismiss();
			Toast.makeText(getBaseContext(), respuesta, Toast.LENGTH_SHORT)
					.show();
			
		}

		@Override
		protected String doInBackground(JSONObject... params) {
			//JSONParser jParser = new JSONParser();
	        // Getting JSON from URL
	        /*JSONObject json = jParser.getJSONFromUrl(url);
	        return json;
	        
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
			//return respuesta;*/return null;
		}

	}

}
