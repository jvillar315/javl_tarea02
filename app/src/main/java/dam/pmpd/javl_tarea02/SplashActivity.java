package dam.pmpd.javl_tarea02;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Actividad de presentación inicial (Splash Screen).
 *
 * <p>Esta actividad se utiliza para mostrar una pantalla de introducción
 * durante un tiempo definido antes de redirigir a la actividad principal de la aplicación.</p>
 *
 * @author jvillar
 */
public class SplashActivity extends AppCompatActivity {

    /**
     * Método que se llama al crear la actividad.
     *
     * <p>Configura el diseño de la pantalla Splash y programa un temporizador para
     * redirigir a la actividad principal después de un período definido.</p>
     *
     * @param savedInstanceState El estado guardado de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar el diseño del Splash Screen
        setContentView(R.layout.splash_screen);

        // Usar un Handler para mostrar la pantalla Splash durante 3 segundos
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            // Crear un Intent para iniciar MainActivity
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);

            // Finalizar la actividad Splash para evitar que quede en el back stack
            finish();
        }, 3000); // Tiempo en milisegundos (3000 ms = 3 segundos)
    }
}
