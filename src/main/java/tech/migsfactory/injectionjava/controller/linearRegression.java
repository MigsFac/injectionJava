package tech.migsfactory.injectionjava.controller;

import java.util.Map;

public class linearRegression{
	public double weightClassCalc(Map<String, String[]> index,double kg,String classes,double baseDose,double minDose) {
		
		double x = kg;
		double y;
		
		if(classes.equals("A")) {
			y = -0.00000028 * Math.pow(x, 4) + 0.00004915* Math.pow(x, 3) - 0.00337375 *Math.pow(x, 2) + 0.16773060 *x + 0.53976823 ;
		} else if (classes.equals("B")) {
			
			y = -0.00000023 * Math.pow(x, 4) + 0.00003846 * Math.pow(x, 3) - 0.00262028 * Math.pow(x, 2) + 0.27495836 * x + 0.14075609; 
			
		
		} else if (classes.equals("c")) {
			y = -0.00000001 * Math.pow(x, 4) - 0.00001462 * Math.pow(x, 3) + 0.00247677 * Math.pow(x, 2) + 0.38073934 * x - 0.24312323; 
		} else {
			y =0;
			System.out.println("classes error");
		
		}
		double multiplication = y * baseDose;
		
		double result;
		if (multiplication<minDose) {
			result = minDose;
		} else {
			result = ((double)Math.round(multiplication * 10))/10;
		}
		
		return result;
	}
}