package dam.pmpd.javl_tarea02;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import dam.pmpd.javl_tarea02.databinding.ActivityMainBinding;

/**
 * Actividad principal de la aplicación que gestiona el menú de navegación, el cambio de idioma,
 * y muestra una lista de personajes utilizando un RecyclerView.
 *
 * <p>Esta actividad inicializa la interfaz de usuario principal de la aplicación, configura
 * el menú de navegación, el RecyclerView y maneja las preferencias de idioma.</p>
 *
 * @autor jvillar315
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ActivityMainBinding binding;

    /**
     * Inicializa la actividad, configurando el idioma, el menú de navegación y el RecyclerView.
     *
     * @param savedInstanceState Si la actividad se está reiniciando después de haber sido detenida,
     *                           este Bundle contiene los datos más recientes que suministró.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar el idioma de la aplicación
        loadLocale();

        // Configuración de ViewBinding
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Configurar el Navigation Drawer
        setupDrawer();

        // Configurar el RecyclerView
        setupRecyclerView();

        // Mostrar mensaje de bienvenida
        Snackbar.make(binding.getRoot(), getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show();
    }

    /**
     * Configura el menú de navegación con un botón de alternancia y un oyente de selección.
     */
    private void setupDrawer() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, binding.drawerLayout, R.string.open_drawer, R.string.close_drawer);

        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * Rellena el RecyclerView con una lista de personajes y configura el oyente de clics.
     */
    private void setupRecyclerView() {
        ArrayList<Character> characters = new ArrayList<>();
        characters.add(new Character(
                getString(R.string.mario_name),
                R.drawable.mario,
                getString(R.string.mario_description),
                getString(R.string.mario_abilities)
        ));
        characters.add(new Character(
                getString(R.string.luigi_name),
                R.drawable.luigi,
                getString(R.string.luigi_description),
                getString(R.string.luigi_abilities)
        ));
        characters.add(new Character(
                getString(R.string.peach_name),
                R.drawable.peach,
                getString(R.string.peach_description),
                getString(R.string.peach_abilities)
        ));
        characters.add(new Character(
                getString(R.string.bowser_name),
                R.drawable.bowser,
                getString(R.string.bowser_description),
                getString(R.string.bowser_abilities)
        ));

        binding.recyclerViewCharacters.setLayoutManager(new LinearLayoutManager(this));
        SimpleAdapter adapter = new SimpleAdapter(characters, character -> {
            DetailsFragment fragment = DetailsFragment.newInstance(
                    character.getName(),
                    character.getImageResource(),
                    character.getDescription(),
                    character.getAbilities()
            );

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainer, fragment)
                    .addToBackStack(null)
                    .commit();
        });
        binding.recyclerViewCharacters.setAdapter(adapter);
    }

    /**
     * Maneja la selección de un ítem del menú de navegación.
     *
     * @param item El ítem de menú seleccionado.
     * @return true si el ítem fue manejado correctamente.
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Mostrar la lista de personajes
            binding.fragmentContainer.setVisibility(View.GONE);
            binding.recyclerViewCharacters.setVisibility(View.VISIBLE);

        } else if (id == R.id.nav_settings) {
            // Abrir la actividad de configuración
            startActivity(new Intent(this, SettingsActivity.class));

        } else if (id == R.id.nav_language) {
            // Cambiar idioma
            showLanguageSwitchDialog();
        }

        // Cerrar el drawer
        binding.drawerLayout.closeDrawer(binding.navigationView, true);
        return true;
    }

    /**
     * Infla el menú contextual.
     *
     * @param menu El menú a inflar.
     * @return true si el menú fue creado correctamente.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.context_menu, menu);
        return true;
    }

    /**
     * Maneja la selección de un ítem del menú contextual.
     *
     * @param item El ítem de menú seleccionado.
     * @return true si el ítem fue manejado correctamente.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_about) {
            showAboutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Muestra el cuadro de diálogo "Acerca de" con detalles de la aplicación.
     */
    private void showAboutDialog() {
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.mario)
                .setTitle(getString(R.string.about_dialog_title))
                .setMessage(getString(R.string.about_dialog_message))
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    /**
     * Carga el idioma de la aplicación desde las preferencias compartidas.
     */
    private void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String language = prefs.getString("App_Language", "es"); // Por defecto, español
        LocaleHelper.setLocale(this, language);
    }

    /**
     * Establece el idioma de la aplicación y reinicia la actividad para aplicar los cambios.
     *
     * @param languageCode El código del idioma a establecer (por ejemplo, "en" o "es").
     */
    private void setLanguage(String languageCode) {
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("App_Language", languageCode);
        editor.apply();

        LocaleHelper.setLocale(this, languageCode);

        // Reiniciar actividad para aplicar los cambios
        recreate();
    }

    /**
     * Muestra un cuadro de diálogo que permite al usuario cambiar el idioma de la aplicación.
     */
    private void showLanguageSwitchDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.select_language)
                .setItems(new CharSequence[]{"English", "Español"}, (dialog, which) -> {
                    if (which == 0) {
                        setLanguage("en");
                    } else {
                        setLanguage("es");
                    }
                })
                .create()
                .show();
    }
}
