import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws Exception {
        File file = new File("movies.txt");
        Scanner scanner = new Scanner(file);

        ArrayList<String> movieTitles = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String currentLine = scanner.nextLine();
            movieTitles.add(currentLine.trim());
        }
        int rnd = new Random().nextInt(movieTitles.size() - 1);
        String selectedMovie = movieTitles.get(rnd);
        String selectedMovieUnderscore = getTitleInUnderscore(selectedMovie);
        System.out.println(selectedMovie);
        System.out.println(selectedMovieUnderscore);

        Scanner sc = new Scanner(System.in);
        int counterMistakes = 0;
        while (counterMistakes <= 10) {
            System.out.println("Guess a letter: ");
            char c = sc.next().charAt(0);
            selectedMovieUnderscore = correctName(c, selectedMovie, selectedMovieUnderscore);

            if (!selectedMovie.contains(Character.toString(c))) {
                counterMistakes++;
            }
            System.out.println("You are guessing:" + selectedMovieUnderscore);
            System.out.println("You have guessed " + counterMistakes + " wrong letters.");

            if (selectedMovieUnderscore.equals(selectedMovie)) {
                System.out.println("You win");
                System.out.println("You have guessed " + selectedMovie + " correctly.");
                break;
            }
            
        }

    }

    public static String getTitleInUnderscore(String movieTitle) {

        return movieTitle.replaceAll("[a-zA-Z]", "_");
    }

    public static String correctName(char letter, String movieTitle, String movieTitleUnderscore) {
        int index = -1;
        StringBuilder movieTitleBuilder = new StringBuilder(movieTitleUnderscore);
        while ((index = movieTitle.indexOf(letter, index + 1)) != -1) {
            movieTitleBuilder.setCharAt(index, letter);
        }

        return movieTitleBuilder.toString();
    }
}