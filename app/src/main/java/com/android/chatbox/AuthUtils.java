package com.android.chatbox;
import android.app.Activity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import android.support.annotation.NonNull;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.OnFailureListener;
import android.content.Context;
import android.widget.Toast;
import android.content.Intent;
import android.os.Bundle;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import java.util.*;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import java.util.concurrent.TimeUnit;

class AuthUtils{

	private static Intent i=new Intent();

    private static String codeSent;
	
	//Sign In
	public static void signIn(String email, final String password,final Activity _context) {
		AppUtils.showProgressDialog(_context,"Sign In...");
		FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
			.addOnCompleteListener(_context, new OnCompleteListener<AuthResult>() {

				
				@Override
				public void onComplete(@NonNull Task<AuthResult> task) {
					AppUtils.hideProgressDialog(_context);
					
					if(!task.isSuccessful()){
						AppUtils.showDialog(_context,"failed",task.getException().getMessage());
					}
					else
					{
						AppUtils.toast(_context.getApplicationContext(), "Successfull "+password);
						i.setClass(_context,ChatActivity.class);
						_context.startActivity(i);
						_context.finish();
					}
				}

				
			})
			.addOnFailureListener(new OnFailureListener() {
				@Override
				public void onFailure(@NonNull Exception e) {
					
			
				}
			});
			}
			
	//Create User
	public static void createUser(String email, String password,final Activity _context) {
		AppUtils.showProgressDialog(_context,"Creating Account");

		FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
			.addOnCompleteListener(_context, new OnCompleteListener<AuthResult>() {
				@Override
				public void onComplete(@NonNull Task<AuthResult> task) {
					AppUtils.hideProgressDialog(_context);

					if (!task.isSuccessful()) {
						AppUtils.showDialog(_context,"failed",task.getException().getMessage());
					} else {
//						initNewUserInfo();
						AppUtils.toast(_context,"Registration and login Successfull");
						i.setClass(_context,VarifyActivity.class);
						_context.startActivity(i);
						
						
					}
				}
			})
			.addOnFailureListener(new OnFailureListener() {
				@Override
				public void onFailure(@NonNull Exception e) {
					AppUtils.hideProgressDialog(_context);
				}
			})
			;
	}
	public static void _sendVerificationCode (String phone,final Activity _context) {
        AppUtils.showProgressDialog(_context,"sending...");
        
        PhoneAuthProvider.getInstance()
            .verifyPhoneNumber(
                                phone, 
                                120, 
                                TimeUnit.SECONDS, 
                                _context,
                                
       new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);
            codeSent = s;
            AppUtils.hideProgressDialog(_context);
            AppUtils.showDialog(_context,forceResendingToken.toString(),s.toString()+"::"+PhoneAuthProvider.getInstance().toString());
            VarifyActivity.showOtp();
        }
        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
            
        }
        @Override
        public void onVerificationFailed(FirebaseException e) {
            AppUtils.hideProgressDialog(_context);
            AppUtils.showDialog(_context,"Failed",e.getMessage());
        }
    });
    }
    public static void _varifyCode (String code,Activity _context) {
        
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeSent, code);
        AppUtils.showProgressDialog(_context,"Verifying...");
        AppUtils.showDialog(_context,credential.toString(),credential.getProvider().toString());
     signInWithPhoneAuthCredential(credential,_context);
    
    }

    public static void signInWithPhoneAuthCredential(PhoneAuthCredential credential,final Activity _context) {
        FirebaseAuth.getInstance().signInWithCredential(credential) .addOnCompleteListener(_context, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(Task<AuthResult>task) {
             
            if(task.isSuccessful()){
            AppUtils.hideProgressDialog(_context);
            AppUtils.toast(_context,"Match");
            
        }else{
            AppUtils.hideProgressDialog(_context);
            AppUtils.toast(_context," Not Matched");
          
            }
                }
            });       
    }
    
   
	
		}
