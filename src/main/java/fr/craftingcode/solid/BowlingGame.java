package fr.craftingcode.solid;

class BowlingGame {
    static final int NB_FRAMES = 10;
    private int[] rolls = new int[21];
    private int currentRoll;
    private int[] frames = new int[10];


    void roll(int pins) {
        this.rolls[currentRoll++] = pins;
    }

    int score() {
        updateScoreInFrames(NB_FRAMES);
        int score = computeSumScoreOfAllFrames();
        return score;
    }

    private void updateScoreInFrames(int nbFrames) {
        int firstRollInFrame = 0;
        for (int currentFrame = 0; currentFrame < nbFrames; currentFrame++) {
            int scoreOfTheFrame = 0;
            if (isAStrike(firstRollInFrame)) {
                scoreOfTheFrame += 10 + this.rolls[firstRollInFrame + 1] + this.rolls[firstRollInFrame + 2];
            } else if (isASpareGame(firstRollInFrame)) {
                scoreOfTheFrame += 10 + firstRollOfNextFrame(firstRollInFrame);
            } else {
                scoreOfTheFrame = countPinsDownInTwoRolls(firstRollInFrame);
                nbFrames--;
            }
            firstRollInFrame += 2;
            frames[currentFrame] = scoreOfTheFrame;
        }
    }

    private boolean isAStrike(int roll) {
        return this.rolls[roll] == 10;
    }

    private int firstRollOfNextFrame(int roll) {
        return this.rolls[roll + 2];
    }

    private boolean isASpareGame(int firstRollInFrame) {
        return countPinsDownInTwoRolls(firstRollInFrame) == 10;
    }

    private int countPinsDownInTwoRolls(int firstRollInFrame) {
        return this.rolls[firstRollInFrame] + this.rolls[firstRollInFrame + 1];
    }

    private int computeSumScoreOfAllFrames() {
        int score = 0;
        for (int i = 0; i < frames.length; i++) {
            score += frames[i];
        }
        return score;
    }

    int scoreForFrame(int frameNumber) {
        score();
        return this.frames[frameNumber - 1];
    }
}
