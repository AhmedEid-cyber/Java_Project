import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * Program to calculate a SHA-512 (or other) digest over a file
 */

/**
 * @author Ahmed Eid Buradhah
 * @student ID: 46145818
 */
public class Digest {
	
	private static final String progName = "Digest";		// Name of the program
	private static final int bufSize = 512;					// Almost any sensible value will work here

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		BufferedInputStream in = null;			// A buffered input stream to read from
		byte[] inputBuffer = new byte[bufSize];	// A buffer for the input read from the file
		int bytesRead = 0;						// Number of bytes read into the input file buffer
		byte[] messageDigest = null;			// A variable for the actual digest value, as an array of bytes
		
		// First, check the user has provided all the required arguments, and if they haven't, tell them then exit
		if(args.length != 1) {
			printUsageMessage(); System.exit(1);
		}

		// Open the input file
		try {
			in = new BufferedInputStream(new FileInputStream(args[0]));
		} catch (FileNotFoundException e) {
			printErrorMessage("Unable to open input file: " + args[0], null);
			System.exit(1);
		}
		
		// Define a variable for the required cryptoprimitive (1 mark)
		MessageDigest md = null;

		// Now, instantiate the required cryptoprimitive (2 marks)
		try {
			md = MessageDigest.getInstance("SHA-1"); // "SHA-256", "SHA3-256"
		} catch (Exception e) {
			printErrorMessage("There is an exception : ", e);
			System.exit(1);
		}
		
		// "Prime the pump" - we've got to read something before we can digest it
		// and not do anything if we read nothing.
		try {
			bytesRead = in.read(inputBuffer);
		} catch (IOException e) {
			printErrorMessage("Error reading input file " + args[0],e); System.exit(1);
		}

		// As long as we've read something, loop around updating the digest value
		// bytesRead will be zero if nothing was read, or -1 on EOF - treat them both the same
		while (bytesRead > 0) {
			
			// Update the digest with the bytes that were read (3 marks)
			md.update(inputBuffer, 0, bytesRead);

			// And read in the next block of the file
			try {
				bytesRead = in.read(inputBuffer);
			} catch (IOException e) {
				printErrorMessage("Error reading input file " + args[0],e); System.exit(1);
			}
		}
		
		// Get the final digest value (1 mark)
		messageDigest = md.digest();
		
		// And print the digest value as a long hex string (1 mark)
		System.out.println(BitDiddler.byteArrayToHexStr(messageDigest));
	}

	/**
	 * Print an error message on stderr, optionally picking up additional detail from
	 * a passed exception
	 * @param errMsg
	 * @param e
	 */
	private static void printErrorMessage(String errMsg, Exception e) {
		System.err.println(errMsg);
		if (e != null) 
			System.err.println(e.getMessage());
	}

	/**
	 * Print a usage message
	 */
	private static void printUsageMessage() {
		System.out.println(progName + " $Revision: 1.0 $: Usage: " + progName + " infile");
	}
	
}
