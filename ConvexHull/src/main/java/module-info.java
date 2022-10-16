module com.example.convexhull {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.convexhull to javafx.fxml;
    exports com.example.convexhull;
}