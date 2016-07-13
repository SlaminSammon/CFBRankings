/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.rankings;
import java.util.*;
import java.io.*;
/**
 *
 * @author Chuck
 */
public class FootballRankings {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException{
        ArrayList<Team> teams = readTeamNames();
        teams = readStats(teams);
        for(int i = 0; i < teams.size(); ++i){
            teams.get(i).calculateScore();
        }
        teams = sortTeams(teams);
        int j =1, ACC=1, AAC=1, B12=1, BIG=1, CUSA=1, MAC=1, MWC=1,PAC=1,SEC=1,SB=1;
        PrintWriter mainWriter = new PrintWriter("2015rankings.txt");
        PrintWriter ACCWriter = new PrintWriter("2015ACCrankings.txt");
        PrintWriter AACWriter = new PrintWriter("2015AACrankings.txt");
        PrintWriter B12Writer = new PrintWriter("2015B12rankings.txt");
        PrintWriter BIGWriter = new PrintWriter("2015BIGrankings.txt");
        PrintWriter CUSAWriter = new PrintWriter("2015CUSArankings.txt");
        PrintWriter MACWriter = new PrintWriter("2015MACrankings.txt");
        PrintWriter MWCWriter = new PrintWriter("2015MWCrankings.txt");
        PrintWriter PACWriter = new PrintWriter("2015PACrankings.txt");
        PrintWriter SECWriter = new PrintWriter("2015SECrankings.txt");
        PrintWriter SBWriter = new PrintWriter("2015SBrankings.txt");
        for(int i=teams.size()-1; i >= 0;--i){
            mainWriter.println(j + "\t" + teams.get(i).schoolName + "     " +teams.get(i).getScore());
            ++j;
            if(teams.get(i).conference.equals("ACC")){
                ACCWriter.println(ACC + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++ACC;
            }
            else if(teams.get(i).conference.equals("AAC")){
                AACWriter.println(AAC + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++AAC;
            }
            else if(teams.get(i).conference.equals("BIG12")){
                B12Writer.println(B12 + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++B12;
            }
            else if(teams.get(i).conference.equals("BIG10")){
                BIGWriter.println(BIG + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++BIG;
            }
            else if(teams.get(i).conference.equals("CUSA")){
                CUSAWriter.println(CUSA + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++CUSA;
            }
            else if(teams.get(i).conference.equals("MAC")){
                MACWriter.println(MAC + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++MAC;
            }
            else if(teams.get(i).conference.equals("MWC")){
                MWCWriter.println(MWC + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++MWC;
            }
            else if(teams.get(i).conference.equals("PAC12")){
                PACWriter.println(PAC + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++PAC;
            }
            else if(teams.get(i).conference.equals("SEC")){
                SECWriter.println(SEC + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++SEC;
            }
            else if(teams.get(i).conference.equals("SUN_BELT")){
                SBWriter.println(SB + "\t" + teams.get(i).schoolName + "\t\t" +teams.get(i).getScore());
                ++SB;
            }
            else{}
        }
        mainWriter.close();
        ACCWriter.close();
        AACWriter.close();
        B12Writer.close();
        BIGWriter.close();
        CUSAWriter.close();
        MACWriter.close();
        MWCWriter.close();
        PACWriter.close();
        SECWriter.close();
        SBWriter.close();
        
    }
    public static ArrayList<Team> readTeamNames(){
        ArrayList<Team> teamNames = new ArrayList();
        String file = "teams.txt";
        File names = new File(file);
        try{
            Scanner reader = new Scanner(names);
            while(reader.hasNext()){//Until file is empty
                String holder = reader.nextLine();
                String[] parts = holder.split("-");
                Team hold = new Team(parts[0],parts[1]);
                teamNames.add(hold);
            }
        }
        catch(Exception e){
            System.out.println("File not found");
            System.exit(0);
        }
        return teamNames;
    }
    public static ArrayList<Team> readStats(ArrayList<Team> teams){
        String stats = "2015stats.txt";
        File file = new File(stats);
        try{
            int i = 0;
            Scanner reader = new Scanner(file);
            while(reader.hasNext()){
                String holder = reader.nextLine();
                String[] parts = holder.split(",");
                teams.get(i).setScored(Double.parseDouble(parts[0]));
                teams.get(i).setPointsAllowed(Double.parseDouble(parts[1]));
                teams.get(i).setGained(Double.parseDouble(parts[2]));
                teams.get(i).setAllowedYards(Double.parseDouble(parts[3]));
                teams.get(i).setWin(Double.parseDouble(parts[4]));
                teams.get(i).setTurn(Double.parseDouble(parts[5]));
                ++i;
            }
        }
        catch(Exception e){
            System.out.println("File not found");
            System.exit(0);
        }
        return teams;
    }
    public static ArrayList<Team> sortTeams(ArrayList<Team> lister){
        Team key = new Team("Yolo","yolo");//Swapper
        for(int i=0; i <lister.size(); ++i){
            for(int j = i+1; j < lister.size(); ++j){
                if(lister.get(i).getScore() > lister.get(j).getScore()){
                    Collections.swap(lister, i, j);
                }
            }
        }
        return lister;
    }
}
