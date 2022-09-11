module com.example.marquinhoscut {
	requires javafx.controls;
	requires javafx.fxml;
	
	requires com.dlsc.formsfx;
	requires org.kordamp.bootstrapfx.core;
	
	opens com.example.marquinhoscut to javafx.fxml;
	exports com.example.marquinhoscut;
	exports com.example.marquinhoscut.Controller;
	opens com.example.marquinhoscut.Controller to javafx.fxml;
}
