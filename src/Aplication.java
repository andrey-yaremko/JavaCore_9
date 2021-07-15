import java.util.Scanner;
import java.io.ObjectInputStream.GetField;


public class Aplication {

//enum Seasons
    enum Seasons {

        WINTER, SUMMER, SPRING, AUTUMN;

    }
//inserted month information in to enum months
    enum months {

        JANUARY(31, Seasons.WINTER),
        FEBRUARY(29, Seasons.WINTER),
        MARCH(31, Seasons.SPRING),
        APRIL(30, Seasons.SPRING),
        MAY(31, Seasons.SPRING),
        JUNE(30, Seasons.SUMMER),
        JULY(31, Seasons.SUMMER),
        AUGUST(31, Seasons.SUMMER),
        SEPTEMBER(30, Seasons.AUTUMN),
        OCTOBER(31, Seasons.AUTUMN),
        NOVEMBER(30, Seasons.AUTUMN),
        DECEMBER(31, Seasons.WINTER);
    // getters for variable days, s
        public Integer getDays() {
            return days;
        }

        public Seasons getS() {
            return s;
        }

        Integer days;
        Seasons s;
    //months constructor
        months(Integer days, Seasons s) {
            this.days = days;
            this.s = s;
        }
    }
    // сreated to display menu on the console
    static void menu() {

        System.out.println("Натисніть 1 щоб перевірити чи є такий місяць");
        System.out.println("Натисніть 2 щоб вивести всі місяці з такою ж порою року");
        System.out.println("Натисніть 3 щоб вивести всі місяці які мають таку саму кількість днів");
        System.out.println("Натисніть 4 щоб вивести на екран всі місяці які мають меншу кількість днів");
        System.out.println("Натисніть 5 щоб вивести на екран всі місяці які мають більшу кількість днів");
        System.out.println("Натисніть 6 щоб вивести на екран наступну пору року");
        System.out.println("Натисніть 7 щоб вивести на екран попередню пору року");
        System.out.println("Натисніть 8 щоб вивести на екран всі місяці які мають парну кількість днів");
        System.out.println("Натисніть 9 щоб вивести на екран всі місяці які мають непарну кількість днів");
        System.out.println("Натисніть 10 щоб вивести на екран чи введений з консолі місяць має парну кількість днів");


    }

