package isidore;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeParseException;

class AttendanceRecord {
    private LocalDate date;
    private Date checkInTime;
    private Date checkOutTime;

    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public AttendanceRecord(LocalDate date, Date checkInTime, Date checkOutTime) {
        this.date = date;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }

    public LocalDate getDate() {
        return date;
    }

    public Date getCheckInTime() {
        return checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }
}
class GymMemberss {

    private List<AttendanceRecord> dailyAttendanceRecords = new ArrayList<>();

    public void addDailyAttendanceRecord(LocalDate date, Date checkInTime, Date checkOutTime) {
        AttendanceRecord record = new AttendanceRecord(date, checkInTime, checkOutTime);
        dailyAttendanceRecords.add(record);
    }

    public List<AttendanceRecord> getDailyAttendanceRecords() {
        return dailyAttendanceRecords;
    }

    private int id;
    private String name;
    private int age;
    public Date checkInTime;
    public Date checkOutTime;
    private String workoutType;
    private String trainer;
    private static AtomicInteger idGenerator = new AtomicInteger(1);

    public GymMemberss(String name, int age, String trainer) {
        this.id = idGenerator.getAndIncrement();
        this.name = name;
        this.age = age;
        this.checkInTime = null;
        this.checkOutTime = null;
        this.trainer = trainer;
    }


    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    private LocalDate checkInDate;
    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getCheckInTime() {

        return checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }

    public void checkIn() {
        this.checkInTime = new Date();
        LocalDate currentDate = LocalDate.now();
        addDailyAttendanceRecord(currentDate, this.checkInTime, null);
    }

    public void checkOut() {
        this.checkOutTime = new Date();
        LocalDate currentDate = LocalDate.now();

        // Find and update the attendance record for the current day
        for (AttendanceRecord record : dailyAttendanceRecords) {
            if (record.getDate().equals(currentDate)) {
                record.setCheckOutTime(this.checkOutTime);
                break;
            }
        }
    }


    public void setWorkoutType(String workoutType) {
        this.workoutType = workoutType;
    }

    public String getWorkoutType() {
        return workoutType;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTrainer() {
        return trainer;
    }
}
class CheckInRecord {
    private LocalDateTime checkInTime;
    private LocalDateTime checkOutTime;

    public void checkIn() {
        checkInTime = LocalDateTime.now();
    }

    public void checkOut() {
        checkOutTime = LocalDateTime.now();
    }

    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    public boolean isCheckedIn() {
        return checkInTime != null && checkOutTime == null;
    }
}
class AttendanceLog {
    private List<GymMemberss> log = new ArrayList<>();
    private List<String> trainers = new ArrayList<>();



    public void addTrainer(String trainerName) {
        trainers.add(trainerName);
    }

    public void removeTrainer(String trainerName) {
        trainers.remove(trainerName);
    }

    public List<String> getTrainers() {
        return Collections.unmodifiableList(trainers);
    }


    public void addMember(GymMemberss member) {
        log.add(member);
    }

    public void removeMember(int memberId) {
        GymMemberss memberToRemove = getMemberById(memberId);
        if (memberToRemove != null) {
            log.remove(memberToRemove);
        }
    }

    public GymMemberss getMemberById(int memberId) {
        for (GymMemberss member : log) {
            if (member.getId() == memberId) {
                return member;
            }
        }
        return null;
    }

    public List<GymMemberss> getLog() {
        return Collections.unmodifiableList(log);
    }
}

class AdminLoginForm {
    private AttendanceLog attendanceLog;
    private Scanner scanner;


    public AdminLoginForm(AttendanceLog attendanceLog, Scanner scanner) {
        this.attendanceLog = attendanceLog;
        this.scanner = scanner;
    }

    public boolean authenticate(String username, String password) {
        return username.equals("admin") && password.equals("admin123");
    }

