package gcm.gcm1;

import java.sql.Date;


public class AppointmentData {


    private Integer appointmentID;
    private Date firstAppointmentDate;
    private Date nextAppointmentDate;
    private String appointmentPatientName;
    private String appointmentPatientContact;

    
    public AppointmentData(Integer appointmentID, Date firstAppointmentDate,
            Date nextAppointmentDate, String appointmentPatientName, String appointmentPatientContact) {
        this.appointmentID = appointmentID;
        this.firstAppointmentDate = firstAppointmentDate;
        this.nextAppointmentDate = nextAppointmentDate;
        this.appointmentPatientName = appointmentPatientName;
        this.appointmentPatientContact = appointmentPatientContact;
    }

    public Integer getAppointmentID() {
        return appointmentID;
    }


    public Date getFirstAppointmentDate() {
        return firstAppointmentDate;
    }

    public Date getNextAppointmentDate() {
        return nextAppointmentDate;
    }

    public String getAppointmentPatientName() {
        return appointmentPatientName;
    }

    public String getAppointmentPatientContact() {
        return appointmentPatientContact;
    }

    

}
