package MonitoredAssignment2;

public class Mult {
	public static void main(String[] args) {

		String x = Terminal.getString("Please Enter a Numeric String: ");
		String y = Terminal.getString("Please Enter a Numeric String: ");

		Terminal.put("The Product of the Strings is = " +mult(x, y));
	}

	// In this method, the sign of the product of the strings is determined.
	//Also the method directs the implementation of the Multiplication.
	public static String mult(String op1, String op2) {

		String result = "";
		String signOfResult;

		String signOfOp1 = sign(op1);
		String signOfOp2 = sign(op2);

		// If the strings have different signs, then the sign of the result is '-' negative.
		if (!signOfOp1.equals(signOfOp2)) {
			signOfResult = "-";
		}
		//Otherwise, the sign of the result is '+' positive.
		else {
			signOfResult = "+";
		}

		// Here we get the Strings without sign in case the have any. 
		String op1Modified = stringWithoutSign(op1);
		String op2Modified = stringWithoutSign(op2);

		// This conditional statements are for the order of the operands in when calling the product method
		// Always the Shorter string is the second parameter in the product method.  
		if (op1Modified.length() < op2Modified.length()) {
			result = product(op2Modified, op1Modified);
		}
		else {
			result = product(op1Modified, op2Modified);
		}

		return signOfResult + result;
	}

	private static String product(String op1, String op2) {

		String result = "";

		//The array is used to store the intermediate results of the multiplication.
		//The size of the array is always the length of the shorter operand which is op2.
		int sizeOfArray = 0;
		sizeOfArray = op2.length();

		String sumForMult [] = new String [sizeOfArray];

		//The carryIn is used when the product of two digits is > 9
		//The countForKeepingPosition is used for adding zero(s) at the end of the intermediate results when switching from one digit to the next one.
		int carryIn = 0;
		int countForKeepingPosition = 0;
		int indexOfArray = 0;


		for (int index2 = op2.length() -1; index2 >= 0; ) {

			for (int index1 = op1.length() -1;index1 >=0; --index1) {

				int valueAtOp1 = op1.charAt(index1) - '0';
				int valueAtOp2 = op2.charAt(index2) - '0';

				int mult = (valueAtOp1 * valueAtOp2) + carryIn;

				//The temporaryStorage String to determines the value of the carry in for the next cycle of the loop,
				//and to determines which digit should be in the intermediate result.
				String temporaryStorage = "";
				temporaryStorage = mult + temporaryStorage; 

				//If the length of the temporaryStorage > 1, which means that the product of those digits > 9.
				//So the carryIn value resides at the index 0 of the temporaryStorage,
				// and the digit which will be passed to the intermediate result resides at the index 1.
				if (index1 != 0 && temporaryStorage.length() > 1) {
					carryIn = temporaryStorage.charAt(0) - '0';
					mult = temporaryStorage.charAt(1) - '0';
				}

				//If the length of the temporaryStorage == 1, which means that the product of those digits <= 9.
				//So the carryIn value is 0,
				//and the digit which will be passed to the intermediate result resides at the index 0 of the temporaryStorage. 
				if (index1 != 0 && temporaryStorage.length() == 1) {
					carryIn = 0;
					mult = temporaryStorage.charAt(0) - '0';
				}

				//When index1 == 0, then we must write the product of the specified digits as it is,
				//because there will be no other digit to continue adding the carryIn.
				//That is why in this case the carry in must be 0.
				if (index1 == 0) {
					carryIn =0;
				}
				result = mult + result;

				//Whenever index1 == 0, that means that the loop is done with multiplying the specified digit of the second operand by the all the digits of the first operand.
				//So we have to decrement the value of index2 so we can do the second cycle of the multiplication.
				//Here the method addZerosForPosiiton is called, if the intermediate results needs to be extended with zero(s) to maintain the position.
				//After each time the method addZerosForPosiiton is called, the countForKeepingPosition integer is incremented for the next intermediate result.
				//Then the result is stored in the array for future addition to get the final results.
				//After that the index of the array for future use in case there is another intermediate result.
				//The result is set to "" just to make sure that there will not be any mistakes.
				if (index1 == 0) {
					--index2;
					result = addZerosForPosiiton(result,countForKeepingPosition);
					++countForKeepingPosition;
					sumForMult[indexOfArray] = result;
					++ indexOfArray;
					result = "";
				}
			}
		}

		//After the completion of multiplying all the digits of the the first operand by all the digits of the second operand,
		//We have to sum the intermediate result(s) by using the addition method which was previously used in the first assignment.
		//Initialising the result with the value "0" to avoid any mistakes.
		result = "0";

		//If the array's size == 1, then the results resides at the index 0 of the array, and there is no need to add anything.
		if (sizeOfArray == 1) {
			return result = sumForMult[0];
		}

		//Otherwise, we sum all the values which are stored in the indexes of array.
		else {
			for(int index = 0; index < sizeOfArray; ++index) {
				result =addition(result , sumForMult[index]);
			}
		}

		return result;
	}

	//This method implement the addition operation
	private static String addition(String op1, String op2) {  

		String result = "";

		//Here the method addZeros returns the shorter string being equal to the other
		//string by adding zeros to the left of the string
		int lengthDifference = op1.length() - op2.length();

		if (op1.length() < op2.length()) {
			op1 = addZeros(op1, lengthDifference);
		}
		if (op1.length() > op2.length()) {
			op2 = addZeros(op2, lengthDifference);
		}

		int index = 0;
		int carryIn = 0;
		int sum = 0;

		for (index = op2.length() - 1; index >= 0; --index) {

			int valueOfOp1 = op1.charAt(index) - '0' + carryIn;
			int valueOfOp2 = op2.charAt(index) - '0';

			sum = valueOfOp1 + valueOfOp2;

			if (sum > 9) {
				sum = sum - 10;
				carryIn = 1;
			}
			else {
				carryIn = 0;
			}

			//if the sum of the last numbers is more than 9 we add extra 1 to the left of
			//the final result
			if (index == 0 && (valueOfOp1 + valueOfOp2 > 9)) {
				result = "1" + sum + result;
			} else {
				result = sum + result;
			}
		}

		return result;
	}

	// This method extract the sign of the operand passed into its parameter
	private static String sign(String op) {

		String result = "";

		char sign = op.charAt(0);

		// If the string does not have a sign at the first character then the sign is +
		if (sign == '+' || (sign >= '0' && sign <= '9')) {
			sign = '+';
		}
		if (sign == '-') {
			sign = '-';
		}

		return sign + result;
	}

	//If the any string has a sign, the method stringWithoutSign returns the string without a sign
	private static String stringWithoutSign(String op) {

		char firstChar = op.charAt(0);

		if (firstChar == '-' || firstChar == '+') {
			op = op.substring(1);
			return op;
		}
		else {
			return op;	
		}
	}

	//Here the method addZeros returns the shorter string being equal to the other
	//string by adding zeros to the left of the string
	private static String addZeros(String op, int lengthDifference) {

		if (lengthDifference < 0) {	
			lengthDifference = -lengthDifference;
		}

		for (int index = 0; index < lengthDifference; ++index) {
			op = 0 + op;
		}

		return op;
	}

	//Here the method addZerosForPosiiton is called, if the intermediate results needs to be extended with zero(s) to maintain the position.
	private static String addZerosForPosiiton(String op,  int countForKeepingPosition) {

		if (countForKeepingPosition == 0) {
			return op;
		}

		for (int index = 1; index <= countForKeepingPosition; ++index) {
			op = op + 0;
		}

		return op;		
	}
}