import java.util.Scanner;
import static java.lang.Integer.valueOf;


public class HellowWord {
    public static void main(String[] args)
    {
        System.out.println("Введите выражение: ");
        Scanner in = new Scanner(System.in);
        Resolution s = new Resolution(in.nextLine());
        System.out.println(s.res);
    }
}

class RomanArabNumerals
{
    String RomanVal;
    int ArabVal;
    String roman_nums[] = {"I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX"};
    int arab_nums[] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20};

    RomanArabNumerals(String RomanVal)
    {
        this.RomanVal = RomanVal;
    }
    RomanArabNumerals(int ArabVal)
    {
        this.ArabVal = ArabVal;
    }

    int roman_to_arab()
    {
        for (int i = 0; i < this.roman_nums.length; i++)
        {
            if(this.RomanVal.equals(roman_nums[i]))
            {
                this.ArabVal = arab_nums[i];
                break;
            }
            if(i == 19)
            {
                System.out.println("Ошибка ввода.");
                System.exit(3);
            }
        }
        return this.ArabVal;
    }

    String arab_to_roman()
    {
        for(int i = 0; i < this.arab_nums.length; i ++)
        {
            if (this.ArabVal == this.arab_nums[i])
            {
                this.RomanVal = this.roman_nums[i];
                break;
            }
            if(i == 19)
            {
                System.out.println("Ошибка ввода.");
                System.exit(3);
            }
        }
        return this.RomanVal;
    }
}

class MathematicalExpression
{
    String expr;
    String a;
    String b;
    char op;
    MathematicalExpression(String expr)
    {
        this.expr = expr.trim();
        int index_op = this.expr.indexOf("+");
        if (index_op == -1)
            index_op = this.expr.indexOf("-");
        if (index_op == -1 )
            index_op = this.expr.indexOf("*");
        if (index_op == -1)
            index_op = this.expr.indexOf("/");
        try
        {
            op = this.expr.charAt(index_op);
        }
        catch(Exception StringIndexOutOfBoundsException)
        {
            System.out.println("Ошибка ввода, нет нужного оператора");
            return;
        }
        String parametr = "\\" + Character.toString(op);
        String[] el = this.expr.split(parametr);
        a = el[0].trim();
        b = el[1].trim();
    }
}

class Resolution extends MathematicalExpression
{
    int a;
    int b;
    String res;

    Resolution(String expr)
    {
        super(expr);
        try
        {
            a = valueOf(super.a);
            b = valueOf(super.b);
            this.res = Integer.toString(operation());
        }
        catch (Exception NumberFormatException)
        {
            a = new RomanArabNumerals(super.a).roman_to_arab();
            b = new RomanArabNumerals(super.b).roman_to_arab();
            this.res = new RomanArabNumerals(operation()).arab_to_roman();
        }
    }
    int operation()
    {
        int res  = 0;
        switch (super.op)
        {
            case '+':
                res = a + b;
                break;
            case '*':
                res = a * b;
                break;
            case '-':
                res = a - b;
                break;
            case '/':
                try
                {
                    res = a / b;
                }
                catch (Exception ArithmeticException)
                {
                    System.out.println("На ноль делить нельзя");
                    System.exit(123);
                }
                break;
        }
        return res;
    }
}

