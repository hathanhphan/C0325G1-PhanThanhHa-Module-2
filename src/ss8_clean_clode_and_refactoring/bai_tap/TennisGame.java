package ss8_clean_clode_and_refactoring.bai_tap;

public class TennisGame {
    private static final String LOVE = "Love";
    private static final String FIFTEEN = "Fifteen";
    private static final String THIRTY = "Thirty";
    private static final String FORTY = "Forty";
    private static final String ALL = "-All";
    private static final String DEUCE = "Deuce";
    private static final String ADVANTAGE = "Advantage ";
    private static final String WIN_FOR = "Win for ";

    public static String getScore(String player1Name, String player2Name, int player1Score, int player2Score) {
        if (player1Score == player2Score) {
            return getEqualScore(player1Score);
        }

        if (player1Score >= 4 || player2Score >= 4) {
            return getAdvantageOrWinScore(player1Name, player2Name, player1Score, player2Score);
        }

        return getNormalScore(player1Score, player2Score);
    }

    private static String getEqualScore(int score) {
        return switch (score) {
            case 0 -> LOVE + ALL;
            case 1 -> FIFTEEN + ALL;
            case 2 -> THIRTY + ALL;
            case 3 -> FORTY + ALL;
            default -> DEUCE;
        };
    }

    private static String getAdvantageOrWinScore(String player1Name, String player2Name, int player1Score, int player2Score) {
        int scoreDifference = player1Score - player2Score;

        if (scoreDifference == 1) {
            return ADVANTAGE + player1Name;
        } else if (scoreDifference == -1) {
            return ADVANTAGE + player2Name;
        } else if (scoreDifference >= 2) {
            return WIN_FOR + player1Name;
        } else {
            return WIN_FOR + player2Name;
        }
    }

    private static String getNormalScore(int player1Score, int player2Score) {
        return translateScore(player1Score) + "-" + translateScore(player2Score);
    }

    private static String translateScore(int score) {
        return switch (score) {
            case 0 -> LOVE;
            case 1 -> FIFTEEN;
            case 2 -> THIRTY;
            case 3 -> FORTY;
            default -> throw new IllegalArgumentException("Score is invalid: " + score);
        };
    }
}

