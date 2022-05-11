package training.exercise;

import serializer.Serializer;
import base.InitializeInterface;

/*
	Intentory:
	ID - идентификатор
	String name - название
*/

public class BodyPart implements InitializeInterface<BodyPart> {
	private Integer ID;
	public Integer getID() { return ID; }
	public void setID(Integer ID) { this.ID = ID; }
	
	private String title;
	public void setTitle(String title) { this.title = title; }
	public String getTitle() { return title; }

	public BodyPart(String title) { this.title = title; }

	// interface method implementation
	public void initiateByID(Serializer serializer, Integer ID) {
		try {
			BodyPart bodyPart = (BodyPart)serializer.createObjectByID(BodyPart.class, ID);
			customClone(bodyPart);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void initiateFromJson(Serializer serializer, String json) {
		try {
			BodyPart bodyPart = (BodyPart)serializer.createObjectFromJSON(BodyPart.class, json);
			customClone(bodyPart);
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public void customClone(BodyPart clone) {
		this.ID = clone.getID();
		this.title = clone.getTitle();
	}


	public BodyPart(Serializer serializer, Integer ID) {
		initiateByID(serializer, ID);
	}
}