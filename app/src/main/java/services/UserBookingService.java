package services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Train;
import entities.User;
import utils.UserServiceUtil;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class UserBookingService {
    private User user;
    private ObjectMapper objectMapper = new ObjectMapper();
    private List<User> userList = new ArrayList<>();
    private static final String USER_PATH = "app/src/main/java/localDb/users.json";

    public UserBookingService(User u) throws IOException {
        this.user = u;
        userList = loadUsers();
    }

    public UserBookingService() throws IOException {
        userList = loadUsers();
    }

    private List<User> loadUsers() throws IOException {
        File users = new File(USER_PATH);
        if (!users.exists()) return new ArrayList<>();
        return objectMapper.readValue(users, new TypeReference<List<User>>() {});
    }

    public Boolean loginUser() {
        Optional<User> foundUser = userList.stream().filter(user1 ->
                user1.getName().equals(user.getName()) &&
                        UserServiceUtil.checkPassword(user.getPassword(), user1.getPassword())
        ).findFirst();
        return foundUser.isPresent();
    }

    public Boolean signUp(User user1) {
        try {
            userList.add(user1);
            saveUserListToFile();
            return Boolean.TRUE;
        } catch (IOException e) {
            return Boolean.FALSE;
        }
    }

    private void saveUserListToFile() throws IOException {
        File usersFile = new File(USER_PATH);
        objectMapper.writeValue(usersFile, userList);
    }

    public void fetchBooking() {
        if (user != null)
            user.printTickets();
    }

    public Boolean cancelBooking(String ticketId) {
        return Boolean.FALSE;
    }

    public List<Train> getTrains(String source, String destination) {
        try {
            TrainService trainService = new TrainService();
            return trainService.searchTrains(source, destination);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
