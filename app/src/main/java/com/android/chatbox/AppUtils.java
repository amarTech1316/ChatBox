package com.android.chatbox;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import android.content.Context;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.app.Activity;
import android.app.ProgressDialog;


public class AppUtils
{
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
	Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

	private static ProgressDialog waitingDialog;
	
	public static boolean validate(String emailStr, String password) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return (password.length() > 0 || password.equals(";")) && matcher.find();
	}
	
	public static boolean validate(String emailStr, String password, String repeatPassword) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
        return password.length() > 0 && repeatPassword.equals(password) && matcher.find();
    }
	
	public static void toast(Context _context,String _s){
		Toast.makeText(_context,_s.toString(),Toast.LENGTH_SHORT).show();
	}
	public static void showDialog(Activity _context,String _tittle,String _error){
		AlertDialog.Builder infoDialog=new AlertDialog.Builder(_context);
		infoDialog.setTitle(_tittle);
		infoDialog.setMessage(_error);
		infoDialog.setCancelable(false);
		infoDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					p1.cancel();
				}
			});
		infoDialog.create().show();
	}
	
	public static void showProgressDialog(Activity _context,String message){
		 waitingDialog =new ProgressDialog(_context);
		waitingDialog.setMessage(message);
		waitingDialog.setCancelable(false);
		waitingDialog.show();
		
		}
	public static void hideProgressDialog(Activity _context){
		waitingDialog.dismiss();
	}
	
	
	
		
}
