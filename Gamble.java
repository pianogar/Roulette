import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import javax.swing.*;
import java.awt.event.*;
import javax.imageio.ImageIO;

public class Gamble {
    public static DrawingPanel frame = new DrawingPanel(800, 800);
    public static Graphics g = frame.getGraphics();
    public static File image = new File("");
    public static int[] rouletteNums = { 0, 32, 15, 19, 4, 21, 2, 25, 17, 34, 6, 27, 13, 36, 11, 30, 8, 23, 10, 5, 24,
            16, 33, 1, 20, 14, 31, 9, 22, 18, 29, 7, 28, 12, 35, 3, 26 };
    public static int[] imagenums = { 0, 350, 341, 331, 322, 312, 302, 292, 283, 273, 263, 254, 244, 234, 224, 215, 205,
            196, 186, 176, 166, 156, 147, 137, 127, 117, 108, 98, 88, 78, 69, 59, 49, 40, 30, 20, 10 };
    public static int[] red = { 32, 19, 21, 25, 34, 27, 36, 30, 23, 5, 16, 1, 14, 9, 18, 7, 12, 3 };
    public static int[] black = { 15, 4, 2, 17, 6, 13, 11, 8, 10, 24, 33, 20, 31, 22, 29, 28, 35, 26 };
    public static Scanner scr = new Scanner(System.in);
    public static int money = 100;
    public static int turn = 0;
    public static int[] xPoints = { 390, 410, 400 };
    public static int[] yPoints = { 100, 100, 145 };
    public static Font font = new Font("Comic Sans", Font.BOLD, 200);
    public static boolean game = true;
    public static int play = 0;
    public static int story = 0;
    public static int played = 0;
    public static int highScore = 0;
    public static String name = "";
    public static int max = 100;
    public static boolean check = false;
    public static boolean ifEqual = false;
    public static int police = 0;
    public static int[] wantedPercent = { 0, 2, 7, 18, 32, 50 };
    public static JFrame myJFrame = new JFrame();

    public static void main(String[] args) throws Exception {
        while (game) {
            if (played % 10 == 0 && played != 0) {
                System.out.println("You have played " + played + " games get a life");
                Thread.sleep(3000);
                for (int i = 3600; i >= 0; i--) {
                    System.out.println("\fYou have " + i + " seconds left");
                    Thread.sleep(1000);
                }
                System.out.println("Okay you can play again");
            }
            File myObj = new File("highScore");
            Scanner myReader = new Scanner(myObj);
            String data = "";
            while (myReader.hasNextLine()) {
                data = myReader.nextLine();
            }
            myReader.close();
            int indexnum1 = data.indexOf(",") + 1;
            int indexnum2 = data.lastIndexOf(",");
            int indexname1 = data.indexOf(".") + 1;
            int indexname2 = data.lastIndexOf(".");
            highScore = Integer.parseInt(data.substring(indexnum1, indexnum2));
            name = data.substring(indexname1, indexname2);
            money = 100;
            g.setColor(Color.RED);
            g.setFont(font);
            turn = 0;
            image = images.tables[0];
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.fillPolygon(xPoints, yPoints, 3);
            if (played > 0) {
                if (played == 1) {
                    System.out.println("You have played " + played + " time");
                } else {
                    System.out.println("You have played " + played + " times");
                }
                Thread.sleep(3000);
                System.out.println("Would you like to skip the story?\n(1 for yes, 2 for no)");
                story = scr.nextInt();
            }
            if (story == 1) {
            } else {
                story();
            }
            logic();
            winOrLoss();
            System.out.println("Would you like to try again?\n(1 for yes, 2 for no)");
            play = scr.nextInt();
            if (play == 2)
                System.exit(0);
        }

    }

