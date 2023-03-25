package utility;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class Search {

    public void userSearch(TextField search, TableView table, ObservableList<userData> list) {
        search.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(list);
                    return;
                }

                ObservableList<userData> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<userData, ?>> column = table.getColumns();

                for (int row = 0; row < list.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {

                        TableColumn colVar = column.get(col);
                        String cellValue = colVar.getCellData(list.get(row)).toString();
                        cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(search.getText().toLowerCase()) && cellValue.startsWith(search.getText().toLowerCase())) {
                            items.add(list.get(row));
                            break;
                        }
                    }
                }
                table.setItems(items);
            }
        });

    }
    public void testSearch(TextField search, TableView table, ObservableList<TestData> list) {
        search.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(list);
                    return;
                }

                ObservableList<TestData> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<TestData, ?>> column = table.getColumns();

                for (int row = 0; row < list.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {

                        TableColumn colVar = column.get(col);
                        String cellValue = colVar.getCellData(list.get(row)).toString();
                        cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(search.getText().toLowerCase()) && cellValue.startsWith(search.getText().toLowerCase())) {
                            items.add(list.get(row));
                            break;
                        }
                    }
                }
                table.setItems(items);
            }
        });

    }
    
    public void drugSearch(TextField search, TableView table, ObservableList<DrugsData> list) {
        search.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable observable) {
                if (search.textProperty().get().isEmpty()) {
                    table.setItems(list);
                    return;
                }

                ObservableList<DrugsData> items = FXCollections.observableArrayList();
                ObservableList<TableColumn<DrugsData, ?>> column = table.getColumns();

                for (int row = 0; row < list.size(); row++) {
                    for (int col = 0; col < column.size(); col++) {

                        TableColumn colVar = column.get(col);
                        String cellValue = colVar.getCellData(list.get(row)).toString();
                        cellValue = cellValue.toLowerCase();

                        if (cellValue.contains(search.getText().toLowerCase()) && cellValue.startsWith(search.getText().toLowerCase())) {
                            items.add(list.get(row));
                            break;
                        }
                    }
                }
                table.setItems(items);
            }
        });

    }

}
