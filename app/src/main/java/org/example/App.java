package org.example;

import entities.Ticket;
import entities.User;
import services.UserBookingService;
import utils.UserServiceUtil;
import entities.Train;

import java.io.IOException;
import java.sql.Time;
import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("Running train app");
        Scanner scanner = new Scanner(System.in);
        int option = 0;
        UserBookingService userBookingService = null;

        try {
            userBookingService = new UserBookingService();
        } catch (IOException e) {
            System.out.println("Something went wrong"+ e.getMessage());
            return;
        }

        Train trainSelectedForBooking = null;

        while (option != 7) {
            System.out.println("Choose option");
            System.out.println("1. Sign up");
            System.out.println("2. Login");
            System.out.println("3. Fetch Booking");
            System.out.println("4. Search Train");
            System.out.println("5. Book a seat");
            System.out.println("6. Cancel my booking");
            System.out.println("7. Exit the app");

            option = scanner.nextInt();

            switch (option) {
                case 1: {
                    System.out.println("Enter the username to signup");
                    String name = scanner.next();
                    System.out.println("Enter the password");
                    String password = scanner.next();
                    User newUser = new User(name, password, UserServiceUtil.hashPassword(password), new ArrayList<>(), UUID.randomUUID().toString());
                    userBookingService.signUp(newUser);
                    break;
                }

                case 2: {
                    System.out.println("Enter the username to login");
                    String name = scanner.next();
                    System.out.println("Enter the password");
                    String password = scanner.next();
                    User loginUser = new User(name, password, password, new ArrayList<>(), UUID.randomUUID().toString());
                    try {
                        userBookingService = new UserBookingService(loginUser);
                        if (userBookingService.loginUser())
                            System.out.println("Login successful");
                        else
                            System.out.println("Invalid credentials");
                    } catch (IOException e) {
                        System.out.println("Error loading user data"+e.getMessage());
                    }
                    break;
                }

                case 3:
                    userBookingService.fetchBooking();
                    break;

                case 4: {
                    System.out.println("Type your source station");
                    String source = scanner.next();
                    System.out.println("Enter Destination");
                    String destination = scanner.next();
                    List<Train> trains = userBookingService.getTrains(source, destination);
                    int index = 1;
                    for (Train t : trains) {
                        System.out.println(index + ". Train id: " + t.getTrainId());
                        for (Map.Entry<String, String> entry : t.getStationTimes().entrySet()) {
                            System.out.println("station " + entry.getKey() + " time " + entry.getValue());
                        }
                        index++;
                    }
                    if (!trains.isEmpty()) {
                        System.out.println("Select a train by typing 1 2 3 ...");
                        int selectedIndex = scanner.nextInt();
                        if (selectedIndex >= 1 && selectedIndex <= trains.size()) {
                            trainSelectedForBooking = trains.get(selectedIndex - 1);
                            System.out.println("Train selected: " + trainSelectedForBooking.getTrainId());
                            System.out.println("Select Seat No:");
                            List<List <Integer>> seats =trains.get(selectedIndex-1).getSeats();
                            for (int i=0;i<seats.size();i++){
                                for(int j=0;j<seats.get(i).size();j++){
                                    System.out.print(seats.get(i).get(j)+" ");
                                }
                                System.out.println("");
                            }
                            System.out.println("Enter Seat No:");
                            int seatNo = scanner.nextInt();
                            try{

                                int row = (seatNo - 1) / 5;
                                int col = (seatNo - 1) % 5;
                                if(seats.get(row).get(col)==0){
                                selectedIndex--;
                                User user = userBookingService.fetchLogedUser();
                                Ticket ticket = new Ticket(trains.get(selectedIndex).getTrainId(),user.getUserId(),source,destination,"2:3:40",trains.get(selectedIndex));
                                user.getTicketsBooked().add(ticket);
                                user.setTicketsBooked(user.getTicketsBooked());
                                System.out.println("seat book successfully");
                               List<User> users = userBookingService.l
                            }
                            }catch (Exception e){
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    break;
                }

                case 5:
                    System.out.println("Booking functionality not implemented yet.");
                    break;

                case 6:
                    System.out.println("Cancellation not implemented yet.");
                    break;

                case 7:
                    System.out.println("Exiting the app.");
                    break;
            }
        }
    }
}
