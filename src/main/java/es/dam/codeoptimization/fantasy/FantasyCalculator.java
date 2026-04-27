/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.dam.codeoptimization.fantasy;
import es.dam.codeoptimization.PlayerStats;

/**
 * THE CLASS YOU HAVE TO MODIFY
 * @author Your Name
 */
public class FantasyCalculator {

    // Method to calculate the points
    public static int calcP(PlayerStats s) {
        int result = 0; 
        
        int minutes = s.minutes;
        int goals = s.goals;
        int assists = s.assists;
        boolean yellowCard = s.yellowCard;
        boolean redCard = s.redCard;
        int saves = s.saves;
        int goalsAgainst = s.goalsAgainst;
        char matchResult = s.matchResult;
        String position = s.position;

        
        
        result = pointsForTime(result, minutes);
            
        result = pointsForCards(result, yellowCard, redCard);
            
        result = pointsForMatchResult(result, matchResult);
        
        result = pointsForGoals(result, goals, 5);

        result = pointsForAssists(result, assists, 6);
        
        switch (position) {
            case "PORTERO":
                result = pointsForSaves(result, saves);

                result = pointsForGoalsAgainst(result, goalsAgainst);

                break;
                
            case "DEFENSA":
                result = pointsForGoalsAgainst(result, goalsAgainst);
                
                break;
        }
        
        return result;
    }
    
    static int pointsForTime(int result, int minutes) {
        if (minutes > 0 && minutes < 60) {
                result = result + 3;
            } else if (minutes >= 60) {
                result = result + 5;
            }
        
        return result;
    }
    
    static int pointsForGoals(int result, int goals, int n) {
        for (int i = 0; i < goals; i++) {
                result = result + n;
            }
        
        return result;
    }
    
    static int pointsForAssists(int result, int assists, int n) {
        result = result + (assists * n);
        
        return result;
    }
    
    static int pointsForSaves(int result, int saves) {
        result = result + saves;
        
        return result;
    }
    
    static int pointsForGoalsAgainst(int result, int goalsAgainst) {
        if (goalsAgainst == 0) {
                result = result + 5; 
            } else if (goalsAgainst == 1) {
                result = result + 3;
            } else if (goalsAgainst == 2) {
                result = result + 1;
            }
        
        return result;
    }
    
    static int pointsForCards(int result, boolean yellowCard, boolean redCard) {
        if (yellowCard == true)
            result = result - 3;
        if (redCard == true)
            result = result - 5;
        
        return result;
    }
    
    static int pointsForMatchResult(int result, char matchResult) {
        if (matchResult == 'G') {
            result = result + 5;
        } else if (matchResult == 'E') {
            result = result + 2;
        }
        
        return result;
    }
    
}