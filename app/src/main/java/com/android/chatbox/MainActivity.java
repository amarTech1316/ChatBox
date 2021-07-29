package com.android.chatbox;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.text.Editable;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import android.support.annotation.NonNull;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import android.os.Build;
import android.content.Intent;
import android.app.ActivityOptions;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.CardView;
import android.app.Dialog;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Context;


public class MainActivity extends AppCompatActivity
{

	private EditText et_email;
	private EditText et_password;

	private Button btn_signin;

	private FirebaseAuth mAuth;

	private FloatingActionButton fab;

	private Intent i;
	

	private CardView cv_login;

	//private AuthUtil authutils;
	

	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
		
		cv_login=(CardView)findViewById(R.id.cv_login);
		
		fab = (FloatingActionButton) findViewById(R.id.fab);
		et_email=(EditText)findViewById(R.id.et_email);
		et_password=(EditText)findViewById(R.id.et_pass);
		btn_signin=(Button)findViewById(R.id.bt_signin);
		// authutils=new AuthUtil();
		// mAuth=FirebaseAuth.getInstance();
	
		i=new Intent();
		
	
    }
	
	public void clickLogin(View view){
		String email=et_email.getText().toString();
		String password=et_password.getText().toString();
		if(AppUtils.validate(email,password)){
		AuthUtils.signIn(email,password,MainActivity.this);
		
		}else{
			AppUtils.toast(getApplicationContext(),"Wrong Email Or Password ");
		}
	}
	
	public void clickRegisterLayout(View view) {
		try{
        getWindow().setExitTransition(null);
        getWindow().setEnterTransition(null);
		
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			ActivityOptions options =
                   ActivityOptions.makeSceneTransitionAnimation(this, fab, fab.getTransitionName());
			i.setClass(getApplicationContext(),RegisterActivity.class);
			startActivity(i,options.toBundle());
		} else {
			i.setClass(getApplicationContext(),RegisterActivity.class);
			startActivity(i);
		}
		}catch(Exception e){
			Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();}
    }

    



	
	
	
	}
