/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package football.rankings;
/**
 *
 * @author Chuck
 */
//High yards gain average = 594.175 (YPGH)
//low yards gain avg = 241.425 (YPGL)
//low yards allowed avg = 236.475 (YAGL)
//high avg ya = 530.225 (YAGH)
//ppg high = 50.35 (PPGH)
//ppg low = 11.4(PPGL
//pa low = 11.8(PAL)
//pa high = 44.975(PAH)
//Turn diff high = 21.8 (TDH)
//Turn diff low =  19.2 (TDL)
public class Team {
    public String schoolName, conference;
    private double yardsGained, yardsAllowed, pointsScored, pointsAllowed, winPercentage, score, SOS, turnDiff;
    private final double YPGH = 594.175, YPGL = 241.425;
    private final double YAGL = 236.475, YAGH = 530.225;
    private final double PPGH = 50.35, PPGL = 11.4;
    private final double PAL = 11.8, PAH = 44.975;
    private final double TDH = 21.8, TDL = -19.2;
    public Team(String school, String Conf){
        schoolName = school;
        conference = Conf;
        yardsGained = yardsAllowed = pointsScored = pointsAllowed = winPercentage = score = 0;
    }
    public void setGained(double yg){
        yardsGained = yg;
    }
    public void setAllowedYards(double ya){
        yardsAllowed = ya;
    }
    public void setScored(double ps){
        pointsScored = ps;
    }
    public void setPointsAllowed(double pa){
        pointsAllowed = pa;
    }
    public void setWin(double wp){
        winPercentage = wp;
    }
    public void setTurn(double td){
        turnDiff = td;
    }
    public void calculateScore(){
        //Score ypg
        score += ((yardsGained - YPGL)/(YPGH -YPGL)) *10;
        //score yard allow
        score += 10 - (((yardsAllowed - YAGL)/(YAGH-YAGL))*10);
        //ppg score
        score += ((pointsScored - PPGL)/(PPGH - PPGL)) *10;
        //pa score
        score += 10 - (((pointsAllowed - PAL)/(PAH-PAL))*10);
        score += (winPercentage *10);
        score += calcScoreTurn();
    }
    public double getScore(){
        return score;
    }
    public double calcTTD(){
        double diffAvg = winPercentage - .5;
        double TTD = turnDiff * diffAvg + turnDiff;
        if((winPercentage > .5 && turnDiff < 0) || (winPercentage < .5 && turnDiff > 0))
            return TTD;
        else return turnDiff;
        
    }
    public double calcScoreTurn(){
        double TD = calcTTD();
        if(TD > 0) return (2 * (TD/TDH));
        else return (-2 * (TD/TDL));
    }
}

