package gcm.gcm1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;






public class Controller implements Initializable{

    @FXML
    private TextField nameField;
    @FXML
    private TextField lastnameField;
    @FXML
    private TextField ageField;
    @FXML
    private TextField addressField;
    @FXML
    private TextField contactField;
    @FXML
    private TextField updateNameField;
    @FXML
    private TextField updateContactField;
    @FXML
    private TextField updateAddressField;
    @FXML
    private TextField appointmentNameField;
    @FXML
    private TextField appointmentContactField;
    @FXML
    private TextArea IllnessesArea;
    @FXML
    private TextArea allgeriesArea;
    @FXML
    private TextArea previousMedicamentArea;
    @FXML
    private TextArea notesArea;
    @FXML
    private TextArea medicamentArea;
    @FXML
    private TextArea instructionsArea;
    @FXML
    private DatePicker nextAppointmentField;
    @FXML
    private DatePicker appointmentNextField;
    @FXML
    private Button addPatientButton;
    @FXML
    private TableView<PatientData> patientTableView;
    @FXML
    private TableColumn<PatientData, String> patientName_col; 
    @FXML
    private TableColumn<PatientData, String> patientAddress_col;
    @FXML 
    private TableColumn<PatientData, Integer> patientAge_col;
    @FXML 
    private TableColumn<PatientData, Integer> patientId_col;
    @FXML
    private TableColumn<PatientData, String> patientContact_col;
    @FXML
    private TableColumn<PatientData, String> patientAppointment_col;
    @FXML
    private TableColumn<PatientData, String> patientgender_col;
    @FXML
    private TableView<AppointmentData> appointmentTableView;
    @FXML 
    private TableColumn<AppointmentData, Integer> appointmentPatientId_col;
    @FXML
    private TableColumn<PatientData, String> appointmentPatientName_col;
    @FXML
    private TableColumn<PatientData, String> appointmentPatientContact_col;
    @FXML
    private TableColumn<AppointmentData, String> firstAppointmentDate_col;
    @FXML
    private TableColumn<AppointmentData, String> nextAppointmentDate_col;
    @FXML 
    private TableView<HistoryData> historyTableView;
    @FXML 
    private TableColumn<HistoryData, Integer> historyPatientId_col;
    @FXML
    private TableColumn<HistoryData, String> historyPatientName_col;
    @FXML
    private TableColumn<HistoryData, String> historyPatientContact_col;
    @FXML
    private TableColumn<HistoryData, Date> hisotryPatientAppointment_col;
    @FXML
    private TableColumn<HistoryData, Date> historyPatientInsertionDate_col;
    @FXML
    private ComboBox<String> patientGender;
    @FXML
    private Button deletePatientButton;
    @FXML
    private Button updatePatientButton;
    @FXML
    private Button addAppointmentButton;
    @FXML
    private Button updateAppointmentButton;
    @FXML
    private TextField searchTextField;
    @FXML
    private TextField appointmentSearchField;
    @FXML
    private TextField historySearchField;
    @FXML
    private AnchorPane mainform;
    @FXML
    private AnchorPane dashboard_form;
    @FXML
    private AnchorPane addPatient_form;
    @FXML
    private AnchorPane appointment_form;
    @FXML
    private AnchorPane history_form;
    @FXML
    private Button addPatientButton_form;
    @FXML
    private Button dashboardButton_form;
    @FXML
    private Button appointmentButton_form;
    @FXML
    private Button historyButton_form;
    

    
    private String nameString;
    private String addressString;
    private String contactString;
    private String ageString;
    private String illnessesString;
    private String allergiesString;
    private String takenMedicineString;
    private String notesString;
    private String medicamentString;
    private String instructionsString;
    private String appointmentDate;
    private int id;

    
    
    private ObservableList<String> genderList = FXCollections.observableArrayList("Male", "Female");

    private ObservableList<PatientData> patientDataList = FXCollections.observableArrayList();
    private ObservableList<AppointmentData> appointmentDataList = FXCollections.observableArrayList();
    private ObservableList<HistoryData> historyDataList = FXCollections.observableArrayList();

    // Database tools
    private Connection connect;
    private PreparedStatement statement;
    private ResultSet result;
    // private Date appointmentDate;
    // private LocalDate date;

    private final AlertMessage alert = new AlertMessage();


