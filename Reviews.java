//package critics;
package critics;

//Student Ahmed Buradhah(as on eStudent)
//[] Declaration from student that they haven't viewed another person's code for this assignment. Add a x between the brackets

//No built-in library can be used can be used (apart from file reading, which is already done)
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Reviews {

    public int nCharacters = 0; 	//number of characters in the spreadsheet 
    public int nCritics = 0; 	//number of critics in the spreadsheet
    public String[] characters; 	//holds the list of characters, as read from the file
    public int[][] ratings; 		//holds the ratings by 1 or more critics for the characters

	//ratings[0][0] gives the rating for FIRST character by FIRST critic
    //ratings[0][1] gives the rating for FIRST character by SECOND critic
    //...
    //ratings[0][nCritics-1] gives the rating for FIRST character by LAST critic
    //...
    //...
    //ratings[nCharacters-1][0] gives the rating for LAST character by FIRST critic
    //ratings[nCharacters-1][1] gives the rating for LAST character by SECOND critic
    //...
    //ratings[nCharacters-1][nCritics-1] gives the rating for LAST character by LAST critic
    //in general, ratings[i][k] gives rating for character at index i as given by critic at index k
    /**
     * DO NOT MODIFY
     *
     * @param fileBaseName
     * @throws FileNotFoundException
     */
    public Reviews(String fileBaseName) throws FileNotFoundException {
        getRatings(fileBaseName + ".csv");
    }

    /**
     * DO NOT MODIFY This function reads the data from the file supplied and
     * populates the two arrays: characters, ratings
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public void getRatings(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(filename));
        String[] tokens = scanner.nextLine().split(",+"); //ignore header
        nCritics = tokens.length - 1;
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            nCharacters++;
        }
        characters = new String[nCharacters];
        ratings = new int[nCharacters][nCritics];

        scanner = new Scanner(new FileReader(filename));
        scanner.nextLine();
        for (int i = 0; i < characters.length; i++) {
            tokens = scanner.nextLine().split(",+");
            characters[i] = tokens[0];
            for (int k = 1; k < tokens.length; k++) {
                ratings[i][k - 1] = Integer.parseInt(tokens[k]);
            }
        }
    }

    /**
     * ************************************
     * YOUR JOB BEGINS BELOW THIS COMMENT * ************************************
     */
    /**
     * P/CR Let's start easy :)
     *
     * @return the total number of ratings in the spreadsheet
     */
    public int totalRatingsCount() {

        int count = 0;
        for (int i = 1; i <= nCharacters; i++) {

            for (int j = 1; j <= nCritics; j++) {

                count += 1;
            }
        }
        return count; //to be completed
    }

    /**
     * P/CR
     *
     * @param idx
     * @return the average rating for the character at given index, 0 being the
     * first character's index return -1 if index is invalid (no character at
     * that index)
     */
    public double averageRating(int idx) {

        try {
            double sum = 0;
            for (int i = 0; i < nCritics; i++) {

                sum += ratings[idx][i];

            }

            double avg = sum / nCritics;
            return avg; //to be completed
        } catch (ArrayIndexOutOfBoundsException exp) {
            return -1;
        }
    }

    /**
     * P/CR
     *
     * @param target
     * @return index at which target exists, -1 if it doesn't exist. NOTE: use
     * String1.equalsIgnoreCase(String2) to compare two Strings
     */
    public int getIndex(String target) {

        try {
            for (int i = 0; i < nCharacters; i++) {
                if (target.equalsIgnoreCase(characters[i])) {
                    return i;
                }
            }
        } catch (ArrayIndexOutOfBoundsException exp) {
            return -1;
        }
        return -1;
    }

    /**
     * P/CR
     *
     * @param character
     * @return average rating for given character (case-insensitive)
     */
    public double averageRating(String character) {
    	try {
            int temp = -1;
            for (int i = 0; i < nCharacters; i++) {
                if (character.equalsIgnoreCase(characters[i])) {
                    temp = i;
                    break;
                }
            }
            
            double sum = 0;
            if (temp != -1) {
                for (int i = 0; i < nCritics; i++) {
                    sum += ratings[temp][i];
                }
                double avg = sum / nCritics;
                return avg;
            }
            else {
                return -1;
            }
        } catch (ArrayIndexOutOfBoundsException exp) {
            return -1;
        }
    }

    /**
     * P/CR
     *
     * @param criticIndex
     * @return average rating given by the critic at given index, 0 being the
     * first critic's index return -1 if the critic index provided is invalid
     */
    public double averageRatingBy(int criticIndex) {
        try {
            double sum = 0;
            for (int i = 0; i < nCharacters; i++) {
                sum += ratings[i][criticIndex];
            }
            double avg = sum / nCharacters;
            return avg;
        } catch (ArrayIndexOutOfBoundsException exp) {
            return -1;
        }
    }

    /**
     * P/CR
     *
     * @return the percentage of total ratings that are perfect (10)
     */
    public double percentagePerfectRatings() {
        double count = 0;
        for (int i = 0; i < nCharacters; i++) {

            for (int j = 0; j < nCritics; j++) {

                count += 1;
            }
        }

        double perfectCount = 0;
        for (int i = 0; i < nCharacters; i++) {

            for (int j = 0; j < nCritics; j++) {
                if (ratings[i][j] == 10) {
                    perfectCount += 1;
                }
            }
        }
        double percentage = (perfectCount * 100) / count;
        return percentage;
    }

    /**
     * P/CR
     *
     * @return the favorite character of the critics. That is, the character
     * with the highest rating. in case of tie, return the character that exists
     * earliest (above others)
     */
    public String favoriteCharacter() {
        int temp = 0;
        int sum = 0;
        int rating = 0;
        for (int i = 0; i < nCharacters; i++) {
            sum = 0;
            for (int j = 0; j < nCritics; j++) {
                sum += ratings[i][j];
            }
            if (sum > rating) {
                rating = sum;
                temp = i;
            }
        }
        String name = characters[temp];
        return name; //to be completed
    }

    /**
     * P/CR
     *
     * @return the index of the critic who has given the lowest average rating
     */
    public int harshestCriticIndex() {
        double sum = 0;
        double rating = 11;
        double avg;
        int temp = 0;
        for (int i = 0; i < nCritics; i++) {
            sum = 0;
            for (int j = 0; j < nCharacters; j++) {
                sum += ratings[j][i];
            }
            avg = sum / nCharacters;
            if (avg < rating) {
                rating = avg;
                temp = i;
            }
        }
        return temp;
    }

    /**
     * D/HD
     *
     * @return the critic who gives the flattest ratings as explained below.
     * return lowest index in case of tie. 1. for each critic: a. determine the
     * average rating given by a critic. b. add the sum of positive difference
     * between each rating and the average. 2. return the critic with the lowest
     * sum of positive differences
     *
     * For example: First critic (index 0) ratings: 10, 8, 5, 7 (average 7.5;
     * differences: 2.5, 0.5, 2.5, 0.5; sum = 6.0) Second critic (index 1)
     * ratings: 6, 5, 6, 5 (average 5.5; differences: 0.5, 0.5, 0.5, 0.5; sum =
     * 2.0) return 0
     */
    public int flattestCritic() {
        double sum = 0;
        double avg = 0;
        int temp = 0;
        double diff = 0;
        double temp1 = 11;

        for (int i = 0; i < nCritics; i++) {
            sum = 0;
            diff= 0 ;
            for (int j = 0; j < nCharacters; j++) {
                sum += ratings[j][i];
            }
            avg = sum / nCharacters;
            for (int k = 0; k < nCharacters; k++) {
                if (avg > ratings[k][i]) {
                    diff += avg - ratings[k][i];
                } else {
                    diff += ratings[k][i] - avg;
                }
            }
            if (diff < temp1) {
                temp1 = diff;
                temp = i;
            }
        }
        return temp; //to be completed
    }

    /**
     * D/HD
     *
     * @param idx
     * @return details of characters nicely formatted. Assume the length of the
     * longest character name (amongst all the characters) is maxLength. Assume
     * the length of the character at index idx is currentLength You should
     * display the name of character at index idx followed by (maxLength -
     * currentLenght + 1) spaces followed by a colon (:) followed by a space.
     *
     * See expected output sample for further details
     *
     * Use diffchecker.com to compare your output against expected output.
     */
    public String getDetails(int idx) {

    	  int maxLength = 0;
          for (int a = 0; a < nCharacters; a++) {
              if(maxLength < characters[a].length()){
                  maxLength = characters[a].length();
              }
          }
          String s = "";
          int length = characters[idx].length();
          int spaces = maxLength - length +1;
          s = characters[idx];
          for (int a = 0; a < spaces; a++) {
              s += " ";
          }
          s += ":";
          for (int a = 0; a < nCritics; a++) {
              if(ratings[idx][a] < 10){
                  s += "  "+ratings[idx][a]+",";
              }
              else
                  s += " "+ratings[idx][a]+",";
          }
          s = s.substring(0, s.length()-1);
          s += " (Average rating: "+averageRating(idx)+")";
          
          return s;
    
    }

    /**
     * D/HD
     *
     * @param idx
     * @return get average rating with one instance of highest rating and one
     * instance of lowest rating discarded For example, if a character gets
     * ratings 6, 8, 10, 4, 10, 7, 4, 4 You should remove one 10 and one 4,
     * remaining ratings being 6, 8, 10, 7, 4, 4, and the average 39/6 = 6.5
     * return -1 if index is invalid
     */
    public double olympicStyleRating(int idx) {
    	try {
            int temp;
            int[][] ratings = new int[nCharacters][nCritics];
            for (int a = 0; a < nCharacters; a++) {
                for (int b = 0; b < nCritics; b++) {
                    ratings[a][b] = this.ratings[a][b];
                }
            }
           
            for (int i = 0; i < nCritics; i++) {
                for(int j=i+1;j<nCritics;j++){
                if (ratings[idx][i] > ratings[idx][j]) {
                    temp = ratings[idx][i];
                    ratings[idx][i] = ratings[idx][j];
                    ratings[idx][j] = temp;
                }
                }
            }
            
            double sum = 0;
            for (int i = 1; i < nCritics - 1; i++) {
                sum += ratings[idx][i];
            }
            double avg = sum / (nCritics - 2);
            return avg;
        } catch (ArrayIndexOutOfBoundsException exp) {
            return -1;
        }
    }

    /**
     * D/HD
     *
     * @return an array of character names, in order of highest to lowest
     * average ratings
     */
    public String[] mostToLeastFavorite() {
        double[] averages = new double[nCharacters];
        for (int i = 0; i < nCharacters; i++) {
            averages[i] = averageRating(i);
            System.out.print(averages[i]+", ");
        }
        String[] names = new String[nCharacters];
        for (int a = 0; a < nCharacters; a++) {
            names[a] = characters[a];
        }   
        int n = names.length; 
        for (int i = 0; i < n; i++) 
        { 
            for (int j = 0; j < n - i - 1; j++){
                if (averages[j] < averages[j+1]){
                            double temp = averages[j]; 
                            String tempName = names[j];
                            averages[j] = averages[j+1];
                            names[j]=names[j+1];
                            averages[j+1] = temp; 
                            names[j+1]=tempName;
                        }
                    }
        }

        return names; //to be completed
    }

    /**
     * D/HD
     *
     * @return a two dimensional array that contains 11 sub-arrays. sub-array i
     * contains characters (in order of appearance) with average ratings between
     * i (inclusive) and (i+1) exclusive.
     *
     * A sub-array shall be empty if there are no characters in that range.
     *
     * The last sub-array will contain characters that have an average rating of
     * exactly 10.
     */
    public String[][] groupByRatings() {
        String group[][] = new String[11][];
        int k=0;
        try{
            for(double i=0.0;i<11.0;i++){
                int l=0;
                for(int j=0;j<nCharacters;j++){
                    double avg_rating = averageRating(j);
                    if(avg_rating >= i && avg_rating < i+1){
                        l++;
                    }
                }
                group[k]=new String[l];
                if(l!=0){
                    l=0;
                    for(int j=0;j<nCharacters;j++){
                        double avg_rating = averageRating(j);
                        if(avg_rating >= i && avg_rating < i+1){
                            group[k][l]=characters[j];
                            l++;
                        }
                    }
                }
                k++;
            }
        }
        catch(NullPointerException e){
            System.out.println("We have a null pointer exception");
        }
        return group; //to be completed
}

    /**
     * HD
     *
     * @param criticIndex
     * @return an array of characters in order of highest to lowest ratings by
     * the critic at given index return null if critic index is invalid
     */
    public String[] mostToLeastFavoriteByCritic(int criticIndex) {
    	try{
            int[] ratingArray = new int[nCharacters];
            for (int i = 0; i < nCharacters; i++) {
                ratingArray[i] = ratings[i][criticIndex];
            }

            String[] names = new String[nCharacters];
            for (int a = 0; a < nCharacters; a++) {
                names[a] = characters[a];
            }
            int n = names.length; 
            for (int i = 0; i < n; i++) 
                { 
                    for (int j = 0; j < n - i - 1; j++){
                        if (ratingArray[j] < ratingArray[j+1]){
                            int temp = ratingArray[j]; 
                            String tempName = names[j];
                            ratingArray[j] = ratingArray[j+1];
                            names[j]=names[j+1];
                            ratingArray[j+1] = temp; 
                            names[j+1]=tempName;
                        }
                    }
                }

            return names; //to be completed
            }
            catch(Exception exp){
            return null;
            }
    }

		
    public static void main(String[] args) throws FileNotFoundException {

        Reviews r = new Reviews("harryPotter");
        
        System.out.println(r.totalRatingsCount());
        System.out.println(r.averageRating(2));
        System.out.println(r.getIndex("Sokka"));        
        System.out.println(r.averageRating("Zuko"));
        System.out.println(r.averageRatingBy(2));
        System.out.println(r.percentagePerfectRatings());        
        System.out.println(r.favoriteCharacter());
        System.out.println(r.harshestCriticIndex());
        System.out.println(r.flattestCritic());
        System.out.println(r.olympicStyleRating(2));
        
        String[] names = r.mostToLeastFavorite();
        for (int i = 0; i < r.nCharacters; i++) {
            System.out.printf(names[i] + " ");
        }
        System.out.println("");
       
        String[] names1 = r.mostToLeastFavoriteByCritic(0);
        for (int i = 0; i < r.nCharacters; i++) {
            System.out.printf(names1[i] + " ");
        }

    }
    
}