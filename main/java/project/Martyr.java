package project;

public class Martyr implements Comparable<Martyr> {
    private String name;
    private int age;
    private String location;
    private String dateOfMartyrdom;
    private char gender;

    public Martyr(){
    }

    public Martyr(String name) {
        this.name = name;
    }

    public Martyr(String name, int age, String location, String dateOfMartydom, char gender) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.dateOfMartyrdom = dateOfMartydom;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() { return age; }

    public void setAge(int age) { this.age = age; }

    public String getLocation() { return location; }

    public void setLocation(String location) { this.location = location; }

    public String getDateOfMartyrdom() { return dateOfMartyrdom; }

    public void setDateOfMartyrdom(String dateOfMartyrdom) { this.dateOfMartyrdom = dateOfMartyrdom;}

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(Martyr name) {
        if(this.name.compareTo(name.getName()) == 0 )
            return 0;
        return -1;
    }

    public boolean compareGender(Martyr martyr) {
        return this.gender == martyr.getGender();
    }

    public boolean compareAge(Martyr martyr) {
        return this.gender == martyr.getGender();
    }

    public boolean compareDate(Martyr martyr) {
        return this.dateOfMartyrdom.equals(martyr.getDateOfMartyrdom());
    }

/*
    public int compareGender(MyList<Martyr> martyrs){
        int counter = 0;
        for(int i = 0; i < martyrs.count; i++){
            if(martyrs.get(i).getGender() == this.getGender())
                counter++;
        }
        return counter;
    }

    public int compareAge(MyList<Martyr> martyrs){
        int counter = 0;
        for(int i = 0; i < martyrs.count; i++){
            if(martyrs.get(i).getAge() == this.getAge())
                counter++;
        }
        return counter;
    }

    public int compareDate(MyList<Martyr> martyrs){
        int counter = 0;
        for(int i = 0; i < martyrs.count; i++){
            if(martyrs.get(i).getDateOfMartyrdom().equals(this.getDateOfMartyrdom()))
                counter++;
        }
        return counter;
    }
*/
}
