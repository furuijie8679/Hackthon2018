package ghost.svc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Tells Hibernate to make a table for Entity.
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)// Primary key
    private Integer id;
    private String content;
    private double lat;
    private double lng;
    private long timestamp;
    private String creatorClientUuid;
    private String cellToken;

    public String getCellToken() {
        return cellToken;
    }

    public void setCellToken(String cellToken) {
        this.cellToken = cellToken;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getCreatorClientUuid() {
        return creatorClientUuid;
    }

    public void setCreatorClientUuid(String creatorClientUuid) {
        this.creatorClientUuid = creatorClientUuid;
    }
}
