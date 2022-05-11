package base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import serializer.Serializer;

public interface InitializeInterface<T> {
	public void initiateByID(Serializer serializer, Integer ID);
	public void initiateFromJson(Serializer serializer, String json);
	public void customClone(T clone);
}