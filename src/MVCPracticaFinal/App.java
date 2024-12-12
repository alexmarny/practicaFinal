package MVCPracticaFinal;
import controller.Controller;

import model.Model;
import model.IRepository;
import model.BinaryRepository;
import model.NotionRepository;

import view.BaseView;
import view.InteractiveView;
import view.ttsView;
import view.GUIView;
import model.RepositoryException;


public class App {

		/**
		 * @param args the command line arguments
		 */

		public static void main(String[] args) {
			
			IRepository repository;
			BaseView view; 
			
			// LLamada esperada java -jar app.jar view repository
			// por ejemplo: java -jar app.jar bin 
			if(args.length <= 5 && args.length > 0){
				view = getViewForoption(args[0]);
				repository = getRepositoryForOption(args);
				
			}else{
				// Opciones por defecto:
				view = new InteractiveView();
				//view = new GUIView();
				//view = new ttsView();
				
				repository = new BinaryRepository(); 
				//repository = new NotionRepository("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", "1577b06ef02d8052a495cb64292c3fb9");
			}
			
			Model model = new Model(repository);
			Controller c = new Controller(model, view);

			try {
				c.init();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
			
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
				case "tts":
					return new ttsView();

				case "interactive":
					return new InteractiveView();
					
				case "gui":
					return new GUIView();

				default:
					return new InteractiveView();
			}
		}
		
	}
