package com.example.bula;


import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class BulaApplicationTests {

    @Test
    public void checkValidPositions1() {
        Core core = new Core();

        core.sp.set(11, 2);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(4, 5);

        assertTrue(nextFPPossibleMoves.size() == 1);
        assertTrue(nextFPPossibleMoves.get(0).position1 == 6);
        assertTrue(nextFPPossibleMoves.get(0).move1 == 4);
        assertTrue(nextFPPossibleMoves.get(0).position2 == 10);
        assertTrue(nextFPPossibleMoves.get(0).move2 == 5);
    }

    @Test
    public void checkValidPositions2() {
        Core core = new Core();

        core.sp.set(11, 1);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(4, 5);


        assertTrue(nextFPPossibleMoves.size() == 3);
    }
/*
 4x5

            13          |           |           |         13|
            X         5 |           |           |         O |
            X         X |O O O 4    |           |         O |
           =================================================

4x5

            13          |           |           |         13|
            X         O |           |           |         O |
            X       4 X |O O O 5    |           |         O |
           =================================================

4x5

            13          |           |           |         13|
            X           |           |           |         O |
            X         X |O O O 4 O O|O O 5      |         O |
           =================================================

4x5

            13          |           |           |         13|
            X           |           |           |         O |
            X         X |O O O O 5 O|O O 4      |         O |
           =================================================

4x5

            13          |           |           |         13|
            X         5 |           |           |         O |
            X       4 X |           |           |         O |
           =================================================


4x5

            13          |           |           |         13|
            X           |           |           |         O |
            X       4 X |O O O O 5  |           |         O |
           =================================================


4x5

            13          |           |           |         13|
            X         5 |           |           |         O |
            X         X |O O O 4    |           |         O |
           =================================================
* */

    @Test
    public void checkValidPositions3() {
        Core core = new Core();

        core.fp.set(11, 1);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(4, 5);


        assertTrue(nextFPPossibleMoves.size() == 7);
    }

    @Test
    public void checkValidPositions4() {
        Core core = new Core();

        core.fp.set(11, 2);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(4, 5);


        assertTrue(nextFPPossibleMoves.size() == 8);
    }
    /*
    4x5

            13          |           |           |         13|
            X         O |           |           |         O |
            X         X |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions5() {
        Core core = new Core();

        core.fp.set(11, 1);
        core.sp.set(11, 1);
        core.spBlockFp.set(11, true);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(4, 5);


        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    /*
    4x5

            13          |           |           |         13|
            X       O O |           |           |         O |
            X       X X |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions6() {
        Core core = new Core();

        core.fp.set(11, 1);
        core.sp.set(11, 1);
        core.spBlockFp.set(11, true);

        core.fp.set(10, 1);
        core.sp.set(10, 1);
        core.spBlockFp.set(10, true);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(4, 5);


        assertTrue(nextFPPossibleMoves.size() == 0);
    }

    /*
    3x1

            13          |           |           |         13|
            X       O   |           |           |         O |
            X       X   |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions7() {
        Core core = new Core();

        core.fp.set(10, 1);
        core.sp.set(10, 1);
        core.spBlockFp.set(10, true);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(3, 1);


        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    /*
    3x1

            13          |           |           |         13|
            X     O     |           |           |         O |
            X     X     |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions8() {
        Core core = new Core();

        core.fp.set(9, 1);
        core.sp.set(9, 1);
        core.spBlockFp.set(9, true);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(3, 1);


        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    /*
    3x1

            13          |           |           |         13|
            X O         |           |           |         O |
            X X         |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions9() {
        Core core = new Core();

        core.fp.set(7, 1);
        core.sp.set(7, 1);
        core.spBlockFp.set(7, true);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(3, 1);


        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    /*
    3x1

            13          |           |           |         13|
            X O         |           |           |         O |
            X O         |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions10() {
        Core core = new Core();
        core.sp.set(7, 2);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(3, 1);

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    /*
    3x1

            13          |           |           |         13|
            X           |           |           |         O |
            X O   O O   |           |           |         O |
           =================================================
     */

    @Test
    public void checkValidPositions11() {
        Core core = new Core();
        core.sp.set(7, 1);
        core.sp.set(9, 1);
        core.sp.set(10, 1);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(3, 1);

        assertTrue(nextFPPossibleMoves.size() == 3);
    }

        /*
    2x1

            13          |           |           |         13|
            X O   O O   |           |           |         O |
            X O   O O   |           |           |         O |
           =================================================

     */

    @Test
    public void checkValidPositions12() {
        Core core = new Core();
        core.sp.set(7, 2);
        core.sp.set(9, 2);
        core.sp.set(10, 2);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 1);

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions13() {
        Core core = new Core();


        //1 x 2 & 2 x 1 same as 3 x 1 & 1 x 1 in this scenario
        //2 x 2 & 2 x 2
        //1 x 3 & 1 x 1
        //1 x 4
        //4 x 1
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);
        ConsoleUI consoleUI = new ConsoleUI(core);
        consoleUI.drawBoard();
        assertTrue(nextFPPossibleMoves.size() == 5);
    }

    @Test
    public void checkValidPositions14() {
        Core core = new Core();
        core.sp.set(10, 2);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions15() {
        Core core = new Core();
        core.sp.set(14, 2);
        core.fp.set(12, 3);
        core.fp.set(6, 1);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);
        ConsoleUI consoleUI = new ConsoleUI(core);
        consoleUI.drawBoard();
        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions16() {
        Core core = new Core();
        core.sp.set(10, 2);
        core.fp.set(6, 1);
        core.fp.set(8, 3);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);
        //ConsoleUI consoleUI = new ConsoleUI(core);
        //consoleUI.drawBoard();

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions17() {
        Core core = new Core();
        core.sp.set(10, 2);
        core.fp.set(6, 3);
        core.fp.set(8, 3);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);

        ConsoleUI consoleUI = new ConsoleUI(core);
        consoleUI.drawBoard();

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions18() {
        Core core = new Core();
        core.sp.set(12, 2);
        core.fp.set(6, 1);
        core.fp.set(8, 1);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);
        ConsoleUI consoleUI = new ConsoleUI(core);
        consoleUI.drawBoard();

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions19() {
        Core core = new Core();
        core.sp.set(12, 2);
        core.fp.set(6, 1);
        core.fp.set(8, 2);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);
        ConsoleUI consoleUI = new ConsoleUI(core);
        consoleUI.drawBoard();

        assertTrue(nextFPPossibleMoves.size() == 1);
    }

    @Test
    public void checkValidPositions20() {
        Core core = new Core();
        core.sp.set(12, 2);
        core.fp.set(6, 3);
        core.fp.set(8, 1);
        List<Core.PositionMove> nextFPPossibleMoves = core.getNextCurrentPlayerPossibleMoves(2, 2);
        ConsoleUI consoleUI = new ConsoleUI(core);
        consoleUI.drawBoard();

        assertTrue(nextFPPossibleMoves.size() == 3);
    }
}
