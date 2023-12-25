package com.example.movie_example_01;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class dashboardController implements Initializable {

    @FXML
    private DatePicker addMovies_date;

    @FXML
    private AnchorPane topForm;

    @FXML
    private Button addMovies_btn;

    @FXML
    private Button addMovies_clearBtn;

    @FXML
    private TableColumn<movieData, Date> addMovies_col_date;

    @FXML
    private TableColumn<movieData, String> addMovies_col_duration;

    @FXML
    private TableColumn<movieData, String> addMovies_col_genre;

    @FXML
    private TableColumn<movieData, String> addMovies_col_movieTitle;


    @FXML
    private Button addMovies_deleteBtn;

    @FXML
    private TextField addMovies_duration;

    @FXML
    private AnchorPane addMovies_form;

    @FXML
    private TextField addMovies_genre;

    @FXML
    private ImageView addMovies_imageView;

    @FXML
    private Button addMovies_import;

    @FXML
    private Button addMovies_insertBtn;

    @FXML
    private TextField addMovies_movieTitle;

    @FXML
    private TextField addMovies_search;

    @FXML
    private TableView<movieData> addMovies_tableView;

    @FXML
    private Button addMovies_updateBtn;

    @FXML
    private Button availableMovies_btn;

    @FXML
    private Button availableMovies_buyBtn;

    @FXML
    private Button availableMovies_clearBtn;

    @FXML
    private TableColumn<?, ?> availableMovies_col_genre;

    @FXML
    private TableColumn<?, ?> availableMovies_col_movieTitle;

    @FXML
    private TableColumn<?, ?> availableMovies_col_showingDate;

    @FXML
    private Label availableMovies_date;

    @FXML
    private AnchorPane availableMovies_form;

    @FXML
    private Label availableMovies_genre;

    @FXML
    private ImageView availableMovies_imageView;

    @FXML
    private Label availableMovies_movieTitle;

    @FXML
    private Label availableMovies_normalClass_price;

    @FXML
    private Spinner<Integer> availableMovies_normalClass_quantity;

    @FXML
    private Button availableMovies_reciepeBtn;

    @FXML
    private Button availableMovies_selectMovie;

    @FXML
    private Label availableMovies_specialClass_price;

    @FXML
    private Spinner<Integer> availableMovies_specialClass_quantity;

    @FXML
    private TableView<movieData> availableMovies_tableView;

    @FXML
    private Label availableMovies_title;

    @FXML
    private Label availableMovies_total;

    @FXML
    private Button close;

    @FXML
    private Button customers_btn;

    @FXML
    private Button customers_clearBtn;

    @FXML
    private TableColumn<?, ?> customers_col_date;

    @FXML
    private TableColumn<?, ?> customers_col_genre;

    @FXML
    private TableColumn<?, ?> customers_col_movieTitle;

    @FXML
    private TableColumn<?, ?> customers_col_ticketNumber;

    @FXML
    private TableColumn<?, ?> customers_col_time;

    @FXML
    private Label customers_date;

    @FXML
    private Button customers_deleteBtn;

    @FXML
    private AnchorPane customers_form;

    @FXML
    private Label customers_genre;

    @FXML
    private Label customers_movieTitle;

    @FXML
    private TextField customers_search;

    @FXML
    private TableView<?> customers_tableView;

    @FXML
    private Label customers_ticketNumber;

    @FXML
    private Label customers_time;

    @FXML
    private AnchorPane dashboard_availableMovies;

    @FXML
    private Button dashboard_btn;

    @FXML
    private AnchorPane dashboard_form;

    @FXML
    private AnchorPane dashboard_totalEarnToday;

    @FXML
    private AnchorPane dashboard_totalSoldTicket;

    @FXML
    private Button editScreening_btn;

    @FXML
    private TableColumn<?, ?> editScreening_col_current;

    @FXML
    private TableColumn<?, ?> editScreening_col_duration;

    @FXML
    private TableColumn<?, ?> editScreening_col_genre;

    @FXML
    private TableColumn<?, ?> editScreening_col_movieTitle;

    @FXML
    private ComboBox<?> editScreening_current;

    @FXML
    private Button editScreening_deleteBtn;

    @FXML
    private AnchorPane editScreening_form;

    @FXML
    private ImageView editScreening_imageView;

    @FXML
    private TextField editScreening_search;

    @FXML
    private TableView<movieData> editScreening_tableView;

    @FXML
    private Label editScreening_title;

    @FXML
    private Button editScreening_updateBtn;

    @FXML
    private Button minimize;

    @FXML
    private Button signout;

    @FXML
    private Label username;

    private Image image;

    private double x = 0;
    private double y = 0;

    private Connection connect;
    private PreparedStatement prepare;
    private Statement statement;
    private ResultSet result;

    private SpinnerValueFactory<Integer> spinner1;
    private SpinnerValueFactory<Integer> spinner2;

    private float price1 = 0;
    private float price2 = 0;
    private float total = 0;
    private int qty1 = 0;
    private int qty2 = 0;

    public void receipt(){



    }

    public void buy(){

        String sql = "INSERT INTO customer (type,total,date) VALUES(?, ?, ?)";

        connect = database.connectDb();

        String type = "";
        if(price1 > 0){
            type = "Special Class";
        }else if(price2 > 0 && price1 == 0){
            type = "Normal Class";
        }else if(price2 > 0 && price1 == 0) {
            type = "Special & Normal Class";
        }

        Date date = new Date();
        java.sql.Date setDate = new java.sql.Date(date.getTime());

        try {

            prepare = connect.prepareStatement(sql);
            prepare.setString(1, type);
            prepare.setString(2, String.valueOf(total));
            prepare.setDate(3, setDate);

            Alert alert;

            if(availableMovies_imageView.getImage() == null
                    || availableMovies_title.getText().isEmpty()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();
            }else if(price1 == 0 && price2 == 0){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please indicate the quantity of ticket you want to purchase");
                alert.showAndWait();

            }else{
                prepare.executeUpdate();

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully purchase!");
                alert.showAndWait();

                String sql1 = "SELECT * FROM customer";
                prepare = connect.prepareStatement(sql1);
                result = prepare.executeQuery();

                int num = 0;

                while(result.next()){
                    num = result.getInt("id");

                }
                String sql2 = "INSERT INTO customer_info(customer_id, type, total, movieTitle) VALUES (?, ?, ?, ?)";

                prepare = connect.prepareStatement(sql2);
                prepare.setString(1, String.valueOf(num));
                prepare.setString(2, type);
                prepare.setString(3, String.valueOf(total));
                prepare.setString(4, availableMovies_title.getText());
                prepare.execute();

                clearPurchaseTicketInfo();


            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void clearPurchaseTicketInfo(){

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        availableMovies_specialClass_quantity.setValueFactory(spinner1);
        availableMovies_normalClass_quantity.setValueFactory(spinner2);

        availableMovies_specialClass_price.setText("$0.0");
        availableMovies_normalClass_price.setText("$0.0");
        availableMovies_total.setText("$0.0");

        availableMovies_imageView.setImage(null);
        availableMovies_title.setText("");

    }

    public void showSpinnerValue(){

        spinner1 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);
        spinner2 = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10, 0);

        availableMovies_specialClass_quantity.setValueFactory(spinner1);
        availableMovies_normalClass_quantity.setValueFactory(spinner2);

    }

    public void getSpinnerValue(MouseEvent event){

        qty1 = availableMovies_specialClass_quantity.getValue();
        qty2 = availableMovies_normalClass_quantity.getValue();

        price1 = (qty1 * 25);
        price2 = (qty2 * 10);

        total = (price1 + price2);

        availableMovies_specialClass_price.setText("$"+String.valueOf(price1));
        availableMovies_normalClass_price.setText("$"+String.valueOf(price2));

        availableMovies_total.setText("$"+String.valueOf(total));

    }


    public ObservableList<movieData> availableMoviesList(){

        ObservableList<movieData> listAvMovies = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie WHERE current = 'Showing'";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            movieData movD;

            while(result.next()){

                movD = new movieData(result.getInt("id")
                        , result.getString("movie_Title")
                        , result.getString("genre")
                        , result.getString("duration")
                        , result.getString("image")
                        , result.getDate("date")
                        , result.getString("current"));
                listAvMovies.add(movD);

            }

        }catch(Exception e){e.printStackTrace();}
        return listAvMovies;
    }

    private ObservableList<movieData> availableMoviesList;
    public void showAvailableMovies(){

        availableMoviesList = availableMoviesList();

        availableMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        availableMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        availableMovies_col_showingDate.setCellValueFactory(new PropertyValueFactory<>("date"));

        availableMovies_tableView.setItems(availableMoviesList);

    }

    public void selectAvailableMovies(){

        movieData movD = availableMovies_tableView.getSelectionModel().getSelectedItem();
        int num = availableMovies_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < 1){
            return;
        }

        availableMovies_movieTitle.setText(movD.getTitle());
        availableMovies_genre.setText(movD.getGenre());
        availableMovies_date.setText(String.valueOf(movD.getDate()));

        getData.path = movD.getImage();
        getData.title = movD.getTitle();

    }

    public void selectMovie(){

        Alert alert;

        if(availableMovies_movieTitle.getText().isEmpty()
                || availableMovies_genre.getText().isEmpty()
                || availableMovies_date.getText().isEmpty()){
            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please select the movie first");
            alert.showAndWait();

        }else {

            String uri = "file:" + getData.path;

            image = new Image(uri, 142, 195, false, true);
            availableMovies_imageView.setImage(image);

            availableMovies_title.setText(getData.title);

            availableMovies_movieTitle.setText("");
            availableMovies_genre.setText("");
            availableMovies_date.setText("");

        }

    }

    private String[] currentList = {"Showing", "End Showing"};

    public void comboBox(){
        List<String> listCurrent = new ArrayList<>();

        for(String data: currentList){
            listCurrent.add(data);
        }

        ObservableList listC = FXCollections.observableArrayList(listCurrent);
        editScreening_current.setItems(listC);
    }

    public ObservableList<movieData> editScreeningList(){

        ObservableList<movieData> editSList = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            movieData movD;

            while(result.next()){

                movD = new movieData(result.getInt("id"),
                        result.getString("movie_Title"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                editSList.add(movD);
            }

        }catch(Exception e){e.printStackTrace();}
        return editSList;
    }

    public void updateEditScreening(){

        String sql = "UPDATE movie SET current = '"
                + editScreening_current.getSelectionModel().getSelectedItem()
                + "' WHERE movie_Title = '" + editScreening_title.getText() + "'";

        connect = database.connectDb();

        try{

            statement = connect.createStatement();

            Alert alert;

            if(editScreening_title.getText().isEmpty()
                    || editScreening_imageView.getImage() == null
                    || editScreening_current.getSelectionModel().isEmpty()){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            }else{

                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully Update!");
                alert.showAndWait();

                showEditScreening();
                clearEditScreening();

            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void clearEditScreening(){

        editScreening_title.setText("");
        editScreening_imageView.setImage(null);
        //editScreening_current.setSelectionModel(null);

    }

    public void searchEditScreening(){

        FilteredList<movieData> filter = new FilteredList(editScreeningL, e -> true);

        editScreening_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateMovieData ->{

                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String searchKey = newValue.toLowerCase();

                if(predicateMovieData.getTitle().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMovieData.getGenre().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMovieData.getDuration().toLowerCase().contains(searchKey)){
                    return true;
                }else if(predicateMovieData.getCurrent().toLowerCase().contains(searchKey)){
                    return true;
                }


                return false;
            });

        });

        SortedList<movieData> sortData = new SortedList<>(filter);

        sortData.comparatorProperty().bind(editScreening_tableView.comparatorProperty());

        editScreening_tableView.setItems(sortData);

    }

    public void selectEditScreening(){

        movieData movD = editScreening_tableView.getSelectionModel().getSelectedItem();
        int num = editScreening_tableView.getSelectionModel().getFocusedIndex();

        if((num -1) < -1){
            return;
        }

        String uri = "file:" + movD.getImage();
        image = new Image(uri, 142, 178, false, true);
        editScreening_imageView.setImage(image);

        editScreening_title.setText(movD.getTitle());

    }

    private ObservableList<movieData> editScreeningL;
    public void showEditScreening(){

        editScreeningL = editScreeningList();

        editScreening_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        editScreening_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        editScreening_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        editScreening_col_current.setCellValueFactory(new PropertyValueFactory<>("current"));

        editScreening_tableView.setItems(editScreeningL);

    }


    public void searchAddMovies(){

        FilteredList<movieData> filter = new FilteredList<>(listAddMovies, e -> true);

        addMovies_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateMovieData ->{

                if(newValue == null || newValue.isEmpty() ){
                    return true;
                }

                String keySearch = newValue.toLowerCase();

                if(predicateMovieData.getTitle().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMovieData.getGenre().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMovieData.getDuration().toLowerCase().contains(keySearch)){
                    return true;
                }else if(predicateMovieData.getDate().toString().contains(keySearch)){
                    return true;
                }

                return false;

            });

        });

        SortedList<movieData> sortData = new SortedList<>(filter);
        sortData.comparatorProperty().bind(addMovies_tableView.comparatorProperty());

        addMovies_tableView.setItems(sortData);

    }


    public void importImage(){

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image File", "*png", "*jpg"));

        Stage stage = (Stage)addMovies_form.getScene().getWindow();
        File file = open.showOpenDialog(stage);

        if(file != null){

            image = new Image(file.toURI().toString(), 132, 176, false, true);
            addMovies_imageView.setImage(image);
            getData.path = file.getAbsolutePath();

        }

    }


    public void movieId(){

        String sql = "SELECT count(id) AS total FROM movie";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            if(result.next()){
                getData.movieId = result.getInt("total");
            }

        }catch(Exception e){e.printStackTrace();}

    }
    public void insertAddMovies(){

        movieId();

        String sqll = "SELECT * FROM movie WHERE movie_Title = '" + addMovies_movieTitle.getText() + "'";

        connect = database.connectDb();

        Alert alert;

        try{

            statement = connect.createStatement();
            result = statement.executeQuery(sqll);

            if(result.next()){
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText(addMovies_movieTitle.getText() + "was already exist!");
                alert.showAndWait();
            }else{

                if(addMovies_movieTitle.getText().isEmpty()
                        || addMovies_genre.getText().isEmpty()
                        || addMovies_duration.getText().isEmpty()
                        || addMovies_imageView.getImage() == null
                        || addMovies_date.getValue() == null){

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Please fill all blank fields!");
                    alert.showAndWait();
                }else{

                    String sql = "INSERT INTO movie (id,movie_Title,genre,duration,image,date) VALUES (?,?,?,?,?,TO_DATE(?, 'YYYY-MM-DD'))";

                    String uri = getData.path;
                    uri = uri.replace("\\", "\\\\");


                    movieId();


                    Integer mID = Integer.valueOf(String.valueOf(getData.movieId + 1));



                    prepare = connect.prepareStatement(sql);
                    prepare.setInt(1, mID);
                    prepare.setString(2, addMovies_movieTitle.getText());
                    prepare.setString(3, addMovies_genre.getText());
                    prepare.setString(4, addMovies_duration.getText());
                    prepare.setString(5, uri);
                    prepare.setString(6, String.valueOf(addMovies_date.getValue()));


                    prepare.execute();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully add new movie!");
                    alert.showAndWait();

                    clearAddMoviesList();
                    showAddMoviesList();
                }


            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void updateAddMoviesList(){

        String uri = getData.path;
        uri = uri.replace("\\", "\\\\");

        String sql = "UPDATE movie SET movie_Title = '" + addMovies_movieTitle.getText()
                + "', genre = '" + addMovies_genre.getText()
                + "', duration = '" + addMovies_duration.getText()
                + "', image = '" + uri + "', date = '" + addMovies_date.getValue()
                + "' WHERE id = '" + Integer.valueOf(getData.movieId) + "'";

        connect = database.connectDb();

        try{

            statement = connect.createStatement();

            Alert alert;

            if(addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_imageView.getImage() == null
                    || addMovies_date.getValue() == null){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            }else{

                statement.executeUpdate(sql);

                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Successfully update " + addMovies_movieTitle.getText());
                alert.showAndWait();

                showAddMoviesList();
                clearAddMoviesList();


            }

        }catch(Exception e){e.printStackTrace();}


    }

    public void deleteAddMovies(){

        String sql = "DELETE FROM movie WHERE movie_Title = '"
                + addMovies_movieTitle.getText() + "'";

        connect = database.connectDb();

        try{

            statement = connect.createStatement();

            Alert alert;

            if(addMovies_movieTitle.getText().isEmpty()
                    || addMovies_genre.getText().isEmpty()
                    || addMovies_duration.getText().isEmpty()
                    || addMovies_date.getValue() == null
                    || addMovies_imageView.getImage() == null){

                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please select the movie first");
                alert.showAndWait();

            }else{

                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to delete " + addMovies_movieTitle.getText() + "?");

                Optional<ButtonType> option = alert.showAndWait();

                if(ButtonType.OK.equals(option.get())){

                    statement.executeUpdate(sql);

                    showAddMoviesList();
                    clearAddMoviesList();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully delete!");
                    alert.showAndWait();
                }else{
                    return;
                }

            }

        }catch(Exception e){e.printStackTrace();}

    }

    public void clearAddMoviesList(){

        addMovies_movieTitle.setText("");
        addMovies_genre.setText("");
        addMovies_duration.setText("");
        addMovies_imageView.setImage(null);
        addMovies_date.setValue(null);

    }

    public ObservableList<movieData> addMoviesList(){

        ObservableList<movieData> listData = FXCollections.observableArrayList();

        String sql = "SELECT * FROM movie";

        connect = database.connectDb();

        try{

            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            movieData movD;

            while(result.next()){
                movD = new movieData(result.getInt("id"),
                        result.getString("movie_Title"),
                        result.getString("genre"),
                        result.getString("duration"),
                        result.getString("image"),
                        result.getDate("date"),
                        result.getString("current"));
                listData.add(movD);
            }

        }catch(Exception e){e.printStackTrace();}
        return listData;

    }
    private ObservableList<movieData> listAddMovies;
    public void showAddMoviesList(){


        listAddMovies = addMoviesList();

        addMovies_col_movieTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        addMovies_col_genre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        addMovies_col_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        addMovies_col_date.setCellValueFactory(new PropertyValueFactory<>("date"));

        addMovies_tableView.setItems(listAddMovies);

        searchAddMovies();


    }

    public void selectAddMoviesList(){

        movieData movD = addMovies_tableView.getSelectionModel().getSelectedItem();
        int num = addMovies_tableView.getSelectionModel().getSelectedIndex();

        if((num - 1) < -1){
            return;
        }

        getData.path = movD.getImage();

        getData.movieId = movD.getId();

        addMovies_movieTitle.setText(movD.getTitle());
        addMovies_genre.setText(movD.getGenre());
        addMovies_duration.setText(movD.getDuration());

        String getDate = String.valueOf(movD.getDate());


        String uri = "file:" + movD.getImage();

        image = new Image(uri, 132, 176, false, true);
        addMovies_imageView.setImage(image);

        addMovies_date.setValue(movD.getDate().toLocalDate());

    }

    public void logout(){

        signout.getScene().getWindow().hide();

        try{
            Parent root = FXMLLoader.load(getClass().getResource("hello-view.fxml"));

            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((MouseEvent event) ->{

                x = event.getSceneX();
                y = event.getSceneY();

            });

            root.setOnMouseDragged((MouseEvent event) ->{

                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenX() - y);

            });

            stage.initStyle(StageStyle.TRANSPARENT);

            stage.setScene(scene);
            stage.show();
        }catch(Exception e){e.printStackTrace();}

    }

    public void switchForm(ActionEvent event){
        if(event.getSource() == dashboard_btn){

            dashboard_form.setVisible(true);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:#038DAF;");
            addMovies_btn.setStyle("-fx-background-color:transparent;");
            availableMovies_btn.setStyle("-fx-background-color:transparent;");
            editScreening_btn.setStyle("-fx-background-color:transparent;");
            customers_btn.setStyle("-fx-background-color:transparent;");



        }else if(event.getSource() == addMovies_btn){

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(true);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            addMovies_btn.setStyle("-fx-background-color:#038DAF;");
            availableMovies_btn.setStyle("-fx-background-color:transparent;");
            editScreening_btn.setStyle("-fx-background-color:transparent;");
            customers_btn.setStyle("-fx-background-color:transparent;");

        }else if(event.getSource() == availableMovies_btn){

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(true);
            editScreening_form.setVisible(false);
            customers_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            addMovies_btn.setStyle("-fx-background-color:transparent;");
            availableMovies_btn.setStyle("-fx-background-color:#038DAF;");
            editScreening_btn.setStyle("-fx-background-color:transparent;");
            customers_btn.setStyle("-fx-background-color:transparent;");

            showAvailableMovies();

        }else if(event.getSource() == editScreening_btn){

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(true);
            customers_form.setVisible(false);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            addMovies_btn.setStyle("-fx-background-color:transparent;");
            availableMovies_btn.setStyle("-fx-background-color:transparent;");
            editScreening_btn.setStyle("-fx-background-color:#038DAF;");
            customers_btn.setStyle("-fx-background-color:transparent;");

            showEditScreening();

        }else if(event.getSource() == customers_btn){

            dashboard_form.setVisible(false);
            addMovies_form.setVisible(false);
            availableMovies_form.setVisible(false);
            editScreening_form.setVisible(false);
            customers_form.setVisible(true);

            dashboard_btn.setStyle("-fx-background-color:transparent;");
            addMovies_btn.setStyle("-fx-background-color:transparent;");
            availableMovies_btn.setStyle("-fx-background-color:transparent;");
            editScreening_btn.setStyle("-fx-background-color:transparent;");
            customers_btn.setStyle("-fx-background-color:#038DAF;");

        }
    }

    public void displayUsername(){
        username.setText(getData.username);
    }

    public void close(){
        System.exit(0);
    }

    public void minimize(){
        Stage stage = (Stage)topForm.getScene().getWindow();
        stage.setIconified(true);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        displayUsername();

        showAddMoviesList();

        showEditScreening();

        comboBox();

        showAvailableMovies();

        showSpinnerValue();

    }
}
