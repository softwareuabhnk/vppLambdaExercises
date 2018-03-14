package exercise;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;

public class ExerciseLambda {

	/*
	 * This is an exercise just to get your feet wet and try some lambda
	 * expressions. Make use of the collection streams to finish these exercises.
	 * (List<String> yourCollection; yourCollection.stream()) The stream have
	 * methods such as .filter(Predicate), .map(Function), .sorted(Comparator),
	 * .reduce() .sum/.average/.count .collect(Collector) (Collectors.asList())
	 */

	/*
	 * Initial set of words to work with.
	 */
	static List<String> listOfWords = Arrays.asList("every", "problem", "in", "computer", "science", "can", "be",
			"solved", "by", "adding", "another", "level", "of", "indirection", "except", "too", "many", "levels", "of",
			"indirection");

	/*
	 * Print out all the words in listOfWords
	 */
	@Test
	public void printAllWords() {
		listOfWords.stream().forEach(s -> System.out.println(s));
	}

	/*
	 * Create a string that consists of the first letter of each word in the list of
	 * Strings.
	 */
	@Test
	public void stringOfFirstLetterFromEveryWord() {
		
		Function<String, String> firstChar = word -> {
			return word.substring(0,1);
		};
		
		String string = "";
		for (String item : listOfWords) {
			string = string.concat(firstChar.apply(item));
		}	
		
		assertEquals("epicscbsbaaloietmloi", string);
	}

	// Exercise 2: Convert all words in wordList to upper case
	@Test
	public void makeUpperCase() {

		List<String> myList = listOfWords.stream().map(word -> word.toUpperCase()).collect(Collectors.toList());

		assertEquals(Arrays.asList("EVERY", "PROBLEM", "IN", "COMPUTER", "SCIENCE", "CAN", "BE", "SOLVED", "BY",
				"ADDING", "ANOTHER", "LEVEL", "OF", "INDIRECTION", "EXCEPT", "TOO", "MANY", "LEVELS", "OF",
				"INDIRECTION"), myList);
	}

	/*
	 * Find all the words in wordList that have even length
	 */
	@Test
	public void findEvenLengthWords() {
	
		// Create instance of Predicate named evenLengthWords
		Predicate<String> evenLengthWords = word -> {
			return (word.length() % 2) == 0; 
			};
			
			List<String> output = listOfWords.stream().filter(evenLengthWords).collect(Collectors.toList());
			
			assertEquals(Arrays.asList("in", "computer", "be", "solved", "by",
					"adding", "of", "except", "many", "levels", "of"), output);
		 
			// Method test in  built in Interface of type predicate is used to test if 
			// my name Nils consists of an even number of characters.
			System.out.println(evenLengthWords.test("Nils"));
	}

	/*
	 * Count the words in wordList
	 */
	@Test
	public void countNumberOfWords() {
		
		int count = listOfWords.size();
		
		assertEquals(20, count);
	}

	/*
	 * Count the total amount of characters in the words in wordList
	 */
	@Test
	public void countNumberOfCharactersInWords() {
		
		Function<String, Integer> noChars = word -> {
			return word.length();
		};
		
		ToIntFunction<String> chars = word -> {
			return word.length();
		};
		
		int countChars = 0;
		for (String item : listOfWords) {
			countChars = countChars + noChars.apply(item);
		}	
			
		int noOfChars = listOfWords.stream().mapToInt(chars).sum();
		
		assertEquals(105, noOfChars);
	}

	/*
	 * Find the first square that is divisible by five
	 */
	@Test
	public void findFirstSquareThatIsDivisibleBy5() {
		
			IntPredicate firstSquare = number -> {
			return (Math.sqrt(number)) % 5 == 0; 
			};
		
				
			try {
				final int first = IntStream.range(1, 100).filter(firstSquare).findFirst().getAsInt();
		
				assertEquals(25, first);
			}
			catch ( NoSuchElementException e) {
				System.out.println("Exception: NoSuchElementException");
		 
			} 
	}

	/*
	 * Join the second, third and forth strings of the list into a single string,
	 * where each word is separated by a hyphen (-). Print the resulting string.
	 * (Hint: skip/limit)
	 */
	@Test
	public void joinChosenStringsAndSeparateByHyphen() {
		
		List<String> list = Arrays.asList("The", "quick", "brown", "fox", "jumped", "over", "the", "lazy", "dog");

		String merged = list.stream().skip(1).limit(3).collect(Collectors.joining("-"));
		
		assertEquals("quick-brown-fox", merged);
		
	}

	/*
	 * Convert every key-value pair of the map into a string and append them all
	 * into a single string, in iteration order. Hint: StringBuilder
	 */
	@Test
	public void mapTheKeyValuePairsToString() {
		Map<String, Integer> map = new TreeMap<>();
		
		map.put("c", 3);
		map.put("b", 2);
		map.put("a", 1);
	
		
		StringBuilder sb = new StringBuilder();
		
		for (Map.Entry<String, Integer> entry : map.entrySet())
		{
			sb.append(entry.getKey());
		    sb.append(entry.getValue());    
		}
		
		assertEquals("a1b2c3", sb.toString());
	}

	/**
	 * Create a new thread that prints the numbers from the list.
	 */
	@Test
	public void printNumbersFromNewThread() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// TODO Thread t;
		fail();
	}
}
