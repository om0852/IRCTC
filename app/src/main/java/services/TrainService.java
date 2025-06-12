package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Ticket;
import entities.Train;
import entities.User;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TrainService {
    private ObjectMapper objectMapper = new ObjectMapper();

    private static final String USER_PATH = "app/src/main/java/localDb/trains.json";
    private List<Train> loadUsers() throws IOException {
        File users = new File(USER_PATH);
        if (!users.exists()) return new ArrayList<>();
        return objectMapper.readValue(users, new TypeReference<List<Train>>() {});
    }
    private List<Train> trainList = new ArrayList<>();
    // Should be loade d from file/db
public TrainService() throws IOException{
    trainList =loadUsers();

}
    public List<Train> searchTrains(String source, String destination) {
        return trainList.stream()
                .filter(train -> validTrain(train, source, destination))
                .collect(Collectors.toList());
    }

    private boolean validTrain(Train train, String source, String destination) {
        List<String> stationOrder = train.getStations();
        int sourceIndex = stationOrder.indexOf(source);
        int destinationIndex = stationOrder.indexOf(destination);
        System.out.println(sourceIndex+" "+destinationIndex);
        return sourceIndex != -1 && destinationIndex != -1 && sourceIndex < destinationIndex;
    }
}
