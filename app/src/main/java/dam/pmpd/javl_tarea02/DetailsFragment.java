package dam.pmpd.javl_tarea02;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import dam.pmpd.javl_tarea02.databinding.FragmentDetailsBinding;

/**
 * Fragmento que muestra los detalles de un personaje.
 *
 * <p>Este fragmento utiliza View Binding para mostrar información como el nombre, imagen,
 * descripción y habilidades de un personaje específico. Recibe los datos a través de argumentos
 * que se configuran mediante el método {@link #newInstance}.</p>
 *
 * @autor jvillar315
 */
public class DetailsFragment extends Fragment {
    private FragmentDetailsBinding binding;

    /**
     * Crea y devuelve la vista jerárquica asociada al fragmento.
     *
     * @param inflater  El objeto LayoutInflater que se utiliza para inflar el diseño del fragmento.
     * @param container El contenedor al que se agregará el fragmento (puede ser null).
     * @param savedInstanceState Estado previamente guardado del fragmento (puede ser null).
     * @return La vista inflada y configurada del fragmento.
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflar el diseño del fragmento con View Binding
        binding = FragmentDetailsBinding.inflate(inflater, container, false);

        // Obtener los argumentos y asignarlos a las vistas
        if (getArguments() != null) {
            String characterName = getArguments().getString("name");
            int characterImage = getArguments().getInt("image", 0);
            String characterDescription = getArguments().getString("description");
            String characterAbilities = getArguments().getString("abilities");

            // Configurar los datos en las vistas
            binding.nameCharacter.setText(characterName);
            binding.imageCharacter.setImageResource(characterImage);
            binding.descriptionCharacter.setText(characterDescription);
            binding.abilitiesCharacter.setText(characterAbilities);
        }

        return binding.getRoot();
    }

    /**
     * Crea una nueva instancia de {@link DetailsFragment} con los detalles del personaje.
     *
     * @param name          Nombre del personaje.
     * @param imageResource Recurso de imagen asociado al personaje.
     * @param description   Descripción del personaje.
     * @param abilities     Habilidades del personaje.
     * @return Una instancia configurada de {@link DetailsFragment}.
     */
    public static DetailsFragment newInstance(String name, int imageResource, String description, String abilities) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString("name", name);
        args.putInt("image", imageResource);
        args.putString("description", description);
        args.putString("abilities", abilities);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Método llamado después de que la vista jerárquica del fragmento ha sido creada.
     *
     * <p>Este método se utiliza para procesar cualquier lógica adicional después de que la
     * vista ha sido inflada y configurada.</p>
     *
     * @param view               La vista principal del fragmento.
     * @param savedInstanceState Estado previamente guardado del fragmento (puede ser null).
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Recibir los argumentos enviados y configurar las vistas
        if (getArguments() != null) {
            String characterName = getArguments().getString("name");
            int characterImage = getArguments().getInt("image", 0);
            String characterDescription = getArguments().getString("description");
            String characterAbilities = getArguments().getString("abilities");

            // Mostrar los datos en las vistas
            binding.nameCharacter.setText(characterName);
            binding.imageCharacter.setImageResource(characterImage);
            binding.descriptionCharacter.setText(characterDescription);
            binding.abilitiesCharacter.setText(characterAbilities);
        }
    }
}
