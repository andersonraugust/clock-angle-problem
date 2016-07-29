package com.clock.model;

public class Clock {
    
    private int hours;
    private int minutes;
    
    public Clock(int hours, int minutes){
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHour() {
        return hours;
    }
    
    public int getMinute() {
        return minutes;
    }
    
    public int getHourAngle(){
        return (int)(0.5 * ((60 * this.hours) + this.minutes));
    }
    
    public int getMinuteAngle() {
        return minutes * 6;
    }
    
    public int getAngle(){
        int totalAngle =  Math.abs(this.getHourAngle() - this.getMinuteAngle());
        while (totalAngle > 180){
            totalAngle = 360 - totalAngle;
        }
        return Math.abs(totalAngle);
    }

    public void setHour(int hour) {
        this.hours = hour;
    }
    
    public void setMinute(int minute) {
        this.minutes = minute;
    }

}
