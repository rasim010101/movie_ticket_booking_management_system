
module com.example.movie_example_01 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;


    opens com.example.movie_example_01 to javafx.fxml;
    exports com.example.movie_example_01;
}