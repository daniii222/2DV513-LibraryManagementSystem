
public class Program {

	public static void main(String[] args) {
		model.Database db = new model.Database();
		model.ManagementSystem managementSystem = new model.ManagementSystem(db);
		view.Console view = new view.Console(db);
		controller.LibraryController controller = new controller.LibraryController(managementSystem, view);
		while (controller.menu());
	}
}
