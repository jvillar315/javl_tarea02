package dam.pmpd.javl_tarea02;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;

import java.util.Locale;

/**
 * Clase auxiliar para gestionar el cambio de idioma en la aplicación.
 *
 * <p>Esta clase proporciona un método estático para cambiar el idioma del contexto de la aplicación.
 * Modifica la configuración de {@link Resources} para aplicar un idioma específico.</p>
 *
 * @autor jvillar315
 */
public class LocaleHelper {

    /**
     * Establece el idioma de la aplicación.
     *
     * <p>Este método configura un nuevo {@link Locale} basado en el código de idioma proporcionado,
     * lo aplica como predeterminado y actualiza los recursos del contexto.</p>
     *
     * @param context       El contexto de la aplicación o actividad en el que se aplicará el cambio de idioma.
     * @param languageCode  El código del idioma a aplicar (por ejemplo, "es" para español o "en" para inglés).
     */
    public static void setLocale(Context context, String languageCode) {
        // Crear una instancia de Locale con el código de idioma proporcionado
        Locale locale = new Locale(languageCode);

        // Establecer el idioma como predeterminado
        Locale.setDefault(locale);

        // Obtener los recursos y la configuración actual del contexto
        Resources resources = context.getResources();
        Configuration config = resources.getConfiguration();

        // Aplicar el nuevo Locale a la configuración
        config.setLocale(locale);
        config.setLayoutDirection(locale);

        // Actualizar la configuración de los recursos del contexto
        context.getResources().updateConfiguration(config, resources.getDisplayMetrics());
    }
}

