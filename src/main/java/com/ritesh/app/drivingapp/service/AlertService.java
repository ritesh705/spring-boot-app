package com.ritesh.app.drivingapp.service;

import com.ritesh.app.drivingapp.model.Alert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {
    List<Alert> alertList = new ArrayList<Alert>();

    public void saveOrUpdate(Alert alert) {
        alertList.add(alert);
    }

    public Alert getEmployeeById(int alertId) {
        Alert alert = null;
        if(alertList != null && alertList.size()>0){
            for(Alert o : alertList)
            {
                if(o.getAlertId() == alertId){
                    alert = o;
                    break;
                }
            }
        }
        return alert;
    }
}
