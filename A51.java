package A51;

public class A51 {
	
	public static void main(String[] args) {
		
		String key = "";
		
		int[] regX = { 1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1,0,1 };
		int[] regY = { 1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1,0,0,1,1 };
		int[] regZ = { 1,1,1,0,0,0,0,1,1,1,1,0,0,0,0,1,1,1,1,0,0,0,0 };
		
		System.out.println("Start:");
		System.out.print("x: ");
		printArray(regX);
		System.out.print("y: ");
		printArray(regY);
		System.out.print("z: ");
		printArray(regZ);
		
		for(int i = 0; i < 64; i++) {
			int majority = maj(regX[8], regY[10], regZ[10]);
			if (regX[8] == majority) {
				x(regX);
			}
			if (regY[10] == majority) {
				y(regY);
			}
			if (regZ[10] == majority) {
				z(regZ);
			}
			
			key = Integer.toString(regX[regX.length - 1] ^ regY[regY.length - 1] ^ regZ[regZ.length - 1]) + key; 
		}
		
		System.out.println("End:");
		System.out.print("x: ");
		printArray(regX);
		System.out.print("y: ");
		printArray(regY);
		System.out.print("z: ");
		printArray(regZ);
		
		System.out.println("Key: " + key.substring(32));
		
	}
	
	public static int maj(int x, int y, int z) {
		if ((x == 1 &&  y == 1) || (y == 1 && z == 1) || (z == 1 && x == 1)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public static void shift(int[] reg, int newBit) {
		for (int i = reg.length - 1; i > 0; i--) {
			reg[i] = reg[i-1];
		}
		reg[0] = newBit;
	}
	
	public static int[] x(int[] reg) {
		int newX = reg[13] ^ reg[16] ^ reg[17] ^ reg[18];
		shift(reg, newX);
		return reg;
	}
	
	public static int[] y(int[] reg) {
		int newY = reg[20] ^ reg[21];
		shift(reg, newY);
		return reg;
	}
	
	public static int[] z(int[] reg) {
		int newZ = reg[7] ^ reg[20] ^ reg[21] ^ reg[22];
		shift(reg, newZ);
		return reg;
	}
	
	public static void printArray(int[] arr) {
		for(int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
		}
		System.out.println();
	}
} 
