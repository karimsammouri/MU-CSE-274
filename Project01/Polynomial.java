/** 
 * Name: Karim R. Sammouri
 * Instructor: Prof. James Kiper
 * Course: CSE 274 - F
 * Description: This program implements a polynomial ADT.
 * Attribution: Everything aside from the pre-written code given by the 
 * course is written by me.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Polynomial {

	private ArrayList<Node> terms;

	private class Node implements Comparable<Node> {
		double coefficient;
		int exponent;

		public Node(double c, int e) {
			coefficient = c;
			exponent = e;
		}

		@Override
		public int compareTo(Node node) {

			return node.exponent - this.exponent;
		}
	}

	/**
	 * Default constructor.
	 */
	public Polynomial() { // default constructor
		terms = new ArrayList<Node>();
	}

	/**
	 * Copy constructor.
	 * 
	 * @param poly
	 *            Polynomial to be copied.
	 */
	public Polynomial(Polynomial poly) { // copy constructor
		terms = new ArrayList<Node>();

		for (int i = 0; i < poly.terms.size(); i++) {
			terms.add(poly.terms.get(i));
		}

		Collections.sort(terms);
	}

	/**
	 * Full constructor.
	 * 
	 * @param coef
	 *            An array list of double coefficients.
	 * @param expon
	 *            An array list of integer exponents.
	 * @throws Exception
	 *             If the size of coefficients and exponents don't match.
	 */
	public Polynomial(ArrayList<Double> coef, ArrayList<Integer> expon)
			throws Exception {
		terms = new ArrayList<Node>();

		if (coef.size() == expon.size()) {
			for (int i = 0; i < coef.size(); i++) {
				terms.add(new Node(coef.get(i), expon.get(i)));
			}
			Collections.sort(terms);
		} else {
			throw new Exception(
					"Number of coefficients and exponents don't match.");
		}
	}

	/**
	 * Checks if the polynomial and the given polynomial are equal.
	 * 
	 * @param poly
	 *            Polynomial to be checked.
	 * @return True if the two polynomials are equal and false otherwise.
	 */
	public boolean equals(Polynomial poly) { // checks for equality
		if (this.terms.size() != poly.terms.size()) {
			return false;
		}

		for (int i = 0; i < this.terms.size(); i++) {
			if (this.terms.get(i).coefficient != poly.terms.get(i).coefficient
					|| this.terms.get(i).exponent != poly.terms
							.get(i).exponent) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Adds the polynomial and the given polynomial.
	 * 
	 * @param poly
	 *            Polynomial to be added.
	 * @return Result of the addition of the two polynomials.
	 */
	public Polynomial add(Polynomial poly) {
		Polynomial result = new Polynomial();
		boolean done = false;
		int i = 0;
		int j = 0;

		while (!done) {
			if (i == this.terms.size() && j == poly.terms.size()) {
				done = true;
			} else {
				if (i == this.terms.size()) {
					while (j < poly.terms.size()) {
						result.terms.add(new Node(poly.terms.get(j).coefficient,
								poly.terms.get(j).exponent));
						j++;
					}
					done = true;
				} else if (j == poly.terms.size()) {
					while (i < this.terms.size()) {
						result.terms.add(new Node(this.terms.get(i).coefficient,
								this.terms.get(i).exponent));
						i++;
					}
					done = true;
				} else {
					if (this.terms.get(i).exponent == poly.terms
							.get(j).exponent) {
						result.terms.add(new Node(
								this.terms.get(i).coefficient
										+ poly.terms.get(j).coefficient,
								this.terms.get(i).exponent));
						i++;
						j++;
					} else if (this.terms.get(i).exponent > poly.terms
							.get(j).exponent) {
						result.terms.add(new Node(this.terms.get(i).coefficient,
								this.terms.get(i).exponent));
						i++;
					} else {
						result.terms.add(new Node(poly.terms.get(j).coefficient,
								poly.terms.get(j).exponent));
						j++;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Subtracts the polynomial by the given polynomial.
	 * 
	 * @param poly
	 *            Polynomial to be subtracted by.
	 * @return Result of the subtraction of the two polynomials.
	 */
	public Polynomial subtract(Polynomial poly) {
		Polynomial result = new Polynomial();
		boolean done = false;
		int i = 0;
		int j = 0;

		while (!done) {
			if (i == this.terms.size() && j == poly.terms.size()) {
				done = true;
			} else {
				if (i == this.terms.size()) {
					while (j < poly.terms.size()) {
						result.terms
								.add(new Node(-poly.terms.get(j).coefficient,
										poly.terms.get(j).exponent));
						j++;
					}
					done = true;
				} else if (j == poly.terms.size()) {
					while (i < this.terms.size()) {
						result.terms.add(new Node(this.terms.get(i).coefficient,
								this.terms.get(i).exponent));
						i++;
					}
					done = true;
				} else {
					if (this.terms.get(i).exponent == poly.terms
							.get(j).exponent) {
						result.terms.add(new Node(
								this.terms.get(i).coefficient
										- poly.terms.get(j).coefficient,
								this.terms.get(i).exponent));
						i++;
						j++;
					} else if (this.terms.get(i).exponent > poly.terms
							.get(j).exponent) {
						result.terms.add(new Node(this.terms.get(i).coefficient,
								this.terms.get(i).exponent));
						i++;
					} else {
						result.terms
								.add(new Node(-poly.terms.get(j).coefficient,
										poly.terms.get(j).exponent));
						j++;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Multiplies the polynomial by the given polynomial.
	 * 
	 * @param poly
	 *            Polynomial to be multiplied by.
	 * @return Result of the multiplication of the two polynomials.
	 */
	public Polynomial multiply(Polynomial poly) {
		Polynomial result = new Polynomial();
		Polynomial temp;

		for (int i = 0; i < this.terms.size(); i++) {
			for (int j = 0; j < poly.terms.size(); j++) {
				Node node = new Node(
						this.terms.get(i).coefficient
								* poly.terms.get(j).coefficient,
						this.terms.get(i).exponent
								+ poly.terms.get(j).exponent);
				temp = new Polynomial();
				temp.terms.add(node);
				result = result.add(temp);
			}
		}

		return result;
	}

	/**
	 * Evaluates the polynomial by a given value.
	 * 
	 * @param value
	 *            Value to evaluate the polynomial by.
	 * @return Result of the polynomial being evaluated.
	 */
	public double evaluate(double value) { // evaluate the polynomial with the
											// parameter value for the variable
		double result = 0;

		for (int i = 0; i < this.terms.size(); i++) {
			result += this.terms.get(i).coefficient
					* Math.pow(value, this.terms.get(i).exponent);
		}

		return result;
	}

	/**
	 * Takes the derivative of the polynomial.
	 * 
	 * @return Result of taking the derivative of the polynomial.
	 */
	public Polynomial derivative() { // finds the derivative of host polynomial
		Polynomial result = new Polynomial();

		for (int i = 0; i < this.terms.size(); i++) {
			result.terms.add(new Node(
					this.terms.get(i).coefficient * this.terms.get(i).exponent,
					this.terms.get(i).exponent - 1));
		}

		return result;
	}

	/**
	 * Returns a string representation of the polynomial.
	 * 
	 * @return String representation of the polynomial
	 */
	public String toString() {
		StringBuilder result = new StringBuilder();

		for (Node term : terms) {
			if (Math.abs(term.coefficient) < 0.0000000001) { // skip if
																// coefficient
																// is zero
			} else if (term.exponent > 1) {
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + "x^" + term.exponent
							+ " ");
				} else {
					result.append("- " + -1 * term.coefficient + "x^"
							+ term.exponent + " ");
				}
			} else if (term.exponent < 0) {
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + "x^" + term.exponent
							+ " ");
				} else {
					result.append("- " + -1 * term.coefficient + "x^"
							+ term.exponent + " ");
				}
			} else if (term.exponent == 1) {
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + "x ");
				} else {
					result.append("- " + -1 * term.coefficient + "x ");
				}
			} else { // term.exponent == 0
				if (term.coefficient > 0.0) {
					result.append("+ " + term.coefficient + " ");
				} else {
					result.append("- " + -1 * term.coefficient + " ");
				}
			}
		}
		if (result.length() == 0)
			return "0";
		if (result.charAt(0) == '+')
			result.deleteCharAt(0);
		while (result.charAt(0) == ' ')
			result.deleteCharAt(0);
		while (result.charAt(result.length() - 1) == ' ')
			result.deleteCharAt(result.length() - 1);
		if (result.charAt(0) == '-' && result.charAt(1) == ' ')
			result.deleteCharAt(1);

		return result.toString();
	}
}
