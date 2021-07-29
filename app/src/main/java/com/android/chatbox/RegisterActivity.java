package com.android.chatbox;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.support.v7.widget.CardView;
import android.view.View;
import android.animation.Animator;
import android.view.ViewAnimationUtils;
import android.view.animation.AccelerateInterpolator;
import android.animation.AnimatorListenerAdapter;
import android.support.design.widget.FloatingActionButton;
import android.content.Intent;
import android.widget.EditText;
import com.google.firebase.auth.FirebaseAuth;



public class RegisterActivity extends AppCompatActivity
{

	private CardView cvAdd;

	private FloatingActionButton fab;
	private Intent i=new Intent();

	private EditText editTextUsername;

	private EditText editTextPassword;

	private EditText editTextRepeatPassword;
  
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
		fab = (FloatingActionButton) findViewById(R.id.fab);
		cvAdd=(CardView)findViewById(R.id.cv_add);
		editTextUsername=(EditText)findViewById(R.id.et_username);
		editTextPassword=(EditText)findViewById(R.id.et_username);
		editTextRepeatPassword=(EditText)findViewById(R.id.et_username);
		ShowEnterAnimation();
		
		fab.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					animateRevealClose();
				}
			});
		}
        
    private void ShowEnterAnimation() {
        Transition transition = TransitionInflater.from(this).inflateTransition(R.transition.fabtransition);
        getWindow().setSharedElementEnterTransition(transition);

        transition.addListener(new Transition.TransitionListener() {
				@Override
				public void onTransitionStart(Transition transition) {
					cvAdd.setVisibility(View.GONE);
				}

				@Override
				public void onTransitionEnd(Transition transition) {
					transition.removeListener(this);
					animateRevealShow();
				}

				@Override
				public void onTransitionCancel(Transition transition) {

				}

				@Override
				public void onTransitionPause(Transition transition) {

				}

				@Override
				public void onTransitionResume(Transition transition) {

				}


			});
    }

    public void animateRevealShow() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd, cvAdd.getWidth()/2,0, fab.getWidth() / 2, cvAdd.getHeight());
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					super.onAnimationEnd(animation);
				}

				@Override
				public void onAnimationStart(Animator animation) {
					cvAdd.setVisibility(View.VISIBLE);
					super.onAnimationStart(animation);
				}
			});
        mAnimator.start();
    }

    public void animateRevealClose() {
        Animator mAnimator = ViewAnimationUtils.createCircularReveal(cvAdd,cvAdd.getWidth()/2,0, cvAdd.getHeight(), fab.getWidth() / 2);
        mAnimator.setDuration(500);
        mAnimator.setInterpolator(new AccelerateInterpolator());
        mAnimator.addListener(new AnimatorListenerAdapter() {
				@Override
				public void onAnimationEnd(Animator animation) {
					cvAdd.setVisibility(View.INVISIBLE);
					super.onAnimationEnd(animation);
					fab.setImageResource(R.drawable.ic_signup);
					RegisterActivity.super.onBackPressed();
				}

				@Override
				public void onAnimationStart(Animator animation) {
					super.onAnimationStart(animation);
				}
			});
        mAnimator.start();
    }
	
	public void clickRegister(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        String repeatPassword = editTextRepeatPassword.getText().toString();
        if(AppUtils.validate(username, password, repeatPassword)){
            AuthUtils.createUser(username,password,RegisterActivity.this);
        }else {
            AppUtils.toast(getApplicationContext(),"Invalid email or not match password");
        }
    }
	
    
    @Override
    public void onBackPressed() {
        animateRevealClose();
    }
	
}

