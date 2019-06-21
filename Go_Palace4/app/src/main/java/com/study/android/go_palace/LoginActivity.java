package com.study.android.go_palace;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.squareup.picasso.Picasso;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG ="lecture";
    private  static final int RC_SIGN_IN = 1001;
    AdView mAdView;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    //google Sign views
    private SignInButton mBtnGoogleSignIn;  //로그인 버튼
    private Button mBtnGoogleSignOut;   //로그아웃 버튼
    private TextView mTxtProfileInfo;   //사용자 정보 표시
    private ImageView mImgProfile; //사용자 프로필 이미지 표시

   private Button Loginbutton;
   private Button Joinbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Loginbutton = findViewById(R.id.Loginbutton);
        Joinbutton =findViewById(R.id.Joinbutton);
        initViews();
        initFirebaseAuth();

        // 초기화
        String bannerid = getResources().getString(R.string.ad_unit_id_1);
        MobileAds.initialize(getApplicationContext(), bannerid);

        // 테스트 광고 부르기
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().
                addTestDevice("A6E5982CC602E6DEC334D487388352C9").build();
        mAdView.loadAd(adRequest);


    }

    public void onBtnClicked(View v) {
        final Dialog loginDialog = new Dialog(this);
        loginDialog.setContentView(R.layout.custom_dialog);
        loginDialog.setTitle("로그인 화면");

        final EditText username = loginDialog.findViewById(R.id.editText);
        final EditText password = loginDialog.findViewById(R.id.editText2);

        Button login = loginDialog.findViewById(R.id.button1);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().trim().length() > 0
                        && password.getText().toString().trim().length() > 0) {
                    Toast.makeText(getApplicationContext(), "로그인 성공",
                            Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                 //로그인 성공시


                    loginDialog.dismiss();
                    //로그인 로직 .finish();
                    //회원정보페이지가 보여야하고
                    // 로그인-> 마페로 바껴야한다.

                    Loginbutton.setVisibility(View.GONE);
                    Joinbutton.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getApplicationContext(), "다시 입력하세요",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        Button cancel = loginDialog.findViewById(R.id.button2);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDialog.dismiss();
            }
        });
        loginDialog.show();
    }
    public void onBtn2Clicked(View v) {
        CustomCircleProgressDialog customCircleProgressDialog =
                new CustomCircleProgressDialog(LoginActivity.this);
        //주변 클릭 터치 시 프로그래서 사라지지 않게 하기 : false
        customCircleProgressDialog.setCancelable(true);
        customCircleProgressDialog.show();
    }

    private void initViews() {
        mBtnGoogleSignIn = findViewById(R.id.btn_google_signin);
        mBtnGoogleSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        mTxtProfileInfo = findViewById(R.id.txt_profile_info);
        mImgProfile = findViewById(R.id.img_profile);
        mBtnGoogleSignOut = findViewById(R.id.btn_google_signout);
    }

    private void initFirebaseAuth() {
        //firebase 로그인한 사용자 정보
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gsio = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gsio)
                .build();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateProfile();
            }
        };
    }
    //firebase auth관련
    private void updateProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // 비 로그인 상태
            mBtnGoogleSignIn.setVisibility(View.VISIBLE);
            mBtnGoogleSignOut.setVisibility(View.GONE);
            mTxtProfileInfo.setVisibility(View.GONE);
            mImgProfile.setVisibility(View.GONE);


            Loginbutton.setVisibility(View.VISIBLE);
            Joinbutton.setVisibility(View.VISIBLE);
        } else {
            // 로그인 상태
            mBtnGoogleSignIn.setVisibility(View.GONE);
            mBtnGoogleSignOut.setVisibility(View.VISIBLE);
            mTxtProfileInfo.setVisibility(View.VISIBLE);
            mImgProfile.setVisibility(View.VISIBLE);



            Loginbutton.setVisibility(View.GONE);
            Joinbutton.setVisibility(View.GONE);


            String userName = user.getDisplayName(); // 채팅에 사용 될 닉네임 설정
            String userEmail = user.getEmail();
            StringBuilder profile = new StringBuilder();
            profile.append(userName).append("\n").append(userEmail);
            mTxtProfileInfo.setText(profile);
            Uri userPhoto = user.getPhotoUrl();
            if (userPhoto != null){
                Picasso.with(this).load(userPhoto).into(mImgProfile);
            }
        }
    }

    public void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);

    }
    public void signOut(View v){
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateProfile();
            }
        });

    }
    @Override
    public void onStart(){
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop(){
        super.onStop();
        mAuth.removeAuthStateListener(mAuthListener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(LoginActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                updateProfile();
            }
        }
    }

    //광고
    //광고숨기기?
    public void hideAd(View v){
        if(mAdView.isEnabled())
            mAdView.setEnabled(false);
        if(mAdView.getVisibility() != View.INVISIBLE)
            mAdView.setVisibility(View.INVISIBLE);
    }
    public  void showAd(View v) {
        if (mAdView.isEnabled())
            mAdView.setEnabled(true);
        if (mAdView.getVisibility() != View.VISIBLE)
            mAdView.setVisibility(View.VISIBLE);
    }
}
