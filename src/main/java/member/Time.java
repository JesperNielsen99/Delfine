package member;

import java.time.LocalDateTime;

public class Time {
    private String name;
    private Time time;
    private LocalDateTime date;

    public Time(String name, Time time, LocalDateTime date) {
     this.name = name;
     this.time = time;
     this.date = date;
    }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Time getTime() { return time; }

    public void setTime(Time time) { this.time = time; }

    public LocalDateTime getDate() { return date; }

    public void setDate(LocalDateTime date) { this.date = date; }
}
