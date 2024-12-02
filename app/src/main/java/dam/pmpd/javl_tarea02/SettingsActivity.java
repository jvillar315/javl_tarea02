package dam.pmpd.javl_tarea02;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import dam.pmpd.javl_tarea02.databinding.ActivitySettingsBinding;

/**
 * Actividad de configuración de la aplicación.
 *
 * <p>Esta actividad permite al usuario gestionar las preferencias de la aplicación,
 * como la habilitación de notificaciones u otras configuraciones similares.</p>
 *
 * @autor jvillar315
 */
public class SettingsActivity extends AppCompatActivity {
    // Binding para acceder a los elementos de la interfaz gráfica
    private ActivitySettingsBinding binding;

    /**
     * Método que se llama al crear la actividad.
     *
     * <p>Inicializa la vista de la actividad utilizando View Binding y configura
     * las opciones de preferencias utilizando SharedPreferences.</p>
     *
     * @param savedInstanceState El estado guardado de la actividad, si existe.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar la vista utilizando View Binding
        binding = ActivitySettingsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Crear un ejemplo de SharedPreferences para almacenar preferencias simples
        SharedPreferences sharedPreferences = getSharedPreferences("AppPreferences", MODE_PRIVATE);

        // Recuperar el estado inicial de las notificaciones (valor predeterminado: true)
        boolean isNotificationsEnabled = sharedPreferences.getBoolean("notifications_enabled", true);

        // Establecer el estado inicial del Switch según el valor de SharedPreferences
        binding.switchNotifications.setChecked(isNotificationsEnabled);

        // Configurar el listener del Switch para actualizar las preferencias
        binding.switchNotifications.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Guardar el nuevo estado de las notificaciones en SharedPreferences
            sharedPreferences.edit().putBoolean("notifications_enabled", isChecked).apply();
        });

        // Agregar configuración de otras opciones aquí si es necesario
    }
}
