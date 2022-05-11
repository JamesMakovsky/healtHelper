package serializer;

import java.util.*;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;

import com.google.gson.*;

import exception.*;

import meal.product.Product;
import meal.product.Calority;
import meal.Meal;

import training.exercise.inventory.Inventory;
import training.exercise.BodyPart;
import training.exercise.Exercise;
import training.Training;

import calendar.CalendarCell;

/*
	Serializer
	gson - Google json
*/

public class Serializer {
	/*
		READ_FIRST_MODE - читать первую строку файла с json
		STRING_SEARCH_MODE - читать n-ую строку файла с json
		ID_SEARCH_MODE - читать, пока не наткнемся на нужный ID
	*/ 
	private static final int READ_FIRST_MODE = 0;
	private static final int STRING_SEARCH_MODE = 1;
	private static final int KEY_SEARCH_MODE = 2;

	// Google json serializer
	private static Gson gson;
	private GsonBuilder gsonBuilder;

	public Serializer() {
		gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();
		gsonBuilder.registerTypeAdapter(CalendarCell.class, new CalendarCellAdapter());
		gson = gsonBuilder.create();
	}

	// parse data from json string
	public Object createObjectFromJSON(Class classIdentifier, String jsonString)
	throws ClassNotFoundException {
		JsonElement jsonElement = new JsonParser().parse(jsonString);
		Object targetObject = gson.fromJson(jsonElement, classIdentifier);
		
		return targetObject;
	}

	// create an object from file
	public Object createObjectFromFile(Class classIdentifier, String filePath) 
	throws ClassNotFoundException, FileNotFoundException, FileLengthException, WrongDataFormatException, NoSuchValueException {
		return createObjectFromJSON(classIdentifier, getJsonFromFile(filePath, READ_FIRST_MODE, null, "0"));
	}

	// create an object by its key
	public Object createObjectByKey(Class classIdentifier, String key, String value)
	throws ClassNotFoundException, FileNotFoundException, FileLengthException, WrongDataFormatException, NoSuchValueException {
		return createObjectFromJSON(classIdentifier, getJsonFromFile(switchJsonFile(classIdentifier), KEY_SEARCH_MODE, key, value));
	}

	public Object createObjectByID(Class classIdentifier, Integer ID)
	throws ClassNotFoundException, FileNotFoundException, FileLengthException, WrongDataFormatException, NoSuchValueException {
		return createObjectByKey(classIdentifier, "ID", ID.toString());
	}

	// switch filename of json
	private String switchJsonFile(Class classIdentifier) {
		String filepath;
		String jsonFolderPath = "json/";
		filepath = jsonFolderPath;
		if (classIdentifier == BodyPart.class) {
				filepath += "bodyPartTable.json";
		} else if (classIdentifier == Product.class) {
				filepath += "productTable.json";
		} else if (classIdentifier == Exercise.class) {
				filepath += "exerciseTable.json";
		}else {
			filepath = "";
		}

		return filepath;
	}

	// get json string from file
	private String getJsonFromFile(String filename, Integer mode, String key, String value)
	throws FileNotFoundException, NumberFormatException, WrongDataFormatException, FileLengthException, NoSuchValueException { 
		//File jsonFile = new File(filename);
		InputStream jsonFile = this.getClass().getClassLoader().getResourceAsStream(filename);
		Scanner scanner = new Scanner(jsonFile);
		String jsonString = "";
		Integer currentStringNumber = 0;
		Boolean flag = false;

		while (scanner.hasNextLine()) {
			jsonString = scanner.nextLine();
			switch (mode) {
				case READ_FIRST_MODE: default: {
					flag = true;
					break;
				}
				case STRING_SEARCH_MODE: {
					if (currentStringNumber == Integer.parseInt(value)) { flag = true; }
					currentStringNumber++;
					break;
				}
				case KEY_SEARCH_MODE: {
					JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
					String fieldValue = jsonObject.get(key).getAsString();
					
					if (fieldValue == null) throw new WrongDataFormatException();
					if (value.equals(fieldValue)) { flag = true; }

					break;
				}
			}

			if (flag) break;
		}

		scanner.close();
	
		// THROW SOMETHING		
		if (flag == false) {
			// if file has no desired number of string
			if (mode == STRING_SEARCH_MODE)	throw new FileLengthException();
			// if no value using desired key was found
			if (mode == KEY_SEARCH_MODE)	throw new NoSuchValueException();
		}

		return jsonString;
	}

	// save to json format
	private String serializeToJSON(Object object) {
		String jsonString = "";
		try {
			jsonString = gson.toJson(object);
		} catch (Exception ex) {
			System.out.println(ex.toString());
		}
		return jsonString;
	}

	// get json string
	public String getJSONString(Object object) {
		return serializeToJSON(object);
	}

	// print JSON string
	public void printJSON(Object object) {
		System.out.println(serializeToJSON(object));
	}
}