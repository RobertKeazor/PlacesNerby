package Model;

public class Results {
    public Photos[] photos;

    private String id;

    private String place_id;

    private String icon;

    private String name;

    private String formatted_address;

    private String rating;

    private String[] types;

    private String reference;

    private Geometry geometry;

    public Photos[] getPhotos() {
        return photos;
    }

    public void setPhotos(Photos[] photos) {
        this.photos = photos;
    }
}