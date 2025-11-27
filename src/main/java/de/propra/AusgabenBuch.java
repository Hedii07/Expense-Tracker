package de.propra;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AusgabenBuch {

    private final Scanner stdin = new Scanner(System.in);


    Map<String, List<Double>> geschaefte = new HashMap<>();

    Map<String, List<Double>> kategorien = new HashMap<>();


    private void report(String[] parts) {
      if (parts.length != 2 || !parts[0].equals("report")) {
        invalid();
        return;
      }

      if (parts[1].equals("shop")) {
        geschaefte.forEach((key, value) ->
            System.out.println(key + ": " + value)
        );
      } else if (parts[1].equals("category")) {
        kategorien.forEach((key, value) ->
            System.out.println(key + ": " + value)
        );
      } else {
        invalid();
      }
    }

    private void add(String[] parts) {

      if (parts.length != 4) {
        invalid();
        return;
      }

      String shop = parts[1];
      String category = parts[2];
      double betrag;

      try {
        betrag = Double.parseDouble(parts[3]);
      } catch (NumberFormatException e) {
        invalid();
        return;
      }

      geschaefte.putIfAbsent(shop, new ArrayList<>());
      geschaefte.get(shop).add(betrag);

      kategorien.putIfAbsent(category, new ArrayList<>());
      kategorien.get(category).add(betrag);
    }

    private void exit() {
      System.out.println("bye");
      stdin.close();
    }

    public void run() {
      while (true) {
        boolean keepRunning = readAndProcess();
        if (!keepRunning) {
          break;
        }
      }
    }

    private void invalid() {
      System.out.println("Ungültige Eingabe.");
    }

    private boolean readAndProcess() {
      String line = stdin.nextLine();
      String[] parts = line.split(" ");

      switch (parts[0]) {
        case "add":
          add(parts);
          return true;

        case "report":
          report(parts);
          return true;

        case "exit":
          exit();
          return false;

        default:
          invalid();
          return true;
      }
    }

    public static void main(String[] args) {
      new AusgabenBuch().run();
    }
}

