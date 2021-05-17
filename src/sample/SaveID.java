package sample;

public class SaveID {
    private int id;

    public SaveID(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SaveID{" +
                "id=" + id +
                '}';
    }
}
