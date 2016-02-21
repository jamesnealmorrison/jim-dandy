package com.jimmie.domain;

public interface LightSource {
	Position getPosition();
	int getRadius();
	IlluminationType getIlluminationType();
	void setPosition(Position position);
}
