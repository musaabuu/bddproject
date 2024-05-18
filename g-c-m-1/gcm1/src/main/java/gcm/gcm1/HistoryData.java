package gcm.gcm1;

import java.sql.Date;

public class HistoryData {
    
    private Integer historyPatientId;
    private String historyPatientName;
    private String historyPatientContact;
    private Date historyPatientAppointment;
    private Date historyInsertionDate;

    
    public HistoryData(Integer historyPatientId, String historyPatientName, String historyPatientContact,
            Date historyPatientAppointment, Date historyInsertionDate) {
        this.historyPatientId = historyPatientId;
        this.historyPatientName = historyPatientName;
        this.historyPatientContact = historyPatientContact;
        this.historyPatientAppointment = historyPatientAppointment;
        this.historyInsertionDate = historyInsertionDate;
    }


    public Integer getHistoryPatientId() {
        return historyPatientId;
    }


    public String getHistoryPatientName() {
        return historyPatientName;
    }


    public String getHistoryPatientContact() {
        return historyPatientContact;
    }


    public Date getHistoryPatientAppointment() {
        return historyPatientAppointment;
    }


    public Date getHistoryInsertionDate() {
        return historyInsertionDate;
    }

    

}
