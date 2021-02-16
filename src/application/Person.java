package application;

import javafx.scene.shape.Circle;

public class Person extends Circle {
	private String fName;
	private String lName;
	private Person left;
	private Person right;

	public Person() {
		this.fName = "";
		this.lName = "";
		this.left = null;
		this.right = null;
	}

	public Person(String fName, String lName) {
		this.fName = fName;
		this.lName = lName;
		this.left = null;
		this.right = null;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Person getLeft() {
		return left;
	}

	public void setLeft(Person left) {
		this.left = left;
	}

	public Person getRight() {
		return right;
	}

	public void setRight(Person right) {
		this.right = right;
	}

}
