package gcm.gcm1;

import java.sql.Date;

public class PatientData {
    
    private String patientName;
    private String patientAddress;
    private Integer patientID;
    private Integer patientAge;
    private Date patientAppointment;
    private String patientContact;


    public PatientData(String patientName, String patientAddress, Integer patientID, Date patientAppointment, 
        String patientContact, Integer patientAge) {
        this.patientName = patientName;
        this.patientAddress = patientAddress;
        this.patientID = patientID;
        this.patientAppointment = patientAppointment;
        this.patientContact = patientContact;
        this.patientAge = patientAge;
    }

    public String getPatientAddress() {
        return patientAddress;
    }

    public String getPatientName() {
        return patientName;
    }

    public Integer getPatientID() {
        return patientID;
    }

    public Date getPatientAppointment() {
        return patientAppointment;
    }

    public String getPatientContact() {
        return patientContact;
    }

    public Integer getPatientAge() {
        return patientAge;
    }

    

}
