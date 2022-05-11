package person;

import java.util.Calendar;
import java.util.GregorianCalendar;

/*
	Person - данные о пользователе
	firstName - имя
	lastName - фамилия
	dateOfBirth - дата рождения
	weight - вес
	height - рост
*/

public class Person {
	// имя
	private String firstName;
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }

	// Фамилия
	private String lastName;
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }

	// Дата рождения
	private Calendar dateOfBirth;
	public Calendar getDateOfBirth() { return dateOfBirth; }
	public void setDateOfBirth(Calendar dateOfBirth) { this.dateOfBirth = dateOfBirth; }
	public void setDateOfBirth(int day, int month, int year) { 
		try {
			this.dateOfBirth = new GregorianCalendar(year, month, day); 
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Вычисление возраста
	public int calculateAge() {
		Calendar currentDate = new GregorianCalendar();
		int age = currentDate.get(Calendar.YEAR) - this.dateOfBirth.get(Calendar.YEAR) - 1;
		if (currentDate.get(Calendar.MONTH) > this.dateOfBirth.get(Calendar.MONTH)) { age++; }
		else if (currentDate.get(Calendar.MONTH) == this.dateOfBirth.get(Calendar.MONTH)) {
			if (currentDate.get(Calendar.DAY_OF_MONTH) >= this.dateOfBirth.get(Calendar.DAY_OF_MONTH)) { age++; }
		}

		return age;
	}

	// Вес
	private Float weight;
	public Float getWeight() { return weight; }
	public void setWeight(Float weight) { this.weight = weight; }

	// Рост
	private Integer height;
	public Integer getHeight() { return height; }
	public void setHeight(Integer height) { this.height = height; }

	// Default constructor
	public Person() {}

	public Person(String firstName, String lastName, Integer day, Integer month, Integer year, Float weight, Integer height) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = new GregorianCalendar(year, month, day);
		this.weight = weight;
		this.height = height;
	}
}