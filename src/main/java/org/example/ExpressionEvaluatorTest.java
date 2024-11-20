package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {

    private ExpressionEvaluator evaluator;
    private Map<Character, Double> variables;

    @BeforeEach
    void setUp() {
        evaluator = new ExpressionEvaluator();
        variables = new HashMap<>();
        variables.put('x', 1.0); // Пример значения переменной
    }

    @Test
    void testSimpleAddition() {
        String expression = "2 + 3";
        double result = evaluator.evaluateExpression(expression, variables);
        assertEquals(5.0, result);
    }

    @Test
    void testSimpleSubtraction() {
        String expression = "5 - 2";
        double result = evaluator.evaluateExpression(expression, variables);
        assertEquals(3.0, result);
    }

    @Test
    void testSimpleMultiplication() {
        String expression = "4 * 2";
        double result = evaluator.evaluateExpression(expression, variables);
        assertEquals(8.0, result);
    }

    @Test
    void testSimpleDivision() {
        String expression = "8 / 2";
        double result = evaluator.evaluateExpression(expression, variables);
        assertEquals(4.0, result);
    }

    @Test
    void testComplexExpression() {
        String expression = "sin(x) + (2 * 3) - (4 / 2)";
        double result = evaluator.evaluateExpression(expression, variables);
        assertEquals(Math.sin(1.0) + 6.0 - 2.0, result, 0.0001);
    }

    @Test
    void testDivisionByZero() {
        String expression = "1 / 0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluateExpression(expression, variables);
        });
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testUndefinedVariable() {
        String expression = "y + 1"; // Переменная 'y' не определена
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluateExpression(expression, variables);
        });
        assertEquals("Undefined variable: y", exception.getMessage());
    }

    @Test
    void testInvalidCharacter() {
        String expression = "2 + a"; // 'a' не является допустимым символом
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluateExpression(expression, variables);
        });
        assertEquals("Invalid character in expression", exception.getMessage());
    }

    @Test
    void testInvalidExpression() {
        String expression = "(2 + 3"; // Неправильное количество скобок
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            evaluator.evaluateExpression(expression, variables);
        });
        assertEquals("Invalid expression", exception.getMessage());
    }
}