    public static void highScore() throws Exception {
        System.out.println("Congrats, you have beaten the highscore! please write your name to be saved.");
        scr.nextLine();
        name = scr.nextLine();
        FileWriter mrWriter = new FileWriter("highScore");
        mrWriter.write("," + max + "," + "." + name + ".");
        mrWriter.close();
    }

    public static void winOrLoss() throws Exception {
        if (money <= 0) {
            System.out.println("You made $" + (money - 100) + "\nyou broke haha");
            played++;
        } else if (money >= 340000) {
            System.out.println("You made $" + (money - 100) + "\ndamn good job, have fun at yale");
            played++;
        } else {
            System.out.println("You made $" + (money - 100) + "\nidk how you got here but you don't have $340,000");
            played++;
        }
        Thread.sleep(3000);
        if (max > highScore) {
            highScore();
        }
    }

    public static void story() throws Exception {
        System.out.println("You just got accepted to your dream school, Yale!");
        Thread.sleep(3000);
        System.out.println("Sadly your parents kicked you out of the house");
        Thread.sleep(3000);
        System.out.println("You have $100 in your bank account");
        Thread.sleep(3000);
        System.out.println("You decide to gamble your last $100 on roulette.");
        Thread.sleep(3000);
        System.out.println("You need $340,000 to get into Yale");
        Thread.sleep(3000);
        System.out.println("Good luck!");
        Thread.sleep(3000);
    }