    public void login() {
        System.out.println("Welcome to the Admin Login Form");

        while (true) {
            try {
                System.out.print("Enter username: ");
                String username = scanner.nextLine();
                System.out.print("Enter password: ");
                String password = scanner.nextLine();

                if (authenticate(username, password)) {
                    System.out.println("Admin login successful!");
                    adminFunction();
                    break;
                } else {
                    System.out.println("Admin login failed. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }
    }

    public void addMembers() {
        try {
            System.out.print("Enter member name: ");
            String memberName = scanner.nextLine();
            System.out.print("Enter member age: ");
            int memberAge = scanner.nextInt();
            scanner.nextLine();
            GymMemberss member = new GymMemberss(memberName, memberAge, null);
            attendanceLog.addMember(member);
            System.out.println(memberName + " (Age: " + memberAge + ") has been added as a gym member with ID " + member.getId());
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid age.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
    public void removeMembers(){
        System.out.println("-----------------------------------------------------");
        System.out.print("Enter member ID to remove: ");
        int memberIdToRemove = scanner.nextInt();
        scanner.nextLine();
        attendanceLog.removeMember(memberIdToRemove);
        System.out.println("Member with ID " + memberIdToRemove + " has been removed.");
        System.out.println("-----------------------------------------------------");
    }
    public void renameMember() {
        System.out.println("-----------------------------------------------------");
        System.out.print("Enter member ID to rename: ");

        int memberIdToRename = scanner.nextInt();
        scanner.nextLine();
        GymMemberss memberToRename = attendanceLog.getMemberById(memberIdToRename);

        if (memberToRename != null) {
            System.out.print("Enter new name for member: ");
            String newName = scanner.nextLine();
            memberToRename.setName(newName);
            System.out.println("Member with ID " + memberIdToRename + " has been renamed to " + newName);
        } else {
            System.out.println("Member with ID " + memberIdToRename + " is not found.");
        }
        System.out.println("-----------------------------------------------------");
    }

    public void addTrainer(String trainerName) {
        attendanceLog.addTrainer(trainerName);
        System.out.println("----------------------------------------------------");
        System.out.println("Trainer " + trainerName + " has been added.");
        System.out.println("----------------------------------------------------");
    }

    public void removeTrainer(String trainerName) {
        attendanceLog.removeTrainer(trainerName);
        System.out.println("----------------------------------------------------");
        if (attendanceLog.getTrainers().remove(trainerName)) {
            System.out.println("Trainer " + trainerName + " has been removed.");
        } else {
            System.out.println("Trainer " + trainerName + " is not found.");
        }
        System.out.println("----------------------------------------------------");
    }

    public void viewTrainers() {
        List<String> trainers = attendanceLog.getTrainers();
        System.out.println("----------------------------------------------------");
        System.out.println("List of Trainers:");
        for (String trainer : trainers) {
            System.out.println(trainer);
        }
        System.out.println("----------------------------------------------------");
    }
    public void Disp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.print("Enter the date (yyyy-MM-dd): ");
        String inputDateStr = scanner.nextLine();

        try {
            LocalDate inputDate = LocalDate.parse(inputDateStr);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            List<GymMemberss> log = attendanceLog.getLog();
            for (GymMemberss member : log) {
                System.out.println("ID: " + member.getId());
                System.out.println("NAME: " + member.getName());
                System.out.println("AGE: " + member.getAge());
                System.out.println("Attendance for " + inputDateStr + ":");

                for (AttendanceRecord record : member.getDailyAttendanceRecords()) {
                    if (record.getDate().equals(inputDate)) {
                        String checkInStatus = (record.getCheckInTime() != null) ? "Checked In at " + dateFormat.format(record.getCheckInTime()) : "Not Checked In";
                        String checkOutStatus = (record.getCheckOutTime() != null) ? "Checked Out at " + dateFormat.format(record.getCheckOutTime()) : "Not Checked Out";

                        System.out.println("Check-in Status: " + checkInStatus);
                        System.out.println("Check-out Status: " + checkOutStatus);
                        System.out.println("");
                    }
                    System.out.println("----------------------------------------------------");
                }

            }
            System.out.println("===================================================");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
    }

    public void adminFunction() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String deyt = String.valueOf(now);
        while (true) {
            System.out.println("\n\t\tAdmin Menu:");
            System.out.println("----------------------------------------------------");
            System.out.println(deyt);
            System.out.println("----------------------------------------------------");
            System.out.println("\t\t1. Add Gym Member");
            System.out.println("\t\t2. Remove Gym Member");
            System.out.println("\t\t3. Rename Gym Member");
            System.out.println("\t\t4. Add Trainer");
            System.out.println("\t\t5. Remove Trainer");
            System.out.println("\t\t6. View Trainers");
            System.out.println("\t\t7. View Attendance Log");
            System.out.println("\t\t8. View List of Members");
            System.out.println("\t\t9. Logout");
            System.out.println("====================================================");
            System.out.print(">>>");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addMembers();
                    break;


                case 2:
                    System.out.println("----------------------------------------------------");
                    System.out.println("List of Members:");
                    for (GymMemberss m : attendanceLog.getLog()) {
                        System.out.println("ID: " + m.getId() + ", Name: " + m.getName());
                    }
                    System.out.println("----------------------------------------------------");
                    removeMembers();
                    break;
                case 3:
                    System.out.println("----------------------------------------------------");
                    System.out.println("List of Members:");
                    for (GymMemberss m : attendanceLog.getLog()) {
                        System.out.println("ID: " + m.getId() + ", Name: " + m.getName());
                    }
                    System.out.println("----------------------------------------------------");

                    renameMember();
                    break;

                case 4:
                    System.out.print("\nEnter trainer name: ");
                    String trainerName = scanner.nextLine();
                    addTrainer(trainerName);
                    break;
                case 5:
                    System.out.print("\nEnter trainer name to remove: ");
                    String trainerToRemove = scanner.nextLine();
                    removeTrainer(trainerToRemove);
                    break;
                case 6:
                    viewTrainers();
                    break;
                case 7:
                    Disp();
                    break;

                case 8:
                    System.out.println("----------------------------------------------------");
                    System.out.println("List of Members:");
                    for (GymMemberss m : attendanceLog.getLog()) {
                        System.out.println("ID: " + m.getId() + ", Name: " + m.getName());
                    }
                    System.out.println("----------------------------------------------------");
                    break;
                case 9:
                    System.out.println("Admin logout.");
                    isidore meyn = new isidore();
                    meyn.main(null);
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }


}

class FrontDeskLoginForm {


    private AttendanceLog attendanceLog;
    private Scanner scanner;

    public FrontDeskLoginForm(AttendanceLog attendanceLog, Scanner scanner) {
        this.attendanceLog = attendanceLog;
        this.scanner = scanner;
    }

    public boolean authenticate(String username, String password) {
        return username.equals("frontdesk") && password.equals("frontdesk123");
    }

    public void login() {
        System.out.println("Welcome to the Front Desk Login Form\n");

        while (true) {
            System.out.print("Enter username: ");
            String username = scanner.nextLine();
            System.out.print("Enter password: ");
            String password = scanner.nextLine();

            if (authenticate(username, password)) {
                System.out.println("");
                System.out.println("Front Desk login successful!");
                frontDeskFunction();
                break;
            } else {
                System.out.println("");
                System.out.println("Front Desk login failed. Please try again.");
            }
        }
    }
    public void viewTrainers() {
        List<String> trainers = attendanceLog.getTrainers();
        System.out.println("----------------------------------------------------");
        System.out.println("List of Trainers:");
        for (String trainer : trainers) {
            System.out.println(trainer);
        }
        System.out.println("----------------------------------------------------");

    }

    public void Disp() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println("----------------------------------------------------");
        System.out.print("Enter the date (yyyy-MM-dd): ");
        String inputDateStr = scanner.nextLine();
        System.out.println("----------------------------------------------------");
        try {
            LocalDate inputDate = LocalDate.parse(inputDateStr);
            dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            List<GymMemberss> log = attendanceLog.getLog();
            for (GymMemberss member : log) {
                System.out.println("ID: " + member.getId());
                System.out.println("NAME: " + member.getName());
                System.out.println("AGE: "+ member.getAge());
                System.out.println("Attendance for " + inputDateStr + ":");

                for (AttendanceRecord record : member.getDailyAttendanceRecords()) {
                    if (record.getDate().equals(inputDate)) {
                        String checkInStatus = (record.getCheckInTime() != null) ? "Checked In at " + dateFormat.format(record.getCheckInTime()) : "Not Checked In";
                        String checkOutStatus = (record.getCheckOutTime() != null) ? "Checked Out at " + dateFormat.format(record.getCheckOutTime()) : "Not Checked Out";

                        System.out.println("Check-in Status: " + checkInStatus);
                        System.out.println("Check-out Status: " + checkOutStatus);
                        System.out.println("");
                    }
                    System.out.println("----------------------------------------------------");
                }

            }
            System.out.println("====================================================");
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the date in yyyy-MM-dd format.");
        }
    }

    public void frontDeskFunction() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String deyt = String.valueOf(now);
        while (true) {
            System.out.println("----------------------------------------------------");
            System.out.println("\t\tFront Desk Menu:");
            System.out.println("----------------------------------------------------");
            System.out.println(deyt);
            System.out.println("----------------------------------------------------");
            System.out.println("\t\t1. Register Member");
            System.out.println("\t\t2. Member Check-In");
            System.out.println("\t\t3. Member Check-Out");
            System.out.println("\t\t4. View Attendance Log");
            System.out.println("\t\t5. View List of Members");
            System.out.println("\t\t6. Logout");
            System.out.println("====================================================");
            System.out.print(">>>");


            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("----------------------------------------------------");
                    System.out.print("Enter member name: ");
                    String memberName = scanner.nextLine();
                    System.out.print("Enter member age: ");
                    int memberAge = scanner.nextInt();
                    scanner.nextLine();
                    GymMemberss member = new GymMemberss(memberName, memberAge, null);
                    attendanceLog.addMember(member);
                    System.out.println("\n"+memberName + " (Age: " + memberAge + ") has been added \ngym member with ID " + member.getId());
                    System.out.println("----------------------------------------------------");
                    break;

                case 2:
                    System.out.println("List of Members:");
                    System.out.println("----------------------------------------------------");
                    for (GymMemberss m : attendanceLog.getLog()) {
                        System.out.println("ID: " + m.getId() + ", Name: " + m.getName());
                    }
                    System.out.println("----------------------------------------------------");

                    System.out.print("Enter member ID: ");
                    int memberId = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("----------------------------------------------------");
                    GymMemberss selectedMember = attendanceLog.getMemberById(memberId);

                    if (selectedMember != null) {

                        System.out.println("----------------------------------------------------");
                        System.out.println("\t\tChoose workout type:");
                        System.out.println("----------------------------------------------------");
                        System.out.println("\t\t1. PUSH");
                        System.out.println("\t\t2. PULL");
                        System.out.println("\t\t3. LEGS");
                        System.out.println("\t\4. FULL BODY");
                        System.out.println("----------------------------------------------------");
                        System.out.print(">>>");
                        int workoutChoice = scanner.nextInt();
                        scanner.nextLine();
                        String workoutType;
                        switch (workoutChoice) {
                            case 1:
                                workoutType = "PUSH";
                                break;
                            case 2:
                                workoutType = "PULL";
                                break;
                            case 3:
                                workoutType = "LEGS";
                                break;
                            case 4:
                                workoutType = "FULL BODY";
                                break;
                            default:
                                workoutType = "Unknown";
                                break;
                        }

                        LocalDate currentDate = LocalDate.now();

                        selectedMember.checkIn();
                        selectedMember.setWorkoutType(workoutType);
                        selectedMember.setCheckInDate(currentDate);
                        System.out.println("----------------------------------------------------");
                        System.out.println(selectedMember.getName() + " has checked in for a " + workoutType + " workout.");

                        viewTrainers();

                        System.out.print("Do you want a trainer? (Enter trainer's name or 0 to decline): ");
                        String trainerChoice = scanner.nextLine();
                        System.out.println("----------------------------------------------------");
                        if (!trainerChoice.equals("0")) {
                            selectedMember.setTrainer(trainerChoice);
                            System.out.println("Trainer " + trainerChoice + " assigned.");
                        }
                    } else {
                        System.out.println("Member with ID " + memberId + " is not a gym member.");
                    }
                    break;


                case 3:

                    System.out.println("List of Members:");
                    System.out.println("----------------------------------------------------");

                    for (GymMemberss m : attendanceLog.getLog()) {
                        System.out.println("ID: " + m.getId() + ", Name: " + m.getName());
                    }

                    System.out.println("----------------------------------------------------");

                    System.out.print("Enter member ID to check out: ");
                    int memberIdToCheckOut = scanner.nextInt();
                    scanner.nextLine();

                    GymMemberss checkOutMember = attendanceLog.getMemberById(memberIdToCheckOut);

                    if (checkOutMember != null) {
                        if (checkOutMember.getCheckInTime() != null && checkOutMember.getCheckOutTime() == null) {

                            checkOutMember.checkOut();
                            System.out.println(checkOutMember.getName() + " has checked out.");
                        } else {
                            System.out.println(checkOutMember.getName() + " is not checked in or is already checked out.");
                        }
                    } else {
                        System.out.println("Member with ID " + memberIdToCheckOut + " is not a gym member.");
                    }
                    break;

                case 4:

                    Disp();
                    break;

                case 5:
                    System.out.println("List of Members:");
                    System.out.println("----------------------------------------------------");
                    for (GymMemberss m : attendanceLog.getLog()) {
                        System.out.println("ID: " + m.getId() + ", Name: " + m.getName());
                    }
                    break;
                case 6:
                    System.out.println("Front Desk logout.");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}


public class isidore {
    public static void main(String[] args) {
        System.out.println("----------------------------------------------------");
        System.out.println("                 Fit World Gym ");


        Scanner scan = new Scanner(System.in);
        AttendanceLog attendanceLog = new AttendanceLog();

        while (true) {
            try {
                System.out.println("----------------------------------------------------");
                System.out.println("\t\t1. Admin" +
                        "\n\t\t2. Front Desk" +
                        "\n\t\t3. Exit");
                System.out.println("----------------------------------------------------");
                System.out.print(">>>");
                int choice = scan.nextInt();
                scan.nextLine();
                if (choice == 1) {
                    System.out.println("Are you sure you want to login as ADMIN? (Y)es or (N)o ?");
                    String letr = scan.nextLine();
                    while(true){
                        try {

                            if(letr.equalsIgnoreCase("y")) {
                                AdminLoginForm adminLoginForm = new AdminLoginForm(attendanceLog, scan);
                                adminLoginForm.login();
                            }
                            else if(letr.equalsIgnoreCase("n")) {
                                main(null);
                            }
                            else {
                                System.out.println("Invalid choice. Try again");
                                main(null);
                            }
                        }catch(InputMismatchException e) {System.out.println("Invalid input");
                            scan.nextLine();
                        }
                    }


                } else if (choice == 2) {
                    System.out.println("Are you sure you want to login as FrontDesk user? (Y)es or (N)o ?");
                    String letr = scan.nextLine();

                    while(true) {
                        try {

                            if(letr.equalsIgnoreCase("y")) {
                                FrontDeskLoginForm frontDeskLoginForm = new FrontDeskLoginForm(attendanceLog, scan);
                                frontDeskLoginForm.login();
                            }
                            else if(letr.equalsIgnoreCase("n")) {
                                main(null);
                            }
                            else {
                                System.out.println("Invalid choice. Try again");
                                main(null);
                            }

                        }catch(InputMismatchException e) {System.out.println("Invalid Input");
                            scan.nextLine();
                        }
                    }

                } else if (choice == 3) {
                    System.out.println("Goodbye!");
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scan.nextLine();
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
            }
        }

        scan.close();
    }
}






