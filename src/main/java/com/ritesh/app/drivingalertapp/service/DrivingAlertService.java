package com.ritesh.app.drivingalertapp.service;

import com.ritesh.app.drivingalertapp.model.DrivingAlert;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrivingAlertService {
    List<DrivingAlert> alertList = new ArrayList<DrivingAlert>();

    public void saveOrUpdate(DrivingAlert alert) {
        alertList.add(alert);
    }

    public DrivingAlert getEmployeeById(int alertId) {
        DrivingAlert alert = null;
        if(alertList != null && alertList.size()>0){
            for(DrivingAlert o : alertList)
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
