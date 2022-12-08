package datahandling;

import member.Member;
import member.MembershipStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

public class SubscriptionTest {
     private Subscription subscription;
     private ArrayList<Member> members;

     //*--------------------------------------------------Setup------------------------------------------------------*\\
     @BeforeEach
     public void setup(){
          subscription = new Subscription();
          members = new ArrayList<>();
          Member junior = new Member("junior","junior","111","junior@junior.com",LocalDate.of(2015,05,05),true,false,true, MembershipStatus.HOBBY,true);
          Member senior = new Member("Senior", "senior","222", "senior@senior.com",LocalDate.of(2000,05,05),true,false, true, MembershipStatus.COMPETITIVE,true);
          Member seniorPlus = new Member("SeniorPlus", "seniorPlus","333", "seniorPlus@seniorPlus.com",LocalDate.of(1935,02,02),true,false, true, MembershipStatus.HOBBY,true);
          Member passive = new Member("passiv", "passiv", "444", "passive@passiv.dk", LocalDate.of(2005, 03, 03), true, false, true, member.MembershipStatus.NONE, true);
          Member StudentJunior = new Member("StudentJunior", "StudentJunior", "555", "student@student.dk", LocalDate.of(2015,05,05), true, true, true, MembershipStatus.HOBBY, true);
          Member StudentSenior = new Member("StudentSenior", "StudentSenior", "555", "student@student.dk", LocalDate.of(2000,05,05), true, true, true, MembershipStatus.HOBBY, true);
          members.add(junior);
          members.add(senior);
          members.add(seniorPlus);
          members.add(passive);
          members.add(StudentJunior);
          members.add(StudentSenior);
     }

     //*---------------------------------------------------Test-------------------------------------------------------*\\
     @Test
     void calculateMemberSubscription(){
         //Arrange
          double juniorExpected = 1000;
          double seniorExpected = 1600;
          double seniorPlusExpected = seniorExpected * (1 - 0.25);
          double passiveExpected = 500;
          double studentJuniorExpected = juniorExpected * (1 - 0.15);
          double studentSeniorExpected = seniorExpected * (1 - 0.15);

         //Act
          double juniorActual = subscription.calculateMemberSubscription(members.get(0));
          double seniorActual = subscription.calculateMemberSubscription(members.get(1));
          double seniorPlusActual = subscription.calculateMemberSubscription(members.get(2));
          double passiveActual = subscription.calculateMemberSubscription(members.get(3));
          double studentJuniorActual = subscription.calculateMemberSubscription(members.get(4));
          double studentSeniorActual = subscription.calculateMemberSubscription(members.get(5));

         //Assert
          assertEquals(juniorExpected, juniorActual);
          assertEquals(seniorExpected, seniorActual);
          assertEquals(seniorPlusExpected, seniorPlusActual);
          assertEquals(passiveExpected, passiveActual);
          assertEquals(studentJuniorExpected, studentJuniorActual);
          assertEquals(studentSeniorExpected, studentSeniorActual);
     }

     @Test
     void expectedTotalIncome(){

          //Arrange
          double juniorExpected = 1000;
          double seniorExpected = 1600;
          double seniorPlusExpected = seniorExpected * (1 - 0.25);
          double passiveExpected = 500;
          double studentJuniorExpected = juniorExpected * (1 - 0.15);
          double studentSeniorExpected = seniorExpected * (1 - 0.15);

          double expectedTotalIncome = juniorExpected + seniorExpected + seniorPlusExpected + passiveExpected + studentJuniorExpected + studentSeniorExpected;

          //Act
          double actualTotalIncome = subscription.expectedTotalIncome(members);

          //Assert
          assertEquals(expectedTotalIncome, actualTotalIncome);
     }
}
