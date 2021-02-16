package application;

import java.util.Stack;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Tree extends Pane {
	public String strgbl = "";
	private Person rootFname = null;
	private Person lNameRoot = null;
	private String str;
	private double radius = 25;
	private double vGap = 70;

	public Tree() {
		this.rootFname = null;
		this.lNameRoot = null;
		strgbl = "";
		this.setStatus("Empty Tree");

	}

	public void setStatus(String msg) {
		strgbl = strgbl + "," + msg;
		Text text = new Text(20, 20, strgbl);
		text.setFont(Font.font("Verdana", FontWeight.NORMAL, 12));
		text.setFill(Color.BLACK);
		getChildren().add(text);
	}

	public void clearAllDisplay() {
		strgbl = "";
		getChildren().clear();
	}

	public void inOrder(Person p) {
		if (p != null) {
			inOrder(p.getLeft());// Before

			setStatus(p.getfName()); // Root

			inOrder(p.getRight());// Right

		}

	}

	public void preOrder(Person p) {
		if (p == null) {
			return;
		}
		setStatus(p.getfName()); // Root
		preOrder(p.getLeft());// Left
		preOrder(p.getRight());// Right
	}

	public void postOrder(Person p) {
		if (p == null) {
			return;
		}
		postOrder(p.getLeft());// Left
		postOrder(p.getRight());// Right
		setStatus(p.getfName()); // Root
	}

	public void displayHeight(Person p) {
		int k = height(p);
		setStatus("Height=" + Integer.toString(k));
	}

	public int height(Person p) {
		if (p == null)
			return 0;
		else {
			/* compute the depth of each subtree */
			int lDepth = height(p.getLeft());
			int rDepth = height(p.getRight());

			/* use the larger one */
			if (lDepth > rDepth)
				return (lDepth + 1);
			else
				return (rDepth + 1);
		}
	}

	/* Function to print level order line by line traversing a tree */
	public void printLevelOrder(Person p) {
		int h = height(p);
		int i;
		for (i = 1; i <= h; i++) {
			printGivenLevel(p, i);
			// System.out.println();
		}

	}

	public Person namelength(Person root, int nameLength) {
		Stack<Person> todo = new Stack<Person>();
		todo.push(root);
		while (!todo.isEmpty()) {
			Person p = todo.pop();
			if (p.getfName().length() == nameLength) {
				setStatus(p.getfName());
				// System.out.println(p.getfName());
				// return p;
			}
			if (p.getLeft() != null) {
				todo.push(p.getLeft());
			}
			if (p.getRight() != null) {
				todo.push(p.getRight());
			}
		}

		return null;
	}

	/* Print nodes at a given level */
	void printGivenLevel(Person p, int level) {
		if (p == null)
			return;
		if (level == 1) {
			setStatus(p.getfName());
			// System.out.println(p.getfName());
		}

		else if (level > 1) {
			printGivenLevel(p.getLeft(), level - 1);
			printGivenLevel(p.getRight(), level - 1);
		}
	}

	public void displayTree(Person root, double x, double y, double hGap, Color color) {
		if (root.getLeft() != null) {
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2, color);
		}

		if (root.getRight() != null) {
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2, color);
		}
		Circle circle = new Circle(x, y, radius);
		// String firstName;
		circle.setFill(color);

		circle.setStroke(Color.BLACK);
		getChildren().addAll(circle, new Text(x - 16, y + 5, root.getfName() + ""));
	}

	public void displayTreeLastName(Person root, double x, double y, double hGap, Color color) {
		if (root.getLeft() != null) {
			getChildren().add(new Line(x - hGap, y + vGap, x, y));
			displayTree(root.getLeft(), x - hGap, y + vGap, hGap / 2, color);
		}

		if (root.getRight() != null) {
			getChildren().add(new Line(x + hGap, y + vGap, x, y));
			displayTree(root.getRight(), x + hGap, y + vGap, hGap / 2, color);
		}

		Circle circle = new Circle(x, y, radius);
		// String firstName;
		circle.setFill(color);

		circle.setStroke(Color.BLACK);
		getChildren().addAll(circle, new Text(x - 16, y + 5, root.getlName() + ""));
	}

	public void addPersonFName(Person p) { // assign root for fName Tree
		addPersonFName(rootFname, p);
	}

	public void addPersonLName(Person p) { // assign root for lName Tree
		addPersonLName(lNameRoot, p);
	}

	public void addPersonFName(Person current, Person newP) {
		if (rootFname == null) {
			rootFname = newP;
			return;
		} else if (current.getfName().equals(newP.getfName())) {
			return;
		} else if (current.getfName().compareTo(newP.getfName()) >= 1) { // if current is greater set new person to
																			// Left/Right

			if (current.getLeft() == null) { // and left of current is null
				current.setLeft(newP); // set new person to left
			} else {
				addPersonFName(current.getLeft(), newP); // if current is occupied,
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(newP);
			} else {
				addPersonFName(current.getRight(), newP);
			}
		}
	}

	public void addPersonLName(Person current, Person newP) {
		if (lNameRoot == null) {
			lNameRoot = newP;
			return;
		} else if (current.getlName().equals(newP.getlName())) {
			return;
		}

		else if (current.getlName().compareTo(newP.getlName()) >= 1) {
			if (current.getLeft() == null) {
				current.setLeft(newP);
			} else {
				addPersonLName(current.getLeft(), newP);
			}
		} else {
			if (current.getRight() == null) {
				current.setRight(newP);
			} else {
				addPersonLName(current.getRight(), newP);
			}
		}
	}

	public Person searchFname(Person rootFname, String firstName) {
		// is this correct??
		if (rootFname == null) {
			// System.out.println("Name not Found");
			setStatus("Name not Found");
			return null;
		}
		if (rootFname.getfName().compareTo(firstName) == 0) {
			// System.out.println("Name Found ");
			setStatus("Name Found");
			rootFname.setFill(Color.BLUE);
			return rootFname;
		}

		if (rootFname.getfName().compareTo(firstName) >= 1) {
			// search left side
			searchFname(rootFname.getLeft(), firstName);
		}

		if (rootFname.getfName().compareTo(firstName) < 1) {
			// search right side
			searchFname(rootFname.getRight(), firstName);

		}
		return null;

	}

	public Person searchLname(Person rootLname, String lastName) {

		if (rootLname == null) {
			System.out.println("Name not Found");
			return null;
		}
		if (rootLname.getlName().compareTo(lastName) == 0) {
			System.out.println("Name Found ");
			return rootLname;
		}

		if (rootLname.getlName().compareTo(lastName) >= 1) {
			// search left side
			System.out.println(rootLname.getlName());
			searchLname(rootLname.getLeft(), lastName);
		}

		if (rootLname.getlName().compareTo(lastName) < 1) {
			// search right side
			System.out.println(rootLname.getlName());
			searchLname(rootLname.getRight(), lastName);

		}

		return null;

	}

	public void detetePersonLastname(String name) {
		Person parent = new Person();
		parent = null;
		Person temp = new Person();
		temp = lNameRoot;
		if (lNameRoot.getlName().equals(name)) {
			System.out.println("Root cannot be deleted");
		} else {
			while (!(temp.getlName().equals(name))) {
				if (temp.getlName().compareTo(name) >= 1) {
					parent = temp;
					temp = temp.getLeft();
				} else if (temp.getlName().compareTo(name) < 1) {
					parent = temp;
					temp = temp.getRight();
				}
			}
			// temp doesn't have any children
			if ((temp.getLeft() == null) && temp.getRight() == null) {
				if (parent.getfName().compareTo(name) >= 1) {
					parent.setLeft(null);
				} else {
					parent.setRight(null);
				}
			}
			// temp doesn't have any children
			if ((temp.getLeft() != null) && temp.getRight() == null) {
				if (parent.getfName().compareTo(name) >= 1) {
					parent.setLeft(temp.getLeft());
				} else {
					parent.setRight(temp.getLeft());
				}
			}
			// temp has only right children
			if ((temp.getLeft() == null) && temp.getRight() != null) {
				if (parent.getfName().compareTo(name) >= 1) {
					parent.setLeft(temp.getRight());
				} else {
					parent.setRight(temp.getRight());
				}
			}
			// temp has both left and right children
			if ((temp.getLeft() != null) && temp.getRight() != null) {
				// find inorder successor
				Person temp1 = new Person();
				temp1 = temp.getLeft();
				while (temp1.getRight() != null) {
					temp1 = temp1.getRight();
				}
				this.str = temp1.getlName();
				detetePersonLastname(this.str);
				temp.setlName(this.str);
			}

		}
	}

	public void detetePersonFirstname(Person root, String name) {
		Person parent = new Person();
		parent = null;
		Person temp = new Person();
		temp = root;
		if (root.getfName().equals(name)) {
			System.out.println("Root cannot be deleted");
		} else {
			while (!(temp.getfName().equals(name))) {
				if (temp.getfName().compareTo(name) >= 1) {
					parent = temp;
					temp = temp.getLeft();
				} else if (temp.getfName().compareTo(name) < 1) {
					parent = temp;
					temp = temp.getRight();
				}
			}
			// temp doesn't have any children
			if ((temp.getLeft() == null) && temp.getRight() == null) {
				if (parent.getfName().compareTo(name) >= 1) {
					parent.setLeft(null);
				} else {
					parent.setRight(null);
				}
			}
			// temp has only left children
			if ((temp.getLeft() != null) && temp.getRight() == null) {
				if (parent.getfName().compareTo(name) >= 1) {
					parent.setLeft(temp.getLeft());
				} else {
					parent.setRight(temp.getLeft());
				}
			}
			// temp has only right children
			if ((temp.getLeft() == null) && temp.getRight() != null) {
				if (parent.getfName().compareTo(name) >= 1) {
					parent.setLeft(temp.getRight());
				} else {
					parent.setRight(temp.getRight());
				}
			}
			// temp has both left and right children
			if ((temp.getLeft() != null) && temp.getRight() != null) {
				// find inorder successor
				Person temp1 = new Person();
				temp1 = temp.getLeft();
				while (temp1.getRight() != null) {
					temp1 = temp1.getRight();
				}
				this.str = temp1.getfName();
				detetePersonFirstname(root, this.str);
				temp.setfName(this.str);
			}

		}
	}

	public Person getRootFname() {
		return rootFname;
	}

	public void setRootFname(Person rootFname) {
		this.rootFname = rootFname;
	}

	public Person getlNameRoot() {
		return lNameRoot;
	}

	public void setlNameRoot(Person lNameRoot) {
		this.lNameRoot = lNameRoot;
	}

}