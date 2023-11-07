import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.time.format.DateTimeParseException;
import java.util.concurrent.atomic.AtomicInteger;



class GymMembers {
    private String membershipType;

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

    public double getMembershipPrice() {
        return membershipPrice;
    }

    public void setMembershipPrice(double membershipPrice) {
        this.membershipPrice = membershipPrice;
    }

    private double membershipPrice;
    public static final String WORKOUT_TYPE_PUSH = Trainer.SPECIALIZATION_PUSH;
    public static final String WORKOUT_TYPE_PULL = Trainer.SPECIALIZATION_PULL;
    public static final String  WORKOUT_TYPE_LEGS = Trainer.SPECIALIZATION_LEGS ;
    public static final String WORKOUT_TYPE_FULL_BODY = Trainer.SPECIALIZATION_FULL_BODY;
    private static AtomicInteger idGenerator = new AtomicInteger(1);
    private int memberId;
    private String name;
    private int age;
    private List<CheckInOutEntry> checkInOutEntries;
    private LocalDateTime CheckInTime;
    private LocalDateTime CheckOutTime;
    private LocalDate checkInDate;
    private String workoutType;

    private Trainer trainer;
    private List<CheckInEntry> checkInEntries;
    private List<CheckOutEntry> checkOutEntries;
    public GymMembers(Integer memberId, String name, int age, String membershipType, double membershipPrice) {
        this.memberId = idGenerator.getAndIncrement();
        this.name = name;
        this.age = age;
        this.checkInOutEntries = new ArrayList<>();
        this.membershipType=membershipType;
        this.membershipPrice=membershipPrice;
        checkInEntries = new ArrayList<>();
        checkOutEntries = new ArrayList<>();
    }
    public void addCheckOutEntry(CheckOutEntry entry) {
        checkOutEntries.add(entry);
    }

    public List<CheckOutEntry> getCheckOutEntries() {
        return checkOutEntries;
    }
    public void addCheckInOutEntry(CheckInOutEntry entry) {
        checkInOutEntries.add(entry);
    }
    public List<CheckInOutEntry> getCheckInOutEntries() {
        return checkInOutEntries;
    }
    public void checkIn() {
        LocalDateTime currentTime = LocalDateTime.now();
        CheckInTime = currentTime;
        checkInDate = currentTime.toLocalDate();
        checkInOutEntries.add(new CheckInOutEntry(currentTime, true));
    }
    public LocalDate getCheckInDate() {
        return checkInDate;
    }
    public void checkOut() {
        if (CheckInTime != null) {
            CheckOutTime = LocalDateTime.now();
            checkInOutEntries.add(new CheckInOutEntry(CheckOutTime, false));
        }
    }

