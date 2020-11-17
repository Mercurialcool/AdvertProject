package by.vasiliuk.project.model.entity;

public enum Section {
FURNITURE(100), CARS(200), ELECTRONICS(300), HOUSEHOLD(400);
    private long id;

    public long getId() {
        return id;
    }

  Section(long id) {
        this.id = id;

    }
}