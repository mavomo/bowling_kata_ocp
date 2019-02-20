package fr.craftingcode.solid;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingGameScoreShould {

    private final BowlingGame game = new BowlingGame();

    @Test
    public void should_create_a_bowling_game() {
        assertThat(game).as("compiles")
                .isNotNull();
    }

    @Test
    public void should_roll_twice_to_score_in_a_frame() {
        game.roll(0);
        game.roll(0);

        assertThat(game.score()).isEqualTo(0);
    }

    @Test
    public void should_sum_all_the_pins_down_per_roll_when_computing_the_score() {
        game.roll(1);
        game.roll(3);

        assertThat(game.score()).isEqualTo(4);
    }

    @Test
    public void should_count_first_pins_down_in_next_roll_when_computing_the_score_a_frame() {
        game.roll(7);
        game.roll(3);
        game.roll(2);
        game.roll(5);
        rollGutterGameNTimes();

        assertThat(game.score())
                .as("F(1) = 7 + 3 + 2 (bonus) && F(2) = 2 + 5")
                .isEqualTo(19);
    }

    @Test
    public void should_count_spare_game_adding_a_bonus() {
        game.roll(7);
        game.roll(3);
        game.roll(2);
        game.roll(5);
        rollGutterGameNTimes();

        assertThat(game.scoreForFrame(1))
                .as("F(1) = 7 + 3 + 2 (bonus) && F(2) = 2 + 5")
                .isEqualTo(12);
    }


    @Test
    public void should_count_the_strike_game_adding_the_two_rolls_of_the_next_frame() {
        game.roll(10);
        game.roll(3);
        game.roll(2);
        game.roll(0);

        rollGutterGameNTimes();

        assertThat(game.scoreForFrame(1))
                .as("F(1) = 10 + 3 (bonus) + 2 (bonus) && F(2) = 3+5")
                .isEqualTo(15);
    }

    @Test
    public void should_score_the_perfect_game() {

        rollThePerfectGame();

        assertThat(game.score())
                .as("sum of all frames = 300")
                .isEqualTo(300);
    }

    private void rollGutterGameNTimes() {
        for (int i = 0; i < 16; i++) {
            game.roll(0);
        }
    }

    private void rollThePerfectGame() {
        for (int i = 0; i < 21; i++) {
            game.roll(10);
        }
    }
}
