import java.io.FileWriter;
import java.io.IOException;

import training.Training;
import meal.Meal;
import serializer.Serializer;
import person.Person;
import calendar.CalendarCell;


public class Test {
	public static void main(String[] args) {
		Serializer serializer = new Serializer();

		try {
			Meal meal = new Meal("Обед");
			meal.addProduct(serializer, 4, 200);
			meal.addProduct(serializer, 0, 300);
			meal.addProduct(serializer, 2, 150);
			meal.deleteProduct(1);
			//serializer.printJSON(meal);

			Training training = new Training("Название", "Описание тренировки");
			training.addExercise(serializer, 0, 3, 10);
			training.addExercise(serializer, 0, 5, 3);
			training.deleteExercise(1);
			//serializer.printJSON(training);

			CalendarCell calendarCell = new CalendarCell();
			calendarCell.addMeal(meal);
			calendarCell.addTraining(training);
			serializer.printJSON(calendarCell);
		} catch(Exception ex) {
			System.out.println(ex);
		}

		/*System.out.println("MEAL TEST:");
		Meal meal = new Meal("Завтрак у Тиффани");
		Product productToWrite = new Product("Говяжье азу", 300, 215, 12.0f, 14.2f, 10.2f);
		meal.addProduct(productToWrite);
		serializer.printJSON(meal);

		try {
			FileWriter fw = new FileWriter("product.json");
			fw.write(serializer.getJSONString(productToWrite));
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			Meal creature = (Meal)serializer.createObjectFromJSON(Meal.class, serializer.getJSONString(meal));
			serializer.printJSON(creature);
		} catch(ClassNotFoundException ex) {
			System.out.println(ex.toString());
		}

		System.out.println("HUMAN BEING TEST:");
		Person person = new Person("Максим", "Пельменидзе", 10, 2, 1999, 80.0f, 180);
		System.out.println("AGE: " + person.calculateAge());
		serializer.printJSON(person);

		try {
			Person clone = (Person)serializer.createObjectFromJSON(Person.class, serializer.getJSONString(person));
			serializer.printJSON(clone);
		} catch(ClassNotFoundException ex) {
			System.out.println(ex.toString());
		}

		try {
			//Product prod = (Product)serializer.createObjectWithID(Product.class, "productTable.json", 0);
			//Product prod = (Product)serializer.createObjectFromFile(Product.class, "productTable.json");
			//prod.setMassGramms(300);
			meal.addProduct(0, 300);
			serializer.printJSON(meal);
		} catch(Exception ex) {
			System.out.println(ex.toString());
		}*/
	}
}