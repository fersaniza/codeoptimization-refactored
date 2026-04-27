/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.dam.codeoptimization.fantasy;
import es.dam.codeoptimization.PlayerStats;

/**
 * Class used to calculate the points of a player for a Fantasy-like game.
 * 
 * @author Izan
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

        result = calculatePoints(result, minutes, yellowCard, redCard, matchResult, goals, assists, saves, goalsAgainst, position);
        
        return result;
    }

    /**
     * Function used to calculate the points of a player.
     * 
     * @param the stadistics done of a player in a match: goals scored, assists..
     * @return the score received according to his stats.
     */
    static int calculatePoints(int result, int minutes, boolean yellowCard, boolean redCard, char matchResult, int goals, int assists, int saves, int goalsAgainst, String position) {
        result = calculatePointsByPosition(result, saves, goalsAgainst, position);
        
        result = calculatePointsCommon(result, minutes, yellowCard, redCard, matchResult, goals, assists);
        
        return result;
    }
    
    /**
     * Function used to calculate part of the points of a player based in his position.
     * 
     * @param the stadistics done of a player in a match that change of weight depend of the position : saves, goals of the against and the position itself.
     * @return the score received according to his stats and position.
     */
    static int calculatePointsByPosition(int result, int saves, int goalsAgainst, String position) {
        
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
    
    /**
     * Function used to calculate part of the points of a player based in his position.
     * 
     * @param the stadistics done of a player in a match that not change of weight depend of the position : played minutes, yellow cards, goals...
     * @return the score received according to his stats.
     */
    static int calculatePointsCommon(int result, int minutes, boolean yellowCard, boolean redCard, char matchResult, int goals, int assists) {
        result = pointsForTime(result, minutes);
            
        result = pointsForCards(result, yellowCard, redCard);
            
        result = pointsForMatchResult(result, matchResult);
        
        result = pointsForGoals(result, goals, 5);

        result = pointsForAssists(result, assists, 6);
        
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