package by.vasiliuk.project.model.entity;

import java.util.Objects;

public class Advert {

    private int id;
    private String text;
    private String title;
    private String userName;
    private String section;

    public Advert(int id, String text, String title, String userName) {
        this.id = id;
        this.text = text;
        this.title = title;
        this.userName = userName;
    }

    public Advert(int id, String text, String title) {
        this.id = id;
        this.text = text;
        this.title = title;
    }

    public Advert() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Advert)) return false;

        Advert advert = (Advert) o;

        if (id != advert.id) return false;
        if (text != null ? !text.equals(advert.text) : advert.text != null) return false;
        if (title != null ? !title.equals(advert.title) : advert.title != null) return false;
        if (userName != null ? !userName.equals(advert.userName) : advert.userName != null) return false;
        return section != null ? section.equals(advert.section) : advert.section == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (userName != null ? userName.hashCode() : 0);
        result = 31 * result + (section != null ? section.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Advert{");
        sb.append("id=").append(id);
        sb.append(", text='").append(text).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", userName='").append(userName).append('\'');
        sb.append(", section='").append(section).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
