package homework2;

import java.util.Arrays;

public class Main {

    private static final int N_WORKING_DAYS = 5;
    private static final int WORKING_HOURS_PER_DAY = 8;

    public static void main(String[] args) {
        /*
        1. Напишите метод, на вход которого подаётся двумерный строковый массив размером 4х4,
        при подаче массива другого размера необходимо бросить исключение MyArraySizeException.
         */

        String[][] str11 = new String[][] {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };
        String[][] str12 = new String[][] {
                {"1", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };
        String[][] str13 = new String[][] {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"}
        };

        try {
            myArraySizeExceptionThrower(str11);
            System.out.println("11 work!");
        } catch (MyArraySizeException e) {
            System.out.println("11 not work!");
            e.getMessage();
        }

        try {
            myArraySizeExceptionThrower(str12);
            System.out.println("12 work!");
        } catch (MyArraySizeException e) {
            System.out.println("12 not work!");
            e.getMessage();
        }

        try {
            myArraySizeExceptionThrower(str13);
            System.out.println("13 work!");
        } catch (MyArraySizeException e) {
            System.out.println("13 not work!");
            e.getMessage();
        }

        /*
        2. Далее метод должен пройтись по всем элементам массива, преобразовать в int,
        и просуммировать. Если в каком-то элементе массива преобразование не удалось
        (например, в ячейке лежит символ или текст вместо числа), должно быть брошено
        исключение MyArrayDataException, с детализацией в какой именно ячейке лежат неверные данные.
         */

        String[][] str21 = new String[][] {
                {"1", "2", "3", "4"},
                {"1", "2", "2", "4"},
                {"1", "2", "3", "4"},
                {"13", "2", "3", "4"}
        };

        String[][] str22 = new String[][] {
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"13", "2ws", "3", "4"}
        };

        String[][] str23 = new String[][] {
                {"!1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"1", "2", "3", "4"},
                {"13", "2", "3", "4"}
        };

        try {
            System.out.println("First 21 work! SUM is: " + myArrayDataExceptionThrowerFirstVersion(str21));
        } catch (MyArrayDataException e) {
            System.out.println("First 21 not work!");
            e.getMessage();
            System.out.println(Arrays.toString(e.getIdxs()));
        }

        try {
            System.out.println("First 22 work! SUM is: " + myArrayDataExceptionThrowerFirstVersion(str22));
        } catch (MyArrayDataException e) {
            System.out.println("First 22 not work!");
            e.getMessage();
            System.out.println(Arrays.toString(e.getIdxs()));
        }

        try {
            System.out.println("First 23 work! SUM is: " + myArrayDataExceptionThrowerFirstVersion(str23));
        } catch (MyArrayDataException e) {
            System.out.println("First 23 not work!");
            e.getMessage();
            System.out.println(Arrays.toString(e.getIdxs()));
        }

        try {
            System.out.println("Second 21 work! SUM is: " + myArrayDataExceptionThrowerSecondVersion(str21));
        } catch (MyArrayDataException e) {
            System.out.println("Second 21 not work!");
            e.getMessage();
            System.out.println(Arrays.toString(e.getIdxs()));
        }

        try {
            System.out.println("Second 22 work! SUM is: " + myArrayDataExceptionThrowerSecondVersion(str22));
        } catch (MyArrayDataException e) {
            System.out.println("Second 22 not work!");
            e.getMessage();
            System.out.println(Arrays.toString(e.getIdxs()));
        }

        try {
            System.out.println("Second 23 work! SUM is: " + myArrayDataExceptionThrowerSecondVersion(str23));
        } catch (MyArrayDataException e) {
            System.out.println("Second 23 not work!");
            e.getMessage();
            System.out.println(Arrays.toString(e.getIdxs()));
        }

        /*
        4. Требуется реализовать enum DayOfWeek, который будет представлять дни недели.
        С его помощью необходимо решить задачу определения кол-ва рабочих часов до конца
        недели по заднному текущему дню. Считается, что текущий день ещё не начался, и
        рабочие часы за него должны учитываться.
         */

        String text = "Текущий день недели - \"%s\". До конца рабочей недели осталось %s часов.";
        for (DayOfWeek dayOfWeek : DayOfWeek.values()) {
            System.out.println(String.format(text, dayOfWeek.getRusDef(), getWorkingHours(dayOfWeek)));
        }
    }

    public static void myArraySizeExceptionThrower(String[][] strArr) throws MyArraySizeException {
        if(strArr.length != 4) throw new MyArraySizeException("First dim not 4!");

        boolean isFourXFour = true;

        for(int i = 0; i < strArr.length; i++) {
            isFourXFour = isFourXFour && strArr[i].length == 4;
            if (!isFourXFour) throw new MyArraySizeException("Second dim not 4");
        }
    }

    public static int myArrayDataExceptionThrowerFirstVersion(String[][] strArr) throws MyArrayDataException {
        int sum = 0;

        for(int i = 0; i < strArr.length; i++)
            for (int j = 0; j < strArr[i].length; j++)
                try {
                    sum += Integer.parseInt(strArr[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Illegal argument!", new int[] {i, j});
                }

        return sum;
    }

    public static int myArrayDataExceptionThrowerSecondVersion(String[][] strArr) throws MyArrayDataException {
        int sum = 0;

        for(int i = 0; i < strArr.length; i++)
            for (int j = 0; j < strArr[i].length; j++)
                if (isInteger(strArr[i][j])) sum += Integer.parseInt(strArr[i][j]);
                else throw new MyArrayDataException("Illegal argument!", new int[]{i, j});

        return sum;
    }

    public static int getWorkingHours(DayOfWeek dayOfWeek) {
        return (N_WORKING_DAYS * WORKING_HOURS_PER_DAY -
                (dayOfWeek != DayOfWeek.SUNDAY && dayOfWeek != DayOfWeek.SATURDAY ?
                        dayOfWeek.ordinal() * WORKING_HOURS_PER_DAY :
                        N_WORKING_DAYS * WORKING_HOURS_PER_DAY));
    }

    private static boolean isInteger(String str) {
        for(char c : str.toCharArray())
            if (c < '0' || c > '9') return false;

        return true;
    }
}
