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
    X(10),
    XI(11),
    XII(12),
    XIII(13),
    XIV(14),
    XV(15),
    XVI(16),
    XVII(17),
    XVIII(18),
    XIX(19),
    XX(20);

    final int intView;

    AveCaesar(int intView) { // новый конструктор
        this.intView = intView;
    }

    public int getIntView() { // метод для извлечения числового представления
        return intView;
    }
}
