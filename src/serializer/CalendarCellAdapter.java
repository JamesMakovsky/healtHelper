package serializer;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import java.util.ArrayList;
import java.io.IOException;

import calendar.CalendarCell;

import training.Training;
import training.exercise.Exercise;
import training.exercise.BodyPart;
import training.exercise.inventory.Inventory;

import meal.Meal;
import meal.product.Product;
import meal.product.Calority;

class CalendarCellAdapter extends TypeAdapter<CalendarCell> {
	@Override
	public CalendarCell read(JsonReader reader) throws IOException {
		CalendarCell calendarCell = new CalendarCell();
		reader.beginObject();
		String fieldName = null;

		while (reader.hasNext()) {
			fieldName = reader.nextName();

			if (fieldName.equals("day")) {
				calendarCell.set(CalendarCell.DAY_OF_MONTH, reader.nextInt());
			} else if (fieldName.equals("month")) {
				calendarCell.set(CalendarCell.MONTH, reader.nextInt());
			} else if (fieldName.equals("year")) {
				calendarCell.set(CalendarCell.YEAR, reader.nextInt());
			} else if (fieldName.equals("mealConsumed")) {
				calendarCell.setMealConsumed(readMealList(reader));
			} else if (fieldName.equals("trainingDone")) {
				calendarCell.setTrainingDone(readTrainingList(reader));
			} else {
				reader.skipValue();
			}
		}

		reader.endObject();
		return calendarCell;
	}

	private ArrayList<Meal> readMealList(JsonReader reader) throws IOException {
		ArrayList<Meal> mealList = new ArrayList<Meal>();
		reader.beginArray();

		while (reader.hasNext()) {
			mealList.add(readMeal(reader));
		}

		reader.endArray();

		return mealList;
	}

