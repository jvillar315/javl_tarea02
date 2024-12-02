package dam.pmpd.javl_tarea02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import dam.pmpd.javl_tarea02.databinding.ItemCharacterBinding;

/**
 * Adaptador personalizado para gestionar y mostrar una lista de objetos de tipo {@link Character}.
 *
 * <p>Este adaptador utiliza RecyclerView junto con View Binding para vincular los datos
 * de los personajes con las vistas de la interfaz.</p>
 *
 * @author jvillar
 */
public class SimpleAdapter extends RecyclerView.Adapter<SimpleAdapter.CharacterViewHolder> {

    // Lista de personajes a mostrar
    private final List<Character> characters;

    // Listener para gestionar clics en elementos de la lista
    private final OnItemClickListener listener;

    /**
     * Constructor del adaptador.
     *
     * @param characters Lista de personajes que se mostrarán en el RecyclerView.
     * @param listener   Listener para gestionar las acciones de clic en los elementos.
     */
    public SimpleAdapter(List<Character> characters, OnItemClickListener listener) {
        this.characters = characters;
        this.listener = listener;
    }

    /**
     * Inflar el diseño de cada elemento de la lista utilizando View Binding.
     *
     * @param parent   El ViewGroup padre al que se añadirá el nuevo View.
     * @param viewType Tipo de vista del nuevo View.
     * @return Un nuevo ViewHolder que contiene las vistas infladas.
     */
    @NonNull
    @Override
    public CharacterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCharacterBinding binding = ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.getContext()),
                parent,
                false
        );
        return new CharacterViewHolder(binding);
    }

    /**
     * Vincula los datos de un personaje a la vista correspondiente.
     *
     * @param holder   ViewHolder que contiene las vistas para un elemento de la lista.
     * @param position Posición del elemento en la lista.
     */
    @Override
    public void onBindViewHolder(@NonNull CharacterViewHolder holder, int position) {
        // Obtener el personaje actual de la lista
        Character character = characters.get(position);

        // Establecer los datos en las vistas
        holder.binding.characterName.setText(character.getName());
        holder.binding.imageCharacter.setImageResource(character.getImageResource());

        // Configurar el clic en el elemento
        holder.itemView.setOnClickListener(v -> {
            // Mostrar un mensaje al seleccionar un personaje
            Context context = holder.itemView.getContext();
            String message = context.getString(R.string.character_selected, character.getName());
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();

            // Notificar al listener sobre el clic en un elemento
            listener.onItemClick(character);
        });
    }

    /**
     * Devuelve el número total de elementos en la lista.
     *
     * @return Número de elementos en la lista.
     */
    @Override
    public int getItemCount() {
        return characters.size();
    }

    /**
     * ViewHolder para gestionar las vistas de cada elemento de la lista.
     *
     * <p>Utiliza View Binding para acceder de forma eficiente a los elementos de la interfaz.</p>
     */
    public static class CharacterViewHolder extends RecyclerView.ViewHolder {
        // Binding para acceder a los elementos del diseño
        private final ItemCharacterBinding binding;

        /**
         * Constructor del ViewHolder.
         *
         * @param binding Instancia de View Binding asociada al diseño del elemento.
         */
        public CharacterViewHolder(@NonNull ItemCharacterBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    /**
     * Interfaz para gestionar los clics en los elementos de la lista.
     */
    public interface OnItemClickListener {
        /**
         * Método llamado cuando se hace clic en un elemento de la lista.
         *
         * @param character El personaje asociado al elemento seleccionado.
         */
        void onItemClick(Character character);
    }
}
