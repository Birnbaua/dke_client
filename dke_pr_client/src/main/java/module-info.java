module birnbaua.dke_pr_client {
    requires javafx.controls;
    requires javafx.fxml;
	requires gson;
	requires javafx.base;
	requires javafx.graphics;
    opens birnbaua.dke_pr_client to javafx.fxml;
    exports birnbaua.dke_pr_client;
}