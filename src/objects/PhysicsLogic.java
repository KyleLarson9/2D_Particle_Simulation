package objects;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import framework.ObjectId;
import framework.ScaleUtils;
import framework.SimulationObject;
import main.Simulation;
import states.SimulationConfig;
import vectors.Vector;

// Need a more efficient way to sort through particles

public class PhysicsLogic {

	private static double gravity;
	private static double coeffRestitution;
	private static double particleVel;
	private static float dt = .00833f; // 1 / FPS

	public static void applyGravity(Particle particle) {
		
		particle.setyVel(particle.getyVel() + gravity * dt);
	}
	
	public static void setParticleVelocityComponents(Particle particle, double radians) {
		double xVel = ScaleUtils.pixelsToMeters(particleVel) * Math.cos(radians);
		double yVel = ScaleUtils.pixelsToMeters(particleVel) * Math.sin(radians);
		particle.setxVel(xVel);
		particle.setyVel(yVel);
	
	}
	
	public static void moveParticle(Particle particle) {
		
		double newX = particle.getX() + ScaleUtils.metersToPixels(particle.getxVel()) * dt * Simulation.SCALE;
		double newY = particle.getY() + ScaleUtils.metersToPixels(particle.getyVel()) * dt * Simulation.SCALE;
		
		particle.setX(newX);
		particle.setY(newY);
		
	}
	
	public static void togglePhysicsSettings(Particle particle) {
		if(SimulationConfig.isGravityEnabled())
			gravity = SimulationConfig.getGravity();
		else
			gravity = 0;
		
		if(SimulationConfig.getIsPerfectlyInelastic())
			coeffRestitution = 0;
		else 
			coeffRestitution = SimulationConfig.getCoeffRestitution();
		
		// get velocity from settings menu input
		particleVel = ScaleUtils.metersToPixels(SimulationConfig.getInitialVelocity());

	}
	
	public static void particleParticleCollision(LinkedList<SimulationObject> objects) {
	    
	    for(int i = 0; i < objects.size(); i++) {
	        
	        SimulationObject object1 = objects.get(i);
	        
	        if(object1.getId() == ObjectId.Projectile) {
	            Particle particle1 = (Particle) object1;
	            
	            for(int j = i + 1; j < objects.size(); j++) {
	                SimulationObject object2 = objects.get(j);
	                
	                if(object2.getId() == ObjectId.Projectile) {
	                    Particle particle2 = (Particle) object2;

	                    double distance = Math.sqrt(Math.pow((particle2.getX() + particle2.getR()) - (particle1.getX() + particle1.getR()), 2)
	                            + Math.pow((particle2.getY() + particle2.getR()) - (particle1.getY() + particle1.getR()), 2));

	                    if(distance <= (particle1.getR() + particle2.getR())) {
	                    	
	                    	// needed values
	                    	Vector particle1Position = new Vector(0, 0, particle1.getX() + particle1.getR(), particle1.getY() + particle1.getR());
	                    	Vector particle1Velocity = new Vector(0, 0, particle1.getxVel(), particle1.getyVel());
	                    	double particle1Mass = particle1.getMass();
	                    	
	                    	Vector particle2Position = new Vector(0, 0, particle2.getX() + particle2.getR(), particle2.getY() + particle2.getR());
	                    	Vector particle2Velocity = new Vector(0, 0, particle2.getxVel(), particle2.getyVel());
	                    	double particle2Mass = particle2.getMass();
	                    	
	                    	calculateFinalParticleVelocities(particle1, particle2, particle1Mass, particle2Mass, particle1Position, particle2Position, particle1Velocity, particle2Velocity);
	                 
	                    	
	                    }
	                    
	                }
	            }
	        }
	    }
	}
	
	private static void calculateFinalParticleVelocities(Particle particle1, Particle particle2, double particle1Mass, double particle2Mass, Vector particle1Position, Vector particle2Position, Vector particle1Velocity, Vector particle2Velocity) {
		
    	Vector particle1FinalVelocity;
    	Vector particle2FinalVelocity;

		
		Vector deltaVelocity = particle1Velocity.subtract(particle2Velocity);
    	Vector deltaPosition = particle1Position.subtract(particle2Position);
    	double massCoefficient = (2*particle2Mass) / (particle1Mass + particle2Mass);
    	double positionMangitudeSquared = particle1Position.subtract(particle2Position).magnitudeSquared();
    	double dotProduct = deltaVelocity.dot(deltaPosition);
    	
    	particle1FinalVelocity = particle1Velocity.subtract(deltaPosition.multiplyByScalar(massCoefficient * (dotProduct/positionMangitudeSquared)));
    	
    	particle1.setxVel(particle1FinalVelocity.x2);
    	particle1.setyVel(particle1FinalVelocity.y2);
    	
    	// particle 2 final velocity
    	
    	Vector deltaVelocity2 = particle2Velocity.subtract(particle1Velocity);
    	Vector deltaPosition2 = particle2Position.subtract(particle1Position);
    	double massCoefficient2 = (2*particle1Mass) / (particle1Mass + particle2Mass);
    	double positionMagnitudeSquared = particle2Position.subtract(particle1Position).magnitudeSquared();
    	double dotProduct2 = deltaVelocity2.dot(deltaPosition2);
    	
    	particle2FinalVelocity = particle2Velocity.subtract(deltaPosition2.multiplyByScalar(massCoefficient2 * (dotProduct2/positionMagnitudeSquared)));
    	
    	particle2.setxVel(particle2FinalVelocity.x2);
    	particle2.setyVel(particle2FinalVelocity.y2);
		
	}
	
	public static void particleBlockCollision(LinkedList<SimulationObject> objects, Particle particle) {
        for (SimulationObject tempObject : objects) {
            if (tempObject.getId() == ObjectId.Block) {
                Rectangle2D blockBounds = tempObject.getBounds();

                if (particle.getTopBounds().intersects(blockBounds)) {
                    particle.setY(blockBounds.getY() + blockBounds.getHeight());
                    particle.setyVel(particle.getyVel() * -coeffRestitution);

                }

                if (particle.getBottomBounds().intersects(blockBounds)) {
                    particle.setY(blockBounds.getY() - particle.getR());
                	particle.setyVel(particle.getyVel() * -coeffRestitution);
                }
                
                
                if (particle.getRightBounds().intersects(blockBounds)) {
                    particle.setX(blockBounds.getX() - particle.getR());
                	particle.setxVel(particle.getxVel() * -coeffRestitution);

                }

                if (particle.getLeftBounds().intersects(blockBounds)) {
                    particle.setX(blockBounds.getX() + blockBounds.getWidth());
                    particle.setxVel(particle.getxVel() * -coeffRestitution);

                }
            }
        }
    }
}

