package datahandling;

import member.Member;
import member.MembershipStatus;
import java.util.ArrayList;

public class Subscription {
    private double passive = 500;
    private double junior = 1000;
    private double senior = 1600;
    private double seniorPlus = 0.25;
    private double student = 0.15;

    //*-----------------------------------------------Constructor----------------------------------------------------*\\
    public Subscription() {
    }

    public Subscription(double passive, double junior, double senior, double seniorPlus, double student){
        this.passive = passive;
        this.junior = junior;
        this.senior = senior;
        this.seniorPlus = seniorPlus;
        this.student = student;
    }

    //*--------------------------------------------------Income------------------------------------------------------*\\
    public double calculateMemberSubscription(Member member) {
        double subscription = 0;
        if (!member.getActivity().equals(MembershipStatus.NONE)) {
            if (member.getAge() < 18) {
                if (member.getIsStudent()) {
                    subscription = junior * (1 - student);
                } else {
                    subscription = junior;
                }
            } else if (member.getAge() >= 18) {
                if (member.getIsStudent()) {
                    subscription = senior * (1 - student);
                } else {
                    subscription = senior;
                }
            } else if (member.getAge() >= 60) {
                subscription = senior * (1 - seniorPlus);
            }
        } else {
            if (member.getIsStudent()) {
                subscription = passive * (1 - student);
            } else {
                subscription = passive;
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

    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public double getPassive() {
        return passive;
    }

    public double getJunior() {
        return junior;
    }

    public double getSenior() {
        return senior;
    }

    public double getSeniorPlus() {
        return seniorPlus;
    }

    public double getStudent() {
        return student;
    }

    //*--------------------------------------------------Setter------------------------------------------------------*\\
    public void setPassive(double passive) {
        this.passive = passive;
    }

    public void setJunior(double junior) {
        this.junior = junior;
    }

    public void setSenior(double senior) {
        this.senior = senior;
    }

    public void setSeniorPlus(double seniorPlus) {
        this.seniorPlus = seniorPlus;
    }

    public void setStudent(double student) {
        this.student = student;
    }
}
