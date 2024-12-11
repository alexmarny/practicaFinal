
package view;

import javax.swing.*;
import java.awt.Color;
import view.helper_classes.CustomFontLoader;
import view.helper_classes.OnClickEventHelper;
import view.helper_classes.RoundedBorder;

public class GUIView {
  public static void main(String[] args) {

     JFrame frame = new JFrame("LISTA DE TAREAS");
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.setSize(835, 500);
     JPanel panel = new JPanel();
     panel.setLayout(null);
     panel.setBackground(Color.decode("#1e1e1e"));

     JLabel TITULO = new JLabel("MENÚ DE TAREAS");
     TITULO.setBounds(244, 30, 340, 53);
     TITULO.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 40));
     TITULO.setForeground(Color.decode("#D9D9D9"));
     panel.add(TITULO);

     JButton ADD = new JButton("AÑADIR TAREA");
     ADD.setBounds(267, 100, 302, 30);
     ADD.setBackground(Color.decode("#2e2e2e"));
     ADD.setForeground(Color.decode("#D9D9D9"));
     ADD.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 17));
     ADD.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     ADD.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(ADD, Color.decode("#232323"), Color.decode("#2e2e2e"));
		ADD.addActionListener(e -> {
			//TODO: Implementar la funcionalidad de añadir tarea
		});

     panel.add(ADD);

     JButton LIST = new JButton("LISTADO DE TAREAS");
     LIST.setBounds(267, 160, 300, 29);
     LIST.setBackground(Color.decode("#2e2e2e"));
     LIST.setForeground(Color.decode("#D9D9D9"));
     LIST.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 17));
     LIST.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     LIST.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(LIST, Color.decode("#232323"), Color.decode("#2e2e2e"));
     panel.add(LIST);

     JButton MODIFY = new JButton("MODIFICAR UNA TAREA");
     MODIFY.setBounds(267, 220, 300, 29);
     MODIFY.setBackground(Color.decode("#2e2e2e"));
     MODIFY.setForeground(Color.decode("#D9D9D9"));
     MODIFY.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 17));
     MODIFY.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     MODIFY.setFocusPainted(false);
     OnClickEventHelper.setOnClickColor(MODIFY, Color.decode("#232323"), Color.decode("#2e2e2e"));
     panel.add(MODIFY);

     JLabel element5 = new JLabel("Your Text");
     element5.setBounds(140, 108, -2, 313);
     element5.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 14));
     element5.setForeground(Color.decode("#D9D9D9"));
     panel.add(element5);

     JButton IMPORT = new JButton("IMPORTAR / EXPORTAR");
     IMPORT.setBounds(267, 280, 300, 29);
     IMPORT.setBackground(Color.decode("#2e2e2e"));
     IMPORT.setForeground(Color.decode("#D9D9D9"));
     IMPORT.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 17));
     IMPORT.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
     IMPORT.setFocusPainted(false);
	 OnClickEventHelper.setOnClickColor(IMPORT, Color.decode("#232323"), Color.decode("#2e2e2e"));

     panel.add(IMPORT);

	JButton EXIT = new JButton("SALIR");
	EXIT.setBounds(267, 340, 300, 29);
	EXIT.setBackground(Color.decode("#2e2e2e"));
	EXIT.setForeground(Color.decode("#D9D9D9"));
	EXIT.setFont(CustomFontLoader.loadFont("./resources/fonts/Lato.ttf", 17));
	EXIT.setBorder(new RoundedBorder(4, Color.decode("#979797"), 1));
	EXIT.setFocusPainted(false);
	EXIT.addActionListener(e -> System.exit(0));
	panel.add(EXIT);

     frame.add(panel);
     frame.setVisible(true);

  }
}
