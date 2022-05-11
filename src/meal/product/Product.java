package meal.product;

import serializer.Serializer;
import base.InitializeInterface;

/*
	Product:
	ID - уникальный номер продукта
	String name - название
	Integer massGramms - масса продукта в граммах
	Энергетическая ценность (БЖУ) в граммах:
	Float calories - калорийность на 100 грамм продукта
	float protein - белки
	float fat - жиры
	float carbohydrates - углеводы
*/

public class Product implements InitializeInterface<Product> {
	// Уникальный идентификатор
	private Integer ID;
	public void setID(Integer ID) { this.ID = ID; }
	public Integer getID() { return ID; }

	// Наименование продукта
	private String name;
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }

	// Калорийность продукта НА 100 ГРАММ ПРОДУКТА
	private Calority calority;
	public void setCalority(Calority calority) { this.calority = calority; }
	public void setCalority(Integer calories, float protein, float fat, float carbohydrates) {
		this.calority = new Calority(calories, protein, fat, carbohydrates);
	}
	public Calority getCalority() { return calority; }

	// Масса в граммах
	private Integer massGramms;
	public void setMassGramms(Integer massGramms) { this.massGramms = massGramms; }
	public Integer getMassGramms() { return massGramms; }

	// interface method implementation
	public void initiateByID(Serializer serializer, Integer ID) {
		try {
			Product product = (Product)serializer.createObjectByID(Product.class, ID);
			customClone(product);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void initiateFromJson(Serializer serializer, String json) {
		try {
			Product product = (Product)serializer.createObjectFromJSON(Product.class, json);
			customClone(product);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void customClone(Product clone) {
		this.ID = clone.getID();
		this.name = clone.getName();
		this.calority = clone.getCalority();
		this.massGramms = clone.getMassGramms();
	}
	

	// Загрузка из файла с продуктами по ID
	public Product(Serializer serializer, Integer ID, Integer massGramms) {
		initiateByID(serializer, ID);
		this.setMassGramms(massGramms);
	}

	// Загрузка из файла с продуктами по JSON представлению
	public Product(Serializer serializer, String jsonString) {
		initiateFromJson(serializer, jsonString);
	}

	// Конструктор
	public Product(String name, Integer massGramms, Integer calories, Float protein, Float fat, Float carbohydrates) {
		this.name = name;
		this.calority = new Calority(calories, protein, fat, carbohydrates);
		this.massGramms = massGramms;
	}

	public Product() {
		this.ID = -1;
		this.name = "Unknown";
		this.calority = new Calority();
	}
}