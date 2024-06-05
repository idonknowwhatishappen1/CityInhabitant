import java.io.Serial;
import java.util.Set;
import java.util.HashSet;
import java.io.Serializable;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Set<Inhabitant> inhabitants1 = new HashSet<Inhabitant>();
//        Set<Inhabitant> inhabitants2 = new HashSet<Inhabitant>();
        City frankfurt = new City("Frankfurt");
        frankfurt.addInhabitant("hoang", "08.06.2003", "single");
        frankfurt.addInhabitant("phi", "07.03.2003", "married");
        frankfurt.addInhabitant("bao", "31.12.2003", "single");

        City berlin = new City("Berlin");
        berlin.addInhabitant("tam", "21.03.2003", "Married");
        berlin.addInhabitant("huy", "12.06.2003", "Single");
        berlin.addInhabitant("hieu", "01.01.2003", "Married");

        int unMarriedCountInFrankfurt = 0;
        int unMarriedCountInBerlin = 0;
        for (Inhabitant inhabitant : frankfurt.getInhabitants()) {
            if (inhabitant.getMaritalStatus().equalsIgnoreCase("single")) {
                unMarriedCountInFrankfurt++;
            }
        }
        for (Inhabitant inhabitant : berlin.getInhabitants()) {
            if (inhabitant.getMaritalStatus().equalsIgnoreCase("single")) {
                unMarriedCountInBerlin++;
            }
        }

        System.out.println("Number of inhabitants in Frankfurt: " + frankfurt.getInhabitants().length);
        System.out.println("Number of inhabitants in Berlin: " + berlin.getInhabitants().length);

        System.out.println("Number of unmarried inhabitants in Frankfurt: " + unMarriedCountInFrankfurt);
        System.out.println("Number of unmarried inhabitants in Berlin: " + unMarriedCountInBerlin);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Which city do you want to search for an inhabitant? (Frankfurt/Berlin)");
        String cityName = scanner.nextLine();
        City city = cityName.equals("Frankfurt") ? frankfurt : berlin;

        System.out.println("Choose the method you want to use: 1 -Add Inhabitant, 2 - Search Inhabitant, 3- Get Martial Status)");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if(choice == 1){
            System.out.println("Enter the name of the inhabitant you want to add: ");
            String name = scanner.nextLine();
            System.out.println("Enter the date of birth of the inhabitant you want to add: ");
            String dateOfBirth = scanner.nextLine();
            System.out.println("Enter the marital status of the inhabitant you want to add: ");
            String maritalStatus = scanner.nextLine();
            city.addInhabitant(name, dateOfBirth, maritalStatus);
            System.out.println("Inhabitant added: name: " + name + " date of Birth: " + dateOfBirth + " status: " + maritalStatus);
        } else if(choice == 2){
            System.out.println("Enter the name of the inhabitant you want to search: ");
            String name = scanner.nextLine();
            Inhabitant searchedInhabitant = city.searchInhabitant(name);
            if (searchedInhabitant != null) {
                System.out.println("Inhabitant found: " + searchedInhabitant.getName());
            } else {
                System.out.println("Inhabitant not found");
            }
        } else if(choice == 3) {
            System.out.println("Enter the name of the inhabitant you want to change marital status: ");
            String name = scanner.nextLine();
            Inhabitant searchedInhabitant = city.searchInhabitant(name);
            if (searchedInhabitant != null) {

                String newMaritalStatus = searchedInhabitant.getMaritalStatus();
                System.out.println("Marital status : " + newMaritalStatus);
            } else {
                System.out.println("Inhabitant not found");
            }
        }   else {
            System.out.println("Invalid choice");
        }


//        System.out.println("Enter the name of the inhabitant you want to search: ");
//        Inhabitant searchedInhabitant = city.searchInhabitant(name);
//        if (searchedInhabitant != null) {
//            System.out.println("Inhabitant found: " + searchedInhabitant.getName());
//        } else {
//            System.out.println("Inhabitant not found");
//
//        }
//        city.addInhabitant(name, dateOfBirth, maritalStatus);
//        System.out.println("Inhabitant added: name: " + name + " date of Birth: " + dateOfBirth + " status: " + maritalStatus);

    }
}
