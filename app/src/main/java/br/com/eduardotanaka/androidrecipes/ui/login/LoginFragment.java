package br.com.eduardotanaka.androidrecipes.ui.login;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.concurrent.Executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import br.com.eduardotanaka.androidrecipes.R;
import br.com.eduardotanaka.androidrecipes.ui.retrofit.RetrofitFragment;
import br.com.eduardotanaka.androidrecipes.util.SharedPreferencesUtil;

public class LoginFragment extends Fragment {

    private final String TAG = LoginFragment.class.getName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        BiometricManager biometricManager = BiometricManager.from(getContext());
        switch (biometricManager.canAuthenticate()) {
            case BiometricManager.BIOMETRIC_SUCCESS:
                Log.d(TAG, "App can authenticate using biometrics.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE:
                Log.e(TAG, "No biometric features available on this device.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE:
                Log.e(TAG, "Biometric features are currently unavailable.");
                break;
            case BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED:
                Log.e(TAG, "The user hasn't associated any biometric credentials with their account.");
                break;
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button btnLogar = view.findViewById(R.id.btnLogar);
        btnLogar.setOnClickListener(v -> {
            SharedPreferencesUtil.getInstance().put(SharedPreferencesUtil.Key.IS_LOGGED, true);
            Navigation.findNavController(v).navigate(R.id.action_login_to_start_fragment);
        });

        Button btnLogarBiometric = view.findViewById(R.id.btnLogarBiometric);
        btnLogarBiometric.setOnClickListener(v ->
            showBiometricPrompt(v)
        );

        Button btnCadastrar = view.findViewById(R.id.btnCadastrar);
        btnCadastrar.setOnClickListener(v ->
            Toast.makeText(getContext(), "IR PARA PAGINA DE CADATRO", Toast.LENGTH_SHORT).show()
        );
    }

    private Handler handler = new Handler();

    private Executor executor = command -> handler.post(command);

    private void showBiometricPrompt(View view) {
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Login com Biometria")
            .setSubtitle("Logue usando sua digital cadastrada")
            .setNegativeButtonText("Cancelar")
            .build();

        BiometricPrompt biometricPrompt = new BiometricPrompt(LoginFragment.this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                Toast.makeText(getContext(), "Authentication error: " + errString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                BiometricPrompt.CryptoObject authenticatedCryptoObject = result.getCryptoObject();
                // User has verified the signature, cipher, or message authentication code (MAC) associated with the crypto object,
                // so you can use it in your app's crypto-driven workflows.
                SharedPreferencesUtil.getInstance().put(SharedPreferencesUtil.Key.IS_LOGGED, true);
                Navigation.findNavController(view).navigate(R.id.action_login_to_start_fragment);            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                Toast.makeText(getContext(), "Authentication failed", Toast.LENGTH_SHORT).show();
            }
        });

        // Displays the "log in" prompt.
        biometricPrompt.authenticate(promptInfo);
    }
}
