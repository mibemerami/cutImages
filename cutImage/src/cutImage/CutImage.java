package cutImage;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
*
* @author  Milan Brzezinski
*/

public class CutImage extends javafx.application.Application{
	public static int width;
	public static int height;
	int anzWidth;
	int anzHeight;
	Label lb1 = new Label();
	Label lb2 = new Label();
	Label lb3 = new Label();
	Label lb4 = new Label();
	Label lb5 = new Label();
	Label lb6 = new Label();
	TextField tf1 = new TextField();
	TextField tf2 = new TextField();
	TextField tf3 = new TextField();
	TextField tf4 = new TextField();
	TextField tf5 = new TextField();
	Button btn = new Button("O.K.");
	BorderPane border = new BorderPane();
	HBox hb1 = new HBox();
	HBox hb2 = new HBox();
	HBox hb3 = new HBox();
	HBox hb4 = new HBox();
	HBox hb5 = new HBox();
	HBox hb6 = new HBox();
	HBox hb7 = new HBox();
	VBox vb1 = new VBox();
	
	GridPane grid = new GridPane();

	public static void main(String[] args) {
		launch(args);

	}
	
	public void start( Stage stage){
		
		stage.setTitle("CutImages 1.0");

		lb1.setText("Insert image-source-path ( e.g.: \"E:\\test\\images\\logo.jpg\" )");
		lb2.setText("Insert width in px ( e.g.: \"30\" )");
		lb3.setText("Insert height in px ( e.g.: \"30\" )");
		lb4.setText("Insert image-destintion-folder ( e.g.: \"E:\\test\\subimages\\\" )");
		lb5.setText("Insert image-file-type, ( without dot, e.g.: \"png\" )");
		
		
		hb1.setSpacing(10);
		hb2.setSpacing(10);
		hb3.setSpacing(10);
		hb4.setSpacing(10);
		hb5.setSpacing(10);
				
		hb1.getChildren().add(tf1);
		hb1.getChildren().add(lb1);
		hb2.getChildren().add(tf2);
		hb2.getChildren().add(lb2);
		hb3.getChildren().add(tf3);
		hb3.getChildren().add(lb3);
		hb4.getChildren().add(tf4);
		hb4.getChildren().add(lb4);
		hb5.getChildren().add(tf5);
		hb5.getChildren().add(lb5);
		hb6.getChildren().add(btn);
		hb7.getChildren().add(lb6);
						
		vb1.getChildren().add(hb1);
		vb1.getChildren().add(hb2);
		vb1.getChildren().add(hb3);
		vb1.getChildren().add(hb4);
		vb1.getChildren().add(hb5);
		vb1.getChildren().add(hb6);
		vb1.getChildren().add(hb7);
		
		this.setReactions();
		
		Scene scene = new Scene(vb1, 550, 450);
		stage.setScene(scene);
		stage.show();

	}
	
	
	void setReactions(){
		btn.setOnAction(e -> {
			String sourcePath =  tf1.getText();
			String destPath = tf4.getText();
			String fileType = tf5.getText();
			
			try{
				BufferedImage img1 = ImageIO.read(new File(sourcePath));
								
				width = Integer.valueOf(tf2.getText());
				height = Integer.valueOf(tf3.getText());
				anzWidth = (int) img1.getWidth()/width;
				anzHeight = (int) img1.getHeight()/height;
				System.out.println("Breite, Höhe: "+width+" "+height);
				System.out.println("Anzahl der neuen Bilder: "+anzWidth+" * "+anzHeight);
				lb6.setText("Width, Height: "+width+" "+height
						+"\n"+"New Images: "+anzWidth+" * "+anzHeight);
				
				for(int i=0; i < anzWidth; i++){
					for(int j = 0; j < anzHeight; j++){
						BufferedImage img2 = img1.getSubimage((i*width),(j*height),width, height);
						ImageIO.write(img2, fileType, new File(destPath+"x"+i+"y"+j+"."+fileType));
					}
				}
				
				lb6.setText("Width, Height: "+width+" "+height
						+"\n"+"New Images: "+anzWidth+" * "+anzHeight
						+"\nAction was successful");
				
			} catch (IOException exc) {
				lb6.setText("Action failed");
	            exc.printStackTrace();
	            
	        }
		});
	}

}
