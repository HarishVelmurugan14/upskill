
public class Pattern {
    public void createRectangleStarPattern(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < count; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void createRightAngledTrianglePattern(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void createRightAngledNumberPyramid(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(j + 1);
            }
            System.out.println();
        }
    }

    public void createRightAngledNumberPyramid2(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(i + 1);
            }
            System.out.println();
        }
    }

    public void pattern5(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = count; j > i; j--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }

    public void pattern6(int count) {
        for (int i = 0; i < count; i++) {
            int newNo = 1;
            for (int j = count; j > i; j--) {
                System.out.print(newNo);
                newNo++;
            }
            System.out.println();
        }
    }

    public void pattern8(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("-");
            }
            for (int j = 0; j < (count * 2) - (i * 2 + 1); j++) {
                System.out.print("*");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("-");
            }
            System.out.println();
        }
    }


    public void pattern11(int count) {
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i + 1; j++) {
                if ((i + j) % 2 == 0) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }

            }
            System.out.println();
        }
    }

    public void pattern12(int count) {
        for (int i = 0; i < count; i++) {

            for (int j = 1; j < i + 2; j++) {
                System.out.print(j);
            }
            for (int j = 0; j < (count * 2) - (i + 1) * 2; j++) {
                System.out.print("-");
            }
            for (int j = i + 1; j > 0; j--) {
                System.out.print(j);
            }
            System.out.println();
        }
    }

    public void pattern13(int count) {
        int num = 1;
        for (int i = 0; i < count; i++) {
            for (int j = 0; j < i + 1; j++) {
                System.out.print(num);
                num++;
            }

            System.out.println();
        }
    }

    public void pattern14(int count) {

        for (int i = 0; i < count; i++) {
            char alphabet = 'A';
            for (int j = 0; j < i + 1; j++) {
                System.out.print(alphabet);
                alphabet++;
            }

            System.out.println();
        }
    }
}

