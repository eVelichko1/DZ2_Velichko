package ru.otus;

import ru.otus.animals.Animal;
import ru.otus.animals.Color;
import ru.otus.factory.AnimalFactory;
import ru.otus.factory.AnimalType;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<>();
        AnimalFactory factory = new AnimalFactory();

        Command currentCommand;
        do {
            currentCommand = askForCommand();
            if (currentCommand == Command.LIST) {
                if (animals.isEmpty()) {
                    System.out.println("Список пуст");
                }
                for (Animal animal: animals) {
                    System.out.println(animal);
                }
            } else if (currentCommand == Command.ADD) {
                AnimalType animalType = askForAnimalType();
                Animal animal = factory.create(animalType);
                animal.setName(askForName());
                animal.setAge(askForAge());
                animal.setWeight(askForWeight());
                animal.setColor(askForColor());
                animals.add(animal);
                animal.say();

            }

        } while (currentCommand != Command.EXIT);
    }

    private static Command askForCommand() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введена неверная команда, попробуйте ещё раз");
            }
            System.out.printf("Введите одну из команд (%s):", String.join("/", Command.VALUES));
            input = scanner.next();
        } while (Command.doesNotContain(input));
        return Command.fromString(input);
    }

    private static AnimalType askForAnimalType() {
        String input = null;
        do {
            if (input != null) {
                System.out.println("Введен неверный тип, попробуйте ещё раз");
            }
            System.out.printf("Введите тип животного (%s):", String.join("/", AnimalType.VALUES));
            input = scanner.next();
        } while (AnimalType.doesNotContain(input));
        return AnimalType.fromString(input);
    }

    private static String askForName() {
        String name;
        while (true) {
            System.out.print("Введите имя животного: ");
            name = scanner.next().trim();

            if (name.isEmpty()) {
                System.out.println("Имя не может быть пустым. Попробуйте ещё раз.");
                continue;
            }

            if (!name.matches("[A-Za-zА-Яа-яЁё\\s]+")) {
                System.out.println("Имя должно содержать только буквы. Попробуйте ещё раз.");
                continue;
            }
            break;
        }
        return name;
    }

    private static int askForAge() {
        int input = 0;
        do {
            System.out.println("Введите возраст животного: ");
            try {
                input = Integer.parseInt(scanner.next());
                if (input <= 0) {
                    System.out.println("Введен неверный возраст, попробуйте ещё раз");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом, попробуйте ещё раз");
            }
        } while (input <= 0);
        return input;
    }

    private static int askForWeight() {
        int weight = 0;
        while (true) {
            System.out.print("Введите вес животного (г): ");
            String line = scanner.next().trim();   // читаем всю строку, чтобы «перевод строки» не «переходил» дальше
            try {
                weight = Integer.parseInt(line);
                if (weight <= 0) {
                    System.out.println("Вес должен быть положительным числом. Попробуйте ещё раз.");
                    continue;
                }
                break;   // всё ок
            } catch (NumberFormatException e) {
                System.out.println("Ввод не является числом. Попробуйте ещё раз.");
            }
        }
        return weight;
    }

    private static Color askForColor() {
        // Список доступных вариантов в приглашении
        StringBuilder prompt = new StringBuilder("Введите цвет животного (доступные варианты: ");
        for (int i = 0; i < Color.values().length; i++) {
            prompt.append(Color.values()[i].getValue());
            if (i < Color.values().length - 1) {
                prompt.append(", ");
            }
        }
        prompt.append("): ");

        while (true) {
            System.out.print(prompt.toString());
            String userInput = scanner.next().trim();

            Color chosen = Color.fromString(userInput);
            if (chosen == null) {
                System.out.println("Неверный цвет. Попробуйте ещё раз.");
                continue;
            }
            return chosen;
        }
    }

    }
