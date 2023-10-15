/*
 * A5/1 Algorithm Implementation
 * Created By:
 * 		Shelby Jorgensen
 * 		Nick Newhard
 * 		Miguel Lima
 * For: CS430 Cybersecurity
 */

package A51;

import java.util.ArrayList;

public class A51 {
	
	public static void main(String[] args) {
		
		// Will hold the final key value
		ArrayList<Integer> key = new ArrayList<Integer>();
		int keyLength = 1;
		
		// Provided starting values for registers X, Y, and Z
		int[] regX = { 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1 };
		int[] regY = { 1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1 };
		int[] regZ = { 1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0 };
					
		// Print out starting register values
		System.out.println("Start:");
		System.out.print("x: ");
		printArray(regX);
		System.out.print("y: ");
		printArray(regY);
		System.out.print("z: ");
		printArray(regZ);
		
		// Error handling for any bad values in registers of in key length
		try {
			
			// Iterate through the number of bits in the desired key
			for(int i = 0; i < keyLength; i++) {
				// Find the majority of the registers at this iteration
				int majority = maj(regX[8], regY[10], regZ[10]);
				
				// If a register has a majority bit, XOR to find the new value and update the register
				if (regX[8] == majority) {
					regX = x(regX);
				}
				if (regY[10] == majority) {
					regY = y(regY);
				}
				if (regZ[10] == majority) {
					regZ = z(regZ);
				}
				
				// Update the front of the key with the necessary bit based on XOR
				key.add(0, (regX[18] ^ regY[21] ^ regZ[22])); 
			}
			
			
			// Print all of the final regester values
			System.out.println("End:");
			System.out.print("x: ");
			printArray(regX);
			System.out.print("y: ");
			printArray(regY);
			System.out.print("z: ");
			printArray(regZ);
			
			// Convert key from Integer ArrayList to int Array for printing in correct format
			int[] finalKey = key.stream().mapToInt(Integer::intValue).toArray();
			System.out.print("Key: ");
			printArray(finalKey);
			
		} catch (Exception e) {
			System.out.println("Key must be a positive value, and resisters must have following sizes:");
			System.out.println("Register X: 19");
			System.out.println("Register Y: 21");
			System.out.println("Register Z: 22");
		}
		
	}
	
	/*
	 * Determines if the majority of the 3 provided bits is 1 or 0, and returns the majority value
	 */
	public static int maj(int x, int y, int z) {
		if ((x == 1 &&  y == 1) || (y == 1 && z == 1) || (z == 1 && x == 1)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	/*
	 * Shift all values to the right, and place the new bit at the front
	 */
	public static void shift(int[] reg, int newBit) {
		for (int i = reg.length - 1; i > 0; i--) {
			reg[i] = reg[i-1];
		}
		reg[0] = newBit;
	}
	
	/*
	 * Find the new bit value of register X, with the proper XOR, then place at the front of the register
	 */
	public static int[] x(int[] reg) {
		int newX = reg[13] ^ reg[16] ^ reg[17] ^ reg[18];
		shift(reg, newX);
		return reg;
	}
	
	/*
	 * Find the new bit value of register Y, with the proper XOR, then place at the front of the register
	 */
	public static int[] y(int[] reg) {
		int newY = reg[20] ^ reg[21];
		shift(reg, newY);
		return reg;
	}
	
	
	/*
	 * Find the new bit value of register Z, with the proper XOR, then place at the front of the register
	 */
	public static int[] z(int[] reg) {
		int newZ = reg[7] ^ reg[20] ^ reg[21] ^ reg[22];
		shift(reg, newZ);
		return reg;
	}
	
	/*
	 * Print out the values of the provided array
	 */
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}
} 
