package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Manager {

    private static MyList<Martyr> martyrs = new MyList<>(16);
    private Scanner scan;
    private PrintWriter print;
    private File file;
    private Boolean fileLoaded = false;

    public boolean readFile(){
        if (fileLoaded)
            return false;

        try {
            scan = new Scanner(file);
        }catch(FileNotFoundException e) {
            e.getMessage();
            return false;
        }
        scan.nextLine();
        while(scan.hasNext()){
            String newMartyr = scan.nextLine();
            Martyr martyrToAdd = createMartyrObj(newMartyr);
            if(martyrToAdd != null)
                martyrs.add(martyrToAdd);
        }
        System.out.println(martyrs.getCount() + " Martyrs read");
        fileLoaded = true;
        return true;
    }

    public Martyr createMartyrObj(String newMartyr){
        String[] martyrArray = newMartyr.split(",");
        if(martyrArray.length != 5)
            return null;

        String name = martyrArray[0];
        int age;
        if (martyrArray[1] == null || martyrArray[1].isBlank() || martyrArray[1].isEmpty())
            age = -1;
        else{
            try {
                age = parseInt(martyrArray[1]);
            }catch (NumberFormatException e){
                    return null;
            }
        }

        String location = martyrArray[2];

        String date = martyrArray[3];
        String[] martyrDateArray;
        martyrDateArray = date.split("/");
        if(martyrDateArray.length!=3)
            return null;
        int martyrMONTH = Integer.parseInt(martyrDateArray[0]);
        int martyrDAY = Integer.parseInt(martyrDateArray[1]);
        int martyrYEAR = Integer.parseInt(martyrDateArray[2]);

        if      (martyrDAY < 1 || martyrDAY > 31)
            return null;
        else if (martyrMONTH < 1 || martyrMONTH > 12)
            return null;
        else if (martyrYEAR < 1900 || martyrYEAR > 2024)
            return null;
        else if (martyrYEAR == 2024 && martyrMONTH >= 3 && martyrDAY > 9)
            return null;

        char gender = martyrArray[4].toUpperCase().charAt(0);
        if(gender != 'M' && gender != 'F' && gender != 'N')
            return null;

        return new Martyr(name, age, location, date, gender);
    }

    public boolean saveToFile(MyList<Martyr> MartyrsToSave){
        try {
            print = new PrintWriter(file);
        }catch(FileNotFoundException e) {
            e.getMessage();
            return false;
        }

        print.println("Name,Age,Event location,Date of death,Gender");
        int i;
        for (i = 0; i < MartyrsToSave.getCount(); i++) {
            print.println(MartyrsToSave.get(i).getName() + "," + MartyrsToSave.get(i).getAge() + "," +
                MartyrsToSave.get(i).getLocation() + "," + MartyrsToSave.get(i).getDateOfMartyrdom() + "," + MartyrsToSave.get(i).getGender());
        }

        System.out.println(i + " Martyrs saved");
        print.close();
        return true;
    }

    public MyList<Martyr> getNames() {
        return martyrs;
    }
    public void setFile(File file) { this.file = file; }

}