import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

class NoteApp {

    // declaring HashMap for notes
    static HashMap<String, String> noteList = new HashMap<>();

    // declaring scanner object
    static Scanner scan = new Scanner(System.in);

    // declaring a static key variable named 'Date' to store date inputs and 'Note'
    // to store ote inputs
    static String date;
    static String note;

    // defining functions
    static void loadData() {
        try {
            File file = new File("noteListData.txt");
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] linePart = line.split("=");
                date = linePart[0];
                note = linePart[1];
                noteList.put(date, note);
            }
            reader.close();
        } catch (Exception e) {
            // can handle exception
        }
    }

    static void saveData() {
        try {
            FileWriter writer = new FileWriter("noteListData.txt");
            for (Map.Entry<String, String> entry : noteList.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {

        }
    }

    static void dateInput() {
        System.out.println("|                                             |");
        System.out.println("|  IMP: Date should be in following format    |");
        System.out.println("|                   DD/MM/YY                  |");
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
        System.out.println("|                                             |");
        System.out.print("    Enter the Date: ");
        date = scan.next();
        scan.nextLine();
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
    }

    static void noteInput() {
        System.out.println("|                                             |");
        System.out.println("|  IMP: Don't hit Enter unless you finished   |");
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
        System.out.println("|                                             |");
        System.out.print("    Enter the Note: ");
        note = scan.nextLine();
        System.out.println("|                                             |");
        System.out.println("-----------------------------------------------");
    }

    static void viewSingleNote() {
        if (noteList.isEmpty()) {
            System.out.println("|                                             |");
            System.out.println("|   There are no Notes to view!               |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------\n");
            try {
                Thread.sleep(2000); // Pause for 1 second
            } catch (InterruptedException e) {
            }
        } else {
            dateInput();
            if (!noteList.containsKey(date)) {
                System.out.println("|                                             |");
                System.out.println("|          Date and Note not Found!           |");
                System.out.println("|                                             |");
                System.out.println("-----------------------------------------------");
            } else {
                System.out.println("");
                System.out.println("    " + date + "  " + noteList.get(date));
                System.out.println("");
                System.out.println("-----------------------------------------------");
            }
        }
        scan.nextLine();
    }

    static void viewAllNotes() {
        if (noteList.isEmpty()) {
            System.out.println("|                                             |");
            System.out.println("|   There are no Notes to view!               |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------\n");
            try {
                Thread.sleep(2000); // Pause for 1 second
            } catch (InterruptedException e) {
            }
        } else {
            System.out.println("|                                             |");
            System.out.println("|   Notes:                                    |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
            for (Map.Entry<String, String> entry : noteList.entrySet()) {
                System.out.println("  " + entry.getKey() + "   " + entry.getValue());
            }
            System.out.println("");
        }
        scan.nextLine();
    }

    static void addNote() {
        System.out.println("|                                             |");
        System.out.println("|               ADDING NEW NOTE               |");
        dateInput();
        String dateFromat = "^\\d{2}/\\d{2}/\\d{2}$";
        Pattern pattern = Pattern.compile(dateFromat);
        Matcher matcher = pattern.matcher(date);
        if(!matcher.matches()){
            System.out.println("|                                             |");
            System.out.println("|     Date Input dosen't match the Format     |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
        }
        else if (noteList.containsKey(date)) {
            System.out.println("|                                             |");
            System.out.println("|        Note at Date already exist!          |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------"); 
        } else {
            noteInput();
            noteList.put(date, note);
            System.out.println("|                                             |");
            System.out.println("|          Note Added Successfully!           |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
        }
        try {
            Thread.sleep(2000); // Pause for 1 second
        } catch (InterruptedException e) {
        }
        scan.nextLine();
    }

    static void editNote() {
        System.out.println("|                                             |");
        System.out.println("|               EDITING A NOTE                |");
        dateInput();
        if (!noteList.containsKey(date)) {
            System.out.println("|                                             |");
            System.out.println("|          Date and Note not Found!           |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
        } else {
            noteInput();
            noteList.put(date, note);
            System.out.println("|                                             |");
            System.out.println("|          Note Edited Successfully!          |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
        }

        try {
            Thread.sleep(2000); // Pause for 1 second
        } catch (InterruptedException e) {
        }
        scan.nextLine();
    }

    static void deleteNote() {
        System.out.println("|                                             |");
        System.out.println("|               DELETING A NOTE               |");
        dateInput();
        if (!noteList.containsKey(date)) {
            System.out.println("|                                             |");
            System.out.println("|          Date and Note not Found!           |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
        } else {
            noteList.remove(date);
            System.out.println("|                                             |");
            System.out.println("|          Note Deleted Successfully!         |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
        }
        try {
            Thread.sleep(2000); // Pause for 1 second
        } catch (InterruptedException e) {
        }

        scan.nextLine();
    }

    public static void main(String[] args) {
        while (true) {
            loadData();
            System.out.println("-----------------------------------------------");
            System.out.println("|                                             |");
            System.out.println("|                 NOTE APP                    |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
            System.out.println("|                                             |");
            System.out.println("|   1) View All Notes                         |");
            System.out.println("|   2) View Single Note                       |");
            System.out.println("|   3) Add Note                               |");
            System.out.println("|   4) Edit Note                              |");
            System.out.println("|   5) Delete Note                            |");
            System.out.println("|   6) Exit                                   |");
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");
            try {
                Thread.sleep(1000); // Pause for 1 second
            } catch (InterruptedException e) {
            }
            System.out.println("|                                             |");
            System.out.print("    Enter your choice: ");
            int choice = scan.nextInt();
            System.out.println("|                                             |");
            System.out.println("-----------------------------------------------");

            switch (choice) {
                case 1:
                    viewAllNotes();
                    break;
                case 2:
                    viewSingleNote();
                    break;
                case 3:
                    addNote();
                    saveData();
                    break;
                case 4:
                    editNote();
                    saveData();
                    break;
                case 5:
                    deleteNote();
                    saveData();
                    break;
                case 6:
                    System.out.println("Exiting Program......");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
            scan.nextLine();

        }
    }
}