
public class Tags {

	private Controller controller = null;
	private Storage storage = null;
	
	public void addListener(Controller controller, Storage storage) {
		this.controller = controller;
		this.storage = storage;
	}
	
	public String start() {
		System.out.println("Succesfully entered tags.start() method. Returning up one level");
		return ("0");
	}
	
}
