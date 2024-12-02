package dam.pmpd.javl_tarea02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;

import dam.pmpd.javl_tarea02.databinding.ActivityLanguageBinding;

/**
 * Actividad para gestionar el cambio de idioma de la aplicación.
 *
 * <p>Esta actividad permite al usuario seleccionar entre diferentes idiomas usando un interruptor
 * (Switch). El idioma seleccionado se guarda en SharedPreferences y se aplica utilizando
 * {@link LocaleHelper}.</p>
 *
 * @autor jvillar315
 */
public class LanguageActivity extends AppCompatActivity {

    private ActivityLanguageBinding binding;

    /**
     * Método llamado cuando se crea la actividad.
     *
     * <p>Este método configura la vista, recupera el idioma actual desde SharedPreferences,
     * ajusta el estado del interruptor de idioma y configura un listener para manejar los cambios.</p>
     *
     * @param savedInstanceState Estado previamente guardado de la actividad (puede ser null).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Inflar el diseño de la actividad utilizando View Binding
        binding = ActivityLanguageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Recuperar el idioma actual de SharedPreferences
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String currentLanguage = prefs.getString("App_Language", "en");

        // Configurar el estado del interruptor según el idioma actual
        binding.languageSwitch.setChecked("es".equals(currentLanguage));

        // Configurar el listener para manejar cambios en el interruptor
        binding.languageSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            // Cambiar idioma según el estado del interruptor
            String newLanguage = isChecked ? "es" : "en";
            changeLanguage(newLanguage);
        });
    }

    /**
     * Cambia el idioma de la aplicación y reinicia la actividad principal.
     *
     * <p>Este método aplica el idioma seleccionado utilizando {@link LocaleHelper},
     * guarda el idioma en SharedPreferences y reinicia la actividad principal para reflejar
     * los cambios.</p>
     *
     * @param language El código del idioma a aplicar (por ejemplo, "es" para español o "en" para inglés).
     */
    private void changeLanguage(String language) {
        // Usar LocaleHelper para establecer el idioma
        LocaleHelper.setLocale(this, language);

        // Guardar el idioma seleccionado en SharedPreferences
        getSharedPreferences("Settings", MODE_PRIVATE)
                .edit()
                .putString("App_Language", language)
                .apply();

        // Reiniciar la actividad principal para reflejar el cambio de idioma
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
