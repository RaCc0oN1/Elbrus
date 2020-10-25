package com.example.elbrus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.elbrus.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

public class MainActivity extends AppCompatActivity {

    Button authBtn, registerBtn;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;
    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        registerBtn = findViewById(R.id.reg_Button);
        authBtn = (Button) findViewById(R.id.auth_button);
//        authBtn.setOnClickListener(this);

        root = findViewById(R.id.root_element);
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigInWindow();
            }
        });
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                regWindow();
            }
        });
    }

    private void sigInWindow() {
        final AlertDialog.Builder registration = new AlertDialog.Builder(this);
        registration.setTitle("Авторизация");
        registration.setMessage("Введите данные");

        LayoutInflater inflater = LayoutInflater.from(this);
        View signinV = inflater.inflate(R.layout.signin, null);
        registration.setView(signinV);

        final MaterialEditText email = signinV.findViewById(R.id.emailField);
        final MaterialEditText pass = signinV.findViewById(R.id.passwordField);

        registration.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        registration.setPositiveButton("Принять", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pass.getText().toString().length() < 5) {
                    Snackbar.make(root, "Введите Пароль", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        startActivity(new Intent(MainActivity.this, Category.class));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Snackbar.make(root, "Введены не верные данные" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                    }
                });
            }
        });

        registration.show();
    }

    private void regWindow() {
        final AlertDialog.Builder registration = new AlertDialog.Builder(this);
        registration.setTitle("Регистрация");
        registration.setMessage("Введите данные");

        LayoutInflater inflater = LayoutInflater.from(this);
        View registrationV = inflater.inflate(R.layout.registration, null);
        registration.setView(registrationV);

        final MaterialEditText name = registrationV.findViewById(R.id.nameField);
        final MaterialEditText secname = registrationV.findViewById(R.id.secnameField);
        final MaterialEditText email = registrationV.findViewById(R.id.emailField);
        final MaterialEditText pass = registrationV.findViewById(R.id.passwordField);

        registration.setNegativeButton("Отменить", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        registration.setPositiveButton("Принять", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (TextUtils.isEmpty(name.getText().toString())) {
                    Snackbar.make(root, "Введите ваше Имя", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(secname.getText().toString())) {
                    Snackbar.make(root, "Введите вашу Фамилию", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(email.getText().toString())) {
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (pass.getText().toString().length() < 5) {
                    Snackbar.make(root, "Введите Пароль, который больше 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(), pass.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        User user = new User();
                        user.setEmail(email.getText().toString());
                        user.setPass(pass.getText().toString());
                        user.setName(name.getText().toString());
                        user.setSecname(secname.getText().toString());
                        user.setScore(0);

                        users.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Snackbar.make(root, "Новый пользователь", Snackbar.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Snackbar.make(root, "Введены не верные данные" + e.getMessage(), Snackbar.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        registration.show();
    }

    public void authClick(View view) {
    }

//    @Override
//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.auth_button:
//                Intent intent = new Intent(this, Category.class);
//                startActivity(intent);
//                break;
//        }
//    }
}
