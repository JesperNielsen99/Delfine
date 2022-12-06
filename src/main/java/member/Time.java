package member;

import java.time.LocalDate;

public class Time {
    private String name;
    private Double time;
    private LocalDate date;
    private SwimDisciplin swimDisciplin;

    public Time(String name, Double time, LocalDate date, SwimDisciplin swimDisciplin) {
     this.name = name;
     this.time = time;
     this.date = date;
     this.swimDisciplin = swimDisciplin;
    }


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Double getTime() { return time; }

    public void setTime(Double time) { this.time = time; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public SwimDisciplin getSwimDisciplin() {
        return swimDisciplin;
    }
}
