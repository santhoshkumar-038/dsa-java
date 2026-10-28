public class KokoEatingBananas {
    public static int kokoEatingBananasBruteForce(int[] piles, int k){
        int answer = -1;
        int maxPile = 0;
        for(int i=0; i<piles.length; i++){
            if(piles[i] > maxPile){
                maxPile = piles[i];
            }
        }
        for(int speed = 1; speed <= maxPile; speed++){
            int totalHours = 0;
            for(int pile: piles){
                totalHours = pile / speed;

                if (pile % speed != 0) {
                    totalHours++;
                }
            }

            if(totalHours <= k){
                return speed;
            }
        }
        return answer;
    }
}
