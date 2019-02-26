package solutions.empire42.tatianego.model;

public class Usuario {

    private String username;
    private String objectId;
    private String email;

    public Usuario(String username, String objectId, String email) {
        this.username = username;
        this.objectId = objectId;
        this.email = email;
    }

    public Usuario() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
