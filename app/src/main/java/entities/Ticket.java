
package entities;
import java.util.*;
import entities.Train;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;

public class Ticket {
    @JsonProperty("ticket_id")
    private String ticketId;

    @JsonProperty("user_id")
    private String userId;

    private String source;
    private String destination;

    @JsonProperty("date_of_travel")
    private String dateOfTravel;

    private Train train;

    public Ticket() {}  // Required for Jackson

    public Ticket(String ticketId, String userId, String source, String destination, String dateOfTravel, Train train) {
        this.ticketId = ticketId;
        this.userId = userId;
        this.source = source;
        this.destination = destination;
        this.dateOfTravel = dateOfTravel;
        this.train = train;
    }



    public String getTicketId() {
        return ticketId;
    }

    public Train getTrain() {
        return train;
    }

    public String getUserId() {
        return userId;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getDateOfTravel() {
        return dateOfTravel;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public void setDateOfTravel(String dateOfTravel) {
        this.dateOfTravel = dateOfTravel;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTicketInfo(){
        return String.format("Ticket ID: %s belongs to user %s from %s to %s on %s",ticketId,userId,source,destination,dateOfTravel);
    }
}