    @FXML
    public void addPatient() throws IOException {

        connect = Database.connectDb();
        LocalDate selectedDate = nextAppointmentField.getValue();
        java.sql.Date date = java.sql.Date.valueOf(selectedDate);

        
        try {
            String sql = "INSERT INTO Patient (name, address, contact, appointment, age) VALUES (?, ?, ?, ?, ?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, nameField.getText());
            statement.setString(2, addressField.getText());
            statement.setString(3, contactField.getText());
            statement.setDate(4, date);
            statement.setInt(5, Integer.parseInt(ageField.getText()));
            statement.executeUpdate();
            
            alert.successMessage("Patient added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        clearFields();
        
        
        
    }

    @FXML
    public void addAppointment() throws IOException {
        LocalDate selectedDate = appointmentNextField.getValue();
        java.sql.Date date = java.sql.Date.valueOf(selectedDate);
        try {
            connect = Database.connectDb();
            String sql = "INSERT INTO Appointment (name, contact, nextAppointment) VALUES (?, ?, ?)";
            statement = connect.prepareStatement(sql);
            statement.setString(1, appointmentNameField.getText());
            statement.setString(2, appointmentContactField.getText());
            statement.setDate(3, date);
            statement.executeUpdate();
            alert.successMessage("Appointment added successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        showAppointmentList();
    }

    @FXML
    public void updateAppointment() throws IOException {

    }

    @FXML
    public void deletePatient() throws IOException {
        
        
        if (patientTableView.getSelectionModel().isEmpty()) {
            alert.errorMessage("Please select a patient to delete");
        } else {
            PatientData patientSelected = patientTableView.getSelectionModel().getSelectedItem();
            if (patientSelected != null) {
                
    
                try {
                    connect = Database.connectDb();
                    statement = connect.prepareStatement("DELETE FROM Patient WHERE id = ?");
                    statement.setInt(1, patientSelected.getPatientID());
                    statement.executeUpdate();
                    patientTableView.getItems().remove(patientSelected);
                    alert.successMessage("Patient deleted successfully");
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (statement != null) {
                            statement.close();
                        }
                        if (connect != null) {
                            connect.close();
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
    
            updateNameField.setText("");
            updateContactField.setText("");
            updateAddressField.setText("");

        }
        
        searchTextField.setText("");
        showPatientList();
    }


    @FXML
    public void updatePatient() throws IOException {
        
        connect = Database.connectDb();

        nameString = updateNameField.getText();
        addressString = updateAddressField.getText();
        Integer index = patientTableView.getSelectionModel().getSelectedIndex();

        if (index <= -1) {
            return;
        }

        id = patientId_col.getCellData(index);



        try {
            String sql = "UPDATE Patient SET name = ?,address = ? WHERE id = '" + id + "'";
            statement = connect.prepareStatement(sql);
            statement.setString(1, nameString);
            statement.setString(2, addressString);
            statement.executeUpdate();
            alert.successMessage("Patient updated successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        clearFields();


        
        showPatientList();
    }



    @FXML 
    public void clearFields() throws IOException {
        nameField.setText("");
        addressField.setText("");
    }
    
    @FXML
    public void getItems(MouseEvent event) {

        PatientData selectedPatient = patientTableView.getSelectionModel().getSelectedItem();

        updateNameField.setText(selectedPatient.getPatientName());
        updateAddressField.setText(selectedPatient.getPatientAddress());
        updateContactField.setText(selectedPatient.getPatientContact());
        nextAppointmentField.setValue(selectedPatient.getPatientAppointment().toLocalDate());
        
    }

    public ObservableList<PatientData> patientData() {
        ObservableList<PatientData> listData = FXCollections.observableArrayList();
    
        try {
            connect = Database.connectDb();
            PatientData patientD;
            statement = connect.prepareStatement("SELECT * FROM Patient");
            result = statement.executeQuery();
    
            while (result.next()) {
                patientD = new PatientData(result.getString("name"), result.getString("address"), 
                result.getInt("id"), result.getDate("appointment"), result.getString("contact"),
                result.getInt("age"));
                listData.add(patientD);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return listData;
    }
    


    @FXML
    public void changeForm(Event event) throws IOException {
        if (event.getSource() == dashboardButton_form) {
            dashboard_form.setVisible(true);
            addPatient_form.setVisible(false);
            appointment_form.setVisible(false);
            history_form.setVisible(false);
            showPatientList();

        } else if (event.getSource() == addPatientButton_form) {
            addPatient_form.setVisible(true);
            dashboard_form.setVisible(false);
            appointment_form.setVisible(false);
            history_form.setVisible(false);
        } else if (event.getSource() == appointmentButton_form) {
            appointment_form.setVisible(true);
            dashboard_form.setVisible(false);
            addPatient_form.setVisible(false);
            history_form.setVisible(false);
            showAppointmentList();
        } else if (event.getSource() == historyButton_form) {
            history_form.setVisible(true);
            appointment_form.setVisible(false);
            dashboard_form.setVisible(false);
            addPatient_form.setVisible(false);
            showHistoryList();
        }
    }

    private ObservableList<PatientData> filteredData = FXCollections.observableArrayList();

    public void searchPatient() {
        searchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setAll(patientDataList.filtered(patient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // String lowercaseString = newValue.toLowerCase();
                if (patient.getPatientName().toLowerCase().startsWith(newValue.toLowerCase())){
                    return true;
                } else if (patient.getPatientID().toString().startsWith(newValue.toLowerCase())){
                    return true;
                } else if (patient.getPatientContact().startsWith(newValue.toLowerCase())) {
                    return true;   
                }else {
                    return false;
                }
                
            }));
        });
    
        ObservableList<PatientData> observableSortedData = FXCollections.observableArrayList(filteredData);

        observableSortedData.addListener((ListChangeListener<PatientData>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    patientTableView.setItems(FXCollections.observableArrayList(filteredData));
                }
            }
        });

        patientTableView.setItems(observableSortedData);
    }

    private ObservableList<AppointmentData> appointmentFilteredData = FXCollections.observableArrayList();

    public void searchappointment() {

        appointmentSearchField.textProperty().addListener((observable, oldValue, newValue) -> {
            appointmentFilteredData.setAll(appointmentDataList.filtered(patient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                // String lowercaseString = newValue.toLowerCase();
                if (patient.getAppointmentPatientName().toLowerCase().startsWith(newValue.toLowerCase())){
                    return true;
                } else if (patient.getAppointmentID().toString().startsWith(newValue.toLowerCase())){
                    return true;
                } else if (patient.getAppointmentPatientContact().startsWith(newValue.toLowerCase())) {
                    return true;   
                }else {
                    return false;
                }
                
            }));
        });
    
        ObservableList<AppointmentData> observableSortedData = FXCollections.observableArrayList(appointmentFilteredData);

        observableSortedData.addListener((ListChangeListener<AppointmentData>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    appointmentTableView.setItems(FXCollections.observableArrayList(appointmentFilteredData));
                }
            }
        });

        appointmentTableView.setItems(observableSortedData);

    }

    public void searchHistory() {

    }
    
    public void showPatientList() {

        patientDataList = patientData();

        patientName_col.setCellValueFactory(new PropertyValueFactory<>("patientName"));
        patientAddress_col.setCellValueFactory(new PropertyValueFactory<>("patientAddress"));
        patientAppointment_col.setCellValueFactory(new PropertyValueFactory<>("patientAppointment"));;
        patientContact_col.setCellValueFactory(new PropertyValueFactory<>("patientContact"));
        patientId_col.setCellValueFactory(new PropertyValueFactory<>("patientID"));
        patientAge_col.setCellValueFactory(new PropertyValueFactory<>("patientAge"));

        
        patientTableView.setItems(patientDataList);
    }

    public ObservableList<AppointmentData> appointmentData() {
        ObservableList<AppointmentData> listData = FXCollections.observableArrayList();
    
        try {
            connect = Database.connectDb();
            AppointmentData patientD;
            statement = connect.prepareStatement("SELECT * FROM Appointment");
            result = statement.executeQuery();
    
            while (result.next()) {
                patientD = new AppointmentData(result.getInt("id"), result.getDate("prevAppointment"),
                result.getDate("nextAppointment"), result.getString("name"), result.getString("contact"));
                listData.add(patientD);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        return listData;
    }

    public void showAppointmentList() {
        appointmentDataList = appointmentData();

        appointmentPatientName_col.setCellValueFactory(new PropertyValueFactory<>("appointmentPatientName"));
        appointmentPatientContact_col.setCellValueFactory(new PropertyValueFactory<>("appointmentPatientContact"));
        appointmentPatientId_col.setCellValueFactory(new PropertyValueFactory<>("appointmentID"));
        nextAppointmentDate_col.setCellValueFactory(new PropertyValueFactory<>("nextAppointmentDate"));
        firstAppointmentDate_col.setCellValueFactory(new PropertyValueFactory<>("firstAppointmentDate"));

        appointmentTableView.setItems(appointmentDataList);
    }

    public ObservableList<HistoryData> getHistoryData() {
        ObservableList<HistoryData> historyDataL = FXCollections.observableArrayList();

        try {
            connect = Database.connectDb();
            HistoryData historyData;
            statement = connect.prepareStatement("SELECT * FROM History");
            result = statement.executeQuery();

            while (result.next()) {
                historyData = new HistoryData(result.getInt("patientId"), result.getString("name"), result.getString("contact"),
                result.getDate("appointmentDate"), result.getDate("insertionDate"));
                historyDataL.add(historyData);
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (result != null) {
                    result.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return historyDataL;
    }

    public void showHistoryList() {
        historyDataList = getHistoryData();

        historyPatientId_col.setCellValueFactory(new PropertyValueFactory<>("historyPatientId"));
        historyPatientName_col.setCellValueFactory(new PropertyValueFactory<>("historyPatientName"));
        historyPatientContact_col.setCellValueFactory(new PropertyValueFactory<>("historyPatientContact"));
        historyPatientInsertionDate_col.setCellValueFactory(new PropertyValueFactory<>("historyInsertionDate"));
        hisotryPatientAppointment_col.setCellValueFactory(new PropertyValueFactory<>("historyPatientAppointment"));

        historyTableView.setItems(historyDataList);
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {


        patientGender.setItems(genderList);
                


    }

}
