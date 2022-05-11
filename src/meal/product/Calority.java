 package meal.product;

// Калорийность на 100 грамм продукта
/*
	Калорийность продукта дается в отношении на 100 грамм
	Калорийность блюда (приема пищи) считается как калорийность продукта,
	умноженное на массу продукта и деленное на 100.
*/

 public class Calority {
 	// Калории в килокалориях
	private Integer calories;
	public void setCalories(Integer calories) { this.calories = calories; }
	public Integer getCalories() { return calories; }

	// белки
	private float protein;
	public void setProtein(float protein) { this.protein = protein; }
	public float getProtein() { return protein; }

	// жиры
	private float fat;
	public void setFat(float fat) { this.fat = fat; }
	public float getFat() { return fat; }

	// углеводы
	private float carbohydrates;
	public void setCarbohydrates(float carbohydrates) { this.carbohydrates = carbohydrates; }
	public float getCarbohydrates() { return carbohydrates; }

	public void addCalority(Calority calority) {
		this.calories += calority.getCalories();
		this.protein += calority.getProtein();
		this.fat += calority.getFat();
		this.carbohydrates += calority.getCarbohydrates();
	}

	public Calority() {
		this.calories = 0;
		this.protein = 0;
		this.fat = 0;
		this.carbohydrates = 0;
	}

	public Calority(Integer calories, float protein, float fat, float carbohydrates) {
		this.calories = calories;
		this.protein = protein;
		this.fat = fat;
		this.carbohydrates = carbohydrates;
	}

	public Calority calculateCalorityOfMass(Integer mass) {
		mass /= 100;
		return new Calority(this.calories * mass, this.protein * mass, this.fat * mass, this.carbohydrates * mass);
	}
 }