	private Meal readMeal(JsonReader reader) throws IOException {
		Meal meal = new Meal();
		reader.beginObject();
		String fieldName = null;

		while (reader.hasNext()) {
			fieldName = reader.nextName();

			if (fieldName.equals("name")) {
				meal.setName(reader.nextString());
			} else if (fieldName.equals("productList")) {
				meal.setProductList(readProductList(reader));
			} else if (fieldName.equals("calority")) {
				meal.setCalority(readCalority(reader));
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return meal;
	}

	private ArrayList<Product> readProductList(JsonReader reader) throws IOException {
		ArrayList<Product> productList = new ArrayList<Product>();
		reader.beginArray();

		while (reader.hasNext()) {
			productList.add(readProduct(reader));
		}

		reader.endArray();
		return productList;
	}

	private Product readProduct(JsonReader reader) throws IOException {
		Product product = new Product();
		String fieldName = null;

		while (reader.hasNext()) {
			fieldName = reader.nextName();

			if (fieldName.equals("ID")) {
				product.setID(reader.nextInt());
			} else if (fieldName.equals("name")) {
				product.setName(reader.nextString());
			} else if (fieldName.equals("calority")) {
				product.setCalority(readCalority(reader));
			} else if (fieldName.equals("massGramms")) {
				product.setMassGramms(reader.nextInt());
			} else {
				reader.skipValue();
			}
		}

		return product;
	}

	private Calority readCalority(JsonReader reader) throws IOException {
		Calority calority = new Calority();
		String fieldName = null;

		while (reader.hasNext()) {
			fieldName = reader.nextName();

			if (fieldName.equals("calories")) {
				calority.setCalories(reader.nextInt());
			} else if (fieldName.equals("protein")) {
				calority.setProtein((float)reader.nextDouble());
			} else if (fieldName.equals("fat")) {
				calority.setFat((float)reader.nextDouble());
			} else if (fieldName.equals("carbohydrates")) {
				calority.setCarbohydrates((float)reader.nextDouble());
			} else {
				reader.skipValue();
			}
		}

		return calority;
	}


	private ArrayList<Training> readTrainingList(JsonReader reader) throws IOException {
		ArrayList<Training> trainingList = new ArrayList<Training>();
		reader.beginArray();

		while (reader.hasNext()) {
			trainingList.add(readTraining(reader));
		}

		reader.endArray();

		return trainingList;
	}

	private Training readTraining(JsonReader reader) throws IOException {
		Training training = new Gson().fromJson(reader, Training.class);
		return training;
	}
	

	@Override
	public void write(JsonWriter writer, CalendarCell calendarCell) throws IOException {
		writer.beginObject();
		writer.name("day");
		writer.value(calendarCell.get(CalendarCell.DAY_OF_MONTH));
		writer.name("month");
		writer.value(calendarCell.get(CalendarCell.MONTH));
		writer.name("year");
		writer.value(calendarCell.get(CalendarCell.YEAR));
		writer.name("mealConsumed");
		writeMealList(writer, calendarCell.getMealConsumed());
		writer.name("trainingDone");
		writeTrainingList(writer, calendarCell.getTrainingDone());
		writer.endObject();
	}

	// WRITING TRAINING
	private void writeInventory(JsonWriter writer, Inventory inventory) throws IOException {
		writer.beginObject();
		if (inventory.getID() != null) {
			writer.name("ID");
			writer.value(inventory.getID());
		}
		writer.name("title");
		writer.value(inventory.getTitle());
		writer.name("weight");
		writer.value(inventory.getWeight());
		writer.endObject();
	}

	private void writeInventoryList(JsonWriter writer, ArrayList<Inventory> inventoryList) throws IOException {
		writer.beginArray();
		for (Inventory inventory : inventoryList) {
			writeInventory(writer, inventory);
		}
		writer.endArray();
	}

	private void writeBodyPart(JsonWriter writer, BodyPart bodyPart) throws IOException {
		writer.beginObject();
		if (bodyPart.getID() != null) {
			writer.name("ID");
			writer.value(bodyPart.getID());
		}
		writer.name("title");
		writer.value(bodyPart.getTitle());
		writer.endObject();
	}

	private void writeBodyPartList(JsonWriter writer, ArrayList<BodyPart> bodyPartList) throws IOException {
		writer.beginArray();
		for (BodyPart bodyPart : bodyPartList) {
			writeBodyPart(writer, bodyPart);
		}
		writer.endArray();
	}

	private void writeExercise(JsonWriter writer, Exercise exercise) throws IOException {
		writer.beginObject();
		if (exercise.getID() != null) {
			writer.name("ID");
			writer.value(exercise.getID());
		}
		if (exercise.getBodyPartList() != null) {
			writer.name("bodyPartList");
			writeBodyPartList(writer, exercise.getBodyPartList());
		}
		if (exercise.getInventoryList() != null) {
			writer.name("inventoryList");
			writeInventoryList(writer, exercise.getInventoryList());
		}
		writer.name("name");
		writer.value(exercise.getName());
		writer.name("description");
		writer.value(exercise.getDescription());
		if (exercise.getTimes() != null) {
			writer.name("times");
			writer.value(exercise.getTimes());
		}
		if (exercise.getApproaches() != null) {
			writer.name("approaches");
			writer.value(exercise.getApproaches());
		}
		if (exercise.getImagePath() != null) {
			writer.name("imagePath");
			writer.value(exercise.getImagePath());
		}
		writer.endObject();
	}

	private void writeExerciseList(JsonWriter writer, ArrayList<Exercise> exerciseList) throws IOException {
		writer.beginArray();
		for (Exercise exercise : exerciseList) { writeExercise(writer, exercise); }
		writer.endArray();
	}

	private void writeExerciseListOfList(JsonWriter writer, ArrayList<ArrayList<Exercise>> exercises) throws IOException {
		writer.beginArray();
		for (ArrayList<Exercise> exerciseList : exercises) { writeExerciseList(writer, exerciseList); }
		writer.endArray();
	}

	private void writeTraining(JsonWriter writer, Training training) throws IOException {
		writer.beginObject();
		if (training.getID() != null) {
			writer.name("ID");
			writer.value(training.getID());
		}
		writer.name("name");
		writer.value(training.getName());
		writer.name("description");
		writer.value(training.getDescription());
		writer.name("exercises");
		writeExerciseListOfList(writer, training.getExercises());
		writer.endObject();
	}

	private void writeTrainingList(JsonWriter writer, ArrayList<Training> trainingList) throws IOException {
		writer.beginArray();
		for (Training training : trainingList) { writeTraining(writer, training); }
		writer.endArray();
	}

	// WRITING MEAL
	private void writeCalority(JsonWriter writer, Calority calority) throws IOException {
		writer.beginObject();
		writer.name("calories");
		writer.value(calority.getCalories());
		writer.name("protein");
		writer.value(calority.getProtein());
		writer.name("fat");
		writer.value(calority.getFat());
		writer.name("carbohydrates");
		writer.value(calority.getCarbohydrates());
		writer.endObject();
	}

	private void writeProduct(JsonWriter writer, Product product) throws IOException {
		writer.beginObject();
		if (product.getID() != null) {
			writer.name("ID");
			writer.value(product.getID());
		}
		writer.name("name");
		writer.value(product.getName());
		writer.name("calority");
		writeCalority(writer, product.getCalority());
		writer.name("massGramms");
		writer.value(product.getMassGramms());
		writer.endObject();
	}

	private void writeProductList(JsonWriter writer, ArrayList<Product> productList) throws IOException {
		writer.beginArray();
		for (Product prod : productList) { writeProduct(writer, prod); }
		writer.endArray();
	}

	private void writeMeal(JsonWriter writer, Meal meal) throws IOException {
		writer.beginObject();
		if (meal.getName() != null) {
			writer.name("name");
			writer.value(meal.getName());
		}
		writer.name("productList");
		writeProductList(writer, meal.getProductList());
		writer.name("calority");
		writeCalority(writer, meal.getCalority());
		writer.endObject();
	}

	private void writeMealList(JsonWriter writer, ArrayList<Meal> mealList) throws IOException {
		writer.beginArray();
		for (Meal meal : mealList) { writeMeal(writer, meal); }
		writer.endArray();
	}
}