    public static void main(String[] args) throws WrongInputConsoleParametersException {
// created two arrays to have access to data inside the enums
        months[] mas = months.values();
        Seasons[] mass = Seasons.values();
//created a scanner sc for use in reading data from the console
        Scanner sc = new Scanner(System.in);
//cycle while runs the menu in the console
        while (true) {
            menu();
            switch (sc.next()) {
                case "1": {

//in the first case we get the data from the console and check if it equals data in enum months
                    System.out.println("Введіть місяць");
                    sc = new Scanner(System.in);
                    String months = sc.next().toUpperCase();

                    boolean flag = checkForMonth(mas, months);
                    if (!flag) {
                        System.out.println("Такий місяць не існує");
                    }
                    break;
                }

// the second case checks for matches and outputs all data from one group of seasons
                case "2": {

                    System.out.println("Введіть пору року");
                    sc = new Scanner(System.in);
                    String Season = sc.next().toUpperCase();

                    boolean flag = sameSeason(mas, Season);

                    if (!flag) {
                        System.out.println("Така пора року не існує");
                    }
                    break;
                }

//reads data from the console and checks for matches, and displays them.
                case "3": {

                    System.out.println("Введіть кількість днів");
                    sc = new Scanner(System.in);
                    int num = sc.nextInt();

                    boolean flag = sameDaysInMonth(mas, num);
                    if (!flag) {
                        System.out.println("місяць з " + num + " днями не існує");
                    }
                    break;
                }

// reads data from the console and compares them with data from enum, outputs those that have smaller values.
                case "4": {
                    System.out.println("Введіть кількість днів ");
                    sc = new Scanner(System.in);
                    int num = sc.nextInt();

                    boolean flag = lessDays(mas, num);

                    if (!flag) { System.out.println("Місяць з" + num + " днями не існує");
                    } break;
                }

// reads data from the console and compares them with data from enum, outputs those that have bigger values.
                case "5": {
                    System.out.println("Введіть кількість днів");
                    sc = new Scanner(System.in);
                    int num = sc.nextInt();

                    if (num < 28) {
                        throw new WrongInputConsoleParametersException("Місяць з " + num + " днями не існує");
                    }

                    boolean flag = moreDays(mas, num);

                    if (!flag) { System.out.println("Місяць з " + num + " днями не існує");
                    } break;
                }

//reads data from the console and compares them with enums data, outputs next value.
                case "6": {
                    System.out.println("Введіть сезон");
                    sc = new Scanner(System.in);
                    String seasons = sc.next().toUpperCase();

                    boolean flag = nextSeason(mass, seasons);
                    if (flag) {
                        Seasons se2 = Seasons.valueOf(seasons);
                        int ordinal = se2.ordinal();

                        if (se2.ordinal() == (mass.length - 1)) {
                            ordinal = 0;
                            System.out.println(mass[ordinal]);
                        } else {
                            System.out.println(mass[ordinal + 1]);
                        }
                    }
                    if (!flag) {
                        System.out.println("Пора року " + seasons + " Не існує");
                    }
                    break;
                }

//reads data from the console and compares them with enums data, outputs previous value.
                case "7": {
                    System.out.println("Введіть сезон");
                    sc = new Scanner(System.in);
                    String seasons = sc.next().toUpperCase();

                    boolean flag = previousSeason(mass, seasons);
                    if (!flag) {
                        System.out.println("Пора року  " + seasons + " Не існує");
                    }
                    break;
                }

//runs through the array and checks if there is an even amount of data if so it outputs them to the console
                case "8": {
                    for (months m : mas) {
                        if (m.getDays() % 2 == 0) {
                            System.out.println(m);
                            System.out.println("Пора року з парною кількістю днів");
                        }
                    }
                    break;
                }

//runs through the array and checks if there is an not even amount of data if so it outputs them to the console
                case "9": {
                    for (months m : mas) {
                        if (m.getDays() % 2 == 1) {
                            System.out.println(m);
                            System.out.println("Пора року з непарною кількістю днів");
                        }
                    }
                    break;
                }

//reads data from the console if such data exists checks whether there is an even amount of data and displays the result
                case "10": {
                    System.out.println("Введіть місяць");
                    sc = new Scanner(System.in);
                    String month = sc.next().toUpperCase();

                    boolean flag = false;

                    for (months m : mas) {
                        if (m.name().equals(month)) {
                            System.out.println("Такий місяць існує");
                            flag = true;
                        }
                    }
                    if (flag) {

                        months mo = months.valueOf(month);

                        if (mo.getDays() % 2 == 0) {
                            System.out.println("Місяць" + mo + " має парну кількість днів");
                        } else {
                            System.out.println("Місяць " + mo + " має непарну кількість днів");
                        }
                    }
                    if (!flag) {
                        System.out.println("місяць " + month + " не існує");
                    }
                }
            }
        }

    }

//	checks if month exists
    private static boolean checkForMonth(months[] mas, String months) {
        boolean flag = false;

        for (months m : mas) {
            if (m.name().equals(months)) {
                System.out.println("Такий місяць існує");
                flag = true;
            }
        }
        return flag;
    }

//  checks for the presence of a season if it exists displays all months of one season
    private static boolean sameSeason(months[] mas, String Season) {
        boolean flag = false;

        for (months m : mas) {
            if (m.getS().name().equals(Season)) {
                flag = true;
            }
        }
        if (flag) {
            Seasons seasons = Seasons.valueOf(Season);

            for (months se : mas) {
                if (se.getS().name().equals(Season)) {
                    System.out.println(se);
                }
            }
        }
        return flag;
    }

// checks for the same number of days if it matches displays to the console
    private static boolean sameDaysInMonth(months[] mas, int num) {
        boolean flag = false;

        for (months m : mas) {
            if (m.getDays() == num) {
                flag = true;
                System.out.println(m);
            }
        }
        return flag;
    }

//	checks the presence of the month if it exists displays the months with less days
    private static boolean lessDays(months[] mas, int num) {
        boolean flag = false;

        for (months m : mas) {
            if (m.getDays() < num) {
                flag = true;
                System.out.println(m);
            }
        }
        return flag;
    }

//  checks the presence of the month if it exists displays the months with more days
    private static boolean moreDays(months[] mas, int num) {
        boolean flag = false;

        for (months m : mas) {
            if (m.getDays() > num) {
                flag = true;
                System.out.println(m);
            }
        }
        return flag;
    }

//	checks for the presence season if it exists displays the next one.
    private static boolean nextSeason(Seasons[] mass, String seasons) {
        boolean flag = false;

        for (Seasons s : mass) {
            if (s.name().equals(seasons)) {
                System.out.println("Така пора року існує");
                flag = true;
            }
        }
        return flag;
    }

// 	checks for the presence month if it exists displays the next one.
    private static boolean previousSeason(Seasons[] mass, String seasons) {
        boolean flag = false;

        for (Seasons s : mass) {
            if (s.name().equals(seasons)) {
                System.out.println("Така пора року існує");
                flag = true;
            }
        }
        if (flag) {
            Seasons se2 = Seasons.valueOf(seasons);
            int ordinal = se2.ordinal();

            if (se2.ordinal() == (mass.length - 1)) {
                ordinal = 2;
                System.out.println(mass[ordinal]);
            } else if (se2.ordinal() == 0) {
                ordinal = mass.length - 1;
                System.out.println(mass[ordinal]);
            } else {
                System.out.println(mass[ordinal - 1]);
            }
        }
        return flag;
    }
}
