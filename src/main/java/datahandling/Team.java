package datahandling;

import member.Member;
import java.util.ArrayList;

public class Team {
   private String trainerName;
   private final ArrayList<Member> teamMembers = new ArrayList<>();

    //*-----------------------------------------------Constructor----------------------------------------------------*\\
    public Team(String trainerName) {
        this.trainerName = trainerName;
    }

    //*---------------------------------------------------Add--------------------------------------------------------*\\
    public void addMemberToTeam(Member member){
        teamMembers.add(member);
    }

    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public ArrayList<Member> getTeamMembers() {
        return teamMembers;
    }

    public String getTrainerName() {
        return trainerName;
    }

    //*---------------------------------------------------Set--------------------------------------------------------*\\
    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

}

