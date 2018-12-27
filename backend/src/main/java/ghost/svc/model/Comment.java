package ghost.svc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // Tells Hibernate to make a table for Entity.
public class Comment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)// Primary key
    private String id;
    private String content;
    private double lat;
    private double lng;
    private long timestamp;
    private String creatorClientUuid;
    private String l15S2CellToken;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public double getLat() {
        return lat;
    }

    public double getLng() {
        return lng;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getCreatorClientUuid() {
        return creatorClientUuid;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public void setCreatorClientUuid(String creatorClientUuid) {
        this.creatorClientUuid = creatorClientUuid;
    }

    public String getL15S2CellToken() {
        return l15S2CellToken;
    }

    public void setL15S2CellToken(String l15S2CellToken) {
        this.l15S2CellToken = l15S2CellToken;
    }
}
