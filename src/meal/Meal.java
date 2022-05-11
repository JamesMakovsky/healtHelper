package meal;

import meal.product.*;
import java.util.ArrayList;
import serializer.Serializer;

/*
	Meal:
	String name - 
*/

public class Meal {
	// Наименование приема пищи
	private String name;
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }

	// Набор продуктов
	private ArrayList<Product> productList;
	public void setProductList(ArrayList<Product> productList) { this.productList = productList; }
	public ArrayList<Product> getProductList() { return productList; }

	// Калорийность пищи, рассчитанное на всю массу продукта
	private Calority calority;
	public void setCalority(Calority calority) { this.calority = calority; }
	public void setCalority(Integer calories, float protein, float fat, float carbohydrates) {
		this.calority = new Calority(calories, protein, fat, carbohydrates);
	}
	public Calority getCalority() { return calority; }

	// Добавление продукта в меню
	public void addProduct(Product prod) {
		addProductAndUpdateCalority(prod);
	}

	// Добавление продукта в меню по уникальному номеру ID
	public void addProduct(Serializer serializer, Integer ID, Integer massGramms) {
		addProductAndUpdateCalority(new Product(serializer, ID, massGramms));
	}

	// Добавление продукта в меню по json представлению
	public void addProduct(Serializer serializer, String jsonString) {
		addProductAndUpdateCalority(new Product(serializer, jsonString));
	}

	// Добавление продукта в меню со всеми необходимыми параметрами
	public void addProduct(String name, Integer massGramms, Integer calories, Float protein, Float fat, Float carbohydrates) {
		addProductAndUpdateCalority(new Product(name, massGramms, calories, protein, fat, carbohydrates));
	}

	// Добавление в список продукта и обновление калорийности приема пищи
	private void addProductAndUpdateCalority(Product prod) {
		productList.add(prod);
		this.calority.addCalority(prod.getCalority().calculateCalorityOfMass(prod.getMassGramms()));
	}

	public Meal(String name) {
		this.name = name;
		this.productList = new ArrayList<Product>();
		this.calority = new Calority();
	}

	public Meal(String name, ArrayList<Product> productList) {
		this.name = name;
		this.productList = productList;
		this.calority = new Calority();
	}

	public Meal() {
		this.name = "Unknown";
		this.productList = new ArrayList<Product>();
		this.calority = new Calority();
	}
}