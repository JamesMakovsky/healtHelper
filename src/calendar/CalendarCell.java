package calendar;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

import meal.Meal;
import training.Training;
import base.InitializeInterface;

import serializer.Serializer;

public class CalendarCell extends GregorianCalendar implements InitializeInterface<CalendarCell> {
	// Употребленная в пищу еда
	private ArrayList<Meal> mealConsumed;
	public void setMealConsumed(ArrayList<Meal> mealConsumed) { this.mealConsumed = mealConsumed; }
	public ArrayList<Meal> getMealConsumed() { return mealConsumed != null ? mealConsumed : new ArrayList<Meal>(); }

	// Рекомендуемая к употреблению пища
	private ArrayList<Meal> mealAdvise;
	public void setMealAdvise(ArrayList<Meal> mealAdvise) { this.mealAdvise = mealAdvise; }
	public ArrayList<Meal> getMealAdvise() { return mealAdvise != null ? mealAdvise : new ArrayList<Meal>(); }

	// Добавить прием пищи
	public void addMeal(Meal meal) {
		try {
			this.mealConsumed.add(meal);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// Выполненные тренировки
	private ArrayList<Training> trainingDone;
	public void setTrainingDone(ArrayList<Training> trainingDone) { this.trainingDone = trainingDone; }
	public ArrayList<Training> getTrainingDone() { return trainingDone != null ? trainingDone : new ArrayList<Training>(); }

	// Рекомендуемые нагрузки на день
	private ArrayList<Training> trainingAdvise;
	public void setTrainingAdvise(ArrayList<Training> trainingAdvise) { this.trainingAdvise = trainingAdvise; }
	public ArrayList<Training> getTrainingAdvise() { return trainingAdvise != null ? trainingAdvise : new ArrayList<Training>(); }

	// Добавить тренировку
	public void addTraining(Training training) {
		try {
			this.trainingDone.add(training);
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	// init arraylists
	private void initArrayLists() {
		mealConsumed = new ArrayList<Meal>();
		mealAdvise = new ArrayList<Meal>();
		trainingDone = new ArrayList<Training>();
		trainingAdvise = new ArrayList<Training>();
	}

	// Загрузка из базы данных о совершенных тренировках
	// и съеденной еде по дате
	private void loadMealsAndTrainings(int y, int m, int d) {
		/* идея такая:
			есть календарь (табличка такая 7 на 5), разделенный на дни месяца, естессн
			каждая ячейка календаря - это день, к которому прекреплен список еды и список тренировок
			уникальный ключ каждого дня (о, ужас!) - дата.
			получив дату из календаря "свыше", т.е. из лица приложения, мы генерим нашу ячейку с нужной датой
			далее, получив дату, загружаем по этому ключу ДДММГГ данные о совершенных инсинуациях в этот день
			пока писал, придумал всё, но сделаю это попозже :)
			необходимо получить жсон, оттуда вынуть милс и трейнингс и положить куда надо.
		*/
		// loadJson
		// getMeals
		// getTrainings
	}

	// interface method implementation
	public void initiateByID(Serializer serializer, Integer ID) {
		/*try {
			CalendarCell product = (CalendarCell)serializer.createObjectByID(CalendarCell.class, ID);
			customClone(product);
		} catch (Exception e) {
			System.out.println(e.toString());
		}*/
	}

	public void initiateFromJson(Serializer serializer, String json) {
		/*try {
			CalendarCell product = (CalendarCell)serializer.createObjectFromJSON(CalendarCell.class, json);
			customClone(product);
		} catch (Exception e) {
			System.out.println(e.toString());
		}*/
	}

	public void customClone(CalendarCell clone) {
		/*this.ID = clone.getID();
		this.name = clone.getName();
		this.description = clone.getDescription();
		this.exercises = clone.getExercises();
		this.massGramms = clone.getMassGramms();*/
	}

	// default constructor
	public CalendarCell() {
		super();
		initArrayLists();
	}

	// default constructor
	public CalendarCell(int year, int month, int day) {
		super(year, month, day);
		initArrayLists();
		loadMealsAndTrainings(year, month, day);
	}
}