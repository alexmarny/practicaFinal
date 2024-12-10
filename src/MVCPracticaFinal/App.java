package MVCPracticaFinal;
import controller.Controller;

import model.Model;
import model.IRepository;
import model.BinaryRepository;
import model.NotionRepository;

import view.BaseView;
import view.InteractiveView;
import view.ttsView;


@SuppressWarnings("unused")
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
				repository = getRepositoryForOption(args);
				
			}else{
				// Opciones por defecto:
				view = new InteractiveView();
				repository = new NotionRepository("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", "1577b06ef02d8052a495cb64292c3fb9");
			}
			
			Model model = new Model(repository);
			Controller c = new Controller(model, view);
			
			c.init();  
		}
	
		private static IRepository getRepositoryForOption(String[] args) {
			switch (args[1]) {
				case "notion":
					return new NotionRepository(args[2], args[3]);

				case "bin":
					return new BinaryRepository();

				default:
					return new BinaryRepository();
			}
		}
	
		private static BaseView getViewForoption(String argumento) {
			switch (argumento) {
				default:
					return new InteractiveView();
			}
		}
		
	}
