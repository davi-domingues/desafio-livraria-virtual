package utils;

import java.util.List;
import java.util.Scanner;

public class ScanManager {
    private Scanner scanner;

    public String readNonEmptyString(String prompt) {
        scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print(prompt);
                String value = scanner.nextLine().trim();
                if (!value.isEmpty()) {
                    return value;
                }
                System.out.println("Valor invalido. Tente novamente.");
            }
        } finally {
            scanner = null;
        }
    }

    public Double readDouble(String prompt) {
        scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print(prompt);
                String value = scanner.nextLine().trim().replace(',', '.');
                try {
                    return Double.parseDouble(value);
                } catch (NumberFormatException e) {
                    System.out.println("Numero invalido. Tente novamente.");
                }
            }
        } finally {
            scanner = null;
        }
    }

    public Integer readInt(String prompt) {
        scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print(prompt);
                String value = scanner.nextLine().trim();
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    System.out.println("Numero invalido. Tente novamente.");
                }
            }
        } finally {
            scanner = null;
        }
    }

    public String readTipo(String prompt) {
        scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print(prompt);
                String value = scanner.nextLine().trim().toLowerCase();
                if (value.equals("impresso") || value.equals("eletronico")) {
                    return value;
                }
                System.out.println("Tipo invalido. Use 'impresso' ou 'eletronico'.");
            }
        } finally {
            scanner = null;
        }
    }

    public List<Integer> readIds(String prompt) {
        scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.print(prompt);
                String value = scanner.nextLine().trim();
                if (value.isEmpty()) {
                    System.out.println("Valor invalido. Tente novamente.");
                    continue;
                }
                String[] parts = value.split(",");
                java.util.List<Integer> ids = new java.util.ArrayList<>();
                boolean valid = true;
                for (String part : parts) {
                    String trimmed = part.trim();
                    if (trimmed.isEmpty()) {
                        valid = false;
                        break;
                    }
                    try {
                        ids.add(Integer.parseInt(trimmed));
                    } catch (NumberFormatException e) {
                        valid = false;
                        break;
                    }
                }
                if (valid && !ids.isEmpty()) {
                    return ids;
                }
                System.out.println("Ids invalidos. Informe numeros separados por virgula.");
            }
        } finally {
            scanner = null;
        }
    }
}
