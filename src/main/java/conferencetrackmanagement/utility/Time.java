package conferencetrackmanagement.utility;

public class Time {
    private int hours;
    private int minutes;
    private boolean isAM;

    public Time(int hours, int minutes, boolean isAM) {
        this.hours = hours;
        this.minutes = minutes;
        this.isAM = isAM;
    }

    public Time addDuration(int min) {
        if (this.minutes + min < 60)
            return new Time(this.hours, this.minutes + min, this.isAM);
        else {
            int additionalHours = (this.minutes + min) / 60;
            int newHours = (this.hours + additionalHours) % 12;
            if (newHours == 0)
                newHours = 12;
            boolean isSame = (additionalHours / 12) % 2 == 0 ? true : false;
            boolean isAfterTwelve = (this.hours > newHours) ? true : false;
            return new Time(newHours, (this.minutes + min) % 60, (isSame == false) ? (!(isAfterTwelve ^ this.isAM)) : ((isAfterTwelve ^ this.isAM)));
        }
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public String toString() {
        String hour0 = "";
        String min0 = "";
        if (hours < 10)
            hour0 = "0";
        if (minutes < 10)
            min0 = "0";
        return hour0 + hours + ":" + min0 + minutes + " " + ((isAM == true) ? "AM" : "PM");
    }
}
