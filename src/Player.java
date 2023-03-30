public class Player {
    private int id, score;
    private String codeName;

    Player(int idNum, String code){
        id = idNum;
        score = 0;
        codeName = code;
    }

    int updateScore(){
        // score increments by 10
        return this.score += 10;
    }

    int getScore(){
        return this.score;
    }

    int getID(){
        return this.id;
    }

    String getCodeName(){
        return this.codeName;
    }
}
