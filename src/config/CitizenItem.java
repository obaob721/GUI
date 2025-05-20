package config;

/**
 *
 * @author PATRICIA
 */
class CitizenItem {
    private int id;
    private String name;

    public CitizenItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name; // What shows in the combo box
    }

    public int getId() {
        return id;
    }
}