    public int getMemberId() {
        return memberId;
    }
    public String getName() {
        return name;
    }
    public int getAge() {
        return age;
    }
    public LocalDateTime getCheckInTime() {
        return CheckInTime;
    }
    public LocalDateTime getCheckOutTime() {
        return CheckOutTime;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWorkoutType() {return workoutType;}
    public void setWorkoutType(String workoutType) {this.workoutType = workoutType;}
    public Trainer getTrainer() {return trainer;}
    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }
    public void displayMemberInfo() {
        System.out.println("\nMember ID: " + memberId);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Check-In Time: " + (CheckInTime != null ? CheckInTime.toString() : "Not checked in"));
        System.out.println("Check-Out Time: " + (CheckOutTime != null ? CheckOutTime.toString() : "Not checked out\n"));
    }
}
class Trainer {
    private static AtomicInteger idTrainer = new AtomicInteger(1);
    private String tName;
    private int Id;
    private String specialization;
    public static final String SPECIALIZATION_PUSH = "PUSH";
    public static final String SPECIALIZATION_PULL = "PULL";
    public static final String SPECIALIZATION_LEGS = "LEGS";
    public static final String SPECIALIZATION_FULL_BODY = "FULL BODY";
    public Trainer(Integer memberId, String name, String specialization) {
        this.Id = idTrainer.getAndIncrement();
        this.tName = name;
        this.specialization = specialization;
    }
    public String gettName() {
        return tName;
    }
    public void settName(String tName) {
        this.tName = tName;
    }
    public int getId() {
        return Id;
    }
    public void setId(int id) {
        Id = id;
    }
    public String getSpecialization() {
        return specialization;
    }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
    public void displayTrainerInfo() {
        System.out.println("\nID: " + Id);
        System.out.println("Trainer Name: " + tName);
        System.out.println("Specialization: " + specialization+"\n");
    }
}
class Accounts {
    public adminFunctions af = new adminFunctions();
    public Scanner scan = new Scanner(System.in);
    public boolean loop = false;
    private String admin_user = "admin";
    private String admin_pass = "pass";
    private int account_id;
    private String username;
    private String password;
    public Accounts (int account_id, String username, String password) {
        this.account_id = account_id;
        this.username = username;
        this.password = password;
    }
    public String getAdmin_user () {
        return admin_user;
    }
    public String getAdmin_pass() {
        return admin_pass;
    }
    public String getUsername () {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public int getAccountID () {
        return account_id;
    }
    int account_added = 0;
    public int acc_id_assign (int a) {
        account_added += 1;
        account_id = account_added;
        return account_id;
    }
    private static ArrayList <Accounts> accounts_Arr = new ArrayList<Accounts>();
    public ArrayList<Accounts> getAccounts_arr ()
    {
        return accounts_Arr;
    }
    public void addAccount (int account_id,String username, String password) {
        Accounts newAcc = new Accounts(acc_id_assign(account_added),username, password);
        accounts_Arr.add(newAcc);
        System.out.println("Account Added Successfully!");
        af.menuA();
    }
    public int account_size;
    int acc_no = 0;
    String p = "";
    public void removeAccount () {
        int i = 0;
        account_size = accounts_Arr.size();
        if (account_size == 0) {
            System.out.println("No account found\n\n");
            af.menuA();
        }
        else {
            System.out.println("\n\n\tRemove account\n\n"
                    + "  Account ID |   Username");
            System.out.println("---------------------------");
            Accounts accountInfo = accounts_Arr.get(i);
            int index_toRemove;
            for (i = 0; i < account_size; i++) {
                accountInfo = accounts_Arr.get(i);
                System.out.printf("      %-6s |    %-10s\n",
                        accountInfo.getAccountID(),
                        accountInfo.getUsername());
            }
            acc_no = 0;
            p = "";
            do {
                try {
                    System.out.print("\n>>>");
                    p = scan.nextLine();
                    acc_no = Integer.parseInt(p);
                    for (i = 0; i < account_size; i++) {
                        accountInfo = accounts_Arr.get(i);
                        if (acc_no == accountInfo.getAccountID()) {
                            index_toRemove = i;
                            System.out.print("Are you sure to remove account '" + accountInfo.getUsername() + "'?"
                                    + "\n\n[Y] Yes \t [N] No");
                            do {
                                System.out.print("\n>>>");
                                String choice = scan.nextLine();
                                if (choice.equalsIgnoreCase("Y")) {
                                    accounts_Arr.remove(index_toRemove);
                                    System.out.println("Account Removed Successfully!\n\n");
                                    loop = false;
                                    af.menuA();
                                }
                                else if (choice.equalsIgnoreCase("N")) {
                                    System.out.println("Account Removal Canceled\n\n");
                                    loop = false;
                                    af.menuA();
                                }
                                else {
                                    System.out.print("Invalid input, please try again.\n\n");
                                    loop = true;
                                }
                            } while(loop);
                        }
                    }
                    if (i == accounts_Arr.size()){
                        System.out.print("Invalid input, Please choose among the Account ID");
                        loop = false;
                        removeAccount();
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input, please try again.");
                    loop = true;
                }
            } while (loop);
        }
    }
    public String changePass (String newPass) {
        this.password = newPass;
        return password;
    }
}
class CheckInOutEntry {
    private LocalDateTime entryTime;
    private boolean isCheckIn;
    public CheckInOutEntry(LocalDateTime entryTime, boolean isCheckIn) {
        this.entryTime = entryTime;
        this.isCheckIn = isCheckIn;
    }
    public LocalDateTime getEntryTime() {
        return entryTime;
    }
    public boolean isCheckIn() {
        return isCheckIn;
    }
    @Override
    public String toString() {
        return (isCheckIn ? "Check-In" : "Check-Out") + ": " + entryTime;
    }
}
class CheckOutEntry {
    private LocalDateTime checkOutTime;

    public CheckOutEntry(LocalDateTime checkOutTime) {
        this.checkOutTime = checkOutTime;
    }

    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

    @Override
    public String toString() {
        return "Check-Out: " + checkOutTime;
    }
}
class gym{
    public static ArrayList<GymMembers> membersList = new ArrayList<>();
    public static ArrayList<Trainer> trainerList = new ArrayList<>();
}
class CheckInEntry {
    private LocalDateTime checkOutTime;
    private LocalDateTime checkInTime;
    public CheckInEntry(LocalDateTime checkInTime,LocalDateTime checkOutTime) {
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
    }
    public LocalDateTime getCheckInTime() {
        return checkInTime;
    }
    public LocalDateTime getCheckOutTime() {
        return checkOutTime;
    }

}
class FdFunctions{
    public Scanner scan = new Scanner(System.in);
    public void menuFD(){
        while (true) {
            System.out.println("FRONT DESK____________");
            System.out.println("1.Add Member");
            System.out.println("2.Show Member");
            System.out.println("3.Show Trainer");
            System.out.println("4.Member Check-in");
            System.out.println("5.Member Check-out");
            System.out.println("6.Show Log");
            System.out.println("7.Main menu");
            System.out.println("---------------------");
            System.out.print(">>>");
            String choice = scan.next();
            scan.nextLine();
            switch (choice){
                case "1" :System.out.println("--------------------");
                    addMember();
                    System.out.println("--------------------");
                    break;
                case "2" :
                    System.out.println("--------------------");
                    showMembers();
                    System.out.println("---------------------");
                    break;
                case "3" :System.out.println("--------------------");
                    showTrainer();
                    System.out.println("--------------------");
                    break;
                case "4" :System.out.println("--------------------");
                    memberChkin();
                    System.out.println("--------------------");
                    break;
                case "5" :System.out.println("--------------------");
                    memberChkout();
                    System.out.println("--------------------");
                    break;
                case "6" :System.out.println("--------------------");
                    showLog();
                    System.out.println("--------------------");
                    break;
                case "7" :
                    boolean loop;
                    loop = false;
                    do{
                        System.out.println("press [M] to go back to Login/ main menu\n" +
                                "press [E] to exit");
                        String pili =scan.next();
                        if(pili.equalsIgnoreCase("m")){
                            Main meyn = new Main();
                            meyn.main(null);
                        } else if (pili.equalsIgnoreCase("E")) {
                            System.out.println("GOOD BYE");
                            System.exit(0);
                        }else {
                            System.out.println("WRONG INPUT!");
                            loop = true;
                        }}while (loop);
                    break;
                default:
                    System.out.println("invalid choice");
                    menuFD();
            }
        }
    }

    public void addMember() {
        try {
            System.out.print("Enter member name: ");
            String memberName = scan.nextLine();
            System.out.println("Select membership type:");
            System.out.println("1. Walk-In");
            System.out.println("2. Monthly");
            System.out.println("3. Yearly");
            System.out.print("Enter the membership type (1/2/3): ");

            int membershipTypeChoice;
            String membershipType = "Unknown";
            double membershipPrice = 0.0;
            boolean loop=false;
            do {
                loop = false;
                try {
                    membershipTypeChoice = scan.nextInt();
                    scan.nextLine();

                    switch (membershipTypeChoice) {
                        case 1:
                            membershipType = "Walk-In";
                            membershipPrice = 35.0;
                            break;
                        case 2:
                            membershipType = "Monthly";
                            membershipPrice = 400.0;
                            break;
                        case 3:
                            membershipType = "Yearly";
                            membershipPrice = 1000.0;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid membership type (1/2/3).");
                            loop = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid membership type (1/2/3).");
                    scan.nextLine();
                    loop = true;
                }
            } while (loop);


            System.out.print("Enter member age: ");
            int memberAge = scan.nextInt();
            scan.nextLine();
            if(memberAge<=12){
                System.out.println("Not old enough!");
                addMember();
            }
            else {
                GymMembers members = new GymMembers(0, memberName, memberAge,membershipType,membershipPrice);
                members.setMembershipType(membershipType);
                members.setMembershipPrice(membershipPrice);
                gym.membersList.add(members);
                System.out.println("Member added successfully! Member ID: " + members.getMemberId());
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid age.");
            scan.nextLine();
        }
    }

    public void showMembers(){
        System.out.println("All Members:");

        for(GymMembers members : gym.membersList){
            System.out.println("");
            System.out.println("ID: "+members.getMemberId());
            System.out.println("Name: "+members.getName());
            System.out.println("Age: "+members.getAge());
            System.out.println("Membership Type: "+members.getMembershipType());
            System.out.println("");
        }

    }
    public void showTrainer(){
        System.out.println("Trainers Available");
        for (Trainer trainerss : gym.trainerList){trainerss.displayTrainerInfo();}
    }

    public void memberChkin() {

        System.out.println("MEMBERS CHECK IN");

        for (GymMembers member : gym.membersList) {
            member.displayMemberInfo();
        }

        boolean loop = false;
        int memberIdToCheckIn = 0;

        do {
            loop = false;
            try {
                System.out.print("Enter the member ID to check in: ");
                memberIdToCheckIn = scan.nextInt();

            } catch (Exception e) {
                System.out.println("Invalid Input!");
                loop = true;
            }
        } while (loop);
        GymMembers memberToCheckIn = findMemberById(memberIdToCheckIn);

        if (memberToCheckIn != null) {
            if (memberToCheckIn.getCheckInTime() == null || !memberToCheckIn.getCheckInDate().isEqual(LocalDate.now())) {
                memberToCheckIn.checkIn();

                System.out.println("----------------------------------------------------");
                System.out.println("\t\tChoose workout type:");
                System.out.println("----------------------------------------------------");
                System.out.println("\t\t1. PUSH");
                System.out.println("\t\t2. PULL");
                System.out.println("\t\t3. LEGS");
                System.out.println("\t\t4. FULL BODY");
                System.out.println("\t\t type any other number to have no program.");
                System.out.println("----------------------------------------------------");


                int workoutChoice=0;
                do{
                    try {
                        System.out.print(">>>");
                        workoutChoice = scan.nextInt();
                        scan.nextLine();
                        loop=false;
                    } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a number.");
                    scan.nextLine();
                    loop= true;
                    }
                }while (loop);

                String workoutType = switch (workoutChoice) {
                    case 1 -> GymMembers.WORKOUT_TYPE_PUSH;
                    case 2 -> GymMembers.WORKOUT_TYPE_PULL;
                    case 3 -> GymMembers.WORKOUT_TYPE_LEGS;
                    case 4 -> GymMembers.WORKOUT_TYPE_FULL_BODY;
                    default -> "NO PROGRAM";
                };
                memberToCheckIn.setWorkoutType(workoutType);
                Trainer assignedTrainer = findTrainerBySpecialization(workoutType);

                if (assignedTrainer != null) {
                    memberToCheckIn.setTrainer(assignedTrainer);
                    System.out.println("\nYour trainer is [" + assignedTrainer.gettName() + "] for your " + assignedTrainer.getSpecialization() + " Workout.");
                    if ("walk-in".equalsIgnoreCase(memberToCheckIn.getMembershipType())) {
                        System.out.println("You have been charged P35 for this check-in.");
                    }
                } else {
                    System.out.println("\nNo trainer available for your selected workout type,\nor you didn't pick any trainer.\n");
                }
                memberToCheckIn.addCheckInOutEntry(new CheckInOutEntry(LocalDateTime.now(), true));
            }
        }
    }
            public Trainer findTrainerBySpecialization(String specialization) {
                for (Trainer trainer : gym.trainerList) {
                    if (specialization.equalsIgnoreCase(trainer.getSpecialization())) {
                        return trainer;
                    }
                }
                return null;
            }
        public void memberChkout() {
        System.out.println("MEMBER CHECK OUT");
        System.out.println("Members who have checked in:");

        for (GymMembers member : gym.membersList) {
            if (member.getCheckInTime() != null) {
                member.displayMemberInfo();
            }
        }

        System.out.print("Enter the member ID to check out: ");
        int memberIdToCheckOut = scan.nextInt();
        scan.nextLine();
        GymMembers memberToCheckOut = findMemberById(memberIdToCheckOut);
        if (memberToCheckOut != null) {
            if (memberToCheckOut.getCheckInTime() != null) {
                memberToCheckOut.checkOut();
                CheckOutEntry checkOutEntry = new CheckOutEntry(LocalDateTime.now());
                memberToCheckOut.addCheckOutEntry(checkOutEntry);
                System.out.println("You have checked out at: " + checkOutEntry.getCheckOutTime());
            } else {
                System.out.println("Member with ID " + memberIdToCheckOut + " has not checked in.");
            }
        } else {
            System.out.println("Member with ID " + memberIdToCheckOut + " was not found.");
        }
    }

    public void showLog() {
        try {
            System.out.print("Enter the date (yyyy-MM-dd) to display the log: ");
            String dateStr = scan.nextLine();
            LocalDate logDate = LocalDate.parse(dateStr);
            Set<Integer> processedMembers = new HashSet<>();

            System.out.println("Log for " + logDate);

            for (GymMembers member : gym.membersList) {
                boolean hasProcessed = false;
                for (CheckInOutEntry entry : member.getCheckInOutEntries()) {
                    if (entry.getEntryTime().toLocalDate().isEqual(logDate)) {
                        if (!processedMembers.contains(member.getMemberId())) {
                            System.out.println("\nMember ID: " + member.getMemberId());
                            System.out.println("Name: " + member.getName());
                            System.out.println("Membership Type: "+member.getMembershipType());
                            System.out.println("Check-in: " + (entry.isCheckIn() ? entry.getEntryTime() : "null"));
                            for (CheckOutEntry checkOutEntry : member.getCheckOutEntries()) {
                                if (checkOutEntry.getCheckOutTime().toLocalDate().isEqual(logDate)) {
                                    if (!processedMembers.contains(member.getMemberId())) {
                                        System.out.println("Check-out: " + checkOutEntry.getCheckOutTime());
                                    }
                                }
                            }

                            System.out.println("Workout Type: " + member.getWorkoutType());
                            Trainer assignedTrainer = member.getTrainer();
                            if (assignedTrainer != null) {
                                System.out.println("Assigned Trainer: " + assignedTrainer.gettName());
                            } else {
                                System.out.println("No trainer assigned.");
                            }
                            hasProcessed = true;
                            processedMembers.add(member.getMemberId());
                        }
                    }
                }
                if (!hasProcessed) {
                    System.out.println("\nMember ID: " + member.getMemberId());
                    System.out.println("Name: " + member.getName());
                    System.out.println("Check-in: null");
                    System.out.println("Check-out: null");
                }
            }
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use yyyy-MM-dd format.");
        }
    }
    private String getSpecializationForWorkoutType(String workoutType) {

        String SPECIALIZATION_PUSH = "PUSH";
        String SPECIALIZATION_PULL = "PULL";
        String SPECIALIZATION_LEGS = "LEGS";
        String SPECIALIZATION_FULL_BODY = "FULL BODY";
        return switch (workoutType) {
            case "1" -> SPECIALIZATION_PUSH;
            case "2" -> SPECIALIZATION_PULL;
            case "3" -> SPECIALIZATION_LEGS;
            case "4" -> SPECIALIZATION_FULL_BODY;
            default -> "NO PROGRAM";
        };
    }
    public GymMembers findMemberById(int memberId) {
        for (GymMembers member : gym.membersList) {
            if (member.getMemberId() == memberId) {
                return member;
            }
        }
        return null;
    }


}
    class adminFunctions{
        public Scanner scan = new Scanner(System.in);
        public void menuA(){
            Accounts account = new Accounts(0,null,null);
            while (true) {
                System.out.println("\n\nWELCOME!");
                System.out.println("ADMIN________________");
                System.out.println("1.Add new user account\nRemove account\nChange password");
                System.out.println("---------------------");
                System.out.println("2.Add Gym Member");
                System.out.println("3.Show Gym Member");
                System.out.println("4.Rename Gym Member");
                System.out.println("5.Remove Member");
                System.out.println("6.Add Trainer");
                System.out.println("7.Show Trainer");
                System.out.println("8.Rename Trainer");
                System.out.println("9.Remove Trainer");
                System.out.println("0.Logout/Exit");
                System.out.println("---------------------");
                System.out.print(">>>");
                String choice = scan.next();
                scan.nextLine();
                switch (choice){
                    case "0":
                        boolean loop;
                        loop = false;
                        do{
                            System.out.println("press [L] to Logout\n" +
                                    "press [E] to exit");
                            String pili =scan.next();
                            if(pili.equalsIgnoreCase("L")){
                                Main meyn = new Main();
                                meyn.main(null);
                            } else if (pili.equalsIgnoreCase("E")) {
                                System.out.println("GOOD BYE");
                                System.exit(0);
                            }else {
                                System.out.println("WRONG INPUT!");
                                loop = true;
                            }}while (loop);

                        break;
                    case "1":
                        do {
                            loop = false;
                            System.out.print("\n\n[A] Add User account \n[R] Remove account \n[C] Change account password\n[M] Menu Admin"
                                    + "\n>>>");
                            String choicee = scan.nextLine();
                            if (choicee.equalsIgnoreCase("A")) {
                                System.out.print("\n\tAdd new Account");
                                String username, password;
                                do {
                                    loop = false;
                                    System.out.print("\n\nEnter username: ");
                                    username = scan.nextLine();
                                    if (username.contains(" ")) {
                                        System.out.println("Username cannot contain spaces.\nPlease try again");
                                        loop = true;
                                    }
                                    for (int i = 0; i < account.getAccounts_arr().size(); i++) {
                                        Accounts accountInfo = account.getAccounts_arr().get(i);
                                        if (username.equals(accountInfo.getUsername())) {
                                            System.out.printf("'%s' is already taken.\nPlease use a different one"
                                                    , accountInfo.getUsername());
                                            loop = true;
                                        }
                                    }
                                } while (loop);
                                do {
                                    loop = false;
                                    System.out.print("\nEnter password: ");
                                    password = scan.nextLine();
                                    if (password.contains(" ")) {
                                        System.out.println("Password cannot contain spaces.\nPlease try again");
                                        loop = true;
                                    }
                                } while (loop);
                                account.addAccount(account.getAccountID(),username, password);
                            }
                            else if (choicee.equalsIgnoreCase("R")) {
                                account.removeAccount();
                            }
                            else if (choicee.equalsIgnoreCase("C")) {
                                do {
                                    loop = false;
                                    try {
                                        String p = "";
                                        int acc_no, i = 0;
                                        System.out.print("Select account to change password:\n"
                                                + "\n  Account ID |   Username\n");
                                        System.out.println("---------------------------");
                                        for (i = 0; i < account.getAccounts_arr().size(); i++) {
                                            Accounts accountInfo = account.getAccounts_arr().get(i);
                                            System.out.printf("      %-6s |    %-10s\n",
                                                    accountInfo.getAccountID(),
                                                    accountInfo.getUsername());
                                        }
                                        System.out.print("\n>>>");
                                        p = scan.nextLine();
                                        acc_no = Integer.parseInt(p);

                                        for (i = 0; i < account.getAccounts_arr().size(); i++) {
                                            Accounts accountInfo = account.getAccounts_arr().get(i);

                                            if (acc_no == accountInfo.getAccountID()) {
                                                System.out.print("\n\tItem Selected: "
                                                        + "\n\n\tUsername: " + accountInfo.getUsername()
                                                        + "\n\tPassword: " + accountInfo.getPassword());
                                                String newPass;
                                                do {
                                                    loop = false;
                                                    System.out.print("\n\nEnter new password: ");
                                                    newPass = scan.nextLine();
                                                    if (newPass.contains(" ")) {
                                                        System.out.println("Passowrd cannot contain spaces.\nPlease try again\n");
                                                        loop = true; }
                                                    else if (newPass.equals(accountInfo.getPassword())) {
                                                        System.out.println("Password cannot be the same as the current password.\n");
                                                        loop = true;
                                                    }
                                                } while (loop);
                                                do {
                                                    loop = false;
                                                    System.out.printf("Are you sure to change password for account '%s'?"
                                                            , accountInfo.getUsername());
                                                    System.out.print("\n[Y] Yes \t[N] No\n>>>");
                                                    choicee = scan.nextLine();
                                                    if (choicee.equalsIgnoreCase("Y")) {
                                                        account.getAccounts_arr().get(i).changePass(newPass);
                                                        System.out.println("Account password changed successfully.");
                                                        menuA();
                                                    }
                                                    else if (choicee.equalsIgnoreCase("N")) {
                                                        System.out.println("Change password cancelled.");
                                                        menuA();
                                                    }
                                                    else {loop = true;}
                                                } while (loop);

                                            }
                                        }
                                        if (i == account.getAccounts_arr().size()){
                                            System.out.print("Invalid input, Please choose among the Account ID\n\n");
                                            loop = true; }

                                    } catch (Exception e) {
                                        System.out.println("Invalid input, please try again.");
                                        loop = true;

                                    }
                                } while (loop);
                            } else if (choicee.equalsIgnoreCase("M")) {
                                menuA();
                                System.out.println("\n\n");
                            } else {
                                System.out.println("Invalid input, please try again");
                                loop = true;
                            }
                        } while (loop);
                        break;
                    case "2" :System.out.println("--------------------");
                        addMember();
                        System.out.println("--------------------");
                        break;
                    case "3" :
                        System.out.println("--------------------");
                        showMembers();
                        System.out.println("---------------------");
                        break;
                    case "4" :System.out.println("--------------------");
                        renameMember();
                        System.out.println("--------------------");
                        break;
                    case "5" :System.out.println("--------------------");
                        removeMember();
                        System.out.println("--------------------");
                        break;
                    case "6" :System.out.println("--------------------");
                        addTrainer();
                        System.out.println("--------------------");
                        break;
                    case "7" :System.out.println("--------------------");
                        showTrainer();
                        System.out.println("--------------------");
                        break;
                    case "8" :System.out.println("--------------------");
                        renameTrainer();
                        System.out.println("--------------------");
                        break;
                    case "9" :System.out.println("--------------------");
                        removeTrainer();
                        System.out.println("--------------------");
                        break;
                    default:
                        System.out.println("invalid choice");

                }
            }
        }
        public void addMember() {
            System.out.print("Enter Gym member's name: ");
            String memberName = scan.nextLine();

            boolean loop = false;
            System.out.println("Select membership type:");
            System.out.println("1. Walk-In");
            System.out.println("2. Monthly");
            System.out.println("3. Yearly");
            System.out.print("Enter the membership type (1/2/3): ");

            int membershipTypeChoice;
            String membershipType = "Unknown";
            double membershipPrice = 0.0;

            do {
                loop = false;
                try {
                    membershipTypeChoice = scan.nextInt();
                    scan.nextLine();

                    switch (membershipTypeChoice) {
                        case 1:
                            membershipType = "Walk-In";
                            membershipPrice = 35.0;
                            break;
                        case 2:
                            membershipType = "Monthly";
                            membershipPrice = 400.0;
                            break;
                        case 3:
                            membershipType = "Yearly";
                            membershipPrice = 1000.0;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select a valid membership type (1/2/3).");
                            loop = true;
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid membership type (1/2/3).");
                    scan.nextLine();
                    loop = true;
                }
            } while (loop);

            do {
                try {
                System.out.print("Enter member age: ");
                String p = scan.nextLine();
                int memberAge = Integer.parseInt(p);

                    if (memberAge <= 12) {
                        System.out.println("Not old enough!");
                        loop=true;
                    } else {
                        GymMembers members = new GymMembers(0, memberName, memberAge,membershipType,membershipPrice);
                        gym.membersList.add(members);
                        System.out.println("Member added successfully! Member ID: " + members.getMemberId());
                        loop = false;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input.");
                    loop= true;
                }
            }while (loop);
        }
        public void showMembers(){
            System.out.println("All Members:");
            for(GymMembers members : gym.membersList){ members.displayMemberInfo();}
        }
        public void renameMember() {
            System.out.println("All Members----------------");
            for (GymMembers member : gym.membersList) {
                member.displayMemberInfo();
            }
            System.out.print("Enter Member ID to rename: ");
            int memberIdToRename = 0;
            try {
                memberIdToRename = scan.nextInt();
                scan.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid member ID.");
                scan.nextLine();
                renameTrainer();
            }
            GymMembers memberToRename = findMemberById(memberIdToRename);
            if (memberToRename != null) {
                System.out.print("Enter the new name for the member: ");
                String newMemberName = scan.nextLine();
                memberToRename.setName(newMemberName);
                System.out.println("Member with ID " + memberIdToRename + " has been renamed to " + newMemberName);
            } else {
                System.out.println("Member with ID " + memberIdToRename + " was not found.");
            removeMember();
            }
        }
        private GymMembers findMemberById(int memberId) {
            for (GymMembers member : gym.membersList) {
                if (member.getMemberId() == memberId) {
                    return member;
                }
            }
            return null;
        }
        public void removeMember() {
            System.out.println("All Members----------------");
            for (GymMembers member : gym.membersList) {
                member.displayMemberInfo();
            }
            int memberIdToRemove = 0;
            boolean loop = true;
            do {
                loop = false;
                try {
                    System.out.print("Enter member ID to remove: ");
                    memberIdToRemove = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid member ID.");
                    scan.nextLine();
                    loop = true;
                }
            }while (loop);
            GymMembers memberToRemove = findMemberById(memberIdToRemove);
            if (memberToRemove != null) {
                gym.membersList.remove(memberToRemove);
                System.out.println("Member with ID " + memberIdToRemove + " has been removed.");
            } else {
                System.out.println("Member with ID " + memberIdToRemove + " was not found.");
            }
        }
        public void addTrainer() {
            System.out.print("Enter trainer name: ");
            String trainerName = scan.nextLine();

            System.out.println("Select the specialization:");
            System.out.println("1. " + Trainer.SPECIALIZATION_PUSH);
            System.out.println("2. " + Trainer.SPECIALIZATION_PULL);
            System.out.println("3. " + Trainer.SPECIALIZATION_LEGS);
            System.out.println("4. " + Trainer.SPECIALIZATION_FULL_BODY);
            System.out.print("Enter the number for the specialization: ");

            int specializationChoice = 0;
            boolean loop = false;

            do {
                try {
                    specializationChoice = scan.nextInt();
                    scan.nextLine();

                    if (specializationChoice >= 1 && specializationChoice <= 4) {
                        loop = false;
                    } else {
                        System.out.println("Invalid choice. Please select a number between 1 and 4.");
                        loop = true;
                    }
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scan.nextLine();
                    loop = true;
                }
            } while (loop);

            String trainerSpec;
            switch (specializationChoice) {
                case 1:
                    trainerSpec = Trainer.SPECIALIZATION_PUSH;
                    break;
                case 2:
                    trainerSpec = Trainer.SPECIALIZATION_PULL;
                    break;
                case 3:
                    trainerSpec = Trainer.SPECIALIZATION_LEGS;
                    break;
                case 4:
                    trainerSpec = Trainer.SPECIALIZATION_FULL_BODY;
                    break;
                default:
                    trainerSpec = "Unknown";
                    break;
            }

            Trainer trainerss = new Trainer(0, trainerName, trainerSpec);
            gym.trainerList.add(trainerss);

            System.out.println("ID: " + trainerss.getId());
            System.out.println(trainerss.gettName() + " has been added as a " + trainerSpec + " trainer.");
        }

        public void showTrainer(){
            System.out.println("Trainers Available:");
            for (Trainer trainerss : gym.trainerList){trainerss.displayTrainerInfo();}
        }
        public void renameTrainer() {
            System.out.println("ALL TRAINERS________________________________________");
            for (Trainer trainer : gym.trainerList) {
                trainer.displayTrainerInfo();
            }

            int trainIDrename = 0;
            boolean loop = false;
            do {
                loop= false;
                try {
                    System.out.println("Enter The ID to rename the Trainer");
                    trainIDrename = scan.nextInt();
                    scan.nextLine();
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input. Please enter a valid trainer ID.");
                    loop= false;
                }

            }while (loop);


            Trainer trainerToRename = findTrainerById(trainIDrename);

            if (trainerToRename != null) {
                System.out.print("Enter the new Name: ");
                String newTrainerName = scan.nextLine();
                trainerToRename.settName(newTrainerName);
                System.out.println("Trainer with ID " + trainIDrename + " has been renamed.");
            } else {
                System.out.println("Trainer with ID " + trainIDrename + " was not found.");
            }
        }
        private Trainer findTrainerById(int trainerId) {
            for (Trainer trainer : gym.trainerList) {
                if (trainer.getId() == trainerId) {
                    return trainer;
                }
            }
            return null;
        }
        public void removeTrainer(){
            System.out.println("ALL TRAINERS________________________________________");
            for (Trainer trainers : gym.trainerList){
                trainers.displayTrainerInfo();
            } boolean loop = false;
            int trainerIDtoRemove=0;

            do {
                loop= false;
                try {
                    System.out.print("Select the Trainer ID to remove: ");
                    trainerIDtoRemove = scan.nextInt();
                    scan.nextLine();
                }catch (Exception e){
                    System.out.println("INVALID INPUT");
                    loop=true;
                }

            }while (loop);

            Trainer trainerToRemove = null;
            for(Trainer trainer : gym.trainerList){
                if( trainer.getId() == trainerIDtoRemove ){
                    trainerToRemove= trainer;
                    break;
                }
            }
            if (trainerToRemove != null ){
                gym.trainerList.remove(trainerToRemove);
                System.out.println("Trainer with ID " + trainerToRemove + " has been removed.");
            } else {
                System.out.println("Trainer with ID " + trainerToRemove + " was not found.");
                removeTrainer();
            }
        }
    }
public class Main {
    public static final Scanner scan = new Scanner(System.in);
    public static Accounts account = new Accounts(0, null, null);
    public static boolean loop;
    public static int account_size = account.getAccounts_arr().size();
    public static void main(String[] args) {
        gym g = new gym();
        login();
    }
    static void login() {
        Accounts admin_acc = new Accounts(0, null, null);
        do {
            System.out.println("\nLogin");
            String login_user, login_pass;
            do {
                loop = false;
                System.out.print("Enter username: ");
                login_user = scan.nextLine();
                if (login_user.contains(" ")) {
                    System.out.println("Username cannot contain spaces.\nPlease try again\n");
                    loop = true;
                }
            } while (loop);
            do {
                loop = false;
                System.out.print("Enter password: ");
                login_pass = scan.nextLine();
                if (login_pass.contains(" ")) {
                    System.out.println("Password cannot contain spaces.\nPlease try again\n");
                    loop = true;
                }
            } while (loop);
            int account_size = account.getAccounts_arr().size();

            if (login_user.equals(admin_acc.getAdmin_user()) & login_pass.equals(admin_acc.getAdmin_pass())) {
                loop = false;
                adminFunctions admn = new adminFunctions();
                admn.menuA();
            } else if (account_size == 0) {
                System.out.println("\nWrong Username or Password, please try again\n\n");
                loop = true;
            } else {
                for (int i = 0; i < account_size; i++) {
                    Accounts accountInfo = account.getAccounts_arr().get(i);
                    if (login_user.equals(accountInfo.getUsername()) & login_pass.equals(accountInfo.getPassword())) {
                        System.out.printf("\nWelcome " + accountInfo.getUsername() + "!\n");
                        FdFunctions fd = new FdFunctions();
                        fd.menuFD();
                    } else if (i == account_size - 1) {
                        System.out.println("Wrong Username or Password, please try again");
                        loop = true;
                        break;
                    }
                }
            }
        } while (loop);
    }
}