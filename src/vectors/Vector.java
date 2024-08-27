package vectors;

import states.Simulating;

public class Vector {
	  public double x1, y1;  // Start point
	    public double x2, y2;  // End point
	    
	    public Vector(double x1, double y1, double x2, double y2) {
	        this.x1 = x1;
	        this.y1 = y1;
	        this.x2 = x2;
	        this.y2 = y2;
	    }
	    public Vector subtract(Vector other) {
	        return new Vector(
	            this.x1,
	            this.y1,
	            this.x2 - other.x2,
	            this.y2 - other.y2
	        );
	    }
		
		public double dot(Vector other) {
	        return this.x2 * other.x2 + this.y2 * other.y2;
	    }
		
		 public double magnitudeSquared() {
		        return this.x2 * this.x2 + this.y2 * this.y2;
		    }
		
		public double getMouseDirection(double mouseX, double mouseY) {
			
			double dx = mouseX - x1;
			double dy = mouseY - y1;
			
			return Math.atan2(dy, dx);
		}
		
		public double getDirection() {
			double dx = x2 - x1;
			double dy = y2 - y1;
			
			return Math.atan2(dy, dx);
		}  
		
		public double getMagnitude() {
			return Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		}
		
		public Vector multiplyByScalar(double scalar) {
			
			double dx = x2 - x1; 
			double dy = y2 - y1;
			
			return new Vector(x1, y1, x1 + (dx * scalar), y1 + (dy * scalar));
			
		}
		
		public Vector normalize() {
			
			double magnitude = getMagnitude();
			double dx = (x2 - x1) / magnitude;
			double dy = (y2 - y1) / magnitude;
			
			return new Vector(x1, y1, dx + x1, dy + y1);
			
		} 
		
		public Vector normalizeToMouse(int mouseX, int mouseY) {
			double dx = mouseX - x1;
			double dy = mouseY - y1;
			double magnitude = Math.sqrt(dx * dx + dy * dy);
			
			if(magnitude > 0) {
				return new Vector(x1, y1, x1 + dx/magnitude, y1 + dy/magnitude);
			} else {
				return new Vector(x1, y1, x1, y1);
			}
		}
		
		public Vector offsetByAngle(double radian) {
			double magntiude = getMagnitude();
			
			double newX2 = x1 + magntiude * Math.cos(radian);
			double newY2 = y1 + magntiude * Math.sin(radian);
			
			return new Vector(x1, y1, newX2, newY2);
		}
		
		public static Vector updateVectorPositionToParticle(Vector vector, double middleX, double middleY, double xVel, double yVel, boolean launched, boolean moving) {
			if(!launched) { // get initial direction
				Vector directionVector = new Vector(middleX, middleY, Simulating.getX(), Simulating.getY()); // Simulating might be a problem
				directionVector = directionVector.normalize().multiplyByScalar(20);
				setVector(vector, directionVector);
				
				return directionVector;
			} else if(launched && moving) { // velocity vector
				Vector velocityVector = new Vector(middleX, middleY, middleX + (xVel), middleY + (yVel));
				//velocityVector = velocityVector.normalize().multiplyByScalar(20);
	            setVector(vector, velocityVector);
	            
	            return velocityVector;
	        } else if(launched && !moving) {
				vector.x1 = 0;
				vector.x2 = 0;
				vector.y1 = 0;
				vector.y2 = 0;
				
				return new Vector(vector.x1, vector.y1, vector.x2, vector.y2);
			}
			
			return vector;
			
		}
		
		public static void setVector(Vector vec1, Vector vec2) {
			vec1.x1 = vec2.x1;
			vec1.y1 = vec2.y1;
			vec1.x2 = vec2.x2;
			vec1.y2 = vec2.y2;
		}
		
		@Override
	    public String toString() {
		    return "Vector2D[(" + x1 + ", " + y1 + "), (" + x2 + ", " + y2 + ")]";
	    }
}