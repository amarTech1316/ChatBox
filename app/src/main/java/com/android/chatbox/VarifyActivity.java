package com.android.chatbox;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.LinearLayout;
import android.view.View;
import android.widget.EditText;
import android.text.TextWatcher;
import android.text.Editable;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.android.gms.tasks.Task;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import com.hbb20.CountryCodePicker;
import android.content.Context;

public class VarifyActivity extends AppCompatActivity
{

	private CardView cv_varify;

	public static LinearLayout activity_registerOTPLayout;

	public static LinearLayout activity_registerConfOtpLayout;

	private EditText et_mobileNum;

	private Button bt_verify;

    private Spinner spinner1;

    private CountryCodePicker ccp;
    private String selected_country_code;

    private LinearLayout otp_layout;

    private Button bt_go;

    private PinEntryEditText txtPinEntry;
    
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_varify);
		
		cv_varify=(CardView)findViewById(R.id.cv_varify);
		activity_registerOTPLayout=(LinearLayout)findViewById(R.id.activity_registerOTPLayout);
		activity_registerConfOtpLayout=(LinearLayout)findViewById(R.id.activity_registerConfOtpLayout);
		activity_registerOTPLayout.setVisibility(View.VISIBLE);
		activity_registerConfOtpLayout.setVisibility(View.GONE);
		bt_verify=(Button)findViewById(R.id.bt_verify);
        bt_go=(Button)findViewById(R.id.bt_go);
		et_mobileNum=(EditText)findViewById(R.id.et_mobileNum);
        //ccp = (CountryCodePicker) findViewById(R.id.ccp);
        otp_layout=(LinearLayout)findViewById(R.id.otp_layout);
        txtPinEntry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);
       activity_registerConfOtpLayout.setVisibility(View.GONE);
        
       
        
        bt_verify.setVisibility(View.GONE);
    
//        ccp.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
//
//                
//                @Override
//                public void onCountrySelected() {
//                    //Alert.showMessage(RegistrationActivity.this, ccp.getSelectedCountryCodeWithPlus());
//                     selected_country_code = ccp.getSelectedCountryCodeWithPlus();
//                }
//            });
    
        
        
        
        et_mobileNum.addTextChangedListener(new PhoneNumberFormattingTextWatcher(){

                @Override
                public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
                {

                }

                @Override
                public void onTextChanged(CharSequence _char, int p2, int p3, int p4)
                {
                    
                    if (( _char.length()) == 10) {
                        bt_verify.setVisibility(View.VISIBLE);

                    }else if (( _char.length()) > 10) {
                        et_mobileNum.setText(_char.toString().substring((int)(0), (int)(10)));
                        et_mobileNum.setSelection(et_mobileNum.length());
                        bt_verify.setVisibility(View.VISIBLE);
                        
                    }
                    else {
                        bt_verify.setVisibility(View.GONE);
                    }
                    
                 
                }

                @Override
                public void afterTextChanged(Editable p1)
                {
                    
                    //AppUtils.toast(VarifyActivity.this,p1.toString());
                }
                
            
        });
		
//		et_mobileNum.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
		bt_verify.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
                    try{
                        AuthUtils._sendVerificationCode(et_mobileNum.getText().toString(),VarifyActivity.this);
				}catch(Exception e){
                    AppUtils.showDialog(VarifyActivity.this,"erroe",e.toString());
                    }
                }
				
			
		});
        
        bt_go.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    try{
                        AuthUtils._varifyCode(txtPinEntry.getText().toString(),VarifyActivity.this);
                    }catch(Exception e){
                        AppUtils.showDialog(VarifyActivity.this,"erroe",e.toString());
                    }
                }
                
            
        });
		
		}
       
		
//	public void _sendVerificationCode () {
//        String phone = et_mobileNum.getText().toString();
//        com.google.firebase.auth.PhoneAuthProvider.getInstance().verifyPhoneNumber(phone, 180, java.util.concurrent.TimeUnit.SECONDS, this, new com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
//        @Override
//        public void onVerificationCompleted(com.google.firebase.auth.PhoneAuthCredential phoneAuthCredential) {
//            Toast.makeText(getApplicationContext(),"varification".toString(),Toast.LENGTH_LONG).show();
//        }
//        @Override
//        public void onVerificationFailed(com.google.firebase.FirebaseException e) {
//            Toast.makeText(getApplicationContext(),e.toString(),Toast.LENGTH_LONG).show();
//        }
//        @Override
//        public void onCodeSent(String s, com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken forceResendingToken) {
//            super.onCodeSent(s, forceResendingToken);
//            String codeSent = s;
//        }
//	
//    });
//    }
//    public void _varifyCode () {
//        String code = et_mobileNum.getText().toString();
//        String codeSent = null;
//        com.google.firebase.auth.PhoneAuthCredential credential = com.google.firebase.auth.PhoneAuthProvider.getCredential(codeSent, code);
//        signInWithPhoneAuthCredential(credential);
//    }
//
//    private void signInWithPhoneAuthCredential(com.google.firebase.auth.PhoneAuthCredential credential) {
//        FirebaseAuth.getInstance().signInWithCredential(credential) .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(Task<AuthResult> _param1) {
//                    final boolean _success = _param1.isSuccessful();
//                    final String _errorMessage = _param1.getException() != null ? _param1.getException().getMessage() : "";
//                    AppUtils.toast(VarifyActivity.this,"success");
//                }
//            });       
//    }
    
	public static  void showOtp(){
        
        activity_registerConfOtpLayout.setVisibility(View.VISIBLE);
        activity_registerOTPLayout .setVisibility(View.GONE);
    }
}
