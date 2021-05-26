package com.example.plantilla.ui;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.plantilla.modelo.Propietario;
import com.example.plantilla.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mensajeMutable;
    private MutableLiveData<Boolean> loginMutable;
    private Context context;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMensajeMutable(){
        if(mensajeMutable==null){
            mensajeMutable= new MutableLiveData<>();
        }
        return mensajeMutable;
    }
    public LiveData<Boolean> getLoginMutable(){
        if(loginMutable==null){
            loginMutable= new MutableLiveData<>();
        }
        return loginMutable;
    }

    public void verificarDatos(String usuario, String contrasenia) {
        if(usuario !=null && contrasenia!=null && usuario.length()>0 && contrasenia.length()>0){

            Call<String> respuestaToken = ApiClient.getMyApiClient().login(usuario, contrasenia);
            respuestaToken.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if ( response.isSuccessful() ) {
                        Log.d("Token", response.body());
                        SharedPreferences sharedPreferences = context.getSharedPreferences("token.dat", 0);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("token", "Bearer " + response.body() );
                        editor.commit();
                        loginMutable.setValue(true);
                    } else {
                        mensajeMutable.setValue("Usuario o contraseña incorrecto.");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.d("Token", "Salida incorrecta " + t.getMessage() );
                }
            });

            /*
            ApiClient api=ApiClient.getApi();
            if (api.login(usuario, contrasenia)!=null){
                mensajeMutable.setValue("Hola, Bienvenido!!!");
                loginMutable.setValue(true);
            }else{
                mensajeMutable.setValue("Usuario o contreseña incorrecto");
            }
            */
        }else{
            mensajeMutable.setValue("Los campos no deben estar vacios.");
        }

    }

}
