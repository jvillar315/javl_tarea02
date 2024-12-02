package dam.pmpd.javl_tarea02;

/**
 * Clase que representa un personaje con su nombre, imagen, descripción y habilidades.
 *
 * <p>Esta clase se utiliza para encapsular la información relacionada con un personaje,
 * como su nombre, un recurso de imagen asociado, su descripción y sus habilidades.</p>
 *
 * @autor jvillar315
 */
public class Character {

    private final String name;
    private final int imageResource;
    private final String description;
    private final String abilities;

    /**
     * Constructor para inicializar un personaje con su nombre, imagen, descripción y habilidades.
     *
     * @param name          El nombre del personaje.
     * @param imageResource El recurso de imagen asociado al personaje.
     * @param description   La descripción del personaje.
     * @param abilities     Las habilidades del personaje.
     */
    public Character(String name, int imageResource, String description, String abilities) {
        this.name = name;
        this.imageResource = imageResource;
        this.description = description;
        this.abilities = abilities;
    }

    /**
     *
     * @return El nombre del personaje.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return El identificador del recurso de imagen.
     */
    public int getImageResource() {
        return imageResource;
    }

    /**
     *
     * @return La descripción del personaje.
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @return Las habilidades del personaje.
     */
    public String getAbilities() {
        return abilities;
    }
}
