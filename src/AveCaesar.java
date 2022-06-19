/*
 *  Enum, как один из вариантов представления римских чисел в арабском виде.
 * Обратное представление реализовано функцией intToAveCaesar в теле класса Calculator
 */


public enum AveCaesar {
    I(1),
    II(2),
    III(3),
    IV(4),
    V(5),
    VI(6),
    VII(7),
    VIII(8),
    IX(9),
    X(10);

    final int intView;

    AveCaesar(int intView) { // новый конструктор
        this.intView = intView;
    }

    public int getIntView() { // метод для извлечения числового представления
        return intView;
    }
}
