package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Character, Double> variables = new HashMap<>();

        System.out.print("Введите переменные через запятую (например, x,y,z): ");
        String variableInput = scanner.nextLine();
        String[] variableNames = variableInput.split(",");

        for (String var : variableNames) {
            char variable = var.trim().charAt(0); // Убираем пробелы и получаем первый символ
            System.out.print("Введите значение для переменной " + variable + ": ");
            double value = scanner.nextDouble();
            variables.put(variable, value);
            scanner.nextLine(); // Очистка буфера
        }

        System.out.print("Введите выражение для вычисления: ");
        String expression = scanner.nextLine();

        try {
            double result = ExpressionEvaluator.evaluateExpression(expression, variables);
            System.out.println("Результат: " + result);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
