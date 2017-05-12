package bisectiontest;

public class NumericalAlgorithmsOfSolvingEq {

    private double b, a;
    private final double epsilon = 3e-15;
    private final int max_count = 400;

    private double function(double x) {

        return x * x - 16;
    }

    private double functionDerivative(double x) {
        return 2 * x;
    }

    private boolean conditionChecking(double a, double b) {
        return function(a) * function(b) < 0;
    }
//algortihm from wikipedia.org/wiki/Bisection_method

    public void solveByBisection(double a, double b) {
        if (conditionChecking(a, b)) {
            double x1;
            int i = 1;
            while (Math.abs(a - b) > epsilon && i < max_count) {
                x1 = ((a + b) / 2);
                if ((function(b) * function(x1)) < 0) {
                    a = x1;
                } else {
                    b = x1;
                }

            }
            System.out.println("Found root at: " + Math.round((a + b) / 2));
        } else {
            System.out.println("Wrong arguments");
        }
    }
//algorithm from http://www.codewithc.com/secant-method-algorithm-flowchart/    

    public void solveBySection(double a, double b) {
        double fa, fb, x1;
        int i = 1;
        fa = function(a);
        fb = function(b);
        x1 = (a * fb - b * fa) / (fb - fa);
        while (Math.abs((x1 - b) / x1) > epsilon && i < max_count) {
            i++;
            a = b;
            b = x1;
        }
        System.out.println("Found root at: " + Math.round(x1));
    }
//algorithm from http://www.codewithc.com/regula-falsi-method-algorithm-flowchart/

    public void solveByFalsi(double a, double b) {
        double fa, fb, x1;
        int i = 1;
        fa = function(a);
        fb = function(b);
        if (conditionChecking(a, b)) {
            x1 = (a * fb - b * fa) / (fb - fa);
            while (Math.abs(function(x1)) < epsilon && (i < max_count)) {
                a++;
                if (fb * function(x1) <= 0) {
                    a = x1;
                } else {
                    b = x1;
                }

            }
            System.out.println("Found root at: " + Math.round(x1));
        } else {
            System.out.println("Wrong arguments");
        }
    }
//alghoritm from http://www.codewithc.com/newton-raphson-method-algorithm-flowchart/

    public void solveByNewtonRaphson(double a) {
        double x1 = 0;
        int i = 1;
        while ((Math.abs(function(a)) > epsilon) && (i < max_count)) {
            i++;
            x1 = a - function(a) / functionDerivative(a);
            a = x1;
        }
        if (Math.abs(function(x1)) <= epsilon) {
            System.out.println("Found root at: " + x1);
        } else {
            System.out.println("Failed");
        }
    }
}
