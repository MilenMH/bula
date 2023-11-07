package com.example.bula;

import java.io.Console;

public class ConsoleUI {

    public Core core;

    public ConsoleUI(Core core) {
        this.core = core;
    }

    public void drawBoard() {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println(getFirstLine(""));
        System.out.println(getSecondLine(""));
        System.out.println(getThirdLine(""));
        System.out.println("           =================================================           ");
        System.out.println("           =1=2=3=4=5=6=7=8=9=0=1=2=3=4=5=6=7=8=9=0=1=2=3=4=           ");
        if (core.fpMoves.size() > 0) {
            System.out.println(String.valueOf(core.fpMoves.get(core.fpMoves.size() - 1).die1) + ' '
                   + String.valueOf(+ core.fpMoves.get(core.fpMoves.size() - 1).die2));
        }
        if (core.spMoves.size() > 0) {
            System.out.println(String.valueOf(core.spMoves.get(core.spMoves.size() - 1).die1)
                    + ' ' + String.valueOf(core.spMoves.get(core.spMoves.size() - 1).die2));
        }
    }

    public void drawBoard(int die1, int die2) {
        System.out.print("\033[H\033[2J");
        System.out.flush();

        System.out.println("Die 1 : " + die1 + " , Die 2 : " + die2);
        System.out.println();
        System.out.println(getFirstLine(""));
        System.out.println(getSecondLine(""));
        System.out.println(getThirdLine(""));
        System.out.println("           =================================================           ");
        System.out.println("           =1=2=3=4=5=6=7=8=9=0=1=2=3=4=5=6=7=8=9=0=1=2=3=4=           ");
    }

    private String getThirdLine(String tlString) {
        for (int i = 0; i < core.fp.size(); i++) {
            if (core.fp.get(i) > 0 && !core.fpBlockSp.get(i)) {
                tlString += "X ";
                continue;
            }
            if (core.sp.get(i) > 0 && !core.spBlockFp.get(i)) {
                tlString += "O ";
                continue;
            }
//            if (i % 6 == 0 && i != 0) {
//                tlString += "| ";
//                tlString += "  ";
//                continue;
//            }
            tlString += "  ";

        }
        return tlString;
    }

    private String getSecondLine(String slString) {

        for (int i = 0; i < core.fp.size(); i++) {
            if (core.fp.get(i) > 1 && !core.fpBlockSp.get(i)) {
                slString += "X ";
                continue;
            }
            if (core.fp.get(i) > 0 && core.fpBlockSp.get(i)) {
                slString += "X ";
                continue;
            }
            if (core.sp.get(i) > 1 && !core.spBlockFp.get(i)) {
                slString += "O ";
                continue;
            }
            if (core.sp.get(i) > 0 && core.spBlockFp.get(i)) {
                slString += "O ";
                continue;
            }
//            if (i % 6 == 0 && i != 0) {
//                slString += "|";
//                slString += " ";
//                continue;
//            }
            slString += "  ";

        }
        return slString;
    }

    private String getFirstLine(String flString) {
        for (int i = 0; i < core.fp.size(); i++) {
            if (core.fp.get(i) > 2 && !core.fpBlockSp.get(i)) {
                int numShown = core.fp.get(i) - 2;
                flString += numShown;
                if (numShown > 9){
                    continue;
                }
                flString += " ";
                continue;
            }
            if (core.fp.get(i) > 1 && core.fpBlockSp.get(i)) {
                int numShown = core.fp.get(i) - 1;
                flString += numShown;
                if(numShown > 9){
                    continue;
                }
                flString += " ";
                continue;
            }
            if (core.sp.get(i) > 2 && !core.spBlockFp.get(i)) {
                int numShown = core.sp.get(i) - 2;
                flString += numShown;
                if(numShown > 9){
                    continue;
                }
                flString += " ";
                continue;
            }
            if (core.sp.get(i) > 1 && core.spBlockFp.get(i)) {
                int numShown = core.sp.get(i) - 1;
                flString += numShown;
                if(numShown > 9){
                    continue;
                }
                flString += " ";
                continue;
            }
//            if (i % 6 == 0 && i != 0) {
//                flString += "|";
//                flString += " ";
//                continue;
//            }
            flString += "  ";

        }
        return flString;
    }


}
