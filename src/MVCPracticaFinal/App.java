package MVCPracticaFinal;
import controller.Controller;

import model.Model;
import model.IRepository;
import model.BinaryRepository;
import model.NotionRepository;

import view.BaseView;
import view.InteractiveView;


public class App {

		/**
		 * @param args the command line arguments
		 */

		public static void main(String[] args) {
			
			IRepository repository;
			BaseView view;
			
			// LLamada esperada java -jar app.jar view repository
			// por ejemplo: java -jar app.jar bin 
			if(args.length == 2){
				view = getViewForoption(args[0]);
				repository = getRepositoryForOption(args[1]);
				
			}else{
				// Opciones por defecto:
				view = new InteractiveView();
				repository = new BinaryRepository(10);
			}
			
			Model model = new Model(repository);
			Controller c = new Controller(model, view);
			
			c.init();  
		}
	
		private static IRepository getRepositoryForOption(String argumento) {
			switch (argumento) {
				case "notion":
					return new NotionRepository();

				case "bin":
					return new BinaryRepository( 10);

				default:
					return new BinaryRepository( 10);
			}
		}
	
		private static BaseView getViewForoption(String argumento) {
			switch (argumento) {
				default:
					return new InteractiveView();
			}
		}
		
	}
