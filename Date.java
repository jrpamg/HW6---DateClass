/*
 * January, March, May, July, August, October, December have 31 days
 * February has 28 days, but 29 days on leap year
 * April, June, September, November have 30 days
 * 
 * There's 365 days in a normal year, 366 in a leap year
 */
public class Date {

	private int year;
	private int month;
	private int day;

	public Date(int year, int month, int day) {
		this.year = year;
		this.month = month;
		this.day = day;
	}

	public void addDays(int days) {
		this.day += days;

		while(this.day > this.daysIn(this.month)){
			this.day -= this.daysIn(this.month);
			this.month++;

			if(this.month > 12) {
				this.month -= 12;
				this.year++;
			}
		}
	}

	public void addWeeks(int weeks) {
		this.addDays(7*weeks);
	}

	public int daysTo(Date other) { //assume other date has to be in the future;

		int saveDay = this.day;
		int saveMonth = this.month;
		int saveYear = this.year;

		int totalDays = 0;

		if(other.year <= this.year) {
			if(other.year != this.year) {
				System.out.println("The date entered is in years past.");
				return 0;
			}
			else if(other.month <= this.month){
				if(other.month != this.month) {
					System.out.println("The date enterd is in months past.");
					return 0;
				}
				else if(other.day <= this.day) {
					if(other.day != this.day) {
						System.out.println("The date entered is in days past"); 
						return 0;
					}
					else {
						System.out.println("The date entered is the same day as it's being compared to.");
						return 0;
					}
				}
			}
		}
		
		while(this.year < other.year) {
			this.day++;
			totalDays++;
			if(this.day > this.daysIn(this.month)) {
				this.month++;
				this.day = 1;
			}
			if(this.month > 12) {
				this.month = 1;
				this.year++;
			}
		}
		
		while(this.month < other.month) {
			this.day++;
			totalDays++;
			if(this.day > this.daysIn(this.month)) {
				this.month++;
				this.day = 1;
			}
		}
		
		while(this.day < other.day) {
			this.day++;
			totalDays++;
		}
		
		this.day = saveDay;
		this.month = saveMonth;
		this.year = saveYear;
		return totalDays;
	}

	public int getDay() {
		return this.day;
	}

	public int getMonth() {
		return this.month;
	}

	public int getYear() {
		return this.year;
	}

	public boolean isLeapYear() { //occurs every four years, except for multiples of 100 that aren't multiples of 400
		if(this.year%4 == 0) {
			if(this.year%100 != 0) return true;
			else {
				if(this.year%400 != 0) return false;
				else return true;
			}
		}
		else return false;
	}

	public String toString() {
		if(this.month < 10) {
			String m = "0" + this.month;
			return "The date is " + this.year + "/" + m + "/"+ this.day;

		}
		else return "The date is " + this.year + "/" + this.month + "/" + this.day;
	}

	//EXTRA

	public int daysIn(int month) { //returns days in certain month;
		if(month == 2) {
			if(this.isLeapYear() == true) return 29;
			else return 28;
		}
		else if(month == 4 || month == 6 || month == 9 || month == 11) return 30;
		else return 31;

	}
}
