module com.example.marquinhoscut {
	requires javafx.controls;
	requires javafx.fxml;
	
	requires com.dlsc.formsfx;
	requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.example.marquinhoscut to javafx.fxml;
	exports com.example.marquinhoscut;
	exports com.example.marquinhoscut.Controller;
	opens com.example.marquinhoscut.Controller to javafx.fxml;
	exports com.example.marquinhoscut.Components;
	opens com.example.marquinhoscut.Components to javafx.fxml;
	exports com.example.marquinhoscut.Model;
	opens com.example.marquinhoscut.Model to javafx.fxml;
	
}
