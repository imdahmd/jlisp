package com.f1codz.jlisp.type;

public class Fraction extends Literal {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        super(denominator == 1 ? Integer.toString(numerator) : numerator + "/" + denominator);

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public static Fraction parseFraction(String symbol) {
        try {
            if (!symbol.contains("/"))
                return new Fraction(Integer.parseInt(symbol), 1);

            String[] split = symbol.split("/");
            return new Fraction(Integer.parseInt(split[0]), Integer.parseInt(split[1]));

        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            throw new NumberFormatException("Invalid fraction " + symbol);
        }
    }

    public Fraction divide(Fraction fraction) {
        int numerator = this.numerator * fraction.denominator;
        int denominator = this.denominator * fraction.numerator;

        int gcd = gcd(numerator, denominator);
        while (gcd != 1) {
            numerator /= gcd;
            denominator /= gcd;
            gcd = gcd(numerator, denominator);
        }

        return new Fraction(numerator, denominator);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