    public static int logic() throws Exception {
        int num;
        boolean valid;
        boolean yes = false;
        while (money > 0 && money < 340000) {
            turn++;
            System.out.print("\f");
            System.out.println("\fThe current highscore is $" + highScore + " and was made by " + name);
            System.out.print("You have $" + money
                    + "/$340,000 in your acct\nwhat do you want to bet\n1. 1 number\n2. 2 numbers\n3. 3 numbers\n4. 4 numbers\n5. 5 numbers\n6. 6 numbers\n7. 12 numbers\n8. 18 numbers\n9. all odd/even\n10. all red/black \n11. rob the homeless \nPlease enter the number that corresponds to your choice\nYou are on turn "
                    + turn + "\nWanted lvl: ");
            for (int i = 0; i < police; i++) {
                System.out.print("* ");
            }
            System.out.println();
            num = scr.nextInt();
            valid = false;
            switch (num) {
                case 1: {
                    System.out.println("What number? (0-36)");
                    int numbet = 0;
                    check = false;
                    while (!check) {
                        numbet = scr.nextInt();
                        if (numbet > 36 || numbet < 0) {
                            System.out.println("Not a valid number");
                        } else {
                            check = true;
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 36);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 2: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[2];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 18);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 3: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[3];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1] || rand == numbet[2]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 12);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 4: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[4];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1] || rand == numbet[2] || rand == numbet[3]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 9);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 5: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[5];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1] || rand == numbet[2] || rand == numbet[3]
                            || rand == numbet[4]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 7);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 6: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[6];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1] || rand == numbet[2] || rand == numbet[3]
                            || rand == numbet[4] || rand == numbet[5]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 6);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 7: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[12];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1] || rand == numbet[2] || rand == numbet[3]
                            || rand == numbet[4] || rand == numbet[5] || rand == numbet[6] || rand == numbet[7]
                            || rand == numbet[8] || rand == numbet[9] || rand == numbet[10] || rand == numbet[11]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 3);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 8: {
                    System.out.println("What numbers? (0-36)");
                    int[] numbet = new int[18];
                    for (int i = 0; i < numbet.length; i++) {
                        check = false;
                        while (!check) {
                            ifEqual = false;
                            numbet[i] = scr.nextInt();
                            if (numbet[i] > 36 || numbet[i] < 0) {
                                System.out.println("Not a valid number");
                            } else {
                                for (int j = 0; j < i; j++) {
                                    if (numbet[i] == numbet[j]) {
                                        ifEqual = true;
                                    }
                                }
                                if (ifEqual) {
                                    System.out.println("Not a valid number");
                                } else {
                                    check = true;
                                }
                            }
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (rand == numbet[0] || rand == numbet[1] || rand == numbet[2] || rand == numbet[3]
                            || rand == numbet[4] || rand == numbet[5] || rand == numbet[6] || rand == numbet[7]
                            || rand == numbet[8] || rand == numbet[9] || rand == numbet[10] || rand == numbet[11]
                            || rand == numbet[12] || rand == numbet[13] || rand == numbet[14] || rand == numbet[15]
                            || rand == numbet[16] || rand == numbet[17]) {
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        money = money + (bet * 2);
                    } else {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 9: {
                    System.out.println("press 1 for Even or press 2 for Odd");
                    int numbet = 0;
                    check = false;
                    while (!check) {
                        numbet = scr.nextInt();
                        if (numbet == 1 || numbet == 2) {
                            check = true;
                        } else {
                            System.out.println("Not a valid number");
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (numbet == 1) {
                        if (rand % 2 == 0 && rand != 0) {
                            for (int i = 0; i < 50; i++) {
                                for (int j = 0; j < 4; j++) {
                                    switch (j) {
                                        case 0:
                                            g.setColor(Color.RED);
                                            break;
                                        case 1:
                                            g.setColor(Color.BLUE);
                                            break;
                                        case 2:
                                            g.setColor(Color.GREEN);
                                            break;
                                        case 3:
                                            g.setColor(Color.WHITE);
                                    }
                                    g.drawString("YOU", 190, 370);
                                    g.drawString("WON!!", 100, 570);
                                    Thread.sleep(10);
                                }
                            }
                            image = images.tables[0];
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.setColor(Color.RED);
                            g.fillPolygon(xPoints, yPoints, 3);
                            money = money + (bet * 2);
                        } else {
                            System.out.print("\f");
                            System.out.println("Try again next time :(");
                            Thread.sleep(3000);
                        }
                    } else if (numbet == 2) {
                        if (rand % 2 == 0 && rand != 0) {
                            System.out.print("\f");
                            System.out.println("Try again next time :(");
                            Thread.sleep(3000);
                        } else if (rand != 0) {
                            for (int i = 0; i < 50; i++) {
                                for (int j = 0; j < 4; j++) {
                                    switch (j) {
                                        case 0:
                                            g.setColor(Color.RED);
                                            break;
                                        case 1:
                                            g.setColor(Color.BLUE);
                                            break;
                                        case 2:
                                            g.setColor(Color.GREEN);
                                            break;
                                        case 3:
                                            g.setColor(Color.WHITE);
                                    }
                                    g.drawString("YOU", 190, 370);
                                    g.drawString("WON!!", 100, 570);
                                    Thread.sleep(10);
                                }
                            }
                            image = images.tables[0];
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.setColor(Color.RED);
                            g.fillPolygon(xPoints, yPoints, 3);
                            money = money + (bet * 2);
                        }
                    }
                    break;
                }
                case 10: {
                    System.out.println("press 1 for Red or press 2 for Black");
                    int numbet = 0;
                    check = false;
                    while (!check) {
                        numbet = scr.nextInt();
                        if (numbet == 1 || numbet == 2) {
                            check = true;
                        } else {
                            System.out.println("Not a valid number");
                        }
                    }
                    int bet = 0;
                    while (!valid) {
                        System.out.println("How much money");
                        bet = scr.nextInt();
                        if (money - bet < 0) {
                            System.out.println("You can't get a loan you don't have a credit score");
                        } else if (bet < 0) {
                            System.out.println("You don't have negative money");
                        } else {
                            valid = true;
                        }
                    }
                    money = money - bet;
                    int rand = spin();
                    if (numbet == 1) {
                        for (int i = 0; i < red.length; i++) {
                            if (red[i] == rand) {
                                for (int j = 0; j < 50; j++) {
                                    for (int k = 0; k < 4; k++) {
                                        switch (k) {
                                            case 0:
                                                g.setColor(Color.RED);
                                                break;
                                            case 1:
                                                g.setColor(Color.BLUE);
                                                break;
                                            case 2:
                                                g.setColor(Color.GREEN);
                                                break;
                                            case 3:
                                                g.setColor(Color.WHITE);
                                        }
                                        g.drawString("YOU", 190, 370);
                                        g.drawString("WON!!", 100, 570);
                                        Thread.sleep(10);
                                    }
                                }
                                image = images.tables[0];
                                BufferedImage img = ImageIO.read(image);
                                g.drawImage(img, 0, 0, 800, 800, null);
                                g.setColor(Color.RED);
                                g.fillPolygon(xPoints, yPoints, 3);
                                money = money + (bet * 2);
                                yes = true;
                            }
                        }
                    } else if (numbet == 2) {
                        for (int i = 0; i < black.length; i++) {
                            if (black[i] == rand) {
                                for (int j = 0; j < 50; j++) {
                                    for (int k = 0; k < 4; k++) {
                                        switch (k) {
                                            case 0:
                                                g.setColor(Color.RED);
                                                break;
                                            case 1:
                                                g.setColor(Color.BLUE);
                                                break;
                                            case 2:
                                                g.setColor(Color.GREEN);
                                                break;
                                            case 3:
                                                g.setColor(Color.WHITE);
                                        }
                                        g.drawString("YOU", 190, 370);
                                        g.drawString("WON!!", 100, 570);
                                        Thread.sleep(10);
                                    }
                                }
                                image = images.tables[0];
                                BufferedImage img = ImageIO.read(image);
                                g.drawImage(img, 0, 0, 800, 800, null);
                                g.setColor(Color.RED);
                                g.fillPolygon(xPoints, yPoints, 3);
                                money = money + (bet * 2);
                                yes = true;
                            }
                        }
                    }
                    if (!yes) {
                        System.out.print("\f");
                        System.out.println("Try again next time :(");
                        Thread.sleep(3000);
                    }
                    break;
                }
                case 11: {
                    homeless();
                    break;
                }
                case 12: {
                    System.out.println("What is the password");
                    scr.nextLine();
                    String password = scr.nextLine();
                    if (password.equalsIgnoreCase("anthony")) {
                        int rand = 8;
                        int count = imagenums[rand];
                        for (int i = 0; i < 360; i += 5) {
                            image = images.tables[i];
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep(2);
                        }
                        for (int i = 0; i < 360; i += 8) {
                            image = images.tables[i];
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep(2);
                        }
                        for (int i = 0; i < 360; i += 8) {
                            if (count + i > 359) {
                                image = images.tables[(count + i) - 360];
                            } else {
                                image = images.tables[i + count];
                            }
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep(0);
                        }
                        for (int i = 0; i < 360; i += 8) {
                            if (count + i > 359) {
                                image = images.tables[(count + i) - 360];
                            } else {
                                image = images.tables[i + count];
                            }
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep(2);
                        }
                        for (int i = 0; i < 360; i += 5) {
                            if (count + i > 359) {
                                image = images.tables[(count + i) - 360];
                            } else {
                                image = images.tables[i + count];
                            }
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep(2);
                        }
                        for (int i = 0; i < 360; i += 3) {
                            if (count + i > 359) {
                                image = images.tables[(count + i) - 360];
                            } else {
                                image = images.tables[i + count];
                            }
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep((int) (2 + (i * 8.0 / 360)));
                        }
                        for (int i = 0; i < 360; i++) {
                            if (count + i > 359) {
                                image = images.tables[(count + i) - 360];
                            } else {
                                image = images.tables[i + count];
                            }
                            BufferedImage img = ImageIO.read(image);
                            g.drawImage(img, 0, 0, 800, 800, null);
                            g.fillPolygon(xPoints, yPoints, 3);
                            Thread.sleep((int) (20 * (-1 * Math.cos(0.00872664625 * i)) + 20));
                        }
                        money = 340000;
                        for (int i = 0; i < 50; i++) {
                            for (int j = 0; j < 4; j++) {
                                switch (j) {
                                    case 0:
                                        g.setColor(Color.RED);
                                        break;
                                    case 1:
                                        g.setColor(Color.BLUE);
                                        break;
                                    case 2:
                                        g.setColor(Color.GREEN);
                                        break;
                                    case 3:
                                        g.setColor(Color.WHITE);
                                }
                                g.drawString("YOU", 190, 370);
                                g.drawString("WON!!", 100, 570);
                                Thread.sleep(10);
                            }
                        }
                        image = images.tables[0];
                        BufferedImage img = ImageIO.read(image);
                        g.drawImage(img, 0, 0, 800, 800, null);
                        g.setColor(Color.RED);
                        g.fillPolygon(xPoints, yPoints, 3);
                        System.out.println("Your balance is $340000");
                        Thread.sleep(3000);
                    } else {
                        int num1 = (int) (5 * Math.random() + 1);
                        switch (num1) {
                            case 1:
                                System.out.println("You are NOT the rizzler");
                                break;
                            case 2:
                                System.out.println("You do NOT have a level 5 gyatt");
                                break;
                            case 3:
                                System.out.println("Skibidi toilet is NOT your opp");
                                break;
                            case 4:
                                System.out.println("You are NOT invited to my sigma party");
                                break;
                            case 5:
                                System.out.println("You are NOT baby gronk rizzing up livvy dunne");
                                break;
                        }
                        Thread.sleep(4000);
                    }
                    break;
                }
            }
            max = Math.max(money, max);
            int policeEvent = (int) (100 * Math.random() + 1);
            if (policeEvent <= wantedPercent[police])
                policeEvent();
        }
        return money;
    }

    public static int spin() throws Exception {
        int rand = (int) (37 * Math.random());
        int count = imagenums[rand];
        for (int i = 0; i < 360; i += 8) {
            image = images.tables[i];
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.fillPolygon(xPoints, yPoints, 3);
        }
        for (int i = 0; i < 360; i += 8) {
            if (count + i > 359) {
                image = images.tables[(count + i) - 360];
            } else {
                image = images.tables[i + count];
            }
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.fillPolygon(xPoints, yPoints, 3);
        }
        for (int i = 0; i < 180; i += 5) {
            if (count + i > 359) {
                image = images.tables[(count + i) - 360];
            } else {
                image = images.tables[i + count];
            }
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.fillPolygon(xPoints, yPoints, 3);
            Thread.sleep((int) (2 + (i * 10.0 / 180)));
        }
        for (int i = 180; i < 270; i += 3) {
            if (count + i > 359) {
                image = images.tables[(count + i) - 360];
            } else {
                image = images.tables[i + count];
            }
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.fillPolygon(xPoints, yPoints, 3);
            Thread.sleep((int) (0 + (i * 20.0 / 90)));
        }
        for (int i = 270; i < 360; i++) {
            if (count + i > 359) {
                image = images.tables[(count + i) - 360];
            } else {
                image = images.tables[i + count];
            }
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.fillPolygon(xPoints, yPoints, 3);
            Thread.sleep((int) (0 + ((i - 270) * 35.0 / 90)));
        }
        return rouletteNums[rand];
    }

    public static void homeless() throws Exception {
        int rand = (int) (4 * Math.random() + 1);
        if (rand == 1) {
            image = images.homeless[0];
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            Thread.sleep(3000);
            AudioPlayer.filePath = "homeless/death.wav";
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.play();
            image = images.homeless[2];
            img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            Thread.sleep(5000);
            audioPlayer.stop();
            AudioPlayer.filePath = "homeless/weewoo.wav";
            audioPlayer = new AudioPlayer();
            audioPlayer.play();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 2; j++) {
                    switch (j) {
                        case 0:
                            g.setColor(Color.RED);
                            break;
                        case 1:
                            g.setColor(Color.BLUE);
                            break;
                    }
                    g.fillRect(0, 0, 800, 800);
                    Thread.sleep(648);
                }
            }
            audioPlayer.stop();
            image = images.tables[0];
            img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            System.out.println("\fHow did you fail to rob a homeless man");
            if (police < 5)
                police++;
            Thread.sleep(2000);
            g.setColor(Color.RED);
        } else {
            image = images.homeless[0];
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            Thread.sleep(3000);
            image = images.homeless[1];
            img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.setColor(Color.BLACK);
            g.drawString("+100", 107, 223);
            money += 100;
            AudioPlayer.filePath = "homeless/chaching.wav";
            AudioPlayer audioPlayer = new AudioPlayer();
            audioPlayer.play();
            Thread.sleep(1000);
            audioPlayer.stop();
            Thread.sleep(2000);
            rand = (int) (3 * Math.random() + 1);
            AudioPlayer.filePath = "homeless/weewoo.wav";
            audioPlayer = new AudioPlayer();
            audioPlayer.play();
            if (rand == 1 && police < 5) {
                police++;
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        switch (j) {
                            case 0:
                                g.setColor(Color.RED);
                                break;
                            case 1:
                                g.setColor(Color.BLUE);
                                break;
                        }
                        g.fillRect(0, 0, 800, 800);
                        Thread.sleep(648);
                    }
                }
            }
            audioPlayer.stop();
            image = images.tables[0];
            img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            g.setColor(Color.RED);
        }
    }

    public static int choice = 0;

    public static void policeEvent() throws Exception {
        System.out.println("Quick! The cops are coming into the store, where will you hide?");
        Thread.sleep(1000);
        System.out.println("1. Under the table");
        System.out.println("2. Behind the bar");
        System.out.println("3. Employee only area");
        System.out.println("4. Slot machine area");
        myJFrame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                if (keyCode == KeyEvent.VK_1) {
                    choice = 1;
                } else if (keyCode == KeyEvent.VK_2) {
                    choice = 2;
                } else if (keyCode == KeyEvent.VK_3) {
                    choice = 3;
                } else if (keyCode == KeyEvent.VK_4) {
                    choice = 4;
                }
            }
        });
        myJFrame.setVisible(true);
        ActionListener refresh = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myJFrame.setVisible(true);
            }
        };
        Timer timer = new Timer(100, refresh);
        timer.start();
        for (int i = 5; i >= 1; i--) {
            System.out.println(i + "...");
            Thread.sleep(1000);
        }
        timer.stop();
        myJFrame.setVisible(false);
        myJFrame.dispose();
        switch (choice) {
            case 0: {
                System.out.println("You were too slow, and the cops caught you.");
                Thread.sleep(2000);
                System.out.println("guess your not going to college");
                Thread.sleep(2000);
                money = 0;
                break;
            }
            case 1: {
                System.out.println("You decide to hide under the table, the cops start looking for you...");
                Thread.sleep(3000);
                int rand = (int) (4 * Math.random() + 1);
                if (rand > 1) {
                    System.out.println("The cops found you.");
                    Thread.sleep(2000);
                    System.out.println("guess your not going to college");
                    Thread.sleep(2000);
                    money = 0;
                    break;
                }
                System.out.println("The cops didn't find you!");
                Thread.sleep(2000);
                System.out.println("Have fun gambling!");
                Thread.sleep(2000);
                police = 0;
                break;
            }
            case 2: {
                System.out.println("You decide to hide behind the bar, the cops start looking for you...");
                Thread.sleep(3000);
                int rand = (int) (2 * Math.random() + 1);
                if (rand == 1) {
                    System.out.println("The cops found you.");
                    Thread.sleep(2000);
                    System.out.println("guess your not going to college");
                    Thread.sleep(2000);
                    money = 0;
                    break;
                }
                System.out.println("The cops didn't find you!");
                Thread.sleep(2000);
                System.out.println("Have fun gambling!");
                Thread.sleep(2000);
                police = 0;
                break;
            }
            case 3: {
                int rand1 = (int) (10 * Math.random() + 1);
                if (rand1 <= 6) {
                    System.out.println("The door is locked,");
                    Thread.sleep(2000);
                    System.out.println("The cops found you.");
                    Thread.sleep(2000);
                    System.out.println("guess your not going to college");
                    Thread.sleep(2000);
                    money = 0;
                    break;
                }
                System.out.println("The door is unlocked, and you get inside...");
                Thread.sleep(3000);
                int rand2 = (int) (100 * Math.random() + 1);
                if (rand2 <= 5) {
                    System.out.println("The cops found you.");
                    Thread.sleep(2000);
                    System.out.println("guess your not going to college");
                    Thread.sleep(2000);
                    money = 0;
                    break;
                }
                System.out.println("The cops didn't find you!");
                Thread.sleep(2000);
                System.out.println("Have fun gambling!");
                Thread.sleep(2000);
                police = 0;
                break;
            }
            case 4: {
                System.out.println("You decide to disguise youself as an old man in the slot machines,");
                Thread.sleep(2000);
                System.out.println("But you get a bit distraced while waiting...");
                Thread.sleep(2000);
                slotMachine();
            }
        }
    }

    public static void slotMachine() throws Exception {
        while (money > 0 && money < 340000) {
            boolean spin = true;
            boolean betTrue = false;
            System.out.println("You have $" + money + "/$340,000 in your acct");
            while (spin) {
                System.out.println("press 1 to spin again");
                int num = scr.nextInt();
                if (num == 1) {
                    spin = false;
                }
            }
            int bet = 0;
            while (!betTrue) {
                System.out.println("How much money");
                bet = scr.nextInt();
                if (money - bet < 0) {
                    System.out.println("You can't get a loan you don't have a credit score");
                } else if (bet < 0) {
                    System.out.println("You don't have negative money");
                } else {
                    betTrue = true;
                }
            }
            money = money - bet;
            image = images.slots[0];
            BufferedImage img = ImageIO.read(image);
            g.drawImage(img, 0, 0, 800, 800, null);
            int rand1 = (int) (4 * Math.random());
            int rand2 = (int) (4 * Math.random());
            int rand3 = (int) (4 * Math.random());
            if (rand1 == rand2 && rand2 == rand3) {
                money = money + (bet * 100);
            } else if (rand1 == rand2) {
                money = money + (bet * 50);
            } else if (rand2 == rand3) {
                money = money + (bet * 50);
            } else if (rand1 == rand3) {
                money = money + (bet * 50);
            }
            for (int j = 1; j <= 21 + rand1; j++) {
                int k = 0;
                if (j % 4 == 0) {
                    k = 4;
                } else {
                    k = j % 4;
                }
                image = images.slots[k];
                img = ImageIO.read(image);
                g.drawImage(img, 16, 278, 244, 244, null);
                Thread.sleep(100);
            }
            for (int j = 1; j <= 21 + rand2; j++) {
                int k = 0;
                if (j % 4 == 0) {
                    k = 4;
                } else {
                    k = j % 4;
                }
                image = images.slots[k];
                img = ImageIO.read(image);
                g.drawImage(img, 278, 278, 244, 244, null);
                Thread.sleep(100);
            }
            for (int j = 1; j <= 21 + rand3; j++) {
                int k = 0;
                if (j % 4 == 0) {
                    k = 4;
                } else {
                    k = j % 4;
                }
                image = images.slots[k];
                img = ImageIO.read(image);
                g.drawImage(img, 539, 278, 244, 244, null);
                Thread.sleep(100);
            }
        }
    }
}
