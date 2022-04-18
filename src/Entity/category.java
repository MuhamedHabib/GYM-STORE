package Entity;

public class category {
    public int id;
    public String title;
    public String slug;


    /**********************************
     * GETTERS AND SETTERS
     * */

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    /************************
     * TOSTRING
    * */
    @Override
    public String toString() {
        return "category{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                '}';
    }

    /*********************
     * CONSTRUCTORS
     */
    /*
    * Parametric
    * */

    public category(int id, String title, String slug) {
        this.id = id;
        this.title = title;
        this.slug = slug;
    }

    /*
    * DEFAULT
     */

    public category() {
    }
}
