module birnbaua.dke_pr_client {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
	requires gson;
	opens birnbaua.dke_pr_client.basics to gson;
	exports birnbaua.dke_pr_client.basics;
	requires transitive javafx.base;
	requires javafx.graphics;
	requires jersey.client;
	requires org.controlsfx.controls;
    opens birnbaua.dke_pr_client to javafx.fxml;
    exports birnbaua.dke_pr_client;
}