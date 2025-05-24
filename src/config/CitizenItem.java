package config;

public class CitizenItem {
    private int id;
    private String name;

    // ✅ Constructor
    public CitizenItem(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // ✅ toString defines how it appears in JComboBox
    @Override
    public String toString() {
        return name;
    }

    // ✅ Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
