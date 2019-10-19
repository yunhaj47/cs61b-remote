/** Class of cs61b project1
 *  @author Yunhao Ji
 */

public class Planet {

    /** 6 instance variables 
     *  @param imgFileName is the name of the file that corresponds      
     *  to the image that depicts the body
     */
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    final static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV, 
                  double yV, double m, String img) {
                      xxPos = xP;
                      yyPos = yP;
                      xxVel = xV;
                      yyVel = yV;
                      mass = m;
                      imgFileName = img;
                  }

    /** Take in a Body object and initialize 
     *  an indentical Body object(i.e. a copy)
     * */
    public Planet(Planet b) {
        xxPos = b.xxPos;
        yyPos = b.yyPos;
        xxVel = b.xxVel;
        yyVel = b.yyVel;
        mass = b.mass;
        imgFileName = b.imgFileName;
    
    }

    public double calcDistance(Planet b) {
        double dx = this.xxPos - b.xxPos;
        double dy = this.yyPos - b.yyPos;
        double r_square = Math.pow(dx, 2) + Math.pow(dy,2);
        double r = Math.sqrt(r_square);
        return r;
    }

    public double calcForceExertedBy(Planet b) {
        double force = (G*this.mass*b.mass)
                       /Math.pow(this.calcDistance(b),2);
        return force;
    }

    public double calcForceExertedByX(Planet b) {
        double forceByX = this.calcForceExertedBy(b)*(b.xxPos-this.xxPos)
                          /this.calcDistance(b);
        return forceByX;
    }

    public double calcForceExertedByY(Planet b) {
        double forceByY = this.calcForceExertedBy(b)*(b.yyPos-this.yyPos)
                          /this.calcDistance(b);
        return forceByY;
    }

    public double calcNetForceExertedByX(Planet[] bodys) {
        double netForceByX = 0;
        for (Planet element : bodys) {
            if (this.equals(element)) continue;
            netForceByX += this.calcForceExertedByX(element);
        }
        return netForceByX;
    }

    public double calcNetForceExertedByY(Planet[] bodys) {
        double netForceByY = 0;
        for (Planet element : bodys) { // enhance for-loops
            if (this.equals(element)) continue;
            netForceByY += this.calcForceExertedByY(element);
        }
        return netForceByY;
    }

    /** update the body's position and velocity instance variables
     *  @param dt,fX,fY
     *  this method does not need to return anything
     */ 
    public void update(double dt, double fX, double fY) {
        double aNetX = fX/this.mass;
        double aNetY = fY/this.mass;
        this.xxVel = this.xxVel + dt * aNetX;    
        this.yyVel = this.yyVel + dt * aNetY;
        this.xxPos = this.xxPos + dt * this.xxVel;
        this.yyPos = this.yyPos + dt * this.yyVel;
        
    }
    /** Draw one body */
    public void draw() {
        String filename = "images/" + this.imgFileName;
        //StdDraw.picture(0.5 + this.xxPos/(2*2.5e+11),0.5 + this.yyPos/(2*2.5e+11),filename);
        StdDraw.picture(this.xxPos,this.yyPos,filename);
    }


}