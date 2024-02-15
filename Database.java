import java.util.ArrayList;


public class Database {

	ArrayList<Object> database = new ArrayList<Object>();
	ArrayList<Object> sub_database = new ArrayList<Object>();

	// Get User Information with Id
	public ArrayList<Object> InfoGetter(int id){
		return (ArrayList<Object>)(database.get(id));
	}
	
	// Add new User
	public void addPerson(ArrayList<Object> added){
		if(added.size() < 6) {
			System.out.println("Missing arguments should be 6 but have " + added.size());
		}
		else {
			database.add(added);
		}
	}
	

	public void modifyPerson(int id, int subject, Object replacement){
		this.InfoGetter(id).set(subject, replacement);
	}
	
	
	public static void main(String[] args) {
	 Database d = new Database();
	 
	 // Person 1
	 ArrayList<Object> added = new ArrayList<Object>();
	 
	 added.add(1);added.add("john");added.add("pass2");
	 
	 ArrayList<Object> Expenses = new ArrayList<Object>();
	 Expenses.add(1);Expenses.add(2);added.add(Expenses);
	 
	 ArrayList<Object> Incomes = new ArrayList<Object>();
	 Incomes.add(3);Incomes.add(4);added.add(Incomes);
	 
	 ArrayList<Object> Investments = new ArrayList<Object>();
	 Investments.add(100);Investments.add(200);added.add(Investments);
	 
	 
	 d.addPerson(added);
	 
	 // Person 2
	 ArrayList<Object> added2 = new ArrayList<Object>();
	 
	 added2.add(1);added2.add("joe");added2.add("pass2");
	 added2.add(1);added2.add(2);added2.add(5);
	 
	 d.addPerson(added2);
	 
	 System.out.println(d.InfoGetter(0));
	 System.out.println(d.InfoGetter(1));
	 
	 
	 d.modifyPerson(0, 1, 3);
	 System.out.println(d.InfoGetter(0));
	}
}
