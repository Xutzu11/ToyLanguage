package view;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import cmd.Command;
import controller.Controller;

public class TextMenu {
    private Map < String, Command > commands;

    public TextMenu() { 
        commands = new HashMap <String, Command> (); 
    }

    public void addCommand(Command c) { 
        commands.put(c.getKey(), c);
    }

    public Controller runByName(String s) {
        for (Map.Entry<String, Command> c:commands.entrySet()) {
            if (c.getValue().toString().equals(s)) {
                return c.getValue().returnContr();
            }
        }
        return null;
    }

    public ArrayList < String > getCommands() {
        ArrayList < String > res = new ArrayList<String>();
        for (Map.Entry<String, Command> c:commands.entrySet()) {
            res.add(c.getValue().toString());
        }
        return res;
    }

    private void printMenu() {
        for(Command com : commands.values()){
            String line = String.format("%4s : %s", com.getKey(), com.getDescription());
            System.out.println(line);
        } 
    }   

    public void show() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            printMenu();
            System.out.printf("Input the option: ");
            String key = sc.nextLine();
            Command com = commands.get(key);
            if (com==null) {
                System.out.println("Invalid Option");
                continue; 
            }
            com.execute();
        }
    }
}