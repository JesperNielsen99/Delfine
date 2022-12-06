package datahandling;

import java.time.LocalDate;
import java.util.ArrayList;
import member.*;

public class Club {
    private final ArrayList<Member> currentMembers = new ArrayList<>();
    private final ArrayList<Member> formerMembers = new ArrayList<>();
    private final ArrayList<Member> searchResult = new ArrayList<>();

    private final Team teamJunior = new Team("Junior træner");
    private final Team teamSenior = new Team("Senior træner");
    //*--------------------------------------------------Create------------------------------------------------------*\\
    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex,
                             Boolean isStudent, MembershipStatus activity, boolean hasPaid, boolean currentMember) {
        Member member = new Member(name, address, number, mail, birthdate, sex, isStudent, hasPaid, activity, currentMember);
        currentMembers.add(member);
        addMemberToTeam(member);
    }

    public void createMember(String name, String address, String number, String mail, LocalDate birthdate, boolean sex,
                             Boolean isStudent, MembershipStatus isCompetitive, boolean hasPaid, boolean crawl,
                             boolean BackCrawl, boolean breastStroke, boolean butterfly, boolean currentMember, ArrayList<Time> times) {
        Member member = new Member(name, address, number, mail, birthdate, sex, isStudent, hasPaid, isCompetitive, crawl, BackCrawl, breastStroke, butterfly, currentMember, times);
        currentMembers.add(member);
        addMemberToTeam(member);
    }

    //*--------------------------------------------------Search------------------------------------------------------*\\

    public void searchMember(String searchMemberName) {
        searchResult.clear();
        for (Member member : currentMembers) {
            if (member.getName().toLowerCase().contains(searchMemberName.toLowerCase())) {
                searchResult.add(member);
            }
        }
    }

    //*---------------------------------------------------Add--------------------------------------------------------*\\

    public ArrayList<Member> membersInDebt() {
        ArrayList<Member> membersInDebt = new ArrayList<>();
        for (Member member : currentMembers) {
            if (!member.getHasPaid()) {
                membersInDebt.add(member);
            }
        }
        return membersInDebt;
    }

    public void addMemberToTeam(Member member) {
        if (member.getActivity().equals(MembershipStatus.COMPETITIVE)) {
            if (member.getAge() < 18) {
                teamJunior.addMemberToTeam(member);
            } else {
                teamSenior.addMemberToTeam(member);
            }
        }
    }

    private void addMembersToTeam(ArrayList<Member> members) {
        for (Member member : members) {
            addMemberToTeam(member);
        }
    }

    public ArrayList<ArrayList<Member>> addTop5InTeam(Team team) {
        ArrayList<ArrayList<Member>> bestTimes = new ArrayList<>();

        ArrayList<Member> bestCrawlTimes = new ArrayList<>();
        ArrayList<Member> bestBackCrawlTimes = new ArrayList<>();
        ArrayList<Member> bestBreastStrokeTimes = new ArrayList<>();
        ArrayList<Member> bestButterflyTimes = new ArrayList<>();

        bestTimes.add(bestCrawlTimes);
        bestTimes.add(bestBackCrawlTimes);
        bestTimes.add(bestBreastStrokeTimes);
        bestTimes.add(bestButterflyTimes);

        for (Member member : team.getTeamMembers()) {
            for (SwimDisciplin swimDisciplin : SwimDisciplin.values()) {
                double memberBestTime = member.getBestTime(swimDisciplin);
                switch (swimDisciplin) {
                    case CRAWL -> {
                        if (bestCrawlTimes.size() == 5) {
                            for (Member bestMember : bestCrawlTimes) {
                                if (memberBestTime < bestMember.getBestTime(swimDisciplin) && memberBestTime != 0) {
                                    bestCrawlTimes.remove(bestMember);
                                    bestCrawlTimes.add(member);
                                    break;
                                }
                            }
                        } else {
                            if (memberBestTime != 0) {
                                bestCrawlTimes.add(member);
                            }
                        }
                    }
                    case BACKCRAWL -> {
                        if (bestBackCrawlTimes.size() == 5) {
                            for (Member bestMember : bestBackCrawlTimes) {
                                if (memberBestTime < bestMember.getBestTime(swimDisciplin) && memberBestTime != 0) {
                                    bestBackCrawlTimes.remove(bestMember);
                                    bestBackCrawlTimes.add(member);
                                    break;
                                }
                            }
                        } else {
                            if (memberBestTime != 0) {
                                bestBackCrawlTimes.add(member);
                            }
                        }
                    }
                    case BREASTSTROKE -> {
                        if (bestBreastStrokeTimes.size() == 5) {
                            for (Member bestMember : bestBreastStrokeTimes) {
                                if (memberBestTime < bestMember.getBestTime(swimDisciplin) && memberBestTime != 0) {
                                    bestBreastStrokeTimes.remove(bestMember);
                                    bestBreastStrokeTimes.add(member);
                                    break;
                                }
                            }
                        } else {
                            if (memberBestTime != 0) {
                                bestBreastStrokeTimes.add(member);
                            }
                        }
                    }
                    case BUTTERFLY -> {
                        if (bestButterflyTimes.size() == 5) {
                            for (Member bestMember : bestButterflyTimes) {
                                if (memberBestTime < bestMember.getBestTime(swimDisciplin) && memberBestTime != 0) {
                                    bestButterflyTimes.remove(bestMember);
                                    bestButterflyTimes.add(member);
                                    break;
                                }
                            }
                        } else {
                            if (memberBestTime != 0) {
                                bestButterflyTimes.add(member);
                            }
                        }
                    }
                }
            }
        }
        return bestTimes;
    }

    //*-------------------------------------------------Delete-------------------------------------------------------*\\
    public String deleteMember(Member currentMember) {
        String currentMemberName = currentMember.getName();
        currentMember.setCurrentMember(false);
        formerMembers.add(currentMember);
        currentMembers.remove(currentMember);
        return String.format("%s er blevet slettet fra medlemslisten.", currentMemberName);
    }

    //*--------------------------------------------------Getter------------------------------------------------------*\\
    public int getSizeOfMembers() {
        return currentMembers.size();
    }

    public ArrayList<Member> getCurrentMembers() {
        return currentMembers;
    }


    public ArrayList<Member> getSearchResult() {
        return searchResult;
    }

    public ArrayList<Member> getTeamJuniorMembers() {
        return teamJunior.getTeamMembers();
    }

    public ArrayList<Member> getTeamSeniorMembers() {
        return teamSenior.getTeamMembers();
    }

    public String[] getTrainers() {
        return new String[]{teamJunior.getTrainerName(), teamSenior.getTrainerName()};
    }

    public Team getTeamJunior() {
        return teamJunior;
    }

    public Team getTeamSenior() {
        return teamSenior;
    }

    public ArrayList<ArrayList<Member>> getTop5TimesInTeam(Team team) {
        return addTop5InTeam(team);
    }


    //*--------------------------------------------------Setter------------------------------------------------------*\\
    public void setTrainers(String[] trainers) {
        if (trainers != null) {
            teamJunior.setTrainerName(trainers[0]);
            teamSenior.setTrainerName(trainers[1]);
        }
    }

    public void setMembers(ArrayList<Member> members) {

        for (Member member : members) {
            if (member.getCurrentMember()) {
                currentMembers.add(member);
            } else {
                formerMembers.add(member);
            }
        }
        addMembersToTeam(currentMembers);
    }
    //*--------------------------------------------------Misc--------------------------------------------------------*\\



}
