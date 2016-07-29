package com.clock.controller;

import com.clock.model.Clock;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SessionScoped
@RestController
@ManagedBean(name = "ClockController")
public class ClockController implements Serializable {

    private int hours;
    private int minutes;
    private int angle;
    int hoursLog[];
    int totalMinutes;

    public ClockController() {
        hoursLog = new int[721];
        totalMinutes = 0;
        hours = 0;
        minutes = 0;
    }

    @RequestMapping("/rest/clock")
    public Object clock(@RequestParam(value = "hours") final int hours, @RequestParam(value = "minutes") final int minutes) {
        totalMinutes = (hours * 60) + minutes;

        while (totalMinutes > 720) {
            totalMinutes = totalMinutes - 720;
        }
        if (hoursLog[totalMinutes] == 0) {
            hoursLog[totalMinutes] = new Clock(hours, minutes).getAngle();
        }

        return new Object() {
            int angle = hoursLog[totalMinutes];
        };
    }

    /**
     * Utilizado pelo form do jsf para realizar o retorno.
     *
     * @throws java.io.IOException
     */
    public void defineAngle() throws IOException {
        if (getHours() > 12 || getMinutes() > 59){
            return;
        }
        totalMinutes = (this.getHours() * 60) + this.getMinutes();

        //Limitando a 24 horas.
        while (totalMinutes > 720) {
            totalMinutes = totalMinutes - 720;
        }
        if (hoursLog[totalMinutes] == 0) {
            hoursLog[totalMinutes] = new Clock(getHours(), getMinutes()).getAngle();
        }

        this.setAngle(hoursLog[totalMinutes]);
        reload();
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public void reload() throws IOException {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
    }

}
