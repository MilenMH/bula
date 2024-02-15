package com.example.bula;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Core {

    public static final int MAX_POS = 30;
    public List<Integer> fp;

    public List<Integer> sp;

    public List<Boolean> fpBlockSp;

    public List<Boolean> spBlockFp;

    public int CurrentPlayer;

    public List<PositionMove> fpMoves;
    public List<PositionMove> spMoves;

    public Core(List<Integer> fp, List<Integer> sp, List<Boolean> fpBlockSp, List<Boolean> spBlockFp) {
        this.fp = fp;
        this.sp = sp;
        this.fpBlockSp = fpBlockSp;
        this.spBlockFp = spBlockFp;
        CurrentPlayer = 1;
        this.fpMoves = new ArrayList<>();
        this.spMoves =  new ArrayList<>();
    }

    public Core() {
        this.fp = new ArrayList<>();
        this.sp = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            this.fp.add(0);
        }
        this.fp.add(15);
        for (int i = 1; i <= 29; i++) {
            this.fp.add(0);
        }

        for (int i = 1; i <= 29; i++) {
            this.sp.add(0);
        }
        this.sp.add(15);
        for (int i = 0; i < 6; i++) {
            this.sp.add(0);
        }

        this.fpBlockSp = new ArrayList<>();
        this.spBlockFp = new ArrayList<>();

        for (int i = 0; i < 36; i++) {
            this.fpBlockSp.add(false);
            this.spBlockFp.add(false);
        }
        this.fpMoves = new ArrayList<>();
        this.spMoves =  new ArrayList<>();
        CurrentPlayer = 1;
    }

    public List<PositionMove> getNextCurrentPlayerPossibleMoves(int die1, int die2) {
        List<PositionMove> resList = new ArrayList<>();

        if (die1 != die2) {
            boolean hasValidMove = false;

            hasValidMove = poolMoveDie1AndDie2AfterThat(die1, die2, resList, hasValidMove);

            hasValidMove = poolMoveDie2AndDie1AfterThat(die1, die2, resList, hasValidMove);

            hasValidMove = poolMoveDie1AnotherPoleMoveDie2(die1, die2, resList, hasValidMove);

            if (!hasValidMove) {
                poolMoveDie1(die1, resList);

                poolMoveDie2(die2, resList);
            }
        }
        if (die1 == die2) {
            boolean hasValidMove = false;

            hasValidMove = poolMoveDie1FourTimes(die1, resList, hasValidMove);

            hasValidMove = fourPoolsMoveDie1Once(die1, resList, hasValidMove);

            hasValidMove = onePoolMoveTreeTimesDie1AndAnotherPoolMoveDie1Once(die1, resList, hasValidMove);

            hasValidMove = onePoolMoveTwoTimesDie1AndAnotherTwoPoolsMoveDie1OnceFromTheSamePosition(die1, resList, hasValidMove);


            for (int i = 0; i < fp.size(); i++) {
                for (int y = 0; y < fp.size(); y++) {
                    if (fp.get(i) >= 3  && sp.get(i ) < 2 && !spBlockFp.get(i)
                            && fp.get(y) >= 1 && sp.get(y ) < 2 && !spBlockFp.get(y)
                            && i != y
                            && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                            && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                            && (i + die1) < fp.size() && (y + die1) < fp.size()
                            && i < MAX_POS && y < MAX_POS) {
                        hasValidMove = true;
                        PositionMove positionMove = new PositionMove(i, die1, i , die1, i , die1, y, die1, "3 X 1 & 1 X 1");
                        resList.add(positionMove);
                    }
                }
            }


            for (int i = 0; i < fp.size(); i++) {
                for (int y = 0; y < fp.size(); y++) {
                    if (fp.get(i) >= 2
                            && fp.get(y) >= 2
                            //&& i != y
                            && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                            && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                            && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                            && sp.size() > (y + 2 * die1) && sp.get(y + 2 * die1) < 2 && !spBlockFp.get(y + 2 * die1)
                            && (i + 2 * die1) < fp.size() && (y + 2 * die1) < fp.size()
                            && i < MAX_POS && y < MAX_POS) {
                        hasValidMove = true;
                        PositionMove positionMove = new PositionMove(i, die1, i + die1, die1, y, die1, y + die1, die1, "2 X 2");
                        resList.add(positionMove);
                    }
                }
            }

            if (!hasValidMove) {

                for (int i = 0; i < fp.size(); i++) {
                    if (fp.get(i) >= 1 && sp.get(i ) < 2 && !spBlockFp.get(i)
                            && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                            && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                            && sp.size() > (i + 3 * die1) && sp.get(i + 3 * die1) < 2 && !spBlockFp.get(i + 3 * die1)
                            && (i + 3 * die1) < fp.size()
                            && i < MAX_POS) {
                        hasValidMove = true;
                        PositionMove positionMove = new PositionMove(i, die1, i + die1, die1, i + 2 * die1, die1, "1 X 3");
                        resList.add(positionMove);
                    }
                }

                for (int i = 0; i < fp.size(); i++) {
                    if (fp.get(i) > 2 && sp.get(i ) < 2 && !spBlockFp.get(i)
                            && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                            && (i + die1) < fp.size()
                            && i < MAX_POS) {
                        hasValidMove = true;
                        PositionMove positionMove = new PositionMove(i, die1, i, die1, i, die1, "3 X 1");
                        resList.add(positionMove);
                    }
                }

                for (int i = 0; i < fp.size(); i++) {
                    for (int y = 0; y < fp.size(); y++) {
                        if (fp.get(i) >= 1 && sp.get(i ) < 2 && !spBlockFp.get(i)
                                && fp.get(y) >= 1 && sp.get(y ) < 2 && !spBlockFp.get(y)
                                && i != y
                                && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                                && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                                && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                                && (i + 2 * die1) < fp.size() && (y + die1) < fp.size()
                                && i < MAX_POS && y < MAX_POS) {
                            hasValidMove = true;
                            PositionMove positionMove = new PositionMove(i, die1, i + die1, die1, y, die1, "1 X 2 & 1 X 1");
                            resList.add(positionMove);
                        }
                    }
                }

                for (int i = 0; i < fp.size(); i++) {
                    for (int y = 0; y < fp.size(); y++) {
                        if (fp.get(i) >= 1 && sp.get(i ) < 2 && !spBlockFp.get(i)
                                && fp.get(y) >= 2
                                && i != y
                                && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                                && sp.size() > (i + 2 * die1) && (sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1))
                                && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                                && (i + 2 * die1) < fp.size() && (y + die1) < fp.size()
                                && i < MAX_POS && y < MAX_POS) {
                            hasValidMove = true;
                            PositionMove positionMove = new PositionMove(i, die1, i + die1, die1, y, die1, "1 X 1 & 2 X 1");
                            resList.add(positionMove);
                        }
                    }
                }

                if (!hasValidMove) {

                    for (int i = 0; i < fp.size(); i++) {
                        if (fp.get(i) >= 1 && sp.get(i ) < 2 && !spBlockFp.get(i)
                                && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                                && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                                && (i + 2 * die1) < fp.size()
                                && i < MAX_POS) {
                            hasValidMove = true;
                            PositionMove positionMove = new PositionMove(i, die1, i + die1, die1, "1 X 2");
                            resList.add(positionMove);
                        }
                    }

                    for (int i = 0; i < fp.size(); i++) {
                        if (fp.get(i) > 2
                                && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                                && (i + die1) < fp.size()
                                && i < MAX_POS) {
                            hasValidMove = true;
                            PositionMove positionMove = new PositionMove(i, die1, i, die1, i, die1, "2 X 1");
                            resList.add(positionMove);
                        }
                    }


                    for (int i = 0; i < fp.size(); i++) {
                        for (int y = 0; y < fp.size(); y++) {
                            if (fp.get(i) >= 1 && sp.get(i ) < 2 && !spBlockFp.get(i)
                                    && fp.get(y) >= 1 && sp.get(y ) < 2 && !spBlockFp.get(y)
                                    && i != y
                                    && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                                    && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                                    && (i + die1) < fp.size() && (y + die1) < fp.size()
                                    && i < MAX_POS && y < MAX_POS) {
                                hasValidMove = true;
                                PositionMove positionMove = new PositionMove(i, die1, y, die1, "1 X 1 & 1 X 1");
                                resList.add(positionMove);
                            }
                        }
                    }

                    if (!hasValidMove) {

                        for (int i = 0; i < fp.size(); i++) {
                            if (fp.get(i) != 0 && sp.get(i ) < 2 && !spBlockFp.get(i)
                                    && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                                    && (i + die1) < fp.size()
                                    && i < MAX_POS) {
                                PositionMove positionMove = new PositionMove(i, die1, "1 X 1");
                                resList.add(positionMove);
                            }
                        }
                    }
                }
            }
        }


        return resList;
    }

    private boolean onePoolMoveTwoTimesDie1AndAnotherTwoPoolsMoveDie1OnceFromTheSamePosition(int die1, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            for (int y = 0; y < fp.size(); y++) {
                if (fp.get(i) >= 1  && sp.get(i ) < 2 && !spBlockFp.get(i)
                        && fp.get(y) >= 2  && sp.get(y ) < 2 && !spBlockFp.get(y)
                        && ((i == y && fp.get(i) > 2) || (i != y ))
                        && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                        && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                        && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                        && (i + 2 * die1) < fp.size() && (y + die1) < fp.size()
                        && i < MAX_POS && y < MAX_POS) {
                    hasValidMove = true;
                    PositionMove positionMove = new PositionMove(
                            i, die1, i + die1, die1, y, die1, y, die1, "1 X 2 & 2 X 1");
                    resList.add(positionMove);
                }
            }
        }
        return hasValidMove;
    }

    private boolean onePoolMoveTreeTimesDie1AndAnotherPoolMoveDie1Once(int die1, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            for (int y = 0; y < fp.size(); y++) {
                if (fp.get(i) >= 1  && sp.get(i ) < 2 && !spBlockFp.get(i)
                        && fp.get(y) >= 1 && sp.get(y ) < 2 && !spBlockFp.get(y)
                        && ((i == y && fp.get(i) > 1) || (i != y ))
                        && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                        && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                        && sp.size() > (i + 3 * die1) && sp.get(i + 3 * die1) < 2 && !spBlockFp.get(i + 3 * die1)
                        && sp.size() > (y + die1) && sp.get(y + die1) < 2 && !spBlockFp.get(y + die1)
                        && (i + 3 * die1) < fp.size() && (y + die1) < fp.size()
                        && i < MAX_POS && y < MAX_POS) {
                    hasValidMove = true;
                    PositionMove positionMove = new PositionMove(
                            i, die1, i + die1, die1, i + 2 * die1, die1, y, die1, "1 X 3 & 1 X 1");
                    resList.add(positionMove);
                }
            }
        }
        return hasValidMove;
    }

    private boolean fourPoolsMoveDie1Once(int die1, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            if (fp.get(i) > 3
                    && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                    && (i + die1) < fp.size()
                    && i < MAX_POS) {
                hasValidMove = true;
                PositionMove positionMove = new PositionMove(i, die1, i, die1, i, die1, i, die1, "4 X 1");
                resList.add(positionMove);
            }
        }
        return hasValidMove;
    }

    private boolean poolMoveDie1FourTimes(int die1, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            if (fp.get(i) != 0
                    && fp.get(i) >= 1 && sp.get(i) < 2 && !spBlockFp.get(i) // текущата позиция не е блокирана
                    && sp.size() > (i + die1) && sp.get(i + die1) < 2 && !spBlockFp.get(i + die1)
                    && sp.size() > (i + 2 * die1) && sp.get(i + 2 * die1) < 2 && !spBlockFp.get(i + 2 * die1)
                    && sp.size() > (i + 3 * die1) && sp.get(i + 3 * die1) < 2 && !spBlockFp.get(i + 3 * die1)
                    && sp.size() > (i + 4 * die1) && sp.get(i + 4 * die1) < 2 && !spBlockFp.get(i + 4 * die1)
                    && (i + 4 * die1) < fp.size()
                    && i < MAX_POS) {
                hasValidMove = true;
                PositionMove positionMove = new PositionMove(i, die1, i + die1, die1, i + 2 * die1, die1, i + 3 * die1, die1,
                        "1 X 4");
                resList.add(positionMove);
            }
        }
        return hasValidMove;
    }

    private void poolMoveDie2(int die2, List<PositionMove> resList) {
        for (int i = 0; i < fp.size(); i++) {
            if (fp.get(i) != 0  && sp.get(i ) < 2 && !spBlockFp.get(i)
                    && (i + die2) < fp.size()
                    && !spBlockFp.get(i + die2)
                    && !spBlockFp.get(i)
                    && (sp.get(i + die2) == 0 || ((sp.get(i + die2) == 1 && !spBlockFp.get(i + die2))))
                    && (i + die2) < fp.size()
                    &&( i < MAX_POS)) {

                PositionMove positionMove = new PositionMove(i, die2, "i, die2 {" + i + ", " + die2 + "}");
                resList.add(positionMove);
            }
        }
    }

    private void poolMoveDie1(int die1, List<PositionMove> resList) {
        for (int i = 0; i < fp.size(); i++) {
            if (fp.get(i) != 0  && sp.get(i ) < 2 && !spBlockFp.get(i)
                    && (i + die1) < fp.size()
                    && !spBlockFp.get(i + die1)
                    && !spBlockFp.get(i)
                    && (sp.get(i + die1) == 0 || ((sp.get(i + die1) == 1 && !spBlockFp.get(i + die1))))
                    && (i + die1) < fp.size()
                    &&( i < MAX_POS)) {

                PositionMove positionMove = new PositionMove(i, die1, "i, die1 {" + i + ", " + die1 + "}");
                resList.add(positionMove);
            }
        }
    }

    private boolean poolMoveDie1AnotherPoleMoveDie2(int die1, int die2, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            for (int y = 0; y < fp.size(); y++) {
                if (
                        i != y
                        && fp.get(i) != 0 && fp.get(y) != 0
                        && sp.get(i ) < 2 && !spBlockFp.get(i)
                        && sp.get(y ) < 2 && !spBlockFp.get(y)
                        && (i + die1) < fp.size()
                        && !spBlockFp.get(i + die1)
                        && (sp.get(i + die1) == 0 || ((sp.get(i + die1) == 1 && !spBlockFp.get(i + die1))))
                        && (y + die2) < fp.size()
                        && !spBlockFp.get(y + die2)
                        && (sp.get(y + die2) == 0 || ((sp.get(y + die2) == 1 && !spBlockFp.get(y + die2))))
                        && (i + die1) < fp.size() && (y + die2) < fp.size()
                        &&( i < MAX_POS && y < MAX_POS)) {

                    hasValidMove = true;
                    PositionMove positionMove = new PositionMove(i, die1, y, die2,
                            "i, die1, y, die2 {" + i + ", " + die1 + ", " + y + ", " + die2 + "}");
                    resList.add(positionMove);
                }
            }
        }
        return hasValidMove;
    }

    private boolean poolMoveDie2AndDie1AfterThat(int die1, int die2, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            if (fp.get(i) != 0  && sp.get(i ) < 2 && !spBlockFp.get(i)
                    && (i + die1 + die2) < fp.size()
                    && !spBlockFp.get(i + die2)
                    && (sp.get(i + die2) == 0 || ((sp.get(i + die2) == 1 && !spBlockFp.get(i + die2))))
                    && (sp.get(i + die1 + die2) == 0 || ((sp.get(i + die1 + die2) == 1 && !spBlockFp.get(i + die1 + die2))))
                    && (i + die1 + die2) < fp.size()
                    && i < MAX_POS) {

                hasValidMove = true;
                PositionMove positionMove = new PositionMove(i, die2, i + die2, die1,
                        "i, die2, i + die2, die1{" + i + ", " + die1 + ", " + (i + die1) + ", " + die2 + "}");
                resList.add(positionMove);
            }
        }
        return hasValidMove;
    }

    private boolean poolMoveDie1AndDie2AfterThat(int die1, int die2, List<PositionMove> resList, boolean hasValidMove) {
        for (int i = 0; i < fp.size(); i++) {
            if (fp.get(i) != 0  && sp.get(i ) < 2 && !spBlockFp.get(i)
                    && (i + die1 + die2) < fp.size()
                    && !spBlockFp.get(i + die1)
                    && (sp.get(i + die1) == 0 || ((sp.get(i + die1) == 1 && !spBlockFp.get(i + die1))))
                    && (sp.get(i + die1 + die2) == 0 || ((sp.get(i + die1 + die2) == 1 && !spBlockFp.get(i + die1 + die2))))
                    && (i + die1 + die2) < fp.size()
                    && i < MAX_POS) {

                hasValidMove = true;
                PositionMove positionMove = new PositionMove(i, die1, i + die1, die2,
                        "i, die1, i + die1, die2{" + i + ", " + die1 + ", " + (i + die1) + ", " + die2 + "}" );
                resList.add(positionMove);

            }
        }
        return hasValidMove;
    }

    public void switchPlayersPerspective() {
        List<Integer> temp = new ArrayList<>(fp);
        fp = new ArrayList<>(sp);
        sp = new ArrayList<>(temp);
        Collections.reverse(fp);
        Collections.reverse(sp);

        List<Boolean> tempB = new ArrayList<>(fpBlockSp);
        fpBlockSp = new ArrayList<>(spBlockFp);
        spBlockFp = new ArrayList<>(tempB);
        Collections.reverse(fpBlockSp);
        Collections.reverse(spBlockFp);

        boolean changed = false;
        if (CurrentPlayer == 1) {
            CurrentPlayer = 2;
            changed = true;
        }
        if (CurrentPlayer == 2 && !changed) {
            CurrentPlayer = 1;
        }
    }

    public void performMove(PositionMove positionMove, int die1, int die2) {

        try{
            positionMove.die1 = die1;
            positionMove.die2 = die2;

            if (positionMove.position1 != 0 && positionMove.move1 != 0) {
                performSemiMoveUnblockBlock(positionMove.position1, positionMove.move1);
                positionMove.move1Performed = true;
            }

            if (positionMove.position2 != 0 && positionMove.move2 != 0) {
                performSemiMoveUnblockBlock(positionMove.position2, positionMove.move2);
                positionMove.move2Performed = true;
            }

            if (positionMove.position3 != 0 && positionMove.move3 != 0) {
                performSemiMoveUnblockBlock(positionMove.position3, positionMove.move3);
                positionMove.move3Performed = true;
            }

            if (positionMove.position4 != 0 && positionMove.move4 != 0) {
                performSemiMoveUnblockBlock(positionMove.position4, positionMove.move4);
                positionMove.move4Performed = true;
            }

            if (this.CurrentPlayer == 1) {
                this.fpMoves.add(positionMove);
            }
            if (this.CurrentPlayer == 2){
                this.spMoves.add(positionMove);
            }
        }catch (Exception e){
            System.out.println(positionMove.helper);
            throw e;
        }
    }

    private void performSemiMoveUnblockBlock(int currentPosition, int currentMove) {
        if (fpBlockSp.get(currentPosition) && fp.get(currentPosition) == 1) {
            fpBlockSp.set(currentPosition, false);
        }

        fp.set(currentPosition, fp.get(currentPosition) - 1);
        fp.set(currentPosition + currentMove, fp.get(currentPosition + currentMove) + 1);
        if (sp.get(currentPosition + currentMove) > 0) {
            fpBlockSp.set(currentPosition + currentMove, true);
        }
    }

    public boolean playerWon() {
        for (int i = 0; i < MAX_POS; i++){
            if (fp.get(i) != 0){
                return false;
            }
        }
        return true;
    }

    class PositionMove {
        public int position1;
        public int move1;
        public int position2;
        public int move2;
        public int position3;
        public int move3;
        public int position4;
        public int move4;

        public boolean move1Performed;
        public boolean move2Performed;
        public boolean move3Performed;
        public boolean move4Performed;
        public int die1;
        public int die2;
        public String helper;

        public PositionMove(int position1, int move1, int position2, int move2, String helper) {
            this.position1 = position1;
            this.move1 = move1;
            this.position2 = position2;
            this.move2 = move2;
            this.helper = helper;
            this.move1Performed = false;
            this.move2Performed = false;
            this.move3Performed = false;
            this.move4Performed = false;
        }

        public PositionMove(int position1, int move1, int position2, int move2, int position3, int move3, int position4, int move4, String helper) {
            this.position1 = position1;
            this.move1 = move1;
            this.position2 = position2;
            this.move2 = move2;
            this.position3 = position3;
            this.move3 = move3;
            this.position4 = position4;
            this.move4 = move4;
            this.helper = helper;
            this.move1Performed = false;
            this.move2Performed = false;
            this.move3Performed = false;
            this.move4Performed = false;
        }

        public PositionMove(int position1, int move1, int position2, int move2, int position3, int move3, String helper) {
            this.position1 = position1;
            this.move1 = move1;
            this.position2 = position2;
            this.move2 = move2;
            this.position3 = position3;
            this.move3 = move3;
            this.helper = helper;
            this.move1Performed = false;
            this.move2Performed = false;
            this.move3Performed = false;
            this.move4Performed = false;
        }

        public PositionMove(int position1, int move1, String helper) {
            this.position1 = position1;
            this.move1 = move1;
            this.helper = helper;
            this.move1Performed = false;
            this.move2Performed = false;
            this.move3Performed = false;
            this.move4Performed = false;
        }

        @Override
        public String toString() {
            return "PositionMove{" +
                    "position1=" + position1 +
                    ", move1=" + move1 +
                    ", position2=" + position2 +
                    ", move2=" + move2 +
                    ", position3=" + position3 +
                    ", move3=" + move3 +
                    ", position4=" + position4 +
                    ", move4=" + move4 +
                    ", move1Performed=" + move1Performed +
                    ", move2Performed=" + move2Performed +
                    ", move3Performed=" + move3Performed +
                    ", move4Performed=" + move4Performed +
                    ", helper='" + helper + '\'' +
                    '}';
        }
    }
}
