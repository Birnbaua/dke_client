package birnbaua.dke_pr_client.javafx;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomView {
	
	@SafeVarargs
	public static <T> void applyFilter(TableView<T> view, ObservableList<T> list,TextField field, TableColumn<T,?>... columns) {
		FilteredList<T> filteredList = new FilteredList<>(list, p -> true);
		field.textProperty().addListener((observableValue, oldValue, newValue) -> {
			filteredList.setPredicate(t -> {
				if(newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseString = newValue.toLowerCase();
				for(TableColumn<T,?> column : columns) {
					if(column.getCellData(t) != null && column.getCellData(t).toString().toLowerCase().contains(lowerCaseString)) {
						return true;
					}
				}
				return false;
			});
		});
		SortedList<T> sortedList = new SortedList<>(filteredList);
		sortedList.comparatorProperty().bind(view.comparatorProperty());
		view.setItems(sortedList);
	}
	
	public static <T> void applyStandardSettings(TableView<T> view) {
		view.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		view.getSelectionModel().setCellSelectionEnabled(true);
	}

	public static void applyStandardSettings(TextField... arr) {
		for(TextField field : arr) {
			field.setOnKeyReleased(ke -> {
				switch(ke.getCode()) {
				case ENTER:
					field.commitValue();
					field.getParent().requestFocus();
					break;
				case ESCAPE:
					field.setText(null);
					field.cancelEdit();
					field.getParent().requestFocus();
				default:
					break;
				}
			});
		}
	}
}
