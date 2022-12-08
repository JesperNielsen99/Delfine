package member;

import java.time.LocalDate;

public class Time {
    private String name;
    private Double time;
    private LocalDate date;
    private SwimDiscipline swimDiscipline;

    //*-----------------------------------------------Constructor----------------------------------------------------*\\
    public Time(String name, Double time, LocalDate date, SwimDiscipline swimDiscipline) {
     this.name = name;
     this.time = time;
     this.date = date;
     this.swimDiscipline = swimDiscipline;
    }

    //*--------------------------------------------------Getter------------------------------------------------------*\\

    public String getName() { return name; }

    public Double getTime() { return time; }

    public LocalDate getDate() { return date; }

    public SwimDiscipline getSwimDisciplin() {
        return swimDiscipline;
    }

}
