package objects;

import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import framework.ObjectId;
import framework.ScaleUtils;
import framework.SimulationObject;
import main.Simulation;
import states.SimulationConfig;
import vectors.Vector2D;

public class PhysicsLogic {

	// **** USE GETTERS AND SETTERS INSTEAD
	private static Vector2D particleVelocityVector;
	private static double gravity;
	private static double coeffRestitution;
	private static double particleVel;
    private static double velocityThreshold = 0.03;
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
		
		// set up velocity vector
		particleVel = ScaleUtils.metersToPixels(SimulationConfig.getInitialVelocity());

	}
	
	public static void particleBlockCollision(LinkedList<SimulationObject> objects, Particle particle) {
        for (SimulationObject tempObject : objects) {
            if (tempObject.getId() == ObjectId.Block) {
                Rectangle2D blockBounds = tempObject.getBounds();

                if (particle.getTopBounds().intersects(blockBounds)) {
                    particle.setY(tempObject.getY() + blockBounds.getHeight());
                    particle.setyVel(particle.getyVel() * -coeffRestitution);

//                    if (Math.abs(particle.getyVel()) < velocityThreshold) {
//                        particle.setMoving(false);
//                    }
                }

                if (particle.getBottomBounds().intersects(blockBounds)) {
                    particle.setY(tempObject.getY() - particle.getR());
                    particle.setyVel(particle.getyVel() * -coeffRestitution);

//                    if (Math.abs(particle.getyVel()) < velocityThreshold) {
//                        particle.setMoving(false);
//                    }
                }

                if (particle.getRightBounds().intersects(blockBounds)) {
                    particle.setX(tempObject.getX() - particle.getR());
                    particle.setxVel(particle.getxVel() * -coeffRestitution);

//                    if (Math.abs(particle.getxVel()) < velocityThreshold) {
//                        particle.setMoving(false);
//                    }
                }

                if (particle.getLeftBounds().intersects(blockBounds)) {
                    particle.setX(tempObject.getX() + blockBounds.getWidth());
                    particle.setxVel(particle.getxVel() * -coeffRestitution);

//                    if (Math.abs(particle.getxVel()) < velocityThreshold) {
//                        particle.setMoving(false);
//                    }
                }
            }
        }
    }
}

