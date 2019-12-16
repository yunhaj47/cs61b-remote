public class Arrrrghrays {

    public class Piece {
        public int longitude;
        public int latitude;
        public Piece(int x, int y) {
            longitude = x;
            latitude = y;
        }
    }

    public Piece[][] groupByLat(Piece[] p) {
        int width = (int) Math.sqrt(p.length);
        Piece[][] latGroup = new Piece[width][width];
        for (int i = 0; i < p.length; i++) {
            for (int j =0; j < latGroup.length; j++) {
                if (latGroup[j][0] == null) {
                    latGroup[j][0] = p[i];
                    break;
                }
                else if (latGroup[j][0].latitude == p[i].latitude) {
                    int counter;
                    for (counter = 0; counter < width; counter++) {
                        if (latGroup[j][counter] == null) {
                            break;
                        }
                    }
                    latGroup[j][counter] = p[i];
                    break;
                }
            }
        }
        return latGroup;
    }

    public Piece[][] sortbyLat(Piece[][] p) {
        /** sort in the correct order s.t.
         * the row with smallest latitudes is at the 0th index*/
        Piece[][] res = new Piece[p.length][];
        for (int i = 0; i < p.length; i++) {
            System.arraycopy(p[i],0, res[i],0, p[i].length);
        }
        Piece[] temp;
        for (int i = 0; i < res.length - 1; i++) {
            for (int j = i + 1; j <= res.length - 1; j++) {
                if (res[i][0].latitude > res[j][0].latitude) {
                    temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        return res;
    }

    public Piece[] sortHalfLong(Piece[] p) {
        int n = p.length;
        Piece[] res = new Piece[n];
        System.arraycopy(p, 0, res,0, n);
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j <= n - 1; j++) {
                if (res[i].longitude > res[j].longitude) {
                    Piece temp = res[i];
                    res[i] = res[j];
                    res[j] = temp;
                }
            }
        }
        for (int i = 0; i <= (n-1) / 2; i++) {
            Piece temp = res[i + n / 2];
            res[i + n / 2] = res[i];
            res[i] = temp;
        }
        return res;
    }

    public Piece[][] solvePuzzle(Piece[] scattered) {
        Piece[][] grouped  = groupByLat(scattered);
        int upper = (int) Math.ceil((double) grouped.length / 2);
        int lower = (int) Math.floor((double) grouped.length / 2);
        for (Piece[] row : grouped) {
            Piece[] halfsort = sortHalfLong(row);
            Piece[] temp = new Piece[row.length];
            System.arraycopy(halfsort,0, temp, lower, upper);
            System.arraycopy(halfsort,upper, temp, 0, lower);
        }
        Piece[][] sorted = sortbyLat(grouped);
        return sorted;
    }

}
