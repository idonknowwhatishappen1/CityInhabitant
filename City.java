import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

public class City implements Serializable {
    public String name;
    public Set<Inhabitant> inhabitants = new HashSet<>();

    public City(String name) {
        this.name = name;

    }

    public Inhabitant searchInhabitant(String name) {
        for (Inhabitant inhabitant : inhabitants) {
            if (inhabitant.getName().equals(name)) {
                return inhabitant;
            }
        }
        return null;
    }

    public void addInhabitant(String name, String dateOfBirth, String maritalStatus) {
        Inhabitant newInhabitant = new Inhabitant(name, dateOfBirth, maritalStatus);
        inhabitants.add(newInhabitant);
    }

    public String getMaritalStatus(String name){
        for(Inhabitant inhabitant : inhabitants){
            if(inhabitant.getMaritalStatus().equals("Single")){
                return inhabitant.getMaritalStatus();
            }
        }
        return "No inhabitant found";
    }

    public String getBirthOfAllInhabitants(){
        String res="Date of birth of all inhabitants:\n";
        for(Inhabitant inhabitant : inhabitants){
            res+=inhabitant.getDateOfBirth()+"\n";
        }
        return res;
    }
    public Inhabitant[] getInhabitants() {
        return inhabitants.toArray(new Inhabitant[0]);

    }
}