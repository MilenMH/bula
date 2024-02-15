package com.example.bula;

import java.util.List;
import java.util.Random;

public class BulaApplication {

    public static Random RANDOM = new Random();

    public static void main(String[] args) {
        Core core = new Core();
        ConsoleUI consoleUI = new ConsoleUI(core);


        while (true) {

            consoleUI.drawBoard();

            int die1 = getRandomNumberUsingInts(1, 6);
            int die2 = getRandomNumberUsingInts(1, 6);


            List<Core.PositionMove> nextCurrentPlayerPossibleMoves = core.getNextCurrentPlayerPossibleMoves(die1, die2);
            if (nextCurrentPlayerPossibleMoves.size() == 0){
                if (core.playerWon()){
                    break;
                }
            }else{
                int nextRndMove = getRandomNumberUsingInts(0, nextCurrentPlayerPossibleMoves.size());
                core.performMove(nextCurrentPlayerPossibleMoves.get(nextRndMove), die1, die2);
            }

            core.switchPlayersPerspective();

            die1 = getRandomNumberUsingInts(1, 6);
            die2 = getRandomNumberUsingInts(1, 6);
            nextCurrentPlayerPossibleMoves = core.getNextCurrentPlayerPossibleMoves(die1, die2);
            if (nextCurrentPlayerPossibleMoves.size() == 0){
                if (core.playerWon()){
                    break;
                }
            }else {
                int nextRndMove = getRandomNumberUsingInts(0, nextCurrentPlayerPossibleMoves.size());
                core.performMove(nextCurrentPlayerPossibleMoves.get(nextRndMove), die1, die2);
            }
            core.switchPlayersPerspective();

        }
    }

    public static int getRandomNumberUsingInts(int min, int max) {

        return RANDOM.ints(min, max)
                .findFirst()
                .getAsInt();
    }
}
