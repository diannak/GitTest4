package com.dog.android.happy_dog;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class Fragment4 extends Fragment {
    private static final String TAG = "happy";
    static final String PREF_USER_NAME = "username";
    private static final int RC_SIGN_IN = 1001;
    Button btn_Dog1, btn_Dog2;
    TextView logText, loginText;

    EditText et_id,et_pw;
    TextView tv_id;
    Button btn_login, btn_logout;
    LinearLayout loginOk;
    LinearLayout loginForm;
    LinearLayout dog;
    LinearLayout gglayout;

    private static String id = "";
    private static String pw = "";

    SharedPreferences.Editor editor;

    // Firebase - Authentication
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private GoogleApiClient mGoogleApiClient;

    private SignInButton mBtnGoogleSignIn;
    private Button mBtnGoogleSignOut;
    private TextView mTxtProfileInfo;
    private ImageView mImgProfile;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final ViewGroup rootView =
                (ViewGroup) inflater.inflate(R.layout.fragment4, container, false);

        btn_Dog1 = rootView.findViewById(R.id.btn_Dog1);
        btn_Dog2 = rootView.findViewById(R.id.btn_Dog2);

        btn_Dog1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mlntent = new Intent(getActivity(), Support1Activity.class);
                startActivity(mlntent);
            }
        });
        btn_Dog2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mlntent = new Intent(getActivity(), Support1Activity.class);
                startActivity(mlntent);
            }
        });

        et_id = rootView.findViewById( R.id.id );
        et_pw = rootView.findViewById( R.id.password );
        tv_id = rootView.findViewById( R.id.tv_id );
        logText = rootView.findViewById( R.id.logText );
        loginText = rootView.findViewById( R.id.LoginText );
        btn_login = rootView.findViewById( R.id.loginbtn );
        btn_logout = rootView.findViewById( R.id.btn_logout );
        loginOk = rootView.findViewById( R.id.loginOk );
        loginForm = rootView.findViewById( R.id.loginForm );
        gglayout = rootView.findViewById( R.id.gglayout );
        dog = rootView.findViewById( R.id.dog );

        btn_login.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String result;
                    id = et_id.getText().toString();
                    pw = et_pw.getText().toString();

                    LoginActivity task = new LoginActivity();
                    result = task.execute(id, pw).get();

                    if (result.equals("로그인 성공")) {
                        loginOk.setVisibility( View.VISIBLE );
                        loginForm.setVisibility( View.GONE );
                        dog.setVisibility( View.VISIBLE );
                        logText.setVisibility( View.VISIBLE );
                        loginText.setVisibility( View.GONE );
                        editor.putString("id",id);
                        editor.putString("pw",pw);
                        tv_id.setText( id  + "님 환영합니다.");
                        editor.commit();
                        downKeyboard(getContext(), et_id);
                    } else {
                        Toast.makeText( getContext(), "비밀번호 또는 아이디가 틀립니다.", Toast.LENGTH_SHORT).show();
                        downKeyboard(getContext(), et_id);
                    }

                } catch (Exception e) {
                    Log.d("lecture", ".....ERROR.....!");
                }
            }
        } );

        btn_logout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.clear();
                editor.commit();
                loginOk.setVisibility( View.GONE );
                loginForm.setVisibility( View.VISIBLE );
                dog.setVisibility( View.VISIBLE );
                logText.setVisibility( View.GONE );
                loginText.setVisibility( View.VISIBLE );
            }
        } );

        mBtnGoogleSignIn = rootView.findViewById( R.id.btn_google_signin);
        mBtnGoogleSignIn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        } );

        mTxtProfileInfo = rootView.findViewById( R.id.txt_profile_info );
        mImgProfile = rootView.findViewById( R.id.img_profile );
        mBtnGoogleSignOut = rootView.findViewById( R.id.btn_google_signout );

        mBtnGoogleSignOut.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut(rootView);
            }
        } );
        initFirebaseAuth();
        return rootView;
    }
    public static void downKeyboard(Context context, EditText editText) {
        InputMethodManager mInputMethodManager = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mInputMethodManager.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }


    private void initFirebaseAuth() {
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gsio = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString( R.string.default_web_client_id ))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient
                .Builder(getContext())
                .enableAutoManage(getActivity(), new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi( Auth.GOOGLE_SIGN_IN_API, gsio)
                .build();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                updateProfile();
            }
        };

    }

    private void updateProfile() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null) {
            // 비 로그인 상태
            mBtnGoogleSignIn.setVisibility(View.VISIBLE);
            mBtnGoogleSignOut.setVisibility(View.GONE);
            mTxtProfileInfo.setVisibility(View.GONE);
            mImgProfile.setVisibility(View.GONE);
            gglayout.setVisibility( View.GONE );
            SharedPreferences pref = getContext().getSharedPreferences("login", Activity.MODE_PRIVATE);
            editor = pref.edit();
            String sid = pref.getString("id", "");
            String spw = pref.getString("pw", "");
            et_id.setText(sid);
            et_pw.setText(spw);

            try {
                String result;
                id = et_id.getText().toString();
                pw = et_pw.getText().toString();

                LoginActivity task = new LoginActivity();
                result = task.execute(id, pw).get();
                Log.d( "lecture",  result);
                if (result.equals("로그인 성공")) {
                    loginOk.setVisibility( View.VISIBLE );
                    dog.setVisibility( View.VISIBLE );
                    logText.setVisibility( View.VISIBLE );
                    loginText.setVisibility( View.GONE );
                    loginForm.setVisibility( View.GONE );
                    editor.putString("id",id);
                    editor.putString("pw",pw);
                    tv_id.setText( id + "님 환영합니다." );
                    editor.commit();
                } else {
                    loginOk.setVisibility( View.GONE );
                    loginForm.setVisibility( View.VISIBLE );
                    dog.setVisibility( View.VISIBLE );
                    logText.setVisibility( View.GONE );
                    loginText.setVisibility( View.VISIBLE );
                }

            } catch (Exception e) {

                Log.d("lecture", ".....ERROR.....!");
            }

            Log.d("lecture", id);
            Log.d("lecture", pw);
        } else {
            // 로그인 상태
            mBtnGoogleSignIn.setVisibility(View.GONE);
            mBtnGoogleSignOut.setVisibility(View.VISIBLE);
            mTxtProfileInfo.setVisibility(View.VISIBLE);
            mImgProfile.setVisibility(View.VISIBLE);
            loginForm.setVisibility( View.GONE );
            gglayout.setVisibility( View.VISIBLE );
            logText.setVisibility( View.VISIBLE );
            loginText.setVisibility( View.GONE );

            String userName = user.getDisplayName(); // 채팅에 사용 될 닉네임 설정
            String userEmail = user.getEmail();
            StringBuilder profile = new StringBuilder();
            profile.append(userName).append("\n").append(userEmail);
            mTxtProfileInfo.setText(profile);
            Uri userPhoto = user.getPhotoUrl();
            if (userPhoto != null){
                Picasso.with(getContext()).load(userPhoto).into(mImgProfile);
            }
        }
    }

    public void signIn() {
        Intent intent = Auth.GoogleSignInApi.getSignInIntent( mGoogleApiClient );
        startActivityForResult( intent, RC_SIGN_IN );
    }

    public void signOut(View v) {
        mAuth.signOut();
        Auth.GoogleSignInApi.signOut( mGoogleApiClient ).setResultCallback( new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                updateProfile();
            }
        } );
    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener( mAuthListener );
    }

    @Override
    public void onPause() {
        super.onPause();
        mGoogleApiClient.stopAutoManage(getActivity());
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAuth.removeAuthStateListener( mAuthListener );
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                GoogleSignInAccount account = result.getSignInAccount();
                AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            } else {
                updateProfile();
            }
        }
    }
}