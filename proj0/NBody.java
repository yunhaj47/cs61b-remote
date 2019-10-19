/** The class that will actually run the simulation. */
public class NBody {
    public static double readRadius(String fileName) {
        In in = new In(fileName);
        int numberOfPlanets = in.readInt();
        double radiusOfUniverse = in.readDouble();
        return radiusOfUniverse;
    
    }
    public static Planet[] readPlanets(String fileName) {
        In in = new In(fileName);
        int numberOfPlanets = in.readInt(); //读入planets数量
        double radiusOfUniverse = in.readDouble(); // 读入universe半径
        
        Planet[] bodies = new Planet[numberOfPlanets];
        
        for (int i = 0; i < numberOfPlanets; i++) {
            double xP = in.readDouble();
            double yP = in.readDouble();
            double vX = in.readDouble();
            double vY = in.readDouble();
            double m = in.readDouble();
            String img  = in.readString();
            bodies[i] = new Planet(xP, yP, vX, vY, m, img);
        }
        return bodies;
    }

    public static void main(String[] arg) {
        if(arg.length == 0) System.out.println("Please enter dt and T.");
        double T = Double.parseDouble(arg[0]);
        double dt = Double.parseDouble(arg[1]);
        String fileName = arg[2];
        double radius  = NBody.readRadius(fileName);
        Planet[] bodies = NBody.readPlanets(fileName);
        /** Drawing the background */
        StdDraw.setXscale(0, radius);
        StdDraw.setYscale(0, radius);
        double rx = radius / 2, ry = radius / 2;// center position of image
        StdDraw.picture(rx, ry, "images/starfield.jpg");
        //StdDraw.picture(rx,ry,"images/earth.gif");

        /** Drawing more than one body */
        //for (Body element : bodies) {
        //    element.draw();
        //}
        
        StdDraw.enableDoubleBuffering();

        for(double time = 0; time <= T; time = time + dt) {
           
            double[] xForce = new double[bodies.length];
            double[] yForce = new double[bodies.length];
            
            for(int i = 0; i < bodies.length; i++) {
                xForce[i] = bodies[i].calcNetForceExertedByX(bodies);
                yForce[i] = bodies[i].calcNetForceExertedByY(bodies);
                bodies[i].update(dt, xForce[i], yForce[i]);
            }
            for (Planet element : bodies) {
                element.draw();
            }
            StdDraw.show();
            StdDraw.pause(10); //pause the animationfor 10 milliseconds
          
        }
        StdOut.printf("%d\n", bodies.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < bodies.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                          bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                          bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);
        }

    }
}