package training.exercise.inventory;

/*
	Intentory:
	ID - идентификатор
	String title - название
	Float weight - вес
*/

public class Inventory {
	private Integer ID;
	public Integer getID() { return ID; }
	public void setID(Integer ID) { this.ID = ID; }

	private String title;
	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return title; }

	// Масса снаряжения
	private Float weight;
	public void setWeight(Float weight) { this.weight = weight; }
	public Float getWeight() { return weight; }
	
	public Inventory(String title, Float weight) { 
		this.title = title;
		this.weight = weight;
	}
}