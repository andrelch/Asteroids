public class Vector {
    public double posX;
    public double posY;

    public Vector(){
        this.set(0, 0);
    }

    //parameterised constructor
    public Vector(double posX, double posY){
        this.set(posX,posY);
    }

    public void set(double posX, double posY){
        this.posX = posX;
        this.posY = posY;
    }

    public void add(double dPosX, double dPosY){
        this.posX += dPosX;
        this.posY += dPosY;
    }

    public void multiply(double m){
        this.posX *= m;
        this.posY *= m;
    }

    public double getLength(){
        //calculates vector with pythagoras theorem
        return Math.sqrt(this.posX*this.posX + this.posY*this.posY);
    }

    public void setLength(double L){
        double currentLength = this.getLength();
        //guard against division by 0
        if(currentLength == 0)
            return;
            //scale vector to have length 1
        this.multiply(1/currentLength);
        this.multiply(L);
    }

    public double getAngle(){
        return Math.toDegrees(Math.atan2(this.posY,this.posX));
    }

    public void setAngle(double angleDegrees){
        double L = this.getLength();
        double angleRadians = Math.toRadians(angleDegrees);
        this.posX = L * Math.cos(angleRadians);
        this.posY = L * Math.sin(angleRadians);
    }
}

