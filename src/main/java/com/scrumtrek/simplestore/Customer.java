package com.scrumtrek.simplestore;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String m_Name;
	private List<Rental> m_Rentals = new ArrayList<Rental>();

	public Customer(String name) {
		m_Name = name;
	}

	public String getName() {
		return m_Name;
	}


	public void addRental(Rental arg){
		m_Rentals.add(arg);
	}

	public String Statement()
	{
		double totalAmount = 0;
		int frequentRenterPoints = 0;
				
		String result = "Rental record for " + m_Name + "\n";
		
		for(Rental each: m_Rentals) {
			CalcData data = CalcStatement(each);

			// Show figures for this rental
			result += "\t" + each.getMovie().getTitle() + "\t" + data.amount + "\n";
			totalAmount += data.amount;
			frequentRenterPoints += data.frequentRenterPoints;
		}

		// Add footer lines
		result += "Amount owed is " + totalAmount + "\n";
		result += "You earned " + frequentRenterPoints + " frequent renter points.";
		return result;
	}

	public CalcData CalcStatement(Rental rental)
	{
		CalcData data = new CalcData(0.0, 0);
		// Determine amounts for each line
		switch(rental.getMovie().getPriceCode()) {
			case XXX:
			case Regular:
				data.amount += 2;
				if (rental.getDaysRented() > 2)
				{
					data.amount += (rental.getDaysRented() - 2) * 1.5;
				}
				break;

			case NewRelease:
				data.amount += rental.getDaysRented() * 3;
				break;

			case Childrens:
				data.amount += 1.5;
				if (rental.getDaysRented() > 3)
				{
					data.amount += (rental.getDaysRented() - 3) * 1.5;
				}
				break;
		}

		// Add frequent renter points
		data.frequentRenterPoints++;

		// Add bonus for a two-day new-release rental
		if ((rental.getMovie().getPriceCode() == PriceCodes.NewRelease) && (rental.getDaysRented() > 1))
		{
			data.frequentRenterPoints ++;
		}

		if (rental.getMovie().getPriceCode() == PriceCodes.XXX)
		{
			data.amount *= 0.95;
		}

		return data;
	}

	public static class CalcData
	{
		public int frequentRenterPoints = 0;
		public double amount = 0;

		public CalcData(double a, int f)
		{
			amount = a;
			frequentRenterPoints = f;
		}
	}
}

