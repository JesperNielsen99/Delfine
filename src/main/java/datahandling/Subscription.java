package datahandling;

import member.Member;

import java.util.ArrayList;

public class Subscription {
    private double passiv = 500;
    private double junior = 1000;
    private double senior = 1600;
    private double seniorPlus = 0.25;
    private double student = 0.15;

    public Subscription() {
    }

    public Subscription(double passiv, double junior, double senior, double seniorPlus, double student){
        this.passiv = passiv;
        this.junior = junior;
        this.senior = senior;
        this.seniorPlus = seniorPlus;
        this.student = student;
    }


    public double calculateMemberSubscription(Member member) {
        double subscription = 0;
        if (member.getIsActive()) {
            if (member.getAge() > 18) {
                if (member.getIsStudent()) {
                    subscription = junior * (1 - student);
                } else {
                    subscription = junior;
                }
            } else if (member.getAge() <= 18) {
                if (member.getIsStudent()) {
                    subscription = senior * (1 - student);
                } else {
                    subscription = senior;
                }
            } else if (member.getAge() <= 60) {
                subscription = senior * (1 - seniorPlus);
            }
        } else {
            if (member.getIsStudent()) {
                subscription = passiv * (1 - student);
            } else {
                subscription = passiv;
            }
        }
        return subscription;
    }

    public double expectedTotalIncome(ArrayList<Member> members) {
        double expectedTotalIncome = 0;
        for (Member member : members) {
            expectedTotalIncome += calculateMemberSubscription(member);
        }
        return expectedTotalIncome;
    }

    public double getPassiv() {
        return passiv;
    }

    public void setPassiv(double passiv) {
        this.passiv = passiv;
    }

    public double getJunior() {
        return junior;
    }

    public void setJunior(double junior) {
        this.junior = junior;
    }

    public double getSenior() {
        return senior;
    }

    public void setSenior(double senior) {
        this.senior = senior;
    }

    public double getSeniorPlus() {
        return seniorPlus;
    }

    public void setSeniorPlus(double seniorPlus) {
        this.seniorPlus = seniorPlus;
    }

    public double getStudent() {
        return student;
    }

    public void setStudent(double student) {
        this.student = student;
    }
}
