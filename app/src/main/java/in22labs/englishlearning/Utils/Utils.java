package in22labs.englishlearning.Utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Utils {
	
	public static final String ANALYTICS_KEY="UA-61002290-4";

	public static final String PUBLIC_USER="Company patterns user";

	public static final String PUBLIC_EMAIL="in22labs@gmail.com";


//	}

	static ProgressDialog pDialog;
	String Message;

	public static final Boolean FIRST_USE= false;


	public static final int DATE=2;

	public static final int MONTH=2;

	public static final int YEAR=4;

	public static final int REGNOLENGTH=7;

	public static CountDownTimer timer=null;



	public static void ShowToast(Context context, String Message){

		Toast.makeText(context,Message, Toast.LENGTH_SHORT).show();

	}

	public static boolean validateString(String name) {
		if (name.toString().trim().isEmpty()) {
            //Utils.ShowToast(context,"Please Enter Valid input");
			return false;
		}

		return true;
	}

	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size=1024;
		try
		{
			byte[] bytes=new byte[buffer_size];
			for(;;)
			{
				int count=is.read(bytes, 0, buffer_size);
				if(count==-1)
					break;
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex){}
	}

	public static void ProgresDialog(Context context, String message){

		pDialog=new ProgressDialog(context);
		pDialog.setMessage(message);
		pDialog.show();

		timer = new CountDownTimer(3000, 10) {
			public void onFinish() {
				pDialog.hide();

			}

			@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();


	}


	public static void ProgresDialog1(Context context, String message){

		pDialog=new ProgressDialog(context);
		pDialog.setMessage(message);
		pDialog.show();

		timer = new CountDownTimer(500, 10) {
			public void onFinish() {
				pDialog.hide();

			}

			@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();


	}
	public static void ProgresDialog2(Context context, String message){

		pDialog=new ProgressDialog(context);
		pDialog.setMessage(message);
		pDialog.show();

		timer = new CountDownTimer(4000, 10) {
			public void onFinish() {
				pDialog.hide();

			}

			@Override
			public void onTick(long millisUntilFinished) {
			}
		}.start();


	}

	public static void progresshide(Context context){
		if(pDialog.isShowing()){
			pDialog.dismiss();
		}else{

		}
	}

	public static void AlertInternet(final Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage("no internet connection! Please enable and try again!!")

				.setPositiveButton("Go to Settings", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						Intent intent=new Intent(Settings.ACTION_SETTINGS);
						context.startActivity(intent);
					}
				});
		AlertDialog alert = builder.create();
		alert.show();
	}
	public static boolean validateEmail(String Email) {

		if (Email.isEmpty() || !isValidEmail(Email)) {

			return false;
		}
		return true;
	}

	private static boolean isValidEmail(String email) {
		return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
	}

	public static boolean isNetworkAvailable(final Context context) {
		final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
		return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
	}





}
