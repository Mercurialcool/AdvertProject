package by.vasiliuk.project.model.entity;

public enum Section {
FURNITURE(100), CARS(200), ELECTRONICS(300), HOUSEHOLD(400);
    private int id;

   Section(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
}
}