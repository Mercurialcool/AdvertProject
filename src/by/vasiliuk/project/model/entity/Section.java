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
    public static String getName(int id) {
       String name;
       switch (id){
           case 100: name = "FURNITURE"; break;
           case 200: name = "CARS";break;
           case 300: name = "ELECTRONICS";break;
           case 400: name = "HOUSEHOLD";break;
           default: throw new EnumConstantNotPresentException(Section.class, "");
       }
       return name;
    }

}