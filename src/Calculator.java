/*
 * Выполнение тестового задания для Kata Academy.
 *
 * ТЕСТОВАЯ ЗАДАЧА “КАЛЬКУЛЯТОР”
 * Описание:
 *
 * Создай консольное приложение “Калькулятор”.
 * Приложение должно читать из консоли введенные пользователем строки, числа, арифметические операции проводимые
 * между ними и выводить в консоль результат их выполнения.
 * Реализуй класс Main с методом public static String calc(String input).
 * Метод должен принимать строку с арифметическим выражением между двумя числами и возвращать
 * строку с результатом их выполнения. Ты можешь добавлять свои импорты, классы и методы.
 * Добавленные классы не должны иметь модификаторы доступа (public или другие)
 *
 * Требования:
 *
 * Калькулятор умеет выполнять операции сложения, вычитания, умножения и деления
 * с двумя числами: a + b, a - b, a * b, a / b.
 * Данные передаются в одну строку (смотри пример)!
 * Решения, в которых каждое число и арифмитеческая операция передаются с новой строки считаются неверными.
 *
 * Калькулятор умеет работать как с арабскими (1,2,3,4,5…), так и с римскими (I,II,III,IV,V…) числами.
 *
 * Калькулятор должен принимать на вход числа от 1 до 10 включительно, не более.
 * На выходе числа не ограничиваются по величине и могут быть любыми.
 *
 * Калькулятор умеет работать только с целыми числами.
 *
 * Калькулятор умеет работать только с арабскими или римскими цифрами одновременно,
 * при вводе пользователем строки вроде 3 + II калькулятор должен выбросить исключение и прекратить свою работу.
 *
 * При вводе римских чисел, ответ должен быть выведен римскими цифрами,
 * соответственно, при вводе арабских - ответ ожидается арабскими.
 *
 * При вводе пользователем неподходящих чисел приложение выбрасывает исключение и завершает свою работу.
 *
 * При вводе пользователем строки, не соответствующей одной из вышеописанных арифметических операций,
 * приложение выбрасывает исключение и завершает свою работу.
 *
 * Результатом операции деления является целое число, остаток отбрасывается.
 *
 * Результатом работы калькулятора с арабскими числами могут быть отрицательные числа и ноль.
 * Результатом работы калькулятора с римскими числами могут быть только положительные числа,
 * если результат работы меньше единицы, выбрасывается исключение
 *
 */

import java.util.Scanner;


public class Calculator {
    public static void main(String[] args) throws Exception {

        Scanner data = new Scanner(System.in);
        String example = data.nextLine(); // Считываем целиком введённую строку
        String[] symbols = example.split(" "); // Разделяем на элементы

        if (symbols.length != 3) // Проверка на количество элементов
            throw new Exception("Необходимо ввести пример в формате 1 + 2 или I + II");
        if (symbols[1].length() > 1) // На позиции symbols[1] должен быть арифметический знак, т.е. один символ
            throw new Exception("Некорректный знак действия");

        String x = symbols[0]; // Что ввели как "Х"
        String y = symbols[2]; // Что ввели как "Y"
        char action = symbols[1].charAt(0); // Что ввели как арифметический знак

        String regexInt = "\\d+"; // регуляерное выражение для проверки числа
        String regexAveCaesar = "[IXV]*[IXV]"; // регулярное выражение для проверки строки
        /*
         * Далее в блоках if использую || и &&, чтобы не проверять вторую часть условия,
         * если первое уже дало достаточные основания для вывода
         */
        if (x.matches(regexInt) && y.matches(regexInt)) { // если в X и Y лежат целые числа, то:
            int intX = Integer.parseInt(x); // получаем новую Integer переменную с значением Х
            int intY = Integer.parseInt(y); // получаем новую Integer переменную с значением Y
            if ((intX < 1 || intX > 10) || (intY < 1 || intY > 10)) // если число меньше 1 или больше 10, то Exception
                throw new Exception("Числа должны быть в диапозоне от 1 до 10");
            System.out.println(calculate(intX, intY, action)); // вызов функции калькулятора с полученными значениями
        } else if (x.matches(regexAveCaesar) && y.matches(regexAveCaesar)) { // если в Х и Y состоят из символов X/V/I
            try {
                AveCaesar newX = AveCaesar.valueOf(x); // enum для конвертации строки в число
                int intX = newX.getIntView(); // получаем числовое представление
                AveCaesar newY = AveCaesar.valueOf(y);
                int intY = newY.getIntView();
                int result = calculate(intX, intY, action); // получаем числовой результат
                if (result < 1) {
                    System.out.println("В Римской системе нет отрицательных чисел");
                    return;
                }
                System.out.println(intToAveCaesar(result)); // конвертируем числовой ответ в римский
            } catch (Exception e) {
                throw new Exception("Числа должны быть в диапозоне от I до X");
            }
        } else { // если введены не два целых числа или не два римских числа, то
            throw new Exception("Введите либо два целых числа, либо два римских числа");
        }

    }
        /*
         * Функция, которая совершает действие из поля action между подготовленными X и Y
         */
        public static int calculate ( int x, int y, char action) throws Exception {
            // Значения X и Y, к моменту вызова функции, уже подготовлены. Проверяем введённый знак
            if (action == '+') {
                return x + y;
            } else if (action == '-') {
                return x - y;
            } else if (action == '*') {
                return x * y;
            } else if (action == '/') {
                return x / y; // не проверяем деление на 0, т.к. выше есть проверка что числа больше нуля
            } else { // если введён иной символ
                throw new Exception("Можно использовать только + - / *");
            }
        }

        /*
         * Функция для перевода числового представления результата в римскую систему.
         * Обратное представление реализовано через Enum AveCaesar
         */
        public static String intToAveCaesar(int result)  {
            int[] valueList = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
            // symbolList - необходимые элементы для отображения чисел от 1 до 100
            String[] symbolList = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
            StringBuilder answer = new StringBuilder(); // сюда запишем ответ
            for (int i = 0; i < valueList.length; i += 1) { // пройдёмся по списку valueList
                while (result >= valueList[i]) {  // спускаемся по списку от бо'льшего к меньшему
                    result -= valueList[i]; // "забираем" из результата больши'е куски
                    answer.append(symbolList[i]); // записываем в ответ. И так до победного
                }
            }
            return answer.toString(); // получаем заветное число в римской системе
        }
    }
