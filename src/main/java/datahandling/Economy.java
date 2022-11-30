package datahandling;

import member.Member;

import java.util.ArrayList;

public class Economy {
    private double passiv = 500;
    private double under18 = 1000;
    private double over17 = 1600;
    private double over59 = 0.25;
    private double student = 0.15;

    public Economy() {
    }

    public double calculateMemberSubscription(Member member) {
        double subscription = 0;
        if (member.getIsActive()) {
            if (member.getAge() > 18) {
                if (member.getIsStudent()) {
                    subscription = under18 * (1 - student);
                } else {
                    subscription = under18;
                }
            } else if (member.getAge() <= 18) {
                if (member.getIsStudent()) {
                    subscription = over17 * (1 - student);
                } else {
                    subscription = over17;
                }
            } else if (member.getAge() <= 60) {
                subscription = over17 * (1 - over59);
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
}
