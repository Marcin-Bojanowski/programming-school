package programming.school.main;

import programming.school.dao.ExerciseDao;
import programming.school.dao.GroupDao;
import programming.school.dao.SolutionsDao;
import programming.school.dao.UserDao;
import programming.school.model.Exercise;
import programming.school.model.Group;
import programming.school.model.Solution;
import programming.school.model.User;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final UserDao USER_DAO = new UserDao();
    private static final ExerciseDao EXERCISE_DAO = new ExerciseDao();
    private static final GroupDao GROUP_DAO = new GroupDao();
    private static final SolutionsDao SOLUTION_DAO = new SolutionsDao();
    private static final String EMAIL_REGEX = "[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,})";
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    private static boolean MAIN_MENU_STATUS;
    private static boolean ADMIN_MENU_STATUS = true;
    private static boolean USER_MANAGEMENT_MENU_STATUS = true;
    private static boolean EXERCISES_MANAGEMENT_MENU_STATUS = true;
    private static boolean GROUP_MANAGEMENT_MENU_STATUS = true;


    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu() {
        MAIN_MENU_STATUS = true;
        while (MAIN_MENU_STATUS) {
            System.out.println("Menu:");
            System.out.println("1-Programy administracyjne");
            System.out.println("2-Programy użytkownika");
            System.out.println("3-Wyjście");

            switch (choice(3)) {
                case 1:
                    adminProgramsMenu();
                    break;
                case 2:
                    userPrograms();
                    break;
                case 3:
                    MAIN_MENU_STATUS = false;
                    break;
            }
        }
    }

    public static void adminProgramsMenu() {
        ADMIN_MENU_STATUS = true;
        while (ADMIN_MENU_STATUS) {
            System.out.println("Menu:");
            System.out.println("1-Zarządzanie użytkownikami");
            System.out.println("2-Zarządzanie zadaniami");
            System.out.println("3-Zarządzanie grupami");
            System.out.println("4-Przypisywanie zadań");
            System.out.println("5-Menu główne");
            System.out.println("6-Zakończ");


            switch (choice(6)) {
                case 1:
                    usersManagementMenu();
                    break;
                case 2:
                    exercisesManagementMenu();
                    break;
                case 3:
                    groupManagementMenu();
                    break;
                case 4:
                    solutionsManagementMenu();
                    break;
                case 5:
                    ADMIN_MENU_STATUS = false;
                    break;
                case 6:
                    ADMIN_MENU_STATUS = false;
                    MAIN_MENU_STATUS = false;
                    break;
            }

        }
    }

    private static void userPrograms() {
        User user = login();
        boolean userMenuStatus = true;
        while (userMenuStatus) {
            System.out.println("Menu:");
            System.out.println("1-Dodaj rozwiązanie");
            System.out.println("2-Przegląd rozwiązań");
            System.out.println("3-Menu główne");
            System.out.println("4-Zakończ");

            switch (choice(4)) {
                case 1:
                    addSolution(user);
                    break;
                case 2:
                    showUserSolutions(user);
                    break;
                case 3:
                    userMenuStatus = false;
                    break;
                case 4:
                    userMenuStatus = false;
                    MAIN_MENU_STATUS = false;
                    break;
            }
        }
    }

    public static void usersManagementMenu() {
        USER_MANAGEMENT_MENU_STATUS = true;
        while (USER_MANAGEMENT_MENU_STATUS) {
            User[] userArray = USER_DAO.findAll();
            for (User us : userArray) {
                System.out.println(us.toString());
            }
            System.out.println("Menu:");
            System.out.println("1-Dodaj użytkownika");
            System.out.println("2-Edytuj użytkownika");
            System.out.println("3-Usuń użytkownika");
            System.out.println("4-Menu główne");
            System.out.println("5-Zakończ");

            switch (choice(5)) {
                case 1:
                    addUser();
                    break;
                case 2:
                    editUser();
                    break;
                case 3:
                    deleteUser();
                    break;
                case 4:
                    USER_MANAGEMENT_MENU_STATUS = false;
                    ADMIN_MENU_STATUS = false;
                    break;
                case 5:
                    ADMIN_MENU_STATUS = false;
                    USER_MANAGEMENT_MENU_STATUS = false;
                    MAIN_MENU_STATUS = false;
                    break;
            }
        }
    }

    public static void exercisesManagementMenu() {
        EXERCISES_MANAGEMENT_MENU_STATUS = true;
        while (EXERCISES_MANAGEMENT_MENU_STATUS) {
            Exercise[] exercises = EXERCISE_DAO.findAll();
            for (Exercise ex : exercises) {
                System.out.println(ex.toString());
            }
            System.out.println("Menu:");
            System.out.println("1-Dodaj zadanie");
            System.out.println("2-Edytuj zadanie");
            System.out.println("3-Usuń zadanie");
            System.out.println("4-Menu główne");
            System.out.println("5-Zakończ");

            switch (choice(5)) {
                case 1:
                    addExericse();
                    break;
                case 2:
                    editExercise();
                    break;
                case 3:
                    deleteExercise();
                    break;
                case 4:
                    EXERCISES_MANAGEMENT_MENU_STATUS = false;
                    ADMIN_MENU_STATUS = false;
                    break;
                case 5:
                    ADMIN_MENU_STATUS = false;
                    EXERCISES_MANAGEMENT_MENU_STATUS = false;
                    MAIN_MENU_STATUS = false;
                    break;
            }
        }
    }

    public static void groupManagementMenu() {
        GROUP_MANAGEMENT_MENU_STATUS = true;
        while (GROUP_MANAGEMENT_MENU_STATUS) {
            Group[] groups = GROUP_DAO.findAll();

            for (Group group : groups) {
                System.out.println(group.toString());
            }
            System.out.println("Menu:");
            System.out.println("1-Dodaj grupę");
            System.out.println("2-Edytuj grupę");
            System.out.println("3-Usuń grupę");
            System.out.println("4-Menu główne");
            System.out.println("5-Zakończ");

            switch (choice(5)) {
                case 1:
                    addGroup();
                    break;
                case 2:
                    editGroup();
                    break;
                case 3:
                    deleteGroup();
                    break;
                case 4:
                    GROUP_MANAGEMENT_MENU_STATUS = false;
                    ADMIN_MENU_STATUS = false;
                    break;
                case 5:
                    ADMIN_MENU_STATUS = false;
                    GROUP_MANAGEMENT_MENU_STATUS = false;
                    MAIN_MENU_STATUS = false;
                    break;
            }
        }
    }

    private static void solutionsManagementMenu() {
        boolean solutionManagementMenuStatus = true;
        while (solutionManagementMenuStatus) {
            System.out.println("Menu:");
            System.out.println("1-Przypisz zadanie do użytkownika");
            System.out.println("2-Przegląd rozwiazań użytkownika");
            System.out.println("3-Menu główne");
            System.out.println("4-Zakończ");

            switch (choice(4)) {
                case 1:
                    exerciseToUser();
                    break;
                case 2:
                    userSolutions();
                    break;
                case 3:
                    solutionManagementMenuStatus = false;
                    ADMIN_MENU_STATUS = false;
                    break;
                case 4:
                    ADMIN_MENU_STATUS = false;
                    solutionManagementMenuStatus = false;
                    MAIN_MENU_STATUS = false;
                    break;
            }
        }
    }

    private static void addSolution(User user) {

        Solution solution = new Solution();
        boolean addSolution = userSolutions(user);;
        while (addSolution) {
            System.out.println("Podaj id rozwiązania które chcesz dodać");
            int id = idCheck();
            if (SOLUTION_DAO.readById(id, user) != null) {
                solution = SOLUTION_DAO.readById(id, user);
                if (solution.getDescription() != null) {
                    System.out.println("Rozwiązanie już istnieje");
                } else {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Wrowadź swoje rozwiązanie");
                    String description = scanner.nextLine();
                    solution.setDescription(description);
                    solution.setUpdated(Timestamp.valueOf(LocalDateTime.now()));
                    SOLUTION_DAO.update(solution);
                    addSolution = false;
                }
            } else {
                System.out.println("Brak rozwiązania o podanym id w bazie");
            }

        }

    }

    private static void exerciseToUser() {
        boolean exerciseToUser = true;
        boolean exerciseToUserIf = true;
        showUsers();
        Scanner scanner = new Scanner(System.in);
        while (exerciseToUser) {
            System.out.println("Podaj id użytkownika");
            int idUser = idCheck();
            if (USER_DAO.readById(idUser) != null) {
                while (exerciseToUserIf) {
                    showExercises();
                    System.out.println("Podaj id zadania");
                    int idExercise = idCheck();
                    if (EXERCISE_DAO.readById(idExercise) != null) {
                        Solution solution = new Solution(idExercise, idUser);
                        SOLUTION_DAO.create(solution);
                        exerciseToUser = false;
                        exerciseToUserIf = false;
                    } else {
                        System.out.println("Brak zadania w bazie danych");
                    }
                }
            } else {
                System.out.println("Brak użytkowniak w bazie danych");
            }
        }


    }

    private static void showExercises() {
        Exercise[] exercises = EXERCISE_DAO.findAll();
        for (Exercise ex : exercises) {
            System.out.println(ex.toString());
        }
    }

    private static void showUsers() {
        User[] userArray = USER_DAO.findAll();
        for (User us : userArray) {
            System.out.println(us.toString());
        }
    }

    private static void userSolutions() {
        showUsers();
        System.out.println("Podaj id użytkownika");
        Scanner scanner = new Scanner(System.in);
        int idUser = idCheck();
        Solution[] solutionArray = SOLUTION_DAO.findAll(idUser);
        if (solutionArray.length == 0) {
            System.out.println("Brak rozwiązań");
        } else {
            for (Solution sol : solutionArray) {
                System.out.println(sol.toString()+EXERCISE_DAO.readById(sol.getExerciseId()).toString());
            }
        }

    }

    private static boolean userSolutions(User user) {
        boolean flag = true;
        Solution[] solutionArray = SOLUTION_DAO.findAll(user.getId());
        if (solutionArray.length == 0) {
            System.out.println("Brak zadań do rozwiązania");
            return false;
        } else {
            for (Solution sol : solutionArray) {
                if (sol.getDescription() == null) {
                    System.out.println(sol.toString()+EXERCISE_DAO.readById(sol.getExerciseId()).toString());
                    flag = false;
                }
            }
        }
        if (flag) {
            System.out.println("Brak zadań do rozwiązania");
            return false;
        }
        return true;
    }

    private static void showUserSolutions(User user) {
        boolean flag = true;
        Solution[] solutionArray = SOLUTION_DAO.findAll(user.getId());
        if (solutionArray.length == 0) {
            System.out.println("Brak rozwiązań");

        } else {
            for (Solution sol : solutionArray) {
                if (sol.getDescription() != null) {
                    System.out.println(sol.toString()+EXERCISE_DAO.readById(sol.getExerciseId()));

                    flag = false;
                }
            }
        }
        if (flag) {
            System.out.println("Brak rozwiązań");
        }


    }

    public static void addGroup() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę grupy");
        String name = scanner.nextLine();
        Group group = new Group(name);
        GROUP_DAO.create(group);
    }

    public static void editGroup() {
        boolean editGroupStatus = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id grupy");
        int id = idCheck();
        if (GROUP_DAO.readById(id) != null) {
            Group group = GROUP_DAO.readById(id);
            while (editGroupStatus) {
                System.out.println("Menu:");
                System.out.println("1-Edycja nazwy");
                System.out.println("2-Zakończ edycję");
                System.out.println("3-Zakończ");
                switch (choice(3)) {
                    case 1:
                        System.out.println("Podaj nową nazwę grupy");
                        String name = scanner.nextLine();
                        group.setName(name);
                        GROUP_DAO.update(group);
                        break;
                    case 2:
                        editGroupStatus = false;
                        break;
                    case 3:
                        editGroupStatus = false;
                        ADMIN_MENU_STATUS = false;
                        GROUP_MANAGEMENT_MENU_STATUS = false;
                        MAIN_MENU_STATUS = false;
                        break;
                }
            }
        } else {
            System.out.println("Brak grupy w bazie danych");
        }
    }

    public static void deleteGroup() {
        System.out.println("Podaj id grupy");
        int id = idCheck();
        if (GROUP_DAO.readById(id) != null) {
            GROUP_DAO.delete(GROUP_DAO.readById(id));
        } else {
            System.out.println("Brak grupy w bazie danych");
        }
    }

    public static void addExericse() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę zadania");
        String title = scanner.nextLine();
        System.out.println("Podaj opis zadania");
        String description = scanner.nextLine();
        Exercise exercise = new Exercise(title, description);

        EXERCISE_DAO.create(exercise);
    }

    public static void editExercise() {
        boolean editExerciseStatus = true;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj id zadania");
        int id = idCheck();
        if (EXERCISE_DAO.readById(id) != null) {
            Exercise exercise = EXERCISE_DAO.readById(id);
            while (editExerciseStatus) {
                System.out.println("Menu:");
                System.out.println("1-Edycja nazwy");
                System.out.println("2-Edycja opisu");
                System.out.println("3-Zakończ edycję");
                System.out.println("4-Zakończ");
                switch (choice(4)) {
                    case 1:
                        System.out.println("Podaj nowy tytuł zadania");
                        String title = scanner.nextLine();
                        exercise.setTitle(title);
                        EXERCISE_DAO.update(exercise);
                        break;
                    case 2:
                        System.out.println("Podaj nowy opis zadania");
                        String description = scanner.nextLine();
                        exercise.setDescription(description);
                        EXERCISE_DAO.update(exercise);
                        break;
                    case 3:
                        editExerciseStatus = false;
                        break;
                    case 4:
                        editExerciseStatus = false;
                        ADMIN_MENU_STATUS = false;
                        EXERCISES_MANAGEMENT_MENU_STATUS = false;
                        MAIN_MENU_STATUS = false;
                        break;
                }
            }
        } else {
            System.out.println("Brak zadania w bazie danych");
        }
    }

    public static void addUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj nazwę użytkownika");
        String name = scanner.nextLine();
        System.out.println(name);
        String email = emailCheck();
        String password = passwordCheck();
        User user = new User(name, email, password);
        USER_DAO.create(user);
    }

    public static void editUser() {
        boolean EDIT_USER = true;
        boolean EDIT_USER_STATUS = true;
        Scanner scanner = new Scanner(System.in);
        while (EDIT_USER) {
            showUsers();
            System.out.println("Podaj id użytkownika");
            int id = idCheck();
            if (USER_DAO.readById(id) != null) {
                User user = USER_DAO.readById(id);
                while (EDIT_USER_STATUS) {
                    System.out.println("Menu:");
                    System.out.println("1-Edycja nazwy");
                    System.out.println("2-Edycja email");
                    System.out.println("3-Edycja hasła");
                    System.out.println("4-Zakończ edycję");
                    System.out.println("5-Zakończ");
                    switch (choice(5)) {
                        case 1:
                            System.out.println("Podaj nazwę użytkownika");
                            String name = scanner.nextLine();
                            user.setName(name);
                            USER_DAO.update(user);
                            break;
                        case 2:
                            String email = emailCheck();
                            user.setEmail(email);
                            USER_DAO.update(user);
                            break;
                        case 3:
                            String password = passwordCheck();
                            user.setPassword(password);
                            USER_DAO.update(user);
                            break;
                        case 4:
                            EDIT_USER_STATUS = false;
                            EDIT_USER = false;
                            break;
                        case 5:
                            EDIT_USER_STATUS = false;
                            ADMIN_MENU_STATUS = false;
                            USER_MANAGEMENT_MENU_STATUS = false;
                            MAIN_MENU_STATUS = false;
                            EDIT_USER = false;
                            break;

                    }
                }
            } else {
                System.out.println("Brak użytkownika w bazie danych");
            }
        }


    }

    public static int choice(int menuLenght) {
        boolean status = true;
        int choice = 0;
        Scanner scanner = new Scanner(System.in);
        while (status) {
            while (!scanner.hasNextInt()) {
                String input = scanner.nextLine();
                System.out.println(input + " to błedne dane");
            }
            choice = scanner.nextInt();
            if (choice > menuLenght || choice < 1) {
                System.out.println("Nie ma takiej opcji w menu");
            } else {
                status = false;

            }
        }
        return choice;
    }

    public static String emailCheck() {
        Scanner scanner = new Scanner(System.in);
        String email = "";
        boolean emailStatus = true;
        while (emailStatus) {
            System.out.println("Podaj email użytkownika");
            email = scanner.nextLine();
            if (Pattern.matches(EMAIL_REGEX, email)) {
                if (UserDao.authorizationByEmail(email)) {
                    emailStatus = false;
                } else {
                    System.out.println("Podany email jest już zajęty");
                }

            } else {
                System.out.println(email + " to niepoprawny email. Podaj poprawny email");
            }
        }
        return email;
    }

    public static String passwordCheck() {
        Scanner scanner = new Scanner(System.in);
        String password = "";
        boolean passwordStatus = true;
        while (passwordStatus) {
            System.out.println("Podaj hasło użytkownika (Miniumm 8 znaków, co najmniej jedna mała i duża litera, cyfra, znak specjalny");
            password = scanner.nextLine();
            if (Pattern.matches(PASSWORD_REGEX, password)) {
                passwordStatus = false;
            } else {
                System.out.println(password + " to niepoprawne hasło. Podaj poprawne hasło");
            }
        }
        return password;
    }

    public static int idCheck() {
        Scanner scanner = new Scanner(System.in);
        while (!scanner.hasNextInt()) {
            System.out.println("Podaj id:");
            String input = scanner.nextLine();
            System.out.println(input + " id musi być liczbą");
        }
        return scanner.nextInt();
    }

    public static void deleteUser() {
        System.out.println("Podaj id użytkownika");
        int id = idCheck();
        if (USER_DAO.readById(id) != null) {
            USER_DAO.delete(USER_DAO.readById(id));
        } else {
            System.out.println("Brak użytkownika w bazie danych");
        }
    }

    public static void deleteExercise() {
        System.out.println("Podaj id zadania");
        int id = idCheck();
        if (EXERCISE_DAO.readById(id) != null) {
            EXERCISE_DAO.delete(EXERCISE_DAO.readById(id));
        } else {
            System.out.println("Brak zadania w bazie danych");
        }
    }

    public static User login() {
        boolean loginStatus = true;
        System.out.println("Zaloguj się:");
        Scanner scanner = new Scanner(System.in);
        User user = new User();
        while (loginStatus) {
            System.out.println("Podaj email użytkownika:");
            String email = scanner.nextLine();
            System.out.println("Podaj hasło użytkownika");
            String password = scanner.nextLine();
            if (!USER_DAO.authorization(email, password)) {
                user = USER_DAO.read(email, password);
                loginStatus = false;
            } else {
                System.out.println("Błędne dane logowania");
            }
        }
        return user;

    }

}