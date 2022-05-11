package training.exercise;

import javax.imageio.*;
import java.awt.image.*;
import java.util.ArrayList;

import training.exercise.inventory.*;
import serializer.Serializer;

import base.InitializeInterface;

/*
	Exercise:
	ID - уникальный идентификатор
	MusculGroup bodyPart - задействуемая группа мышц
	Inventory inventory - инвентарь для упражнения
	String name - название упражнения
	String description - текстовое описание упражнения
	Boolean isInventoryUsed - флаг использования инвентаря
	Float caloriesAverage - количество калорий за одно повторение (1 раз)
	Integer approaches - подходы
	Integer times - повторения
	Float fullCalories - полное количество калорий за выполненное упражнение
	String ImagePath - путь к изображению. Параметры изображения будут заданы непосредственно в графической части
	(Удалил) BufferedImage image - изображение/gif-ка, демонстрирующая упражнение
*/

public class Exercise implements InitializeInterface<Exercise> {
	// Уникальный идентификатор
	private Integer ID;
	public Integer getID() { return ID; }
	public void setID(Integer ID) { this.ID = ID; }

	// Задействованные мышечные группы
	protected ArrayList<BodyPart> bodyPartList;
	public ArrayList<BodyPart> getBodyPartList() { return bodyPartList; }
	public void setMusculGroupList(ArrayList<BodyPart> bodyPartList) { this.bodyPartList = bodyPartList; }
	public void addBodyPart(Serializer serializer, Integer bodyPartID) { addBodyPart(new BodyPart(serializer, bodyPartID)); }
	public void addBodyPart(BodyPart bodyPart) {
		if (bodyPartList == null) { bodyPartList = new ArrayList<BodyPart>(); }
		try {
			this.bodyPartList.add(bodyPart);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Снаряжение
	protected ArrayList<Inventory> inventoryList;
	public ArrayList<Inventory> getInventoryList() { return inventoryList; }
	public void setInventoryList(ArrayList<Inventory> inventoryList) { this.inventoryList = inventoryList; }
	public void addInventory(String name, Float weight) { addInventory(new Inventory(name, weight)); }
	public void addInventory(Inventory inventory) {
		if (inventoryList == null) { inventoryList = new ArrayList<Inventory>(); }
		try {
			this.inventoryList.add(inventory);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	// Название упражнения
	protected String name;
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	// Описание упражнения
	protected String description;
	public String getDescription() { return description; }
	public void setDescription(String description) { this.description = description; }

	// Подходы - количество "кругов" повторения упражнения
	protected Integer approaches;
	public Integer getApproaches() { return approaches; }
	public void setApproaches(Integer approaches) { this.approaches = approaches; }

	// Повторения - количество раз выполнения упражнения в каждом подходе
	protected Integer times;
	public Integer getTimes() { return times; }
	public void setTimes(Integer times) { this.times = times; }

	// Тут могла юыть ваша реклама
	private String imagePath;
	public String getImagePath() { return imagePath; }
	public void setImagePath(String imagePath) { this.imagePath = imagePath; }

	// PUBLIC CONSTRUCTOR
	public Exercise(Serializer serializer, String name, String description, ArrayList<Integer> bodyPartIDList, Integer approaches, Integer times) {
		if (bodyPartIDList != null) {
			this.bodyPartList = new ArrayList<BodyPart>();
			for (Integer bodyPartID : bodyPartIDList) {
				this.addBodyPart(serializer, bodyPartID);
			}
		}

		this.name = name;
		this.description = description;
		this.inventoryList = null;
		this.approaches = approaches;
		this.times = times;
	}

	// IdentifierCreature interface override
	public void initiateByID(Serializer serializer, Integer ID) {
		try {
			Exercise exercise = (Exercise)serializer.createObjectByID(Exercise.class, ID);
			customClone(exercise);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void initiateFromJson(Serializer serializer, String json) {
		try {
			Exercise exercise = (Exercise)serializer.createObjectFromJSON(Exercise.class, json);
			customClone(exercise);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void customClone(Exercise clone) {
		this.ID = clone.getID();
		this.name = clone.getName();
		this.description = clone.getDescription();
		this.inventoryList = clone.getInventoryList();
		this.bodyPartList = clone.getBodyPartList();
	}

	public Exercise(Serializer serializer, Integer ID) {
		initiateByID(serializer, ID);
	}
}