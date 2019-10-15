/**
 * @title Assignment 2 - Approximating Pi
 * @author Kyle Meade
 * @netid 18kdm2@queensu.ca
 * @studentnumber 20129191
 * @class CISC124 - Macleod
 * @university Queen's University in Kingston
 */

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assn2_18kdm2 {
	
	// Used to count iterations
    public static int count;
    // For screen input
    private static Scanner screenInput = new Scanner(System.in);
	
	
    // Code your methods here
    
    // This returns a double though the requirements says to use a float, though main
    // expects a double and it is from the start template, so I am just going to stick with Double
    // A method to calculuate digits of pi using the Leibniz summation
    public static double piCalculatorLeibniz() {
    	float prevSum = 0;
    	float sum = 0;
    	float term;
    	int i = 0;
    	
    	int numerator, denominator;
    	
    	do {
    		numerator = (int) Math.pow(-1, i);
    		denominator = (2 * i) + 1;
    		term = (float) numerator / denominator;
    		prevSum = sum;
    		sum += term;
    		i++;
    	} while (sum != prevSum);
    	
    	return sum * 4;
    }
    
    // Method to calculate arctan(x) with floats
    public static double floatArctan(double x) {
    	float prevSum = 0;
    	float sum = 0;
    	float term, numerator;
    	int i = 0;
    	
    	int denominator;
    	
    	do {
    		denominator = (2 * i) + 1;
    		numerator = (float) (Math.pow(-1, i) * Math.pow(x, denominator));
    		term = (float) (numerator / denominator);
    		prevSum = sum;
    		sum += term;
    		i++;
    	} while (sum != prevSum);
    	
    	return sum;
    }
    
    // Method to calculate arctan(x) using doubles
    public static double doubleArctan(double x) {
    	double prevSum = 0;
    	double sum = 0;
    	double term, numerator;
    	int i = 0;
    	
    	int denominator;
    	
    	do {
    		denominator = (2 * i) + 1;
    		numerator = (double) (Math.pow(-1, i) * Math.pow(x, denominator));
    		term = (double) (numerator / denominator);
    		prevSum = sum;
    		sum += term;
    		i++;
    	} while (sum != prevSum);
    	
    	return sum;
    }
    
    // Method to calculate arctan(x) with doubles (with improvements)
    public static double modifiedArctan(double x) {
    	double prevSum = 0;
    	double sum = 0;
    	double remainder = 0;
    	double term, numerator, tempsum, portionOfTermInSum;
    	int i = 0;
    	
    	int denominator;
    	
    	do {
    		denominator = (2 * i) + 1;
    		numerator = (double) (Math.pow(-1, i) * Math.pow(x, denominator));
    		term = (double) (numerator / denominator);
    		term += remainder;
    		
    		// Summation improvement technique
    		tempsum = sum + term;
    		portionOfTermInSum = tempsum - sum;
    		remainder = term - portionOfTermInSum;
        	
    		
    		prevSum = sum;
    		sum = tempsum;
    		i++;
    	} while (sum != prevSum);
    	
    	return sum;
    }
    
    // Method to approximate digits of pi using an arctan based formula
    public static double arcTanPiCalculatorFloat() {
    	float part1 = (float) (8 * floatArctan((double)1/10));
    	float part2 = (float) (4 * floatArctan((double)1/515));
    	float part3 = (float) floatArctan((double)1/239);
    	
    	float pi = 4 * (part1 - part2 - part3);
    	
    	return pi;
    }
    
    // Method to approximate digits of pi using an arctan based formula (doubles version)
    public static double arcTanPiCalculatorDouble() {
    	double part1 = 8 * doubleArctan((double)1/10);
    	double part2 = 4 * doubleArctan((double)1/515);
    	double part3 = doubleArctan((double)1/239);
    	
    	double pi = 4 * (part1 - part2 - part3);
    	
    	return pi;
    }
    
    // Method to approximate digits of pi using an arctan based formula (doubles and improvement method)
    public static double arcTanPiCalculatorDoubleModified() {
    	double part1 = 8 * modifiedArctan((double)1/10);
    	double part2 = 4 * modifiedArctan((double)1/515);
    	double part3 = modifiedArctan((double)1/239);
    	
    	double pi = 4 * (part1 - part2 - part3);
    	
    	return pi;
    }
    
    // Method to approximate digits of pi using Bailey-Borwein-Plouffee with Doubles
    public static double piCalculatorBBP() {
    	double prevSum = 0;
    	double sum = 0;
    	double term, Fraction1, Fraction2, Fraction3, Fraction4, Fraction5;
    	
    	int i = 0;
    	
    	do {
    		Fraction1 = (double) 1 / Math.pow(16, i);
    		Fraction2 = (double) 4 / ((8 * i) + 1);
    		Fraction3 = (double) 2 / ((8 * i) + 4);
    		Fraction4 = (double) 1 / ((8 * i) + 5);
    		Fraction5 = (double) 1 / ((8 * i) + 6);
    		
    		term = Fraction2 - Fraction3 - Fraction4 - Fraction5;
    		term = Fraction1 * term;
    		
    		prevSum = sum;
    		sum += term;
    		i++;
    	} while (prevSum != sum);
    	
    	return sum;
    }
    
    // Method to approximate digits of pi using Bailey-Borwein-Plouffee with BigDecimal 
	public static BigDecimal piCalculatorBBPBig(int scale) {
    	BigDecimal prevSum = BigDecimal.ZERO;
    	BigDecimal sum = BigDecimal.ZERO;
    	BigDecimal term, denominator, eightK, Fraction1, Fraction2, Fraction3, Fraction4, Fraction5;
    	
    	int i = 0;
    	int sumDifference;
    	
    	prevSum.setScale(scale);
    	sum.setScale(scale);
    	
    	do {
    		eightK = new BigDecimal("8");
    		eightK.setScale(scale);
    		eightK = eightK.multiply(new BigDecimal(Integer.toString(i)));
    		
    		Fraction1 = new BigDecimal("1");
    		Fraction1.setScale(scale);
    		Fraction1 = Fraction1.divide(new BigDecimal("16").pow(i), scale, RoundingMode.HALF_EVEN);
    		
    		Fraction2 = new BigDecimal("4");
    		Fraction2.setScale(scale);
    		denominator = new BigDecimal("1");
    		denominator.setScale(scale);
    		denominator = denominator.add(eightK);
    		Fraction2 = Fraction2.divide(denominator, scale, RoundingMode.HALF_EVEN);
    		
    		Fraction3 = new BigDecimal("2");
    		Fraction3.setScale(scale);
    		denominator = new BigDecimal("4");
    		denominator.setScale(scale);
    		denominator = denominator.add(eightK);
    		Fraction3 = Fraction3.divide(denominator, scale, RoundingMode.HALF_EVEN);
    		
    		Fraction4 = new BigDecimal("1");
    		Fraction4.setScale(scale);
    		denominator = new BigDecimal("5");
    		denominator.setScale(scale);
    		denominator = denominator.add(eightK);
    		Fraction4 = Fraction4.divide(denominator, scale, RoundingMode.HALF_EVEN);
    		
    		Fraction5 = new BigDecimal("1");
    		Fraction5.setScale(scale);
    		denominator = new BigDecimal("6");
    		denominator.setScale(scale);
    		denominator = denominator.add(eightK);
    		Fraction5 = Fraction5.divide(denominator, scale, RoundingMode.HALF_EVEN);
    		
    		term = Fraction2;
    		term = term.subtract(Fraction3);
    		term = term.subtract(Fraction4);
    		term = term.subtract(Fraction5);
    		term = term.multiply(Fraction1);
    		
    		prevSum = sum;
    		sum = sum.add(term);
    		
    		sumDifference = sum.compareTo(prevSum);
    		i++;
    	} while (sumDifference != 0);
    	
    	return sum;
    	
    }
    
    // Aids in displaying BigDecimal numbers to the screen using 100
    // digits per line.
    public static void displayResult(BigDecimal aNum) {
    	var asString = aNum.toString();
    	
    	for(int i = 0; i < asString.length(); i++) {
    		System.out.print(asString.charAt(i));
    		if (i > 0 && (i + 1) % 100 == 0)
    			System.out.println();
    	}
    	
    	System.out.println();
    } // end displayResult
    
    // Simplifies reporting the execution time and the number of iterations
    public static void timeIterationsReport(long start) {
        long finishTime = System.nanoTime();
        long diff = finishTime - start;
        if (diff <= 1e3)
            System.out.print("Took " + diff + " nanosec., ");
        else if (diff <= 1e6)
            System.out.print("Took " + Math.round(diff / 10.0) / 100.0 + " microsec. ");
        else if (diff <= 1e9)
        	System.out.print("Took " + Math.round(diff / 1e4) / 100.0 + " millisec. ");
        else
        	System.out.print("Took " + Math.round(diff / 1e7) / 100.0 + " sec. ");
        System.out.println("and required " + count + " iterations.");
        count = 0;
    } // end timeReport
    
    // Used to calculate and display the accuracy of a 16 digit result using the value of
    // pi stored in the Math class.
    public static void accuracyReport(double estimate) {
    	var error = 100 * (estimate - Math.PI) / Math.PI;
    	System.out.printf("Error is %.2e percent.\n\n", error);
    } // end accuracyReport

    // Copied from IOHelper (only method needed)
    public static int getInt(int low, String prompt, int high) {
        int numFromUser = 0;
        String dummy;
        boolean numericEntryOK;
        do {
            System.out.print(prompt);
            numericEntryOK = false;
            try {
                numFromUser = screenInput.nextInt();
                numericEntryOK = true;
            } catch (InputMismatchException e) {
                dummy = screenInput.nextLine();
                System.out.println(dummy + " is not an integer!");
                numFromUser = low;
            } // end try-catch
            // Indicate to the user why he is being prompted again.
            if (numFromUser < low || numFromUser > high) {
                System.out.println("The number is outside the legal limits.");
            }
        } while (!numericEntryOK || numFromUser < low || numFromUser > high);
        return numFromUser;
    } // end full parameter getInt method

    // This supplied main method uses assumed method names that you may certainly change.
    public static void main(String[] args) {

        long startTime;
        double estimate;
        int numDigitsDesired;

        System.out.printf("Math.PI is:\n%.16f\n\n", Math.PI);
        
        startTime = System.nanoTime();
        estimate = piCalculatorLeibniz();
        System.out.printf("%.16f - using Leibniz formula with float.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorFloat();
        System.out.printf("%.16f - using arcTan formula with float.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        

        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorDouble();
        System.out.printf("%.16f - using arcTan formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorDoubleModified();
        System.out.printf("%.16f - using Modified arcTan formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        estimate = piCalculatorBBP();
        System.out.printf("%.16f - using BBP formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for 16 digits:");
        var estimateBigD = piCalculatorBBPBig(16);
        System.out.println(estimateBigD);
        timeIterationsReport(startTime);
        accuracyReport(estimateBigD.doubleValue());

        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for 100 digits:");
        System.out.println(piCalculatorBBPBig(100));
        timeIterationsReport(startTime);
        System.out.println();

        numDigitsDesired = getInt(1000, "How many digits do you want to try for? ", 10000);
        
        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for " + numDigitsDesired + " digits:");
        displayResult(piCalculatorBBPBig(numDigitsDesired));
        timeIterationsReport(startTime);
        
        screenInput.close();

    } // end main

}

/*
 *  1. Sample output:
Math.PI is:
3.1415926535897930

3.1415967941284180 - using Leibniz formula with float.
Took 643.98 millisec. and required 0 iterations.
Error is 1.32e-04 percent.

3.1415927410125732 - using arcTan formula with float.
Took 212.02 microsec. and required 0 iterations.
Error is 2.78e-06 percent.

3.1415926535897927 - using arcTan formula with double.
Took 192.16 microsec. and required 0 iterations.
Error is -1.41e-14 percent.

3.1415926535897936 - using Modified arcTan formula with double.
Took 155.25 microsec. and required 0 iterations.
Error is 1.41e-14 percent.

3.1415926535897930 - using BBP formula with double.
Took 151.13 microsec. and required 0 iterations.
Error is 0.00e+00 percent.

Using BBP formula with BigDecimals for 16 digits:
3.14159265358979320317595567556788
Took 1.98 millisec. and required 0 iterations.
Error is 0.00e+00 percent.

Using BBP formula with BigDecimals for 100 digits:
3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706794715819091680081975291169746753947024414324665724419487001027458418144244366469909721753069771172363
Took 8.03 millisec. and required 0 iterations.

How many digits do you want to try for? 
1000
Using BBP formula with BigDecimals for 1000 digits:
3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706
7982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381
9644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412
7372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160
9433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949
1298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051
3200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892
3542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318
5950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473
0359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019
8903137386704658833866065207762062937445900865438814496839923866144603673057625344724611531119225637
4492581916393087078440563467026030016162256222574908388980937715502589015168076193823819111823517291
8257550792741695455049219011137296069253536237896797518806636304852022264855471942299384337685060752
5845212226767364290060219984730922591573547423410994957232764445406461303302554811101791343212183840
8406654796422248283610938410106849074649430603130738751541451392473607131067088286640626578140772010
1481066356770029150209635975821630607443739145415199083239798702472919633573734166326237844979488496
9774413425790818552426109725325591746397104012713744217377723874010898160744961948312091838867007679
5480123333951823639526477264521251867917758253811875311748048434026125032034935863307423208161045824
7280716667764821190924979538638726844882221082281655448359128863716204457199718748833815327844421709
0473494523638352173344578761853896157737915273365672346225623407736673514348922216512064449236381263
16
Took 152.36 millisec. and required 0 iterations.
*/
/*
 * 2. Technique 1 became incorrect on the 6th decimal digit, while the second technique became incorrect
 * on the 7th digit, making technique 2 superior. Both involve floats, technique 2 just being superior
 * as the mathematical formula itself is more accurate in approximating pi.
 * 3. I imagine the convergence would take way too long, I already tested to see how many iterations and
 * it was well over a million. With the significantly increased precision of a double I imagine the
 * amount of iterations would be raised to some exponent and such calculates are too resource intensive.
 * 4. 3 is way better. Became incorrect on the 16th digits. This is because with doubles, there are
 * significantly more precision, thus within the calculations, less roundoff/error is accumulating to the
 * sum. 
 * 5. It improved by a single decimal place. I used the technique in the Kahan sum demo. Where you use
 * subtractions to approximate and pickup the error made in calculation, and add it to the next
 * sum in the next iteration.
 * 6. The big decimal calculation is better. This is because BigDecimal is a more memory and runtime
 * heavy operation allowing almost no roundoff/error than doubles, which even with lots of precision,
 * still has finite/limited precision, and we're dealing with transcendental numbers here.
 * 7. Basically we can define the scale of precision with BigDecimal. So even though transcendental numbers
 * have infinite decimal places, we can compute as many decimal places we want as long as we have the
 * compute power necessary to reach those lengths. Cause BigDecimal's operations run algorithms that 
 * allow to meet that scale accurately.
 * 